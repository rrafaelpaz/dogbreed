package au.com.dogs.persistence.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="DOG")
public class Dog {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="BREED_NAME", nullable=false)
	private String breedName;
	
	@Column(name="S3_LOCATION", nullable=false)
	private String s3Location;
	
	@Column(name="DATE_UPLOAD")
	private LocalDate dateUpload;
	
	public Dog(String breedName,  String s3Location, LocalDate dateUpload){
		this.breedName = breedName;
		this.s3Location = s3Location;
		this.dateUpload = dateUpload;
	}
	
	protected Dog(){}
	

	public Long getId() {
		return id;
	}

	
	public String getBreedName() {
		return breedName;
	}

	
	public String getS3Location() {
		return s3Location;
	}

	
	public LocalDate getDateUpload() {
		return dateUpload;
	}

	
	
	
	
	

}
