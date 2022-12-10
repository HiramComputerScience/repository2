package com.example.accessingdatarest;

import java.util.List;

import com.example.accessingdatarest.model.request.ApiaryRequestModel;
import com.example.accessingdatarest.model.request.ApiaryUpdateRequestModel;
import com.example.accessingdatarest.model.response.ApiaryResponseModel;

import shared.ApiaryDto;

public interface ApiaryService {

	ApiaryResponseModel createApiary(ApiaryRequestModel apiary);
	List<ApiaryResponseModel> getApiaryByUserId(String userId);
	ApiaryResponseModel updateApiary(ApiaryUpdateRequestModel updatedApiary);
}
