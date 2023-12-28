  package com.spring.model;



import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

 
    private int  id;

    private String firstname;

    private String lastname;
   	private Address address;
    	private UserOrganization userorganization;
    private String experience;
    private String skills;
    private String education;
    private String partnerType;
  
	public UserOrganization getUserOrganization() {
		return userorganization;
	}

	public void setUserOrganization(UserOrganization userOrganization) {
		this.userorganization = userOrganization;
	}

	public String getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boolean getEmailOtpExipry() {
		return emailOtpExipry;
	}

	public void setEmailOtpExipry(Boolean emailOtpExipry) {
		this.emailOtpExipry = emailOtpExipry;
	}

	public Boolean getPhonenumberOtpExpiry() {
		return phonenumberOtpExpiry;
	}

	public void setPhonenumberOtpExpiry(Boolean phonenumberOtpExpiry) {
		this.phonenumberOtpExpiry = phonenumberOtpExpiry;
	}

 private String password;
 private String phonenumber;
private String countryCode;
private Boolean termsandconditions;
	
    private String token;
	
    private String emailOtp;

    private String phonenumberOtp;
	
    private Boolean emailOtpExipry;

    private Boolean phonenumberOtpExpiry;
 private String email;
    private Date registrationDate;
   private Date lastLoginDate;
    private Boolean enabled;
    private Boolean accountNonLocked;
   private Boolean credentialsNonExpired;
    
  //  @Column
    private Boolean accountNonExpired;
    
    public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Boolean getTermsandconditions() {
		return termsandconditions;
	}

	public void setTermsandconditions(Boolean termsandconditions) {
		this.termsandconditions = termsandconditions;
	}
	//@Column
    private String levels;


	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

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

	

	public void setEmailOtpExipry(boolean emailOtpExipry) {
		this.emailOtpExipry = emailOtpExipry;
	}



	public void setPhonenumberOtpExpiry(boolean phonenumberOtpExpiry) {
		this.phonenumberOtpExpiry = phonenumberOtpExpiry;
	}

	

	public int getId() {
        return id;
    }

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
  }
