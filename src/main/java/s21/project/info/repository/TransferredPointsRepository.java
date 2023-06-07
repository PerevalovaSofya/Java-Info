package s21.project.info21.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import s21.project.info21.model.entities.TransferredPoints;

public interface TransferredPointsRepository extends JpaRepository<TransferredPoints, Long> {
}
