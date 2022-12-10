package com.example.accessingdatarest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiaryRepository extends CrudRepository<ApiaryEntity, Long> {
	
	@Query(value = "select * from apiary where real_user_id = :realUserId", nativeQuery = true)
	List<ApiaryEntity> findByRealUserId(@Param("realUserId") Long realUserId);

	ApiaryEntity findByApiaryId(Long apiaryId);
	
	ApiaryEntity findByApiaryNameAndApiaryZip(String apiaryName, String apiaryZip);
	
	@Query(value = "select * from apiary where apiaryZip = :apiaryZip", nativeQuery = true)
	List<ApiaryEntity> findByZipCode(@Param("apiaryZip") String apiaryZip);
	
	ApiaryEntity save(ApiaryEntity apiaryEntity);
	
}
