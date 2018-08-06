package com.mf.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_register")
public class Register {
	@Id
	@GeneratedValue
	private Integer id; // 编号
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerTime;//注册时间
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date trialTime;//初始登录时间
	
	@Column(length=50)
	private String companyName; // 公司名称
	
	@Column(length=100)
	private String secretKey; // 激活秘钥

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Date getTrialTime() {
		return trialTime;
	}

	public void setTrialTime(Date trialTime) {
		this.trialTime = trialTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	@Override
	public String toString() {
		return "Register [id=" + id + ", companyName=" + companyName + ", secretKey=" + secretKey + "]";
	}
	
}
