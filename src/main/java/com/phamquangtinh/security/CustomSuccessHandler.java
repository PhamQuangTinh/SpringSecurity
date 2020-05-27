package com.phamquangtinh.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.phamquangtinh.util.SecurityUtils;

//Sau khi Authentication success, sẽ đến class này để xử lý authorization

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		//determineTargetUrl: định tuyến url nào sẽ được trả về
		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(Authentication authentication) {
		String url = "";
		//Nếu role là ADMIN, thì redirect tới controller quản trị(/quan-tri/trang-chu)
		//Còn nếu role là USER, thì redirect tới controller trang chủ(/trang-chu)
		List<String> roles = SecurityUtils.getAuthorities();
		if(isAdmin(roles)) {
			url = "/quan-tri/trang-chu";
		}
		else if(isUser(roles)) {
			url = "/trang-chu";
		}
		return url;
	}
	
	
	private boolean isAdmin(List<String> roles) {
		if(roles.contains("ADMIN")) {
			return true;
		}
		return false;
	}
	
	private boolean isUser(List<String> roles) {
		if(roles.contains("USER")) {
			return true;
		}
		return false;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	
}
