package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Checks;
import s21.project.info21.model.entities.PeerToPeer;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.services.CommonService;

import java.sql.SQLException;

@Controller
@RequestMapping("/peertopeer")
public class PeerToPeerController {

    @Autowired
    private CommonService<PeerToPeer, Long> commonServicePeerToPeer;

    @Autowired
    private CommonService<Peers, String> peersCommonService;

    @Autowired
    private CommonService<Checks, Long> checksCommonService;

    @GetMapping()
    public String peertopeer(Model model) throws SQLException {
        model.addAttribute("p2p", commonServicePeerToPeer.getAll());
        return "peertopeer/viewall";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServicePeerToPeer.deleteById(id);
        return "redirect:/peertopeer";
    }

    @PostMapping("update/{id}")
    public String updatep2p(@PathVariable Long id, Model model) {
        System.out.println(id);
        model.addAttribute("peers", peersCommonService.getAll());
        model.addAttribute("checks", checksCommonService.getAll());
        model.addAttribute("p2p", commonServicePeerToPeer.findById(id));
        return "peertopeer/update";
    }

    @GetMapping("/add")
    public String addPeerForm(Model model) {
        model.addAttribute("checks", checksCommonService.getAll());
        model.addAttribute("peers", peersCommonService.getAll());
        model.addAttribute("p2p", new PeerToPeer());
        return "peertopeer/add";
    }

    @PostMapping()
    public String addP2P(@ModelAttribute("p2p") @Valid PeerToPeer peerToPeer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "peerToPeer/add";
        } else {
            try {
                commonServicePeerToPeer.add(peerToPeer);
            } catch (GenericJDBCException e) {
            }
        }
        return "redirect:/peertopeer";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("p2p") PeerToPeer peerToPeer) {
        commonServicePeerToPeer.add(peerToPeer);
        return "redirect:/peertopeer";
    }
}
