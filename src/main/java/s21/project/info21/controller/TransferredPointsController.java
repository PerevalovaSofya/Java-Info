package s21.project.info21.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.Peers;
import s21.project.info21.model.entities.TransferredPoints;
import s21.project.info21.services.CommonService;

@Controller
@RequestMapping("/transferredpoints")
public class TransferredPointsController {
    private static final Logger log = LoggerFactory.getLogger(TransferredPointsController.class);

    @Autowired
    private CommonService<TransferredPoints, Long> transferredPointsService;

    @Autowired
    private CommonService<Peers, String> peersService;

    @GetMapping()
    public String transferredPoints(Model model) {
        model.addAttribute("transferredPoints", transferredPointsService.getAll());
        return "transferredpoints/viewall";
    }

    @GetMapping("/add")
    public String addTransferForm(Model model) {
        model.addAttribute("peers", peersService.getAll());
        model.addAttribute("transferredPoint", new TransferredPoints());
        return "transferredpoints/add";
    }

    @PostMapping("")
    public String create(@ModelAttribute("transferredPoint") TransferredPoints transferredPoint, Model model) {
        transferredPointsService.add(transferredPoint);
        log.info("Created a new transferred point with ID {}", transferredPoint.getId());
        return "redirect:/transferredpoints";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        transferredPointsService.deleteById(id);
        log.info("Deleted transferred point with ID {}", id);
        return "redirect:/transferredpoints";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Long id, Model model) {
        TransferredPoints transferredPoint = transferredPointsService.findById(id);
        model.addAttribute("transferredPoint", transferredPoint);
        return "transferredpoints/update";
    }

    @PostMapping("/update/{id}")
    public String updateTransfer(@PathVariable Long id, @ModelAttribute("transferredPoint") TransferredPoints updatedTransferredPoint) {
        TransferredPoints existingTransferredPoint = transferredPointsService.findById(id);
        if (existingTransferredPoint != null) {
            existingTransferredPoint.setCheckingPeer(updatedTransferredPoint.getCheckingPeer());
            existingTransferredPoint.setCheckedPeer(updatedTransferredPoint.getCheckedPeer());
            existingTransferredPoint.setPointsAmount(updatedTransferredPoint.getPointsAmount());
            transferredPointsService.add(existingTransferredPoint);
            log.info("Updated transferred point with ID {}", existingTransferredPoint.getId());
        }
        return "redirect:/transferredpoints";
    }
}
