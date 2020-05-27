package com.phamquangtinh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.phamquangtinh.constant.SystemConstant;
import com.phamquangtinh.dto.MyUser;
import com.phamquangtinh.entity.RoleEntity;
import com.phamquangtinh.entity.UserEntity;
import com.phamquangtinh.repository.UserRepository;


//Nơi xử lý authentication
//custom session khi đăng nhập thành công
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	//Password đã được xử lý ngầm nên k cần parameter password
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		//Nếu không có thông tin user này
		if(userEntity == null) {
			//Exception này được viết bởi spring, và nó sẽ move tới **authentication-failure-url="/dang-nhap?incorrectAccount"** của security.xml
			throw new UsernameNotFoundException("user not found");
		}
		//Nếu có, thì put thông tin vào security, để duy trì thông tin đó khi user login vào hệ thống
		
		//Lấy danh sách các role user
		List<GrantedAuthority> authoritis = new ArrayList<>();
		//Lấy danh sách role bằng cách duyệt List<RoleEntity> trong UserEntity 
		for(RoleEntity role : userEntity.getRoles()) {
			authoritis.add(new SimpleGrantedAuthority(role.getCode()));
		}
		//Tạo User lưu thông tin tài khoản mật khẩu và roles nếu success
//		User user = new User(userEntity.getUserName(), userEntity.getPassWord(), true, true, true, true, authoritis);
		//Tạo riêng MyUser trogn DTO để lưu các thông khác ngoài username và password;
		MyUser user = new MyUser(userEntity.getUserName(), userEntity.getPassWord(), true, true, true, true, authoritis);
		user.setFullName(userEntity.getFullName());
		
		return user;
	}

}
