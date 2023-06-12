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
import s21.project.info21.model.entities.Peers;
import s21.project.info21.services.CommonService;

import java.sql.SQLException;

import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/peers")
public class PeersController {

    private static final Logger log = LoggerFactory.getLogger(PeersController.class);

    @Autowired
    private CommonService<Peers, String> commonService;

    @GetMapping()
    public String peers(Model model) throws SQLException {
        log.info("Accessing peers page");
        model.addAttribute("peers", commonService.getAll());
        return "peers/viewall";
    }

    @GetMapping("/add")
    public String addPeerForm(Model model) {
        log.info("Accessing add peer form");
        model.addAttribute("peers", new Peers());
        return "peers/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("peers") @Valid Peers peer, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", NULL);
            log.error("Error while creating peer: {}", bindingResult.getAllErrors());
        } else {
            commonService.add(peer);
            redirectAttributes.addFlashAttribute("success", NULL);
            log.info("Created a new peer with nickname {}", peer.getNickname());
        }
        return "redirect:/peers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        commonService.deleteById(id);
        log.info("Deleted peer with nickname {}", id);
        return "redirect:/peers";
    }

    @PostMapping("/update/{id}")
    public String updatePeer(@PathVariable String id, Model model) {
        model.addAttribute("peer", commonService.findById(id));
        log.info("Accessing update peer form for peer with nickname {}", id);
        return "peers/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("peers") Peers updatedPeer) {
        commonService.add(updatedPeer);
        log.info("Updated peer with nickname {}", updatedPeer.getNickname());
        return "redirect:/peers";
    }
}
