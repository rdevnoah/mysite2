package com.cafe24.mysite.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {

	@Autowired
	private BoardDao boardDao;

	// @Test // DAO : add and getlist Test
	public void test01() {
		System.out.println("success1");

		BoardVo vo = new BoardVo();
		vo.setTitle("저녁?");
		vo.setContents("오늘 저녁 뭐 드실래요?");
		vo.setHit(1);
		vo.setGroupNo(1);
		vo.setOrderNo(1);
		vo.setDepth(0);
		vo.setUserNo(2L);
		if (boardDao.insert(vo) == false) {
			System.out.println("insert fail");
			return;
		}

		List<BoardVo> list = boardDao.getList();
		for (BoardVo getVo : list) {
			System.out.println(getVo);
		}
	}

	// @Test // DAO : delete test
	public void test02() {
		BoardVo vo = new BoardVo();
		vo.setNo(2L);
		assertTrue(boardDao.delete(vo));

		List<BoardVo> list = boardDao.getList();
		for (BoardVo getVo : list) {
			System.out.println(getVo);
		}
	}

	@Test// getCount and set Pagingbean
	public void test03() {
		/*
		 * int count = boardDao.getCount(); PagingBean pb = new PagingBean(count, 1);
		 * System.out.println("sssssssssss" + pb.getStartRowNumber());
		 * System.out.println("eeeeeeeeeee" + pb.getEndRowNumber()); List<BoardVo> list
		 * = boardDao.getList(pb); // System.out.println("!#!@#!@#!@# "+list.size());
		 * for (BoardVo vo : list) { System.out.println(vo); }
		 */
		assertThat(boardDao.getCount(), is(7));
	}
	
	

}
