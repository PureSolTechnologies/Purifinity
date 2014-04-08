package com.puresol.passwordstore.core.impl.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "\"users\"")
@NamedQueries({ @NamedQuery(name = "SelectUserByEmail", query = "SELECT user FROM UserEntity user WHERE user.email=:email") })
public class UserEntity {

	@Id
	@Column(name = "\"UserId\"", nullable = false)
	@SequenceGenerator(sequenceName = "passwordstore_userid_seq", name = "passwordstore_userid_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passwordstore_userid_seq")
	private Long userId;

	@Column(name = "\"Email\"", nullable = false)
	private String email;

	@Column(name = "\"PasswordHash\"", nullable = false)
	private String pwh;

	@Column(name = "\"State\"", nullable = false)
	private Integer state;

	@Column(name = "\"Created\"", nullable = false)
	private Date created;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwh() {
		return pwh;
	}

	public void setPwh(String pwh) {
		this.pwh = pwh;
	}

	public AccountState getState() {
		return AccountState.fromValue(state);
	}

	public void setState(AccountState state) {
		this.state = state.getValue();
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
