package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s21.project.info21.model.entities.Checks;
import s21.project.info21.model.entities.PeerToPeer;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.services.CommonService;

import java.sql.SQLException;

import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/peertopeer")
public class PeerToPeerController {

    private static final Logger log = LoggerFactory.getLogger(PeerToPeerController.class);

    @Autowired
    private CommonService<PeerToPeer, Long> commonServicePeerToPeer;

    @Autowired
    private CommonService<Peers, String> peersCommonService;

    @Autowired
    private CommonService<Checks, Long> checksCommonService;

    @GetMapping()
    public String peertopeer(Model model) throws SQLException {
        log.info("Accessing peertopeer page");
        model.addAttribute("p2p", commonServicePeerToPeer.getAll());
        return "peertopeer/viewall";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServicePeerToPeer.deleteById(id);
        log.info("Deleted peertopeer with ID {}", id);
        return "redirect:/peertopeer";
    }

    @PostMapping("update/{id}")
    public String updatep2p(@PathVariable Long id, Model model) {
        log.info("Accessing update peertopeer form for peertopeer with ID {}", id);
        model.addAttribute("peers", peersCommonService.getAll());
        model.addAttribute("checks", checksCommonService.getAll());
        model.addAttribute("p2p", commonServicePeerToPeer.findById(id));
        return "peertopeer/update";
    }

    @GetMapping("/add")
    public String addPeerForm(Model model) {
        log.info("Accessing add peertopeer form");
        model.addAttribute("checks", checksCommonService.getAll());
        model.addAttribute("peers", peersCommonService.getAll());
        model.addAttribute("p2p", new PeerToPeer());
        return "peertopeer/add";
    }

    @PostMapping()
    public String addP2P(@ModelAttribute("p2p") @Valid PeerToPeer peerToPeer, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            redirAttrs.addFlashAttribute("error", NULL);
            log.error("Error while creating peertopeer: {}", bindingResult.getAllErrors());
        } else {
            try {
                commonServicePeerToPeer.add(peerToPeer);
                redirAttrs.addFlashAttribute("success", NULL);
                log.info("Created a new peertopeer with ID {}", peerToPeer.getId());
            } catch (Exception e) {
                log.error("Error while creating peertopeer: {}", e.getMessage());
            }
        }
        return "redirect:/peertopeer";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("p2p") PeerToPeer peerToPeer) {
        commonServicePeerToPeer.add(peerToPeer);
        log.info("Updated peertopeer with ID {}", peerToPeer.getId());
        return "redirect:/peertopeer";
    }
}
