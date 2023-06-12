package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s21.project.info21.model.entities.TransferredPoints;
import s21.project.info21.repository.TransferredPointsRepository;


@Service
public class TransferredPointsService extends CommonService<TransferredPoints, Long>{

    private TransferredPointsRepository repository;
    @Autowired
    public TransferredPointsService(TransferredPointsRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
