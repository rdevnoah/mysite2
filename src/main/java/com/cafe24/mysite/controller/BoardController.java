package com.cafe24.mysite.controller;

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
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value= {"/search",""}, method= {RequestMethod.POST, RequestMethod.GET})
	public String list(Model model,
			@RequestParam(value = "nowPage", required = true, defaultValue = "1") String nowPage,
			@RequestParam(value = "kwd", required = true, defaultValue = "") String kwd) {
		
		BoardListVo pageList = boardService.getPageListByPageNum(nowPage, kwd);
		model.addAttribute("pageList", pageList);

		return "board/list";
	}
	
	@Auth
	@RequestMapping("/modify")
	public String modify(@RequestParam(value = "no", required = true, defaultValue = "") String no, Model model) {
	
		BoardVo vo = boardService.getViewByNo(no);
		model.addAttribute("boardVo", vo);

		return "board/modify";
	}

	@Auth
	@RequestMapping("/reply")
	public String reply(@RequestParam(value = "no", required = true, defaultValue = "") String no, Model model) {

		model.addAttribute("no", no);
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modofy(@ModelAttribute BoardVo vo) {
		
		boardService.updateBoard(vo);
		return "redirect:/board";
	}

	@Auth
	@RequestMapping("/detail")
	public String detail(@RequestParam(value = "no", required = true, defaultValue = "") String no, Model model) {
		
		BoardVo vo = boardService.getViewByNo(no);
		System.out.println(vo.getUserNo());
		model.addAttribute("board", vo);
		return "board/view";
	}

	@Auth
	@RequestMapping("/write")
	public String write() {
		
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@RequestParam(value = "authNo", required = true, defaultValue = "") String authNo,
			@RequestParam(value = "no", required = true, defaultValue = "") String no,
			@ModelAttribute BoardVo vo) {
		
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

	
	@Auth
	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "no", required = true, defaultValue = "") String no) {
		boardService.delete(Long.parseLong(no));
		return "redirect:/board";
	}
}
