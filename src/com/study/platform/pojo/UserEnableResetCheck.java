package com.study.platform.pojo;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * UesrEnableCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user_enable_reset_check"
    ,catalog="study_communication"
)

public class UserEnableResetCheck  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String checkEmail;
     private String checkUuid;
     private String checkSign;
     private Long checkExpiredTime;


    // Constructors

    /** default constructor */
    public UserEnableResetCheck() {
    }

	/** minimal constructor */
    public UserEnableResetCheck(Integer id, String checkEmail, String checkUuid, String checkSign) {
        this.id = id;
        this.checkEmail = checkEmail;
        this.checkUuid = checkUuid;
        this.checkSign = checkSign;
    }
    
    /** full constructor */
    public UserEnableResetCheck(Integer id, String checkEmail, String checkUuid, String checkSign, Long checkExpiredTime) {
        this.id = id;
        this.checkEmail = checkEmail;
        this.checkUuid = checkUuid;
        this.checkSign = checkSign;
        this.checkExpiredTime = checkExpiredTime;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="check_email", nullable=false)

    public String getCheckEmail() {
        return this.checkEmail;
    }
    
    public void setCheckEmail(String checkEmail) {
        this.checkEmail = checkEmail;
    }
    
    @Column(name="check_uuid", nullable=false)

    public String getCheckUuid() {
        return this.checkUuid;
    }
    
    public void setCheckUuid(String checkUuid) {
        this.checkUuid = checkUuid;
    }
    
    @Column(name="check_sign", nullable=false)

    public String getCheckSign() {
        return this.checkSign;
    }
    
    public void setCheckSign(String checkSign) {
        this.checkSign = checkSign;
    }
    
    @Column(name="check_expired_time")

    public Long getCheckExpiredTime() {
        return this.checkExpiredTime;
    }
    
    public void setCheckExpiredTime(Long checkExpiredTime) {
        this.checkExpiredTime = checkExpiredTime;
    }
   








}