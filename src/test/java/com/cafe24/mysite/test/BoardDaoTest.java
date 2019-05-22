package com.cafe24.mysite.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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
import com.cafe24.mysite.vo.PagingBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/applicationContext.xml" }) //root wac에 저장된 bean들 사용
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {

	@Autowired
	private BoardDao boardDao;

	@Test // DAO : 단순 add test
	public void test01() {
		boardDao.deleteAll();
		assertThat(boardDao.getCount(),is(0));
		BoardVo vo = new BoardVo();
		vo.setTitle("저녁?");
		vo.setContents("오늘 저녁 뭐 드실래요?");
		vo.setHit(1);
		vo.setGroupNo(1);
		vo.setOrderNo(1);
		vo.setDepth(0);
		vo.setUserNo(2L);
		assertThat(boardDao.insert(vo),is(true));
		assertThat(boardDao.getCount(),is(1));

	}

	@Test // DAO : getPageListTest
	public void test02() {
		assertThat(boardDao.deleteAll(), is(true));
		assertThat(boardDao.getCount(),is(0));
		BoardVo vo1 = new BoardVo("1", "1234", 2L);
		BoardVo vo2 = new BoardVo("2", "1234", 2L);
		BoardVo vo3 = new BoardVo("3", "1234", 2L);
		BoardVo vo4 = new BoardVo("4", "1234", 2L);
		BoardVo vo5 = new BoardVo("5", "1234", 2L);
		
		assertThat(boardDao.insert(vo1), is(true));
		assertThat(boardDao.insert(vo2), is(true));
		assertThat(boardDao.insert(vo3), is(true));
		assertThat(boardDao.insert(vo4), is(true));
		assertThat(boardDao.insert(vo5), is(true));
		int count = boardDao.getCount();
		assertThat(count, is(5));
		
		PagingBean pb = new PagingBean(count, 1);
		List<BoardVo> list = boardDao.getList(pb);
		
		assertThat(count, is(list.size()));
		
		assertThat(checkContain(list, vo1), is(true));
		assertThat(checkContain(list, vo2), is(true));
		assertThat(checkContain(list, vo3), is(true));
		assertThat(checkContain(list, vo4), is(true));
		assertThat(checkContain(list, vo5), is(true));
		assertThat(checkContain(list, new BoardVo()), is(not(true)));
	}

	
	
	public boolean checkContain(List<BoardVo> dbList, BoardVo vo) {
		for (BoardVo db : dbList) {
			if (db.getTitle().equals(vo.getTitle())){
				return true;
			}
		}
		return false;
	}
	

}
