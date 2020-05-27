package com.phamquangtinh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phamquangtinh.entity.NewsEntity;


//Được sử dụng để giao tiếp với database(tương tự như tầng DAO của jsp servlet)
//Long kiểu dữ liệu của khóa chính của NewsEntity	
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
}
