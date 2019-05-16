package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public boolean join(UserVo vo) {
		boolean result=false;
		userDao.insert(vo);
		return result;
	}

	public UserVo getUser(UserVo vo) {
		return userDao.get(vo);
	}
	
	public UserVo getUser(Long userNo) {
		return userDao.get(userNo);
	}

	public UserVo updateUser(UserVo vo) {
		return userDao.update(vo);
	}
}
