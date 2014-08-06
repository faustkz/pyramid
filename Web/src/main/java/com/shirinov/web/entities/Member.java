package com.shirinov.web.entities;


import javax.persistence.*;

/**
 * User: Rustam Shirinov
 * Date: 01/07/14
 * Time: 1:07 AM
 */
@Entity
@Table(name = "member")
@org.hibernate.annotations.Entity(dynamicInsert = true)

@NamedQueries({
        @NamedQuery(name = Member.QUERY_GET_ALL_MEMBERS,
                query = "FROM Member m"),
        @NamedQuery(name = Member.QUERY_GET_BY_ID,
                query = "FROM Member m WHERE m.id=:id")/*,
        @NamedQuery(name = Member.QUERY_GET_BY_ID,
                query = "FROM Member m WHERE m.left_id IS NULL OR m.right_id IS NULL ")   */
        /*@NamedQuery(name = Member.QUERY_COUNT_MEMBERS,
                query = "COUNT (d.id) FROM Member d WHERE d.devision_id = :id ")     */
})

public class Member extends AbstractEntity  {

    private static final long serialVersionUID = 2L;
    public static final String QUERY_COUNT_MEMBERS = "countMembers";
    public static final String QUERY_GET_ALL_MEMBERS = "getAllMembers";
    public static final String QUERY_GET_BY_ID = "getById";
    public static final String QUERY_GET_FREE_MEMBERS = "getById";

    private Long id;
    private User user;
    private Contact contact;
    private Long parentId;
    private Long leftChild;
    private Long rightChild;
    private Devision devision;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(targetEntity = Contact.class, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "contact_id")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Column(name = "parent_id", unique = false, nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Column(name = "left_id", unique = false, nullable = true)
    public Long getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Long leftChild) {
        this.leftChild = leftChild;
    }

    @Column(name = "right_id", unique = false, nullable = true)
    public Long getRightChild() {
        return rightChild;
    }

    public void setRightChild(Long rightChild) {
        this.rightChild = rightChild;
    }

    @ManyToOne(cascade= {CascadeType.REFRESH},fetch = FetchType.EAGER, targetEntity = Devision.class)
    @JoinColumn(name = "devision_id")
    public Devision getDevision() {
        return devision;
    }

    public void setDevision(Devision devision) {
        this.devision = devision;
    }

    public boolean hasBinary(){
        return (leftChild!= null && rightChild!=null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (!devision.equals(member.devision)) return false;
        if (contact != null ? !contact.equals(member.contact) : member.contact != null) return false;
        if (!id.equals(member.id)) return false;
        if (leftChild != null ? !leftChild.equals(member.leftChild) : member.leftChild != null) return false;
        if (parentId != null ? !parentId.equals(member.parentId) : member.parentId != null) return false;
        if (rightChild != null ? !rightChild.equals(member.rightChild) : member.rightChild != null) return false;
        if (!user.equals(member.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (leftChild != null ? leftChild.hashCode() : 0);
        result = 31 * result + (rightChild != null ? rightChild.hashCode() : 0);
        result = 31 * result + devision.hashCode();
        return result;
    }
}