package s21.project.info21.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.ProcedureDAO;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.model.entities.XP;
import s21.project.info21.services.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/peers")
public class PeersController {

    //    @Autowired
//    private PeersService peersservice;
    Peers existingPeer;

    @Autowired
    private CommonService<Peers, String> commonService;

    @GetMapping()
    public String peers(Model model) throws SQLException {
//        procedureDAO.callProcedure();
        model.addAttribute("peers", commonService.getAll());
        return "peers/viewall";
    }

    @GetMapping("/add")
    public String addPeerForm(Model model) {
        model.addAttribute("peers", new Peers());
        return "peers/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("peers") Peers peer) {
        commonService.add(peer);
        return "redirect:/peers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        commonService.deleteById(id);
        return "redirect:/peers";
    }

    @PostMapping("/update/{id}")
    public String updatePeer(@PathVariable String id, Model model) {
//        existingPeer = commonService.findById(id);
        model.addAttribute("peer", commonService.findById(id));
        return "peers/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("peers") Peers updatedPeer) {
//        commonService.deleteById(existingPeer.getNickname());
        commonService.add(updatedPeer);
        return "redirect:/peers";
    }
}
