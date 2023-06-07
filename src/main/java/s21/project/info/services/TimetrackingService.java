package s21.project.info21.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.Timetracking;
import s21.project.info21.repository.CommonRepository;
import s21.project.info21.repository.TimetrackingRepository;

@Service
public class TimetrackingService extends CommonService<Timetracking, Long> {

    private TimetrackingRepository repository;
    @Autowired
    public TimetrackingService(TimetrackingRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
