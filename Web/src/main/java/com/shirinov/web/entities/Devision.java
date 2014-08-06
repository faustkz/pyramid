package com.shirinov.web.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * User: Rustam Shirinov
 * Date: 07/07/14
 * Time: 7:07 PM
 */

@Entity
@Table(name = "devision")
@org.hibernate.annotations.Entity(dynamicInsert = true)

@NamedQueries({
        @NamedQuery(name = Devision.QUERY_GET_ALL_DEVISIONS,
                query = "FROM Devision") /*,
        @NamedQuery(name = Devision.QUERY_COUNT_MEMBERS,
                query = "COUNT (id) FROM Devision d WHERE d.id = :id ")      */
})

public class Devision extends AbstractEntity {

    private static final long serialVersionUID = 9L;

    public static final String QUERY_GET_ALL_DEVISIONS = "getAllDevisions";
    /*public static final String QUERY_COUNT_MEMBERS = "countMembers";*/

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name",nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Devision)) return false;

        Devision devision = (Devision) o;

        if (id != null ? !id.equals(devision.id) : devision.id != null) return false;
        if (name != null ? !name.equals(devision.name) : devision.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
