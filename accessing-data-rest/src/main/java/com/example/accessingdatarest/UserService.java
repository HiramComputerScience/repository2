package com.example.accessingdatarest;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Service;

import com.example.accessingdatarest.model.request.UserLoginRequestModel;
import com.example.accessingdatarest.model.response.UserDetailsResponseModel;
import com.example.accessingdatarest.model.response.UserLoginResponseModel;

import shared.UserDto;


public interface UserService {
	UserDto createUser(UserDto user);
	UserDetailsResponseModel getUser(String email);
	UserLoginResponseModel loginUser(UserLoginRequestModel userLoginRequest);
}
