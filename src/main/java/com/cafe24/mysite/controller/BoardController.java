package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardListVo;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String list(Model model,
			@RequestParam(value = "nowPage", required = true, defaultValue = "1") String nowPage) {

		System.out.println("nowPage : " + nowPage);
		BoardListVo pageList = boardService.getPageListByPageNum(nowPage);
		model.addAttribute("pageList", pageList);

		return "board/list";
	}

	@RequestMapping("/modify")
	public String modify(@RequestParam(value = "no", required = true, defaultValue = "") String no, HttpSession session,
			Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null || session == null) {
			return "redirect:/user/login";
		}
		if ("".equals(no)) {
			return "redirect:/user/login";
		}
		BoardVo vo = boardService.getViewByNo(no);
		model.addAttribute("boardVo", vo);

		return "board/modify";
	}

	@RequestMapping("/reply")
	public String reply(@RequestParam(value = "no", required = true, defaultValue = "") String no, Model model,
			HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null || session == null) {
			return "redirect:/user/login";
		}
		model.addAttribute("no", no);
		return "board/write";
	}

//	@RequestMapping(value = "/reply", method = RequestMethod.POST)
//	public String reply(String no, HttpSession session, @ModelAttribute BoardVo vo) {
//		System.out.println("----------" + no + "---------" + vo);
//		return "redirect:/board";
//	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modofy(@ModelAttribute BoardVo vo) {

		boardService.updateBoard(vo);
		return "redirect:/board";
	}

	@RequestMapping("/detail")
	public String detail(@RequestParam(value = "no", required = true, defaultValue = "") String no, Model model) {
		BoardVo vo = boardService.getViewByNo(no);
		System.out.println(vo.getUserNo());
		model.addAttribute("board", vo);
		return "board/view";
	}

	@Auth
	@RequestMapping("/write")
	public String write(HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null || session == null) {
			return "redirect:/user/login";
		}

		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@RequestParam(value = "authNo", required = true, defaultValue = "") String authNo,
			@RequestParam(value = "no", required = true, defaultValue = "") String no,
			@ModelAttribute BoardVo vo, HttpSession session) {
		if ("".equals(authNo)) {
			return "user/login";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null || session == null) {
			return "redirect:/user/login";
		}
		if ("".equals(no)) {
			vo.setUserNo(Long.parseLong(authNo));
			boardService.writeByAuthUser(vo);
		} else {
			System.out.println("----------" + no + "---------" + vo);
			vo.setUserNo(Long.parseLong(authNo));
			boardService.writeReply(no, vo);
		}
		return "redirect:/board";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "") String no,
			HttpSession session) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		System.out.println(no);
		if (authUser == null || session == null) {
			return "redirect:/user/login";
		}
		if ("".equals(no)) {
			return "user/login";
		}
		boardService.delete(Long.parseLong(no));

		return "redirect:/board";
	}
}
