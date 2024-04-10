package com.immunet.immunet.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="pets")
public class Pet extends BaseEntity {
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Date dob;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Species species;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable=false)
    private Owner owner;
	

	@ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable=false)
    private User creator;

	public Pet(String name, Date dob, Gender gender, Species species) {
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.species = species;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
	}
	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	public User getCreator() {
		return creator;
	}


	public void setCreator(User user) {
		this.creator = user;
	}

}