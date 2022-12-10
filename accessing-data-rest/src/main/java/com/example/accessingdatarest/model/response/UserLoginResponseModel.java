package com.example.accessingdatarest.model.response;

public class UserLoginResponseModel {
	private Boolean isPasswordCorrect;
	private UserDetailsResponseModel userDetails;
	
	public Boolean getIsPasswordCorrect() {
		return isPasswordCorrect;
	}
	public void setIsPasswordCorrect(Boolean isPasswordCorrect) {
		this.isPasswordCorrect = isPasswordCorrect;
	}
	public UserDetailsResponseModel getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetailsResponseModel userDetails) {
		this.userDetails = userDetails;
	}
}
