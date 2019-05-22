package com.cafe24.mysite.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.PagingBean;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<BoardVo> getList() {
		return sqlSession.selectList("board.list");
	}
	
	public Boolean insert(BoardVo vo) {
		return sqlSession.insert("board.insert", vo) == 1;
	}
	

	public Boolean delete(BoardVo vo) {
		return sqlSession.delete("board.delete", vo)==1;
	}
	
	public Boolean deleteAll() {
		return sqlSession.delete("board.deleteAll") == 1;
	}
	
	public int getCount() {
		return sqlSession.selectOne("board.count");
	}
	
	public List<BoardVo> getList(PagingBean pb){
		return sqlSession.selectList("board.pageList", pb);
	}

	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getDetail", no);
	}
}
