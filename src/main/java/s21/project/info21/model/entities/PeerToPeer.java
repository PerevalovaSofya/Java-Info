package s21.project.info21.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import s21.project.info21.model.CommonEntity;

import java.sql.Time;

@Entity
@Table(name = "p2p")
public class PeerToPeer implements CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incr")
    @GenericGenerator(name = "incr", strategy = "increment")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "checkid", nullable = false)
    private Long checkid;

    @NotNull
    @Column(name = "checkingpeer", nullable = false)
    private String checkingpeer;

    @NotNull
    @Column(name = "state", nullable = false)
    private String state;

    @NotNull
    @Column(name = "time", nullable = false)
    private Time time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckid() {
        return checkid;
    }

    public void setCheckid(Long checkid) {
        this.checkid = checkid;
    }

    public String getCheckingpeer() {
        return checkingpeer;
    }

    public void setCheckingpeer(String checkingpeer) {
        this.checkingpeer = checkingpeer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
