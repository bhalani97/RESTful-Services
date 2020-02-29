package com.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Info About User")
public class User {

	private Integer id;
	@Size(min = 2 , message = "Name should have alteast 2 characters")
	@ApiModelProperty(notes = "Should be more than 2 Characteres")
	private String name;
	@Past
	@ApiModelProperty(notes = "Should be in past") 
	private Date birthDate;

	public User(Integer id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

}
