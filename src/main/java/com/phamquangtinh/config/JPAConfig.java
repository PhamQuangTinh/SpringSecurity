package com.phamquangtinh.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//Đây là 1 java config để cấu hình Spring(tương tự như cái file XML trong webapp)
//@EnableTransactionManagement annotation này được gọi để sử dụng transaction
//@EnableJpaRepositories : được gọi để sử dụng repository
@Configuration	
@EnableJpaRepositories(basePackages = {"com.phamquangtinh.repository"})
@EnableTransactionManagement
public class JPAConfig {
	
	//Khai báo Entity manager factory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		//Để open connection với database(phải cung cấp drivername, url, user, password)	
		em.setDataSource(dataSource());
		//persistence-data: là cầu nối để giao tiếp giữa entity và các table trong database, được khai báo trong /resources/META-INF/persistence.xml
		em.setPersistenceUnitName("persistence-data");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		//Generate table dựa trên các entity được định nghĩa(không còn việc những câu lênh dài dòng như bên JDBC nữa)
		em.setJpaProperties(additionalProperties());
		return em;
	}
	
	
	//Tụ động quản lý transaction 
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	
	//open connection
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/laptrinhspringmvc");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
	}
	
	
	Properties additionalProperties() {
		Properties properties = new Properties();
		
		//Chỉ được gọi 1 trong 2 cái này
		//Được gọi tại thời điểm đầu tiên chưa có database( "create-drop" tạo database từ java class ENTITY)
//		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//		properties.setProperty("hibernate.hbm2ddl.auto", "create");
//		Được gọi khi đã có database và có dữ liệu trong database
		properties.setProperty("hibernate.hbm2ddl.auto", "none");
		
		//Khai báo để sử dụng LAZY load of @ManytoMany @OnetoMany
		properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		return properties;
	}
	
	
	
	
	

}
