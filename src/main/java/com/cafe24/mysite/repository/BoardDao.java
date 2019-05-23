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
	

	public Boolean delete(Long no) {
		return sqlSession.delete("board.delete", no)==1;
	}
	
	public Boolean deleteAll() {
		return sqlSession.delete("board.deleteAll") == 1;
	}
	
	public int getCount(String keyword) {
		return sqlSession.selectOne("board.count", keyword);
	}
	
	public List<BoardVo> getList(PagingBean pb){
		return sqlSession.selectList("board.pageList", pb);
	}

	public BoardVo get(Long no) {
		return sqlSession.selectOne("board.getDetail", no);
	}
	
	public long getNew() {
		return sqlSession.selectOne("board.getNew");
	}

	public int update(BoardVo vo) {
		return sqlSession.update("board.update", vo);
	}

	public int updateOthers(BoardVo vo) {
		return sqlSession.update("board.updateOthers", vo);
		
	}

	public boolean insertRe(BoardVo vo) {
		return sqlSession.insert("insertRe", vo) == 1;
	}
}
