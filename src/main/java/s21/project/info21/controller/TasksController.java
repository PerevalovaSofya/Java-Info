package s21.project.info21.controller;

import jakarta.validation.Valid;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Tasks;
import s21.project.info21.services.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@Controller
@RequestMapping("/tasks")
public class TasksController {
    private static final Logger log = LoggerFactory.getLogger(TasksController.class);

    @Autowired
    private CommonService<Tasks, String> commonServiceTasks;

    @GetMapping()
    public String viewTasks(Model model) throws SQLException {
        model.addAttribute("tasks", commonServiceTasks.getAll());
        return "tasks/viewall";
    }

    @GetMapping("/add")
    public String addTaskForm(Model model, Model model2) {
        model2.addAttribute("task", commonServiceTasks.getAll());
        model.addAttribute("tasks", new Tasks());
        return "tasks/add";
    }

    @PostMapping
    public String addTask(@ModelAttribute("tasks") @Valid Tasks task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/tasks";
        } else {
            try {
                commonServiceTasks.add(task);
            } catch (GenericJDBCException e) {
            }
        }
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        commonServiceTasks.deleteById(id);
        log.info("Deleted task with ID {}", id);
        return "redirect:/tasks";
    }

    @PostMapping("/update/{id}")
    public String updatePeer(@PathVariable String id, Model model, Model model2) {
        model.addAttribute("tasks", commonServiceTasks.findById(id));
        model2.addAttribute("task", commonServiceTasks.getAll());
        return "tasks/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("tasks") Tasks task) {
        commonServiceTasks.add(task);
        return "redirect:/tasks";
    }
}
