package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.repository.PeersRepository;
import s21.project.info21.model.entities.Peers;

@Service
public class PeersService extends CommonService<Peers, String> {

    private final PeersRepository repository;

    @Autowired
    public PeersService(PeersRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public boolean existsByNickname(String nickname) {
        return repository.existsById(nickname);
    }
//    public void addPeer(Peers peer) {
//        repository.saveAndFlush(peer);
//    }
//    public List<Peers> getAll() {
//        return repository.findAll();
//    }
//
//    public void deleteByNickname(String id) {
//        repository.deleteById(id);
//    }
//
//    public Peers findById(String id) {
//        return repository.findById(id).orElse(null);
//    }
}