package shared;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.accessingdatarest.UserEntity;

public class ApiaryDto implements Serializable {
	private static final long serialVersionUID = 6476277810944881280L;
	
	private long apiaryId;

	private String apiaryName;
	
	private String apiaryZip;
    
	//note this is the randomized hash version of userId that is publicly viewable, 
	//will use service class to find actual id and use that
	//for mapping into database later
    private String userId;
    
    //note this is the actual database verison of userId, which is used by ApiaryEntity
    private Long realUserId;

	public Long getRealUserId() {
		return realUserId;
	}

	public void setRealUserId(Long realUserId) {
		this.realUserId = realUserId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getApiaryId() {
		return apiaryId;
	}

	public void setApiaryId(String apiaryId) {
		this.apiaryId = Long.parseLong(apiaryId);
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
