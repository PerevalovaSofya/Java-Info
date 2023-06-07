package s21.project.info21.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import s21.project.info21.services.ProcedureService;

import java.sql.SQLException;

@Controller
@RequestMapping("/procedure")
public class QueryController {
    private ProcedureService procedureService;
    @Autowired
    QueryController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping("/{id}")
    public String showOutput(@PathVariable int id, Model model, @RequestParam(value = "args", required = false) Object... args) throws SQLException {
        try {
            String query = procedureService.getQueryByID(id);
            model.addAttribute("result", procedureService.executeQuery(query, args));
        } catch (SQLException e) {
            return "procedures";
        }
        return "procedure_output";
    }

    @GetMapping("/custom_query")
    public String showQueryOutput(@RequestParam(value = "query", required = true) String query, Model model, @RequestParam(value = "args", required = false) Object... args) throws SQLException {
        try {
            model.addAttribute("result", procedureService.executeQuery(query, args));
        } catch (SQLException e) {
            return "procedures";
        }
        return "procedure_output";
    }

}
