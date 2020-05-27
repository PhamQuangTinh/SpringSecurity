package com.phamquangtinh.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Entity cha cho phép kế thừa
//@EntityListeners(AuditingEntityListener.class): để có thẻ sử dụng @Createdby, @CreatedDate,@LastModifiedBy, @LastModifiedDate
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Base {

	// đây là khóa chính, not null, và giá trị tự tăng
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@Column
	@CreatedBy
	private String createdby;
	
	@Column
	@CreatedDate
	private Date createdDate;
	
	@Column
	@LastModifiedBy
	private String modifiedby;
	
	@Column
	@LastModifiedDate
	private Date modifiedDate;

	
	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}
