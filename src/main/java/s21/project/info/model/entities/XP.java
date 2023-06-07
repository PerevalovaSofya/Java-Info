package s21.project.info21.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import s21.project.info21.model.CommonEntity;


@Entity
@Table(name = "xp")
public class XP implements CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incr")
    @GenericGenerator(name = "incr", strategy = "increment")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "checkid", nullable = false)
    private Long checkId;

    public XP() {
    }

    public XP(Long id, Long checkId, Long xpAmount) {
        this.id = id;
        this.checkId = checkId;
        this.xpAmount = xpAmount;
    }

    @Column(name = "xpamount", nullable = false)
    private Long xpAmount;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public void setXpAmount(Long xpAmount) {
        this.xpAmount = xpAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getCheckId() {
        return checkId;
    }

    public Long getXpAmount() {
        return xpAmount;
    }
}
