package com.example.accessingdatarest;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="apiary")
public class ApiaryEntity implements Serializable {
	private static final long serialVersionUID = 6476277810944881280L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long apiaryId;
	
	@Column(unique=true)
	private String apiaryName;

	@Column
	private String apiaryZip;
	
	@Column
	private long realUserId;
	
	public long getRealUserId() {
		return realUserId;
	}

	public void setRealUserId(long realUserId) {
		this.realUserId = realUserId;
	}

	public long getApiaryId() {
		return apiaryId;
	}

	public void setApiaryId(long apiaryId) {
		this.apiaryId = apiaryId;
	}

	public String getApiaryName() {
		return apiaryName;
	}

	public void setApiaryName(String apiaryName) {
		this.apiaryName = apiaryName;
	}
	public String getApiaryZip() {
		return apiaryZip;
	}

	public void setApiaryZip(String apiaryZip) {
		this.apiaryZip = apiaryZip;
	}
    
}
