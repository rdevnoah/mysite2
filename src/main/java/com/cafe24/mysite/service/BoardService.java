package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardListVo;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PagingBean;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public int getCount() {
		return boardDao.getCount();
	}

	public BoardListVo getPageListByPageNum(String nowPage) {
		BoardListVo listVo = new BoardListVo();
		listVo.setPb(new PagingBean(getCount(), Integer.parseInt(nowPage)));
		listVo.setList(boardDao.getList(listVo.getPb()));
		return listVo;
	}

	public BoardVo getViewByNo(String no) {
		return boardDao.get(Long.parseLong(no));
	}

	public Boolean writeByAuthUser(BoardVo vo) {
		return boardDao.insert(vo);
		
	}

}
