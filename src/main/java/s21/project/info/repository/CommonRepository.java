package s21.project.info21.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import s21.project.info21.model.CommonEntity;

@NoRepositoryBean
public interface CommonRepository<T extends CommonEntity, V> extends JpaRepository<T, V> {
}
