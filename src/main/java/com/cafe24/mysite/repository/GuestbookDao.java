package com.cafe24.mysite.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;


@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public Boolean insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo) == 1;
	}

	
	public Boolean delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo)==1;
	}

	
	public List<GuestbookVo> getList() {
		return sqlSession.selectList("guestbook.list");
	}
	
	
	
}
