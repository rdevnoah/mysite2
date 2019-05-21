package com.cafe24.mysite.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardListVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring-servlet.xml",
									"file:src/main/resources/applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void test01() {
		BoardListVo listVo = boardService.getPageListByPageNum("1");
		System.out.println(listVo);
	}
}
