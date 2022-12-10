package com.example.accessingdatarest;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	
	@Query(value="select * from users where user_id = :userId", nativeQuery=true)
	UserEntity findByUserId(@RequestParam("userId") String userId);
	
	Optional<UserEntity> findById(Long id);
	
	//note that simple methods like insert/create, select/find/read, update/put, 
	//and delete are provided by JPA when the appropriate methods are used
	//to call the Repository
}
