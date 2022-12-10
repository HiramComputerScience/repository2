package com.example.accessingdatarest;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accessingdatarest.model.request.ApiaryRequestModel;
import com.example.accessingdatarest.model.request.ApiaryUpdateRequestModel;
import com.example.accessingdatarest.model.response.ApiaryResponseModel;

import shared.ApiaryDto;

@Service
public class ApiaryServiceImpl implements ApiaryService {
	@Autowired
	ApiaryRepository apiaryRepository;

	@Autowired
	UserRepository userRepository;

	@SuppressWarnings("unused")
	public ApiaryResponseModel createApiary(ApiaryRequestModel apiaryRequestModel) {
		ApiaryEntity apiaryEntity = new ApiaryEntity();
		ApiaryResponseModel returnValue = new ApiaryResponseModel();

		UserEntity userEntity = userRepository.findByUserId(apiaryRequestModel.getUserId());
		// System.out.println("real userId? " + userEntity.getId());

		ApiaryEntity checkApiary = apiaryRepository.findByApiaryNameAndApiaryZip(apiaryRequestModel.getApiaryName(),
				apiaryRequestModel.getApiaryZip());

		// apiaryEntity uses the userEntity.id, not userEntity.userId,
		// but userEntity.userId is what comes over internet.
		// Get id for db for user here

		if (userEntity != null && checkApiary == null) {
			ApiaryDto apiary = new ApiaryDto();
			BeanUtils.copyProperties(apiaryRequestModel, apiary);
			apiary.setRealUserId(userEntity.getId());

			// now can map apiaryEntity
			BeanUtils.copyProperties(apiary, apiaryEntity);

			// get completed Entity as indicatory of success, so can send Response
			ApiaryEntity storedApiary = apiaryRepository.save(apiaryEntity);
			Long apiaryId = apiaryEntity.getApiaryId();
			// System.out.println("storedApiary.getApiaryId(): " +
			// storedApiary.getApiaryId());
			BeanUtils.copyProperties(storedApiary, returnValue);
			returnValue.setApiaryId(Long.toString(apiaryId));

		} else {
			BeanUtils.copyProperties(apiaryRequestModel, returnValue);
		}
		return returnValue;
	}

	public List<ApiaryResponseModel> getApiaryByUserId(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		Long realUserId = userEntity.getId();
		List<ApiaryResponseModel> apiaryResponses = new ArrayList<ApiaryResponseModel>();

		List<ApiaryEntity> apiaries = apiaryRepository.findByRealUserId(realUserId);
		ListIterator<ApiaryEntity> apiaryIterator = apiaries.listIterator();
		while (apiaryIterator.hasNext()) {
			ApiaryEntity tempApiaryEntity = apiaryIterator.next();
			ApiaryResponseModel tempApiaryResponse = new ApiaryResponseModel();
			BeanUtils.copyProperties(tempApiaryEntity, tempApiaryResponse);
			tempApiaryResponse.setApiaryId(Long.toString(tempApiaryEntity.getApiaryId()));
			apiaryResponses.add(tempApiaryResponse);
		}

		return apiaryResponses;
	}

	public ApiaryResponseModel updateApiary(ApiaryUpdateRequestModel updatedApiary) {
		ApiaryResponseModel apiaryResponseModel = new ApiaryResponseModel();
		ApiaryDto apiaryDto = new ApiaryDto();
		BeanUtils.copyProperties(updatedApiary, apiaryDto);

		// make sure apiary exists already
		ApiaryEntity existingApiaryEntity = apiaryRepository.findByApiaryId(apiaryDto.getApiaryId());
		// make sure UserEntity exists
		UserEntity userEntity = userRepository.findByUserId(updatedApiary.getUserId());

		// if true, move on.
		if (existingApiaryEntity != null && userEntity != null) {

			Long realUserId = userEntity.getId();
			apiaryDto.setRealUserId(realUserId);

			ApiaryEntity apiaryEntity = new ApiaryEntity();
			BeanUtils.copyProperties(apiaryDto, apiaryEntity);

			ApiaryEntity returnApiaryEntity = apiaryRepository.save(apiaryEntity);

			BeanUtils.copyProperties(returnApiaryEntity, apiaryResponseModel);
			apiaryResponseModel.setApiaryId(Long.toString(returnApiaryEntity.getApiaryId()));
			
		}

		return apiaryResponseModel;
	}
}
