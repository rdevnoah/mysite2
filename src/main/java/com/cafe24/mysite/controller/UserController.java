package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@Valid @ModelAttribute UserVo vo, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error);
			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, @AuthUser UserVo authUser, Model model) {

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "/user/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, @ModelAttribute UserVo vo) {

		if (session == null) {
			return "/";
		}
		
		if (vo == null) {
			return "/user/login"; // 보내고 나면 무조건 코드를 끝을 내줘야 한다. 아니면 밑으로 내려가서 밑의 코드가 수행된다.
		}
		
		UserVo updatedUser = userService.updateUserInfo(vo);
		session.setAttribute("authUser", updatedUser);

		return "redirect:/user/updatesuccess";

	}

	@RequestMapping("/updatesuccess")
	public String updateSuccess() {
		return "user/updatesuccess";
	}
	
	


}
