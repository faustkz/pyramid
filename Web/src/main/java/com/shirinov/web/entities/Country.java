package com.shirinov.web.entities;

import javax.persistence.*;

/**
 * User: Rustam Shirinov
 * Date: 03/07/14
 * Time: 11:21 PM
 */

@Entity
@Table(name="country")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class Country extends AbstractEntity {

    private static final long serialVersionUID = 4L;

    /**
     * Идентификатор страны
     **/
    private Long id;

    /**
     * Название страны
     **/
    private String name;

    /**
     * Телефонный код страны
     **/
    private Integer phoneCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 256, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_code", nullable = true, length = 5)
    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (!id.equals(country.id)) return false;
        if (!name.equals(country.name)) return false;
        if (phoneCode != null ? !phoneCode.equals(country.phoneCode) : country.phoneCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (phoneCode != null ? phoneCode.hashCode() : 0);
        return result;
    }
}
