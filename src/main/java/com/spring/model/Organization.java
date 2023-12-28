package com.spring.model;


import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable {

   
    private int  id;
 
    private String name;

    private String type;

private String phoneNumber;


    private String email;
   
    private Date registrationDate;
    
    private String emailOtp;
    private String phonenumberOtp;
	
    private boolean emailOtpExipry;
    private boolean phonenumberOtpExpiry;

   
    public String getEmailOtp() {
		return emailOtp;
	}

	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
	}

	public String getPhonenumberOtp() {
		return phonenumberOtp;
	}

	public void setPhonenumberOtp(String phonenumberOtp) {
		this.phonenumberOtp = phonenumberOtp;
	}

	public boolean isEmailOtpExipry() {
		return emailOtpExipry;
	}

	public void setEmailOtpExipry(boolean emailOtpExipry) {
		this.emailOtpExipry = emailOtpExipry;
	}

	public boolean isPhonenumberOtpExpiry() {
		return phonenumberOtpExpiry;
	}

	public void setPhonenumberOtpExpiry(boolean phonenumberOtpExpiry) {
		this.phonenumberOtpExpiry = phonenumberOtpExpiry;
	}
  
    public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}  


	public int getId() {
        return id;
    }

    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	

  
  }
