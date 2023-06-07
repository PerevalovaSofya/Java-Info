package s21.project.info21.services;

import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.Recommendations;
import s21.project.info21.repository.CommonRepository;

@Service
public class RecommendationsService extends CommonService<Recommendations, Long> {

    private final CommonRepository<Recommendations, Long> recommendationsRepository;

    public RecommendationsService(CommonRepository<Recommendations, Long> recommendationsRepository) {
        super(recommendationsRepository);
        this.recommendationsRepository = recommendationsRepository;
    }

    // Additional methods for RecommendationsService, if necessary

}
