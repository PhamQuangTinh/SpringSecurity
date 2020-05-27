package com.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamquangtinh.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	//name method được quy định bởi spring
	//findOne(): khi trả về dữ liệu duy nhất
	UserEntity findOneByUserNameAndStatus(String name, int status);

}
