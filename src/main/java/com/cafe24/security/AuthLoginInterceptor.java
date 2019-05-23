package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		// 생성되어 있는 컨텍스트를 가져와야 한다. 그래야 UserService를 쓸 수 있다. 아니면 @Autowired 해서 주입받아 쓰던가S
		//ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); // 
		
		//UserService userService = ac.getBean(UserService.class);
		
		UserVo vo = new UserVo(email, password);
		UserVo authUser = userService.getUser(vo);
		
		if (authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//세션 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
	
}
