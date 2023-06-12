package s21.project.info21.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import s21.project.info21.model.CommonEntity;

import java.time.LocalDate;

@Entity
@Table(name = "friends")
public class Friends implements CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incr")
    @GenericGenerator(name= "incr", strategy= "increment")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "peer1", nullable = false)
    private String peer1;
    @Column(name = "peer2", nullable = false)
    private String peer2;

    public Friends(){};
    public Friends (Long id, String peer1, String peer2) {
        this.id = id;
        this.peer1 = peer1;
        this.peer2 = peer2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeer1() {
        return peer1;
    }

    public void setPeer1(String peer1) {
        this.peer1 = peer1;
    }

    public String getPeer2() {
        return peer2;
    }

    public void setPeer2(String peer2) {
        this.peer2 = peer2;
    }

}
