package com.shirinov.web.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * User: Rustam Shirinov
 * Date: 15/06/14
 * Time: 8:11 PM
 */

@Entity
@Table(name="users")
@org.hibernate.annotations.Entity(dynamicInsert = true)

@NamedQueries({
        @NamedQuery(name = User.QUERY_GET_USER_BY_EMAIL,
                query = "FROM User u WHERE u.email = :email "),
        @NamedQuery(name = User.QUERY_GET_ALL_USERS,
                query = "FROM User u "),
        @NamedQuery(name = User.QUERY_GET_USER_BY_ID,
                query = "FROM User u WHERE u.id = :id ")
})


public class User extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    public static final String QUERY_GET_USER_BY_EMAIL = "getUserByEmail";
    public static final String QUERY_GET_USER_BY_ID = "getUserById";
    public static final String QUERY_GET_ALL_USERS = "getAllUsers";

    public static final Integer ACTIVE_STATUS = 1;
    public static final Integer BLOCKED_STATUS = 2;
    public static final Integer CLOSED_STATUS = 0;

    public static final Integer ADMIN_ROLE = 0;
    public static final Integer USER_ROLE = 1;
    public static final Integer SUPERVISOR_ROLE = 2;
    public static final Integer READ_ONLY_USER = 3;

    /**
     * Идентификатор пользователя
     **/
    private Long id ;

    /**
     * Адрес электронной почты пользователя
     **/
    private String email;

    /**
     * Имя пользователя
     **/
    private String firstName;

    /**
     * Фамилия пользователя
     **/
    private String lastName;

    /**
     * Пароль пользователя
     **/
    private byte[] password;

    /**
     * Статус пользователя
     * Может иметь следующие значени:
     * CLOSED_STATUS = 0   Пользователь удален / Закрыт / Более не может быть востановлен обычным спосабом
     * ACTIVE_STATUS = 1   Пользоветель активен / работает
     * BLOCKED_STATUS = 2  Пользователь временно заблокирован
     **/
    private Integer status;

    /**
     * Роли пользователя
     * Может иметь следующее значения:
     * ADMIN_ROLE = 0;        Администратор
     * USER_ROLE = 1;         Пользователь
     * SUPERVISOR_ROLE = 2;   Управляющий
     * READ_ONLY_USER = 3;    Только просмотр
     */
    private Integer role;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "email", nullable=false, unique = true, length = 128)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "first_name", nullable=false, length = 128, unique = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable=false, length = 128, unique = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", nullable=false, length = 32, unique = false)
    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Column(name = "status", nullable=false, columnDefinition = "int default 1", unique=false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "role", nullable=false, columnDefinition = "int default 1", unique = false)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
