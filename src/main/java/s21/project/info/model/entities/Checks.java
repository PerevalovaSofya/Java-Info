package s21.project.info21.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import s21.project.info21.model.CommonEntity;


import java.time.LocalDate;

@Entity
@Table(name = "checks")
public class Checks implements CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incr")
    @GenericGenerator(name = "incr", strategy = "increment")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "peer", nullable = false)
    private String peer;

    @Column(name = "task", nullable = false)
    private String task;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Checks() {
    }

    public Checks(String peer, String task, LocalDate date) {
        this.peer = peer;
        this.task = task;
        this.date = date;
    }

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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
