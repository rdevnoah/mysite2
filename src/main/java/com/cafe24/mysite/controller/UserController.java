package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo vo) {
		// userDao.insert(vo);
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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "password", required = true, defaultValue = "") String password, HttpSession session,
			Model model) {

		UserVo authUser = userService.getUser(new UserVo(email, password));
		// UserVo authUser = userDao.get(email, password);

		if (authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		// session 처리

		return "redirect:/";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if (authUser == null || session == null) {
			return "user/login";
		}
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		if (session == null) {
			return "redirect:/";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			return "redirect:/";
		}

		UserVo userVo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		return "/user/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpSession session, @ModelAttribute UserVo vo, Model model) {

		if (session == null) {
			return "/";
		}
		
		if (vo == null) {
			model.addAttribute("result", "fail");
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
