package com.shirinov.web.beans;

import com.shirinov.utils.FacesUtils;
import com.shirinov.utils.PasswordHash;
import com.shirinov.web.entities.*;
import com.shirinov.web.entities.dao.AbstractDao;
import com.shirinov.web.entities.dao.MemberDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Rustam Shirinov
 * Date: 22/07/14
 * Time: 7:06 PM
 */
@ManagedBean
@SessionScoped
public class AdminBean implements Serializable {

    private static final long serialVersionUID = 106l;

    private Devision devision;
//    private boolean newDevision ;
    private List<Devision> devivsions;
    private List<Member> members;
    private User user;
    private String devisionName;
    private AbstractDao<Devision> devisionDao = new AbstractDao<>();
    private AbstractDao<Member> membersDao = new AbstractDao<>();
    private FacesUtils facesUtils = new FacesUtils();

    private String newUserFirstName;
    private String newUserLastName;
    private Long newUserDevisionId;
    private Long newUserParentId;
    private String newUserPassword;
    private String newUserEmail;
    private Integer newUserRole;
    private String newDevisionName;
    private String hoster;
    private List<String> hostersList = new ArrayList<>();
    private Map<String, Member> freeMembers = new HashMap<>();
    private Map<String, Devision> listDev = new HashMap<>();
    private List<String> devisionsList = new ArrayList<>();
    private AbstractDao<Balance> balanceDao = new AbstractDao<>();
    private Member activeMember ;
    private AbstractDao<Balance> db = new AbstractDao<>();
    private List<Balance> bb = new ArrayList<>();
    private AbstractDao<Payment> paymentsDao =  new AbstractDao<>();

    public void clear(){
        newUserFirstName = null;
        newUserLastName = null;
        newUserDevisionId = null;
        newUserParentId = null;
        newUserPassword = null;
        newUserEmail=null;
        newUserRole=null;
        newDevisionName=null;
        devivsions.clear();
        listDev.clear();
        devisionsList.clear();
        members.clear();
        freeMembers.clear();
        hostersList.clear();
        if (bb!=null && bb.size()>0) {
            bb.clear();
        }
    }

    public void fill(){
//        clear();
        devivsions = devisionDao.get(Devision.QUERY_GET_ALL_DEVISIONS);
        for(Devision d : devivsions){
            listDev.put(d.getName(),d);
            devisionsList.add(d.getName());
        }
        members = membersDao.get(Member.QUERY_GET_ALL_MEMBERS);
        for (Member m : members){
            if (m.getLeftChild()==null || m.getRightChild()==null)
                freeMembers.put(m.getId()+" : "+m.getUser().getFirstName()+" "+m.getUser().getLastName(),m);
            hostersList.add(m.getId()+" : "+m.getUser().getFirstName()+" "+m.getUser().getLastName());
        }
         bb = db.get(Balance.QUERY_GET_ALL_BALANCE);
    }

    public AdminBean() {
        fill();
       // newDevision=false;
    }

    public Devision getDevision() {
        return devision;
    }

    public void setDevision(Devision devision) {
        this.devision = devision;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDevisionName() {
        return devisionName;
    }

    public void setDevisionName(String devisionName) {
        this.devisionName = devisionName;
    }

    public void removeDevision(int id){
        MemberDao md = new MemberDao();
        int membersInDevision = md.countMembers(id);
        int result = 0;

    }

    public List<Devision> getDevivsions() {
        return devivsions;
    }

    public void createDevision(){
        Devision devision = new Devision();
        devision.setName(devisionName);
        devisionDao.add(devision);
        devision = null;
        facesUtils.updatePage();
    }

    public void setDevivsions(List<Devision> devivsions) {
        this.devivsions = devivsions;
    }


    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Member getMemberById(Long id){
        Member result = null;
        for (Member member : members ){
            if (member.getId()==id) {
                result = member;
                break;
            }
        }
        return result;
    }

    public String getNewUserFirstName() {
        return newUserFirstName;
    }

    public void setNewUserFirstName(String newUserFirstName) {
        this.newUserFirstName = newUserFirstName;
    }

    public String getNewUserLastName() {
        return newUserLastName;
    }

    public void setNewUserLastName(String newUserLastName) {
        this.newUserLastName = newUserLastName;
    }

    public Long getNewUserDevisionId() {
        return newUserDevisionId;
    }

    public void setNewUserDevisionId(Long newUserDevisionId) {
        this.newUserDevisionId = newUserDevisionId;
    }

    public Long getNewUserParentId() {
        return newUserParentId;
    }

    public void setNewUserParentId(Long newUserParentId) {
        this.newUserParentId = newUserParentId;
    }

    public String getNewUserPassword() {
        return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
        this.newUserPassword = newUserPassword;
    }

    public String getNewUserEmail() {
        return newUserEmail;
    }

    public void setNewUserEmail(String newUserEmail) {
        this.newUserEmail = newUserEmail;
    }

    public Integer getNewUserRole() {
        return newUserRole;
    }

    public void setNewUserRole(Integer newUserRole) {
        this.newUserRole = newUserRole;
    }

    public String getNewDevisionName() {
        return newDevisionName;
    }

    public void setNewDevisionName(String newDevisionName) {
        this.newDevisionName = newDevisionName;
    }

    public List<String> getDevisionsList() {
        return devisionsList;
    }

    public void setDevisionsList(List<String> devisionsList) {
        this.devisionsList = devisionsList;
    }

    public String getHoster() {
        return hoster;
    }

    public void setHoster(String hoster) {
        this.hoster = hoster;
    }

    public List<String> getHostersList() {
        return hostersList;
    }

    public void setHostersList(List<String> hostersList) {
        this.hostersList = hostersList;
    }

    public Member getActiveMember() {
        return activeMember;
    }

    public void setActiveMember(Member activeMember) {
        this.activeMember = activeMember;
    }

    public void addMember() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        user = new User();
        user.setStatus(1);
        user.setFirstName(newUserFirstName);
        user.setLastName(newUserLastName);

        user.setPassword(PasswordHash.hash(newUserPassword));
        user.setRole(newUserRole);
        user.setEmail(newUserEmail);
        AbstractDao<User> userDao = new AbstractDao<>();
        userDao.add(user);

        devision = listDev.get(newDevisionName);
        //devisionDao.add(devision);

        Member member = new Member();
        member.setUser(user);
        member.setDevision(devision);
        member.setParentId(newUserParentId);
        newUserParentId = freeMembers.get(hoster).getId();
        member.setParentId(newUserParentId);
        membersDao.add(member);

        Member memberParent = getMemberById(newUserParentId);

        if (memberParent.getLeftChild()==null)
            memberParent.setLeftChild(member.getId());
         else memberParent.setRightChild(member.getId());

        if (memberParent.getLeftChild()!=null && memberParent.getRightChild()!=null){
            Payment p = new Payment();
            p.setInn(50);
            p.setMemberId(memberParent.getId());

            paymentsDao.add(p);

            Balance b = null;///db.getSingleBy("id",memberParent.getId(),Balance.QUERY_GET_BALANCE_BY_MEMBER_ID);
            for(Balance bal : bb){
                if (bal.getMemberId()==memberParent.getId()){
                    b = bal;
                }
            }
            b.setSumma(b.getSumma()+50);
            b.setUpdateDate(null);
            db.update(b);
        }
        membersDao.update(memberParent);
        Payment payment = new Payment();
        payment.setMemberId(member.getId());
        payment.setInn(100);
        paymentsDao.add(payment);

        Balance balance = new Balance();
        balance.setMemberId(member.getId());
        balance.setSumma(100);
        balanceDao.add(balance);


        clear();
        fill();
        facesUtils.updatePage();
        //ТУТ ЗАКОНЧИЬ
    }

    public void openMember(Long id){

        activeMember = getMemberById(id);
        clear();
        fill();
        try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/member.xhtml");
        } catch (IOException e){
            // Hardly possible
        }
    }


    public Integer getBalanceByMemberId(Long id){
        Integer res=0;
        if (bb!=null)
        for(Balance bal : bb){
            if (bal.getMemberId()==id){
                res = bal.getSumma();
            }
        }
        return res;
    }

    public void showBalance(Long id){


    }

}

