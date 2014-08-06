package com.shirinov.web.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Rustam Shirinov
 * Date: 15/07/14
 * Time: 12:19 AM
 */

@Entity
@Table(name = "news")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class News extends AbstractEntity {

    private static final long serialVersionUID = 11L;

    public static final String QUERY_GET_LAST_TEN = "queryGetLastTen";
    public static final String QUERY_GET_NEWS_BY_MONTH = "queryGetLastTen";

    private Long id;
    private Date  date;
    private Setting text;

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

    @Column(name = "text", unique = false, nullable = false, length = 2048)
    public Setting getText() {
        return text;
    }

    public void setText(Setting text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        if (id != null ? !id.equals(news.id) : news.id != null) return false;
        if (text != null ? !text.equals(news.text) : news.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
