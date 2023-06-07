package s21.project.info21.controller;


import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.XP;
import s21.project.info21.services.CommonService;

@Controller
@RequestMapping("/xp")
public class XPController {

    @Autowired
    private CommonService<XP, Long> commonServiceXP;

    @GetMapping
    public String xp(Model model) {
        model.addAttribute("xp", commonServiceXP.getAll());
        return "xp/viewall";
    }

    @GetMapping("/add")
    public String addXpForm(Model model) {
        model.addAttribute("xp", new XP());
        return "xp/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("xp") XP xp) {
        try {
            commonServiceXP.add(xp);
        } catch (GenericJDBCException e) {}
        return "redirect:/xp";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceXP.deleteById(id);
        return "redirect:/xp";
    }

    @GetMapping("/update/{id}")
    public String updateXP(@PathVariable Long id, Model model) {
        XP existingXP = commonServiceXP.findById(id);
        if (existingXP != null) {
            model.addAttribute("xp", existingXP);
        }
        return "xp/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("xp") XP updatedXP) {
        XP existingXP = commonServiceXP.findById(updatedXP.getId());
        if (existingXP != null) {
            existingXP.setCheckId(updatedXP.getCheckId());
            existingXP.setXpAmount(updatedXP.getXpAmount());
            commonServiceXP.add(existingXP);
        }
        return "redirect:/xp";
    }


}
