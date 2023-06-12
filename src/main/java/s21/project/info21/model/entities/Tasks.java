package s21.project.info21.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import s21.project.info21.model.CommonEntity;

import java.util.Objects;

@Entity
@Table(name = "tasks")
public class Tasks implements CommonEntity {


    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Id
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "parenttask", nullable = false)
    private String parentTask;

    @NotNull
    @Column(name = "maxxp", nullable = false)
    private Long maxXP;

    public Tasks() {
    }

    public Tasks(String title, String parentTask, Long maxXP) {
        this.title = title;
        this.parentTask = parentTask;
        this.maxXP = maxXP;
    }

    public String getTitle() {
        return title;
    }

    public String getParenttask() {
        return parentTask;
    }

    public Long getMaxxp() {
        return maxXP;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setParenttask(String parentTask) {
        this.parentTask = parentTask;
    }

    public void setMaxxp(Long maxXP) {
        this.maxXP = maxXP;
    }


}
