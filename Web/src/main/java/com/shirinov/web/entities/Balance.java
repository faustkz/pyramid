package com.shirinov.web.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Rustam Shirinov
 * Date: 03/07/14
 * Time: 11:38 PM
 */


@Entity
@Table(name = "balance")
@org.hibernate.annotations.Entity(dynamicInsert = true)


@NamedQueries({
        @NamedQuery(name = Balance.QUERY_GET_ALL_BALANCE,
                query = "FROM Balance ")/*,
        @NamedQuery(name = Balance.QUERY_GET_BALANCE_BY_MEMBER_ID,
                query = "FROM Balance bb WHERE bb.member_id = :id ")     */
})

public class Balance extends AbstractEntity {

    private static final long serialVersionUID = 7L;

    public static final String QUERY_GET_BALANCE_BY_MEMBER_ID = "getBalanceByMemberId";
    public static final String QUERY_GET_ALL_BALANCE = "getAllBalance";

    private Long id;
    private Integer summa;
    private Date updateDate;
    private Long member_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="summa", unique = false, nullable = false)
    public Integer getSumma() {
        return summa;
    }

    public void setSumma(Integer summa) {
        this.summa = summa;
    }

    @Column(name="date", nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name="member_id", unique = false, nullable = false)
    public Long getMemberId() {
        return member_id;
    }

    public void setMemberId(Long member_id) {
        this.member_id = member_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balance)) return false;

        Balance balance = (Balance) o;

        if (!id.equals(balance.id)) return false;
        if (!member_id.equals(balance.member_id)) return false;
        if (!summa.equals(balance.summa)) return false;
        if (!updateDate.equals(balance.updateDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + summa.hashCode();
        result = 31 * result + updateDate.hashCode();
        result = 31 * result + member_id.hashCode();
        return result;
    }
}

