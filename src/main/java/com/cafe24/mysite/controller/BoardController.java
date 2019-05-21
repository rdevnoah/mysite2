package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardListVo;
import com.cafe24.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(Model model, @RequestParam(value="nowPage", required = true, defaultValue = "1") String nowPage) {
		
		BoardListVo pageList = boardService.getPageListByPageNum(nowPage);
		model.addAttribute("pageList",pageList);
		return "board/list";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam(value="no", required = true, defaultValue = "")String no, Model model) {
		BoardVo vo = boardService.getViewByNo(no);
		model.addAttribute("board", vo);
		return "board/view";
	}
}
