package s21.project.info21.controller;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.XP;
import s21.project.info21.model.entities.Checks;
import s21.project.info21.services.CommonService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/xp")
public class XPController {

    private static final Logger log = Logger.getLogger(XPController.class.getName());

    @Autowired
    private CommonService<XP, Long> commonServiceXP;

    @Autowired
    private CommonService<Checks, Long> commonServiceChecks;

    @GetMapping
    public String xp(Model model) {
        model.addAttribute("xp", commonServiceXP.getAll());
        return "xp/viewall";
    }

    @GetMapping("/add")
    public String addXpForm(Model model) {
        model.addAttribute("checks", commonServiceChecks.getAll());
        model.addAttribute("xp", new XP());
        return "xp/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("xp") XP xp) {
        try {
            commonServiceXP.add(xp);
            log.log(Level.INFO, "XP created: " + xp.toString());
        } catch (GenericJDBCException e) {
            log.log(Level.SEVERE, "Failed to create XP", e);
        }
        return "redirect:/xp";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceXP.deleteById(id);
        log.log(Level.INFO, "XP deleted with id: " + id);
        return "redirect:/xp";
    }

    @GetMapping("/update/{id}")
    public String updateXP(@PathVariable Long id, Model model) {
        model.addAttribute("checks", commonServiceChecks.getAll());
        XP existingXP = commonServiceXP.findById(id);
        if (existingXP != null) {
            model.addAttribute("xp", existingXP);
        }
        return "xp/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("xp") XP updatedXP) {
        System.out.println(updatedXP.getCheckId() + " " + updatedXP.getXpAmount());
        XP existingXP = commonServiceXP.findById(updatedXP.getId());
        if (existingXP != null) {
            existingXP.setCheckId(updatedXP.getCheckId());
            existingXP.setXpAmount(updatedXP.getXpAmount());
            commonServiceXP.add(existingXP);
            log.log(Level.INFO, "XP updated: " + existingXP.toString());
        }
        return "redirect:/xp";
    }
}
