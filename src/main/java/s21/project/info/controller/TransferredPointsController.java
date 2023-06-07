package s21.project.info21.controller;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s21.project.info21.model.entities.TransferredPoints;
import s21.project.info21.services.PeersService;
import s21.project.info21.services.TransferredPointsService;

@Controller
@RequestMapping("/transferredpoints")
public class TransferredPointsController {

    private final TransferredPointsService transferredPointsService;
    private final PeersService peersService;

    public TransferredPointsController(TransferredPointsService transferredPointsService, PeersService peersService) {
        this.transferredPointsService = transferredPointsService;
        this.peersService = peersService;
    }

    @GetMapping()
    public String transferredPoints(Model model) {
        model.addAttribute("transferredPoints", transferredPointsService.getAll());
        return "transferredpoints/viewall";
    }

    @GetMapping("/add")
    public String addTransferForm(Model model) {
        model.addAttribute("transferredPoint", new TransferredPoints());
        return "transferredpoints/add";
    }

    @PostMapping()
    public String create(@ModelAttribute("transferredPoint") TransferredPoints transferredPoint, Model model) {
        String checkingPeer = transferredPoint.getCheckingPeer();
        String checkedPeer = transferredPoint.getCheckedPeer();
        if (!peersService.existsByNickname(checkingPeer)) {
            model.addAttribute("error", "Invalid checking peer value. Please specify an existing value.");
            return "transferredpoints/add";
        }
        if (!peersService.existsByNickname(checkedPeer)) {
            model.addAttribute("error", "Invalid checked peer value. Please specify an existing value.");
            return "transferredpoints/add";
        }

        try {
            transferredPointsService.add(transferredPoint);
        } catch (GenericJDBCException e) {}
        return "redirect:/transferredpoints";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        transferredPointsService.deleteById(id);
        return "redirect:/transferredpoints";
    }

    @PostMapping("/update/{id}")
    public String updateTransfer(@PathVariable Long id, @ModelAttribute("transferredPoint") TransferredPoints updatedTransferredPoint) {
        TransferredPoints existingTransferredPoint = transferredPointsService.findById(id);
        if (existingTransferredPoint != null) {
            if (updatedTransferredPoint.getCheckingPeer() != null) {
                existingTransferredPoint.setCheckingPeer(updatedTransferredPoint.getCheckingPeer());
            }
            if (updatedTransferredPoint.getCheckedPeer() != null) {
                existingTransferredPoint.setCheckedPeer(updatedTransferredPoint.getCheckedPeer());
            }
            if (updatedTransferredPoint.getPointsAmount() != null) {
                existingTransferredPoint.setPointsAmount(updatedTransferredPoint.getPointsAmount());
            }
            transferredPointsService.add(existingTransferredPoint);
        }
        return "redirect:/transferredpoints";
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable Long id, Model model) {
        TransferredPoints transferredPoint = transferredPointsService.findById(id);
        model.addAttribute("transferredPoint", transferredPoint);
        return "transferredpoints/update";
    }
}
