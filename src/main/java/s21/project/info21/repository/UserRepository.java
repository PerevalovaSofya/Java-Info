package s21.project.info21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import s21.project.info21.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
