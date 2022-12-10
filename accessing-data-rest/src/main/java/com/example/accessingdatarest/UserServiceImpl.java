package com.example.accessingdatarest;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.model.request.UserLoginRequestModel;
import com.example.accessingdatarest.model.response.UserDetailsResponseModel;
import com.example.accessingdatarest.model.response.UserLoginResponseModel;

import shared.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	//Recieves object created from JSON sent by UI; 
	//will not have all fields of UserEntity yet. 
	//userId is generated here, so is encryptedPassword.
	//Otherwise handed over...
	@Override
	public UserDto createUser(UserDto user) {
		UserEntity retrievedUserDetails = userRepository.findByEmail(user.getEmail());
		if (retrievedUserDetails != null) throw new RuntimeException("user already exists");
		//this object will be used to fill database
		UserEntity userEntity = new UserEntity();
		
		//copy the values like firstName, email that will just
		//fill entries n database
		BeanUtils.copyProperties(user, userEntity);
		
		//do something for values like userId and encryptedPassword
		//that are created from user entered data
		userEntity.setUserId(utils.generateUserId(30));
		userEntity.setEncryptedPassword(utils.generateEncryptedPassword(user.getPassword()));
		
		//get completed UserEntity object as indicator of success!!!
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		//to send values in UserEntity object back must convert over to dto...
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails,  returnValue);
		
		return returnValue;
	}
	public UserDetailsResponseModel getUser(String email) {
		UserEntity retrievedUserDetails = userRepository.findByEmail(email);
		UserDetailsResponseModel returnValue = new UserDetailsResponseModel();
		BeanUtils.copyProperties(retrievedUserDetails,  returnValue);
		return returnValue;
	}
	
	public UserLoginResponseModel loginUser(UserLoginRequestModel userLoginRequest) {
		UserLoginResponseModel userLoginResponse = new UserLoginResponseModel();
		UserDetailsResponseModel userDetails = new UserDetailsResponseModel();
		UserEntity retrievedUserDetails = userRepository.findByEmail(userLoginRequest.getEmail());
		if (retrievedUserDetails != null) {	
			if (utils.generateEncryptedPassword(userLoginRequest.getPassword()).compareTo(retrievedUserDetails.getEncryptedPassword()) == 0) {
				//fill in userDetails
				BeanUtils.copyProperties(retrievedUserDetails, userDetails);
				//fill in userLoginResponse
				userLoginResponse.setIsPasswordCorrect(true);
				userLoginResponse.setUserDetails(userDetails);
			} else {
				//send back email, but everything else null; means email good, password not.
				userDetails.setEmail(userLoginRequest.getEmail());
				userDetails.setFirstName(null);
				userDetails.setLastName(null);
				userDetails.setUserId(null);
				userLoginResponse.setIsPasswordCorrect(false);
				userLoginResponse.setUserDetails(userDetails);
			}
		} else {
			userDetails = null;
			userLoginResponse.setIsPasswordCorrect(false);
			userLoginResponse.setUserDetails(userDetails);
			//send back everything in userDetails null; means email not good
		}
		return userLoginResponse;
	}

}
