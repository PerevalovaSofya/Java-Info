package s21.project.info21.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s21.project.info21.model.CommonEntity;
import s21.project.info21.repository.CommonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public abstract class CommonService<T extends CommonEntity, V> {

    protected final CommonRepository<T, V> commonRepository;

    public void add(T entity) {
        commonRepository.saveAndFlush(entity);
    }

    public List<T> getAll() {
        return commonRepository.findAll();
    }

    public void deleteById(V id) {
        commonRepository.deleteById(id);
    }

    public T findById(V id) {
        return commonRepository.findById(id).orElse(null);
    }

}
