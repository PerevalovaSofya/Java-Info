package s21.project.info21.repository;

import org.springframework.stereotype.Repository;
import s21.project.info21.model.entities.Tasks;

@Repository
public interface TasksRepository extends CommonRepository<Tasks,String> {
}
