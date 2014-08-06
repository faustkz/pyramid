package com.shirinov.web.entities;

import java.util.Date;

/**
 * User: Rustam Shirinov
 * Date: 03/07/14
 * Time: 11:59 PM
 */

public class Approval extends AbstractEntity {

    private static final long serialVersionUID = 8L;

    public static final Integer DRAFT_STATUS = 0;
    public static final Integer TO_BE_APPROVED_STATUS = 1;
    public static final Integer APPROVED_STATUS = 10;
    public static final Integer REFUSED_STATUS = 2;

    public static final Integer ADD_NEW_MEMBER = 0;
    public static final Integer COMPLITE_BINARY = 1;

    private Long id;
    private Member member;
    private Date date;
    private Integer status;
    private Integer type;
    private Comment comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Approval)) return false;

        Approval approval = (Approval) o;

        if (comment != null ? !comment.equals(approval.comment) : approval.comment != null) return false;
        if (!date.equals(approval.date)) return false;
        if (!id.equals(approval.id)) return false;
        if (!member.equals(approval.member)) return false;
        if (!status.equals(approval.status)) return false;
        if (!type.equals(approval.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + member.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }
}
