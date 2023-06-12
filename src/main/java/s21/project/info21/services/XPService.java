package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.XP;
import s21.project.info21.repository.XPRepository;

@Service
public class XPService extends CommonService<XP, Long> {
    private final XPRepository repository;

    @Autowired
    public XPService(XPRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
