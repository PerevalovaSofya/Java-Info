package s21.project.info21.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import s21.project.info21.model.CommonEntity;

@Entity
@Table(name = "recommendations")
public class Recommendations implements CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incr")
    @GenericGenerator(name = "incr", strategy = "increment")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "peer", nullable = false)
    private String peer;

    @NotNull
    @Column(name = "recommendedpeer")
    private String recommendedPeer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeer() {
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public String getRecommendedPeer() {
        return recommendedPeer;
    }

    public void setRecommendedPeer(String recommendedPeer) {
        this.recommendedPeer = recommendedPeer;
    }
}
