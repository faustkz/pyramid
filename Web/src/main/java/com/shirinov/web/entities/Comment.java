package com.shirinov.web.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Rustam Shirinov
 * Date: 04/07/14
 * Time: 12:06 AM
 */

@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    private static final long serialVersionUID = 9L;

    private Long id;
    private Date date;
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="date", nullable = false, columnDefinition = "DATETIME", unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "text", nullable = false, unique = false, length = 512)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (!date.equals(comment.date)) return false;
        if (!id.equals(comment.id)) return false;
        if (!text.equals(comment.text)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }
}
