package com.shirinov.web.entities;

import javax.persistence.*;

/**
 * User: Rustam Shirinov
 * Date: 03/07/14
 * Time: 11:25 PM
 */

@Entity
@Table(name="city")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class City extends AbstractEntity {

    private static final long serialVersionUID = 5L;

    private Long id;
    private Country country;
    private String name;
    private Integer phoneCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.EAGER, targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "name", length = 64, unique = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone_code", length = 64, nullable = true, unique = false)
    public Integer getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(Integer phoneCode) {
        this.phoneCode = phoneCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        if (!country.equals(city.country)) return false;
        if (!id.equals(city.id)) return false;
        if (!name.equals(city.name)) return false;
        if (phoneCode != null ? !phoneCode.equals(city.phoneCode) : city.phoneCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (phoneCode != null ? phoneCode.hashCode() : 0);
        return result;
    }
}
