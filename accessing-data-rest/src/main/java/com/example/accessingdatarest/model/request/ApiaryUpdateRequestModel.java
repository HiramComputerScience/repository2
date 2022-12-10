package com.example.accessingdatarest.model.request;

public class ApiaryUpdateRequestModel {
	private String apiaryId;
	
	private String apiaryName;
	
	private String apiaryZip;
	
	//note this is the randomized hash version of userId that is publicly viewable, 
	//will use service class to find actual id and use that
	//for mapping into database later
    private String userId;

	public String getApiaryName() {
		return apiaryName;
	}

	public void setApiaryName(String apiaryName) {
		this.apiaryName = apiaryName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApiaryZip() {
		return apiaryZip;
	}

	public void setApiaryZip(String apiaryZip) {
		this.apiaryZip = apiaryZip;
	}

	public String getApiaryId() {
		return apiaryId;
	}

	public void setApiaryId(String apiaryId) {
		this.apiaryId = apiaryId;
	}
}
