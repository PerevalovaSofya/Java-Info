package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.Checks;
import s21.project.info21.repository.ChecksRepository;
import s21.project.info21.repository.CommonRepository;

@Service
public class ChecksService extends CommonService<Checks, Long> {

    private final ChecksRepository repository;

    @Autowired
    public ChecksService(ChecksRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
