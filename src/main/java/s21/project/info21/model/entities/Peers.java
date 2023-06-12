package s21.project.info21.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import s21.project.info21.model.CommonEntity;

import java.time.LocalDate;

@Entity
@Table(name = "peers")
public class Peers implements CommonEntity {

    @Id
    @NotNull
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    public Peers() {
    }


    public Peers(String nickname, LocalDate birthday) {
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getNickname() {
        return nickname;
    }
}
