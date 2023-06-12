package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.model.entities.Recommendations;
import s21.project.info21.services.CommonService;
import s21.project.info21.services.PeersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/recommendations")
public class RecommendationsController {

    private static final Logger log = LoggerFactory.getLogger(RecommendationsController.class);

    @Autowired
    private CommonService<Recommendations, Long> commonServiceRec;
    @Autowired
    private CommonService<Peers, String> commonServicePeers;

    @Autowired
    private PeersService peersService;

    @GetMapping()
    public String recommendations(Model model) {
        model.addAttribute("recommendations", commonServiceRec.getAll());
        return "recommendations/viewall";
    }

    @GetMapping("/add")
    public String addRecommendationForm(Model model) {
        model.addAttribute("peers", commonServicePeers.getAll());
        model.addAttribute("recommendation", new Recommendations());
        return "recommendations/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("recommendation") @Valid Recommendations recommendation, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            redirAttrs.addFlashAttribute("error", NULL);
        } else {
            try {
                commonServiceRec.add(recommendation);
                log.info("Created a new recommendation with ID {}", recommendation.getId());
            } catch (GenericJDBCException e) {
                log.error("Error while creating recommendation: ", e);
            }
        }
        return "redirect:/recommendations";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceRec.deleteById(id);
        log.info("Deleted recommendation with ID {}", id);
        return "redirect:/recommendations";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("peers", commonServicePeers.getAll());
        Recommendations existingRecommendation = commonServiceRec.findById(id);
        if (existingRecommendation != null) {
            model.addAttribute("recommendation", existingRecommendation);
        }
        return "recommendations/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("recommendation") Recommendations updatedRecommendation) {
        Recommendations existingRecommendation = commonServiceRec.findById(updatedRecommendation.getId());
        if (existingRecommendation != null) {
            existingRecommendation.setPeer(updatedRecommendation.getPeer());
            existingRecommendation.setRecommendedPeer(updatedRecommendation.getRecommendedPeer());
            commonServiceRec.add(existingRecommendation);
            log.info("Updated recommendation with ID {}", existingRecommendation.getId());
        }
        return "redirect:/recommendations";
    }
}
