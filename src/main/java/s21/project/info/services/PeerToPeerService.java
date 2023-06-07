package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.PeerToPeer;
import s21.project.info21.repository.ChecksRepository;
import s21.project.info21.repository.CommonRepository;
import s21.project.info21.repository.PeerToPeerRepository;

@Service
public class PeerToPeerService extends CommonService<PeerToPeer, Long> {

    private final PeerToPeerRepository repository;

    @Autowired
    public PeerToPeerService(PeerToPeerRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
