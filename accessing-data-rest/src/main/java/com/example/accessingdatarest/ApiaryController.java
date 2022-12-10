package com.example.accessingdatarest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatarest.model.request.ApiaryRequestModel;
import com.example.accessingdatarest.model.request.ApiaryUpdateRequestModel;
import com.example.accessingdatarest.model.response.ApiaryResponseModel;

import shared.ApiaryDto;

@RestController
@RequestMapping("/apiary") //http:localhost:8085/apiary
public class ApiaryController {
	@Autowired
	ApiaryService apiaryService;
	
	@GetMapping
	public List<ApiaryResponseModel> getApiaryByUserId(@RequestParam String userId) {
		List<ApiaryResponseModel> apiaryResponses = apiaryService.getApiaryByUserId(userId);
		//System.out.println("apiaryResponses.size() " + apiaryResponses.size());
		return apiaryResponses;
	}
	
	@PostMapping
	public ApiaryResponseModel createApiary(@RequestBody ApiaryRequestModel incomingApiary) {
		return apiaryService.createApiary(incomingApiary);
	}
	
	@PutMapping
	public ApiaryResponseModel updateApiary(@RequestBody ApiaryUpdateRequestModel updatedApiary) {
		return apiaryService.updateApiary(updatedApiary);
	}
}
