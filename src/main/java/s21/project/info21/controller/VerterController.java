package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import s21.project.info21.model.entities.Checks;
import s21.project.info21.model.entities.Verter;
import s21.project.info21.services.CommonService;

import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/verter")
public class VerterController {

    private static final Logger log = LoggerFactory.getLogger(VerterController.class);

    @Autowired
    private CommonService<Verter, Long> verterService;

    @Autowired
    private CommonService<Checks, Long> checksService;

    @GetMapping()
    public String verter(Model model) {
        model.addAttribute("verter", verterService.getAll());
        return "verter/viewall";
    }

    @PostMapping("update/{id}")
    public String verterupdate(@PathVariable Long id, Model model) {
        model.addAttribute("checks", checksService.getAll());
        model.addAttribute("verter", verterService.findById(id));
        return "verter/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("verter") Verter verter) {
        verterService.add(verter);
        log.info("Updated Verter: {}", verter);
        return "redirect:/verter";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        verterService.deleteById(id);
        log.info("Deleted Verter with id: {}", id);
        return "redirect:/verter";
    }

    @GetMapping("/add")
    public String addVerter(Model model) {
        model.addAttribute("checks", checksService.getAll());
        model.addAttribute("verter", new Verter());
        return "verter/add";
    }

    @PostMapping()
    public String add(@ModelAttribute("verter") @Valid Verter verter, BindingResult bindingResult, RedirectAttributes redirAttrs) {
        if (bindingResult.hasErrors()) {
            redirAttrs.addFlashAttribute("error", NULL);
        } else {
            try {
                verterService.add(verter);
                redirAttrs.addFlashAttribute("success", NULL);
                log.info("Added Verter: {}", verter);
            } catch (GenericJDBCException e) {
                log.error("Error adding Verter: {}", e.getMessage());
            }
        }
        return "redirect:/verter";
    }
}
