package com.spring.model;

import java.io.Serializable;

public class UserOrganization implements Serializable {
	
    private long id;

    private int userId;
  
 
    private int organizationId;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
    


}