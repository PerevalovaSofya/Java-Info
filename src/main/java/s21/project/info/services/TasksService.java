package s21.project.info21.services;

import org.springframework.stereotype.Service;
import s21.project.info21.model.entities.Tasks;
import s21.project.info21.repository.CommonRepository;
import s21.project.info21.repository.TasksRepository;

@Service
public class TasksService extends CommonService<Tasks, String> {

    private final TasksRepository repository;
    public TasksService(TasksRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
