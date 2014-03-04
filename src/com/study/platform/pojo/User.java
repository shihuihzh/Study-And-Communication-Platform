package com.study.platform.pojo;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * user entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="user"
    ,catalog="study_communication"
, uniqueConstraints = {@UniqueConstraint(columnNames="email"), @UniqueConstraint(columnNames="username")}
)

public class User  implements java.io.Serializable, UserDetails {


    // Fields    

     private Long id;
     private String email;
     private String username;
     private String password;
     private Boolean accountExpired;
     private Boolean accountLocked;
     private Boolean credentialsExpired;
     private Boolean accountEnabled;
     private List<GrantedAuthority> authorities;


    // Constructors

    /** default constructor */
    public User() {
    }

	
    
    public User(String email, String username, String password,
			Boolean accountExpired, Boolean accountLocked,
			Boolean credentialsExpired, Boolean accountEnabled) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.accountExpired = accountExpired;
		this.accountLocked = accountLocked;
		this.credentialsExpired = credentialsExpired;
		this.accountEnabled = accountEnabled;
	}



	/** full constructor */
    public User(Boolean accountExpired, Boolean accountLocked, Boolean credentialsExpired, String email, Boolean accountEnabled, String password, String username) {
        this.accountExpired = accountExpired;
        this.accountLocked = accountLocked;
        this.credentialsExpired = credentialsExpired;
        this.email = email;
        this.accountEnabled = accountEnabled;
        this.password = password;
        this.username = username;
    }

   
    // Property accessors
    @Id @GeneratedValue   
    @Column(name="id", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="account_expired", nullable=false)
    public Boolean getAccountExpired() {
        return this.accountExpired;
    }
    
    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }
    
    @Column(name="account_locked", nullable=false)
    public Boolean getAccountLocked() {
        return this.accountLocked;
    }
    
    public void setAccountLocked(Boolean accountLocked) {
        this.accountLocked = accountLocked;
    }
    
    @Column(name="credentials_expired", nullable=false)
    public Boolean getCredentialsExpired() {
        return this.credentialsExpired;
    }
    
    public void setCredentialsExpired(Boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }
    
    @Column(name="email", unique=true, nullable=false)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="account_enabled")
    public Boolean getAccountEnabled() {
        return this.accountEnabled;
    }
    
    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }
    
    @Column(name="password", nullable=false)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="username", unique=true, nullable=false, length=50)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    
	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = (List<GrantedAuthority>) authorities;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return !this.getAccountExpired();
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return !this.getAccountLocked();
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return !this.getCredentialsExpired();
	}

	@Transient
	@Override
	public boolean isEnabled() {
		return this.getAccountEnabled();
	}
   




}