package com.phamquangtinh.config;

import javax.persistence.EntityListeners;

import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

//Config để sử dụng @EntityListeners trong BaseEntity
//Lấy thông tin người dùng từ session
//@EnableJpaAuditing: bật tính năng auditing
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditingConfig {
	
	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAwareImpl();
		
	}
	
	//Lấy thông tin
	public static class AuditorAwareImpl implements AuditorAware<String>{

		@Override
		public String getCurrentAuditor() {
			Authentication authen = SecurityContextHolder.getContext().getAuthentication();
			if(authen == null) {
				return null;
			}
			return authen.getName();
		}
		
	}

}
