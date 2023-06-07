package s21.project.info21.repository;

import org.springframework.stereotype.Repository;
import s21.project.info21.model.entities.Friends;

@Repository
public interface FriendsRepository extends CommonRepository<Friends, Long> {}