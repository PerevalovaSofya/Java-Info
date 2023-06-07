package s21.project.info21.repository;

import org.springframework.stereotype.Repository;
import s21.project.info21.model.entities.PeerToPeer;

@Repository
public interface PeerToPeerRepository extends CommonRepository<PeerToPeer, Long> {
}
