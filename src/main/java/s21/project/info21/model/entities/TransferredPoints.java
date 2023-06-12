package s21.project.info21.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import s21.project.info21.model.CommonEntity;

@Entity
@Table(name = "transferredpoints")
public class TransferredPoints implements CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incr")
    @GenericGenerator(name = "incr", strategy = "increment")
    private Long id;

    @Column(name = "checkingpeer")
    private String checkingPeer;

    @Column(name = "checkedpeer")
    private String checkedPeer;

    @Column(name = "pointsamount")
    private Integer pointsAmount;


    public TransferredPoints() {
    }

    public TransferredPoints(String checkingPeer, String checkedPeer, Integer pointsAmount) {
        this.checkingPeer = checkingPeer;
        this.checkedPeer = checkedPeer;
        this.pointsAmount = pointsAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckingPeer() {
        return checkingPeer;
    }

    public void setCheckingPeer(String checkingPeer) {
        this.checkingPeer = checkingPeer;
    }

    public String getCheckedPeer() {
        return checkedPeer;
    }

    public void setCheckedPeer(String checkedPeer) {
        this.checkedPeer = checkedPeer;
    }

    public Integer getPointsAmount() {
        return pointsAmount;
    }

    public void setPointsAmount(Integer pointsAmount) {
        this.pointsAmount = pointsAmount;
    }
}
