package com.cafe24.mysite.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public UserVo update(UserVo vo) {
		sqlSession.update("user.update", vo);
		return vo;
	}

	public UserVo get(Long no) {
		return sqlSession.selectOne("user.getByNo", no);
	}

	public UserVo get(UserVo userVo) throws UserDaoException {
		return sqlSession.selectOne("user.getByEmailAndPassword", userVo);
	}

	public Boolean insert(UserVo vo) {
		//System.out.println(vo);
		int count = sqlSession.insert("user.insert", vo);
		//System.out.println(vo); //mapper에 insert 후 바로 직전 primary key 값 얻어오게 했으므로 vo.no에 자동으로 그 값이 들어가 있다. 필요한 경우 리턴하자...
		
		return count==1;
	}

	public UserVo get(String email) {
		return sqlSession.selectOne("user.getByEmail", email);
	}

}
