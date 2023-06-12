package s21.project.info21.repository;

import org.springframework.stereotype.Repository;
import s21.project.info21.model.entities.Timetracking;

@Repository
public interface TimetrackingRepository extends CommonRepository<Timetracking, Long> {
}
