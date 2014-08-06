package com.shirinov.web.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Rustam Shirinov
 * Date: 03/07/14
 * Time: 11:34 PM
 */

@Entity
@Table(name = "payments" )
@org.hibernate.annotations.Entity(dynamicInsert = true)

@NamedQueries({
        @NamedQuery(name = Payment.QUERY_GET_PAYMENTS_BY_ID,
                query = "FROM Payment p WHERE p.id = :id ")
})

public class Payment extends AbstractEntity {

    private static final long serialVersionUID = 6L;

    public static final String  QUERY_GET_PAYMENTS_BY_ID = "queryGetPaymentsById";

    private Long id;
    private Long memberId;
    private Integer inn;
    private Integer outt;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "member_id", unique = false, nullable = false)
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    @Column(name = "inn", unique = false, nullable = true)
    public Integer getInn() {
        return inn;
    }

    public void setInn(Integer inn) {
        this.inn = inn;
    }

    @Column(name = "outt", unique = false, nullable = true)
    public Integer getOutt() {
        return outt;
    }

    public void setOutt(Integer outt) {
        this.outt = outt;
    }

    @Column(name="date", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (!date.equals(payment.date)) return false;
        if (!id.equals(payment.id)) return false;
        if (inn != null ? !inn.equals(payment.inn) : payment.inn != null) return false;
        if (!memberId.equals(payment.memberId)) return false;
        if (outt != null ? !outt.equals(payment.outt) : payment.outt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + memberId.hashCode();
        result = 31 * result + (inn != null ? inn.hashCode() : 0);
        result = 31 * result + (outt != null ? outt.hashCode() : 0);
        result = 31 * result + date.hashCode();
        return result;
    }
}
