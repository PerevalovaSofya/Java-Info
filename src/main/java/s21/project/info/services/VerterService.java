package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.Verter;
import s21.project.info21.repository.VerterRepository;

@Service
public class VerterService extends CommonService<Verter, Long> {
    private final VerterRepository verterRepository;

    @Autowired
    public VerterService(VerterRepository verterRepository) {
        super(verterRepository);
        this.verterRepository = verterRepository;
    }
}
