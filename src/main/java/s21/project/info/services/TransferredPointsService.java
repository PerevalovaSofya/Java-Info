package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.TransferredPoints;
import s21.project.info21.model.repositories.TransferredPointsRepository;

import java.util.List;

@Service
public class TransferredPointsService {

    private final TransferredPointsRepository transferredPointsRepository;

    @Autowired
    public TransferredPointsService(TransferredPointsRepository transferredPointsRepository) {
        this.transferredPointsRepository = transferredPointsRepository;
    }

    public List<TransferredPoints> getAll() {
        return transferredPointsRepository.findAll();
    }

    public TransferredPoints findById(Long id) {
        return transferredPointsRepository.findById(id).orElse(null);
    }

    public void add(TransferredPoints transferredPoints) {
        transferredPointsRepository.save(transferredPoints);
    }

    public void deleteById(Long id) {
        transferredPointsRepository.deleteById(id);
    }
}
