package com.shirinov.web.entities;


import javax.persistence.*;

/**
 * User: Rustam Shirinov
 * Date: 01/07/14
 * Time: 1:17 AM
 */
@Entity
@Table(name="contact")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class Contact extends AbstractEntity {

    private static final long serialVersionUID = 3L;

    private Long id;
    private String mobilePhones;
    private String homePhones;
    private String workPhones;
    private String email;
    private String address;
    private City city;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="mobile_phone", length = 128, unique = false, nullable = true)
    public String getMobilePhones() {
        return mobilePhones;
    }

    public void setMobilePhones(String mobilePhones) {
        this.mobilePhones = mobilePhones;
    }

    @Column(name="home_phone", length = 128, unique = false, nullable = true)
    public String getHomePhones() {
        return homePhones;
    }

    public void setHomePhones(String homePhones) {
        this.homePhones = homePhones;
    }

    @Column(name="work_phone", length = 128, unique = false, nullable = true)
    public String getWorkPhones() {
        return workPhones;
    }

    public void setWorkPhones(String workPhones) {
        this.workPhones = workPhones;
    }

    @Column(name="email", length = 128, unique = false, nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="address", length = 128, unique = false, nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.EAGER, targetEntity = City.class)
    @JoinColumn(name = "city_id")    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (address != null ? !address.equals(contact.address) : contact.address != null) return false;
        if (city != null ? !city.equals(contact.city) : contact.city != null) return false;
        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (homePhones != null ? !homePhones.equals(contact.homePhones) : contact.homePhones != null) return false;
        if (!id.equals(contact.id)) return false;
        if (mobilePhones != null ? !mobilePhones.equals(contact.mobilePhones) : contact.mobilePhones != null)
            return false;
        if (workPhones != null ? !workPhones.equals(contact.workPhones) : contact.workPhones != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (mobilePhones != null ? mobilePhones.hashCode() : 0);
        result = 31 * result + (homePhones != null ? homePhones.hashCode() : 0);
        result = 31 * result + (workPhones != null ? workPhones.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
