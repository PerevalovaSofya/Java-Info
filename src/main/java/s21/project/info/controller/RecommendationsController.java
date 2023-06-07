package s21.project.info21.controller;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.model.entities.Recommendations;
import s21.project.info21.services.CommonService;
import s21.project.info21.services.PeersService;

@Controller
@RequestMapping("/recommendations")
public class RecommendationsController {

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
    public String create(@ModelAttribute("recommendation") Recommendations recommendation, Model model) {
        String peer = recommendation.getPeer();
        String recommendedPeer = recommendation.getRecommendedPeer();
        if (!peersService.existsByNickname(peer)) {
            model.addAttribute("error", "Invalid peer value. Please specify an existing value.");
            return "recommendations/add";
        }
        if (!peersService.existsByNickname(recommendedPeer)) {
            model.addAttribute("error", "Invalid recommended peer value. Please specify an existing value.");
            return "recommendations/add";
        }
        try {
            commonServiceRec.add(recommendation);
        } catch (GenericJDBCException e) {}
        return "redirect:/recommendations";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceRec.deleteById(id);
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
        System.out.println("RES = " + updatedRecommendation.getId());
        if (existingRecommendation != null) {
            existingRecommendation.setPeer(updatedRecommendation.getPeer());
            existingRecommendation.setRecommendedPeer(updatedRecommendation.getRecommendedPeer());
            commonServiceRec.add(existingRecommendation);
        }
        return "redirect:/recommendations";
    }
}
