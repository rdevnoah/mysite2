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

	public int getCount(String keyword) {
		return boardDao.getCount(keyword);
	}

	
	
	public BoardListVo getPageListByPageNum(String nowPage, String keyword) {
		BoardListVo listVo = new BoardListVo();
		listVo.setPb(new PagingBean(getCount(keyword), Integer.parseInt(nowPage)));
		listVo.getPb().setKeyword(keyword);
		listVo.setList(boardDao.getList(listVo.getPb()));
		return listVo;
	}
	
	public BoardVo getViewByNo(String no) {
		return boardDao.get(Long.parseLong(no));
	}

	public Boolean writeByAuthUser(BoardVo vo) {
		return boardDao.insert(vo);
		
	}

	public boolean delete(long boardNo) {
		return boardDao.delete(boardNo);
		
	}

	public int updateBoard(BoardVo vo) {
		return boardDao.update(vo);
		
	}

	public Boolean writeReply(String no, BoardVo vo) {
		System.out.println(vo);
		BoardVo parent = boardDao.get(Long.parseLong(no));
		vo.setGroupNo(parent.getGroupNo());
		vo.setOrderNo(parent.getOrderNo());
		vo.setDepth(parent.getDepth());
		System.out.println(parent);
		boardDao.updateOthers(vo);
		return boardDao.insertRe(vo);
		
	}

}
