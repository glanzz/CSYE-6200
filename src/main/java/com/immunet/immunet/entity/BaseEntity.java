package com.immunet.immunet.entity;


import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;


@MappedSuperclass
public class BaseEntity {
	@Id
	@NonNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer id;
	
	@Column(name = "created_at", nullable=false, updatable = false)
    @CreationTimestamp
    public Date createdAt;

	
	@PrePersist
	protected void onCreate() {
	  setCreatedAt(new Date());
	}
	
	public Integer getId() {
		return id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	

}
