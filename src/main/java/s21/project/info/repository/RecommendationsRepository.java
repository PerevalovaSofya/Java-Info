package s21.project.info21.repository;

import org.springframework.stereotype.Repository;
import s21.project.info21.model.entities.Recommendations;

@Repository
public interface RecommendationsRepository extends CommonRepository<Recommendations, Long> {
}
