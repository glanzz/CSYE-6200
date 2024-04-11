package com.immunet.immunet.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.immunet.immunet.entity.DoctorEntity;
import com.immunet.immunet.entity.VaccineEntity;
import com.immunet.immunet.exception.BadRequest;
import com.immunet.immunet.service.VaccineService;

public class Vaccine {
	Integer id;
	String name;
	Integer frequency;
	List<Integer> intervals;
	Integer offset;
	boolean defaultVaccine;
	Double cost;
	Integer doctorId;
	VaccineService vaccineService;
	

	public boolean isDefaultVaccine() {
		return defaultVaccine;
	}


	public void setDefaultVaccine(boolean defaultVaccine) {
		this.defaultVaccine = defaultVaccine;
	}

	Species species;
	
	public int getId() {
		return this.id;
	}
	
	
	public ShotRecord getShotRecord(Date dob) {
		ShotRecord s = new ShotRecord();
		return s;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) throws BadRequest {
		this.frequency = frequency;
		if (validateFrequency()== false) {
			throw new BadRequest("Frequency not compatible with intervals given");
		}
	}
	public List<Integer> getIntervals() {
		return intervals;
	}
	
	public String getIntervalsString() {
		String intervalString = "";
		for(Integer interval: intervals) {
			intervalString += interval + ",";
		}
		return intervalString;
		
	}
	public void setIntervals(List<Integer> intervals) {
		this.intervals = intervals;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
	}


	public Double getCost() {
		return cost;
	}


	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	
	public Integer getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}


	boolean isDefault() {
		if (defaultVaccine==true) {
			return true;
		}
		return false;	
	}

	
	public  Vaccine(VaccineService vaccineService, String name, Species s,  int frequency, String intervalsCSV, int offset) throws BadRequest {
		this.vaccineService = vaccineService;
			if(name==null || frequency==0||intervalsCSV==null ) {
				throw new BadRequest("Invalid Vaccine data given !");
				
				
			} else {
				this.name=name;
				setSpecies(s);
				setIntervals(parseIntervals(intervalsCSV));
				setFrequency(frequency);
				this.offset=offset;
			}
	}
	
	public Vaccine() {}
	

	public static List<Integer> parseIntervals(String intervalsCSV) throws BadRequest {
		try {
			List<String> intervals = Arrays.asList(intervalsCSV.split(","));
			List<Integer> parsedIntervals = new ArrayList<Integer>();
			intervals.stream().forEach((a) -> {parsedIntervals.add(Integer.parseInt(a));});
			return parsedIntervals;
			
		}
		catch(NumberFormatException e) {
			throw new BadRequest("Intervals data given !");
		}
		
	}
	
	private boolean validateFrequency() {
		return frequency.equals(this.intervals.size() + 1); 
	}
	
	public boolean isPersisted() {
		return this.id != null; 
	}
	
	public void save(DoctorEntity doctor) {
		if (isPersisted()) {
			return;
		} else {
			this.id = vaccineService.save(this, doctor);
		}
	}
	
	
	public void load(VaccineEntity vaccine) throws BadRequest {
		this.id = vaccine.getId();
		this.setCost(vaccine.getCost());
		this.setDefaultVaccine(vaccine.isDefault());
		this.setIntervals(Vaccine.parseIntervals(vaccine.getIntervals()));
		this.setName(vaccine.getName());
		this.setOffset(vaccine.getOffset());
		this.setSpecies(Species.valueOf(vaccine.getSpecies().name()));
		this.setDoctorId(vaccine.getDoctor().getId());
	}
	
	
	
	

}
