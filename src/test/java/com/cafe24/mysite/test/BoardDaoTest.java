package com.cafe24.mysite.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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
		assertThat(boardDao.getCount(""),is(0));
		BoardVo vo = new BoardVo();
		vo.setTitle("저녁?");
		vo.setContents("오늘 저녁 뭐 드실래요?");
		vo.setHit(1);
		vo.setGroupNo(1);
		vo.setOrderNo(1);
		vo.setDepth(0);
		vo.setUserNo(2L);
		assertThat(boardDao.insert(vo),is(true));
		assertThat(boardDao.getCount(""),is(1));

	}
	
	@Test // DAO : delete one board test
	public void test02() {
		boardDao.deleteAll();
		assertThat(boardDao.getCount(""),is(0));
		BoardVo vo1 = new BoardVo("1", "1234", 2L);
		
		assertThat(boardDao.insert(vo1), is(true));
		int count = boardDao.getCount("");
		assertThat(count, is(1));
		long no = boardDao.getNew();
		assertThat(boardDao.delete(no), is(true));
		count = boardDao.getCount("");
		assertThat(count, is(0));
		//test02();
	}

	@Test // DAO : getPageListTest
	public void test03() {
		boardDao.deleteAll();
		assertThat(boardDao.getCount(""),is(0));
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
		int count = boardDao.getCount("");
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
	
	
	@Test // DAO : update test
	public void test04() {
		long no = boardDao.getNew();
		BoardVo updateVo = new BoardVo();
		String title = "으히히히";
		String contents = "으헤헤헤";
		updateVo.setTitle(title);
		updateVo.setContents(contents);
		updateVo.setNo(no);
		boardDao.update(updateVo);
		assertThat(boardDao.get(no).getTitle(), is(updateVo.getTitle()));
	}
	
	//@Test // DAO : insert reply test
	public void test05() {
		
		// title, contents, 부모groupNo, 부모orderNo, 자기userNo
		BoardVo vo1 = new BoardVo("답글1", "답글입니다.", 2, 1, 3L);
		BoardVo vo2 = new BoardVo("답글2", "답글입니다.", 2, 2, 3L);
		BoardVo vo3 = new BoardVo("답글3", "답글입니다.", 2, 3, 3L);
		BoardVo vo4 = new BoardVo("답글4", "답글입니다.", 2, 4, 3L);
		BoardVo vo5 = new BoardVo("답글5", "답글입니다.", 2, 5, 3L);
		BoardVo vo6 = new BoardVo("답글6", "답글입니다.", 2, 6, 3L);
		BoardVo vo7 = new BoardVo("답글7", "답글입니다.", 2, 7, 3L);
		BoardVo vo8 = new BoardVo("답글8", "답글입니다.", 2, 3, 3L);
		BoardVo vo9 = new BoardVo("답글9", "답글입니다.", 2, 3, 3L);
		BoardVo vo10 = new BoardVo("답글10", "답글입니다.", 2, 3, 3L);
		BoardVo vo11 = new BoardVo("답글11", "답글입니다.", 2, 4, 3L);
		BoardVo vo12 = new BoardVo("답글12", "답글입니다.", 2, 4, 3L);
		BoardVo vo13 = new BoardVo("답글13", "답글입니다.", 2, 5, 3L);
		
		boardDao.updateOthers(vo1);
		boardDao.insertRe(vo1);
		boardDao.updateOthers(vo2);
		boardDao.insertRe(vo2);
		boardDao.updateOthers(vo3);
		boardDao.insertRe(vo3);
		boardDao.updateOthers(vo4);
		boardDao.insertRe(vo4);
		boardDao.updateOthers(vo5);
		boardDao.insertRe(vo5);
		boardDao.updateOthers(vo6);
		boardDao.insertRe(vo6);
		boardDao.updateOthers(vo7);
		boardDao.insertRe(vo7);
		boardDao.updateOthers(vo8);
		boardDao.insertRe(vo8);
		boardDao.updateOthers(vo9);
		boardDao.insertRe(vo9);
		boardDao.updateOthers(vo10);
		boardDao.insertRe(vo10);
		boardDao.updateOthers(vo11);
		boardDao.insertRe(vo11);
		boardDao.updateOthers(vo12);
		boardDao.insertRe(vo12);
		boardDao.updateOthers(vo13);
		boardDao.insertRe(vo13);
		assertThat(boardDao.getCount(""), is(18));
		
		
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
