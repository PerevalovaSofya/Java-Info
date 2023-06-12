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
import s21.project.info21.model.entities.Peers;
import s21.project.info21.model.entities.Tasks;
import s21.project.info21.services.CommonService;

import java.sql.SQLException;

import static java.lang.constant.ConstantDescs.NULL;

@Controller
@RequestMapping("/checks")
public class ChecksController {

    private static final Logger log = LoggerFactory.getLogger(ChecksController.class);

    @Autowired
    private CommonService<Checks, Long> commonServiceChecks;

    @Autowired
    private CommonService<Peers, String> commonServicePeers;

    @Autowired
    private CommonService<Tasks, String> commonServiceTasks;

    @GetMapping()
    public String checks(Model model, RedirectAttributes redirAttrs) throws SQLException {
        model.addAttribute("checks", commonServiceChecks.getAll());
        return "checks/viewall";
    }



    @PostMapping()
    public String addChecks(@ModelAttribute("checks") @Valid Checks check, BindingResult bindingResult, RedirectAttributes redirAttrs) {

        if (bindingResult.hasErrors()) {
            redirAttrs.addFlashAttribute("error", NULL);

        } else {
            try {
                commonServiceChecks.add(check);
                log.info("Added a new check with id {}", check.getId());
                redirAttrs.addFlashAttribute("success", NULL);
            } catch (GenericJDBCException e) {
                log.error("Error occurred when adding check: ", e);
            }
        }
        return "redirect:/checks";
    }


    @GetMapping("/add")
    public String addChecksForm(Model model) {
        model.addAttribute("peers", commonServicePeers.getAll());
        model.addAttribute("tasks", commonServiceTasks.getAll());
        model.addAttribute("checks", new Checks());
        return "checks/add";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        commonServiceChecks.deleteById(id);
        log.info("Deleted check with id {}", id);
        return "redirect:/checks";
    }

    @PostMapping("/update/{id}")
    public String updatePeer(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", commonServiceTasks.getAll());
        model.addAttribute("peers", commonServicePeers.getAll());
        Checks existingCheck = commonServiceChecks.findById(id);
        if (existingCheck != null) {
            model.addAttribute("checks", existingCheck);
            log.info("Updating check with id {}", id);
        }
        return "checks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("checks") Checks updatedChecks) {
        Checks existingChecks = commonServiceChecks.findById(updatedChecks.getId());
        if (existingChecks != null) {
            existingChecks.setDate(updatedChecks.getDate());
            existingChecks.setPeer(updatedChecks.getPeer());
            existingChecks.setTask(updatedChecks.getTask());
            commonServiceChecks.add(existingChecks);
            log.info("Updated check with id {}", updatedChecks.getId());
        }
        return "redirect:/checks";
    }
}
