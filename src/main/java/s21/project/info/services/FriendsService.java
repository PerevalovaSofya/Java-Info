package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.repository.FriendsRepository;
import s21.project.info21.model.entities.Friends;

@Service
public class FriendsService extends CommonService<Friends, Long> {

    private final FriendsRepository repository;

    @Autowired
    public FriendsService(FriendsRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
