package com.phamquangtinh.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.phamquangtinh.dto.MyUser;

//Lấy danh sách role từ authoritis của CustomUserDetailsService
public class SecurityUtils {
	
	//Trả về MyUser từ CustomUserDetailsService
	public static MyUser getPrincipal() {
		MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }
	
	//Lấy danh sách role từ authoritis của CustomUserDetailsService
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
		List<String> results = new ArrayList<>();
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		for(GrantedAuthority authority: authorities) {
			results.add(authority.getAuthority());
		}
		
				
		return results;
	}
}
