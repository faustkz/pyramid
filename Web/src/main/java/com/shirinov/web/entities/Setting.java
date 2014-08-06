package com.shirinov.web.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * User: Rustam Shirinov
 * Date: 14/07/14
 * Time: 8:18 PM
 */

@Entity
@Table(name = "settings")
@org.hibernate.annotations.Entity(dynamicInsert = true)
@NamedQueries({
        @NamedQuery(name = Setting.QUERY_GET_VALUE_BY_NAME,
                query = "FROM Setting s WHERE s.name  = :name "),
        @NamedQuery(name = Setting.QUERY_GET_ALL,
                query = "FROM Setting s ")
})

public class Setting extends AbstractEntity {

    private static final long serialVersionUID = 10L;

    public static final String QUERY_GET_VALUE_BY_NAME = "getValueByName";
    public static final String QUERY_GET_ALL = "getAll";

    private Long id;
    private String name;
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false, length = 256)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "value", nullable = false, unique = false, length = 256)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Setting)) return false;

        Setting setting = (Setting) o;

        if (id != null ? !id.equals(setting.id) : setting.id != null) return false;
        if (name != null ? !name.equals(setting.name) : setting.name != null) return false;
        if (value != null ? !value.equals(setting.value) : setting.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
