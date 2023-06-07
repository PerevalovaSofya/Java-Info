package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Checks;
import s21.project.info21.model.entities.Verter;
import s21.project.info21.services.ChecksService;
import s21.project.info21.services.CommonService;
import s21.project.info21.services.VerterService;

@Controller
@RequestMapping("/verter")
public class VerterController {

    @Autowired
    private CommonService<Verter, Long> verterService;

    @Autowired
    private  CommonService<Checks, Long> checksService;

    @GetMapping()
    public String verter(Model model) {
        model.addAttribute("verter", verterService.getAll());
        return "verter/viewall";
    }

    @PostMapping ("update/{id}")
    public String verterupdate(@PathVariable Long id, Model model) {
        model.addAttribute("checks", checksService.getAll());
        model.addAttribute("verter", verterService.findById(id));
        return "verter/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("verter") Verter verter) {
        verterService.add(verter);
        return "redirect:/verter";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        verterService.deleteById(id);
        return "redirect:/verter";
    }

    @GetMapping("/add")
    public String addVerter(Model model) {
        model.addAttribute("checks", checksService.getAll());
        model.addAttribute("verter", new Verter());
        return "verter/add";
    }

    @PostMapping()
    public String add(@ModelAttribute("verter") @Valid Verter verter, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "verter/add";
        } else {
            try {
                verterService.add(verter);
            } catch (GenericJDBCException e) {}
        }
        return "redirect:/verter";
    }
}
