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
@Table(name = "user", catalog = "study_communication", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "username") })
public class User implements java.io.Serializable, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3113267226882184796L;
	// Fields

	/**
	 * 
	 */
	private Long id;
	private String email;
	private String username;
	private String nickname;
	private String password;
	private long regTime;
	private Boolean accountExpired;
	private Boolean accountLocked;
	private Boolean credentialsExpired;
	private Boolean accountEnabled;
	private List<GrantedAuthority> authorities;

	// Constructors

	/** default constructor */
	public User() {
	}
	
	public User(String email, String nickname, String password) {
		this.email = this.username = email;
		this.nickname = nickname;
		this.password = password;
		
		this.regTime = System.currentTimeMillis();
		this.accountEnabled = false;
		this.accountLocked = false;
		this.accountExpired = false;
		this.credentialsExpired = false;
		
	}

	@Column(name = "reg_time", nullable = false)
	public long getRegTime() {
		return regTime;
	}

	public void setRegTime(long regTime) {
		this.regTime = regTime;
	}



	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "account_expired", nullable = false)
	public Boolean getAccountExpired() {
		return this.accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	@Column(name = "account_locked", nullable = false)
	public Boolean getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	@Column(name = "credentials_expired", nullable = false)
	public Boolean getCredentialsExpired() {
		return this.credentialsExpired;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Column(name = "email", unique = true, nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "account_enabled")
	public Boolean getAccountEnabled() {
		return this.accountEnabled;
	}

	public void setAccountEnabled(Boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "username", unique = true, nullable = false, length = 255)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "nickname", length = 255)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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