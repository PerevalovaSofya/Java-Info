package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.model.entities.Timetracking;
import s21.project.info21.services.CommonService;

@Controller
@RequestMapping("/timetracking")
public class TimetrackingController {

    @Autowired
    private CommonService<Timetracking, Long> timetrackingCommonService;

    @Autowired
    private CommonService<Peers, String> peersStringCommonService;

    @GetMapping()
    public String viewall(Model model) {
        model.addAttribute("timetracking", timetrackingCommonService.getAll());
        return "timetracking/viewall";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        timetrackingCommonService.deleteById(id);
        return "redirect:/timetracking";
    }

    @PostMapping("/update/{id}")
    public String updateTimetracking(@PathVariable Long id, Model model) {
        model.addAttribute("peers", peersStringCommonService.getAll());
        model.addAttribute("timetracking", timetrackingCommonService.findById(id));
        return "timetracking/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("timetracking") Timetracking timetracking) {
        timetrackingCommonService.add(timetracking);
        return "redirect:/timetracking";
    }

    @GetMapping("/add")
    public String addTimetrackingForm(Model model) {
        model.addAttribute("peers", peersStringCommonService.getAll());
        model.addAttribute("timetracking", new Timetracking());
        return "timetracking/add";
    }

    @PostMapping()
    public String addTimetracking(@ModelAttribute("timetracking") @Valid Timetracking timetracking, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "timetracking/add";
        } else {
            try {
                timetrackingCommonService.add(timetracking);
            } catch (GenericJDBCException e) {}
        }
        return "redirect:/timetracking";
    }

}
