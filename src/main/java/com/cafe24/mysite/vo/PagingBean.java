package com.cafe24.mysite.vo;

/**
 * 페이징 처리를 위한 비즈니스 계층의 클래스 PagingBean method 구현순서 getStartRowNumber()<br>
 * getEndRowNumber()<br>
 * getTotalPage()<br>
 * getTotalPageGroup()<br>
 * getNowPageGroup()<br>
 * getStartPageOfPageGroup()<br>
 * getEndPageOfPageGroup()<br>
 * isPreviousPageGroup()<br>
 * isNextPageGroup()
 * 
 * @author rdevnoah
 *
 */
public class PagingBean {
	/**
	 * 현재 페이지
	 */
	private int nowPage = 1;
	/**
	 * 페이지당 게시물수
	 */
	private int postCountPerPage = 5;
	/**
	 * 페이지 그룹당 페이지수
	 */
	private int pageCountPerPageGroup = 5;
	/**
	 * database에 저장된 총게시물수
	 */
	private int totalPostCount;
	
	/**
	 * 검색할 때 쓰는 키워드. 없으면 초기화 안해줘도 된다.
	 */
	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PagingBean() {
	}

	public PagingBean(int totalPostCount) {
		this.totalPostCount = totalPostCount;
	}

	public PagingBean(int totalPostCount, int nowPage) {
		this.totalPostCount = totalPostCount;
		this.nowPage = nowPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	/**
	 * 현재 페이지번호에 해당하는 시작 게시물의 row number를 반환 <br>
	 * 
	 * 
	 * @return
	 */
	public int getStartRowNumber() {
		return postCountPerPage * (nowPage - 1);
	}

	/**
	 * 현재 페이지에서 보여줄 게시물 행(row)의 마지막 번호<br>
	 * 
	 * @return
	 */
	public int getEndRowNumber() {
		int flag = postCountPerPage * (nowPage - 1) + postCountPerPage;
		if (flag >= totalPostCount)
			return totalPostCount-1;
		else
			return flag-1;

	}

	/**
	 * 총 페이지 수를 return한다.<br>
	 * 
	 * @return
	 */
	private int getTotalPage() {
		if (totalPostCount % postCountPerPage == 0) {
			return totalPostCount / postCountPerPage;
		} else
			return (totalPostCount / postCountPerPage) + 1;
	}

	/**
	 * 총 페이지 그룹의 수를 return한다.<br>
	 * 
	 * @return
	 */
	private int getTotalPageGroup() {
		if (totalPostCount % (postCountPerPage * pageCountPerPageGroup) == 0) {
			return totalPostCount / (postCountPerPage * pageCountPerPageGroup);
		} else
			return totalPostCount / (postCountPerPage * pageCountPerPageGroup) + 1;

	}

	/**
	 * 현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을 return 하는 메소드 <br>
	 * 
	 * @return
	 */
	private int getNowPageGroup() {
		if (nowPage % pageCountPerPageGroup == 0) {
			return nowPage / pageCountPerPageGroup;
		} else
			return nowPage / pageCountPerPageGroup + 1;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.<br>
	 * 
	 * @return
	 */
	public int getStartPageOfPageGroup() {
		int nowPageGroup = getNowPageGroup();
		return pageCountPerPageGroup * nowPageGroup - (pageCountPerPageGroup - 1);

	}

	/**
	 * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다.<br>
	 * 
	 * @return
	 */
	public int getEndPageOfPageGroup() {
		int nowPageGroup = getNowPageGroup();
		if (pageCountPerPageGroup * postCountPerPage * nowPageGroup >= totalPostCount) {
			return getTotalPage();
		} else {
			return nowPageGroup * pageCountPerPageGroup;
		}
	}

	/**
	 * 이전 페이지 그룹이 있는지 체크하는 메서드 <br>
	 * 
	 * @return
	 */
	public boolean isPreviousPageGroup() {
		boolean flag = false;
		if (getStartPageOfPageGroup() > 1) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 다음 페이지 그룹이 있는지 체크하는 메서드 <br>
	 * 
	 * @return
	 */
	public boolean isNextPageGroup() {
		boolean flag = false;
		if (getNowPageGroup() < getTotalPageGroup()) {
			flag = true;
		}
		return flag;
	}

	public static void main(String args[]) {
		PagingBean p = new PagingBean(47, 10);
		// PagingBean p = new PagingBean(1, 1);
		// 현페이지의 시작 row number 를 조회 46
		System.out.println("getStartRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 47
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());
		// 전체 페이지 수 : 10
		System.out.println("getTotalPage:" + p.getTotalPage());
		// 전체 페이지 그룹 수 : 3
		System.out.println("getTotalPageGroup:" + p.getTotalPageGroup());
		System.out.println("////////////////////////////");
		p = new PagingBean(31, 6);// 게시물수 31 현재 페이지 6
		// 현페이지의 시작 row number 를 조회 26
		System.out.println("getStartRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 30
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());

		// 게시물수 31 -> 총페이지수 7 -> 총페이지그룹->2
		// 현재 페이지 그룹 : 2
		System.out.println("getNowPageGroup:" + p.getNowPageGroup());
		// 페이지 그룹의 시작 페이지 : 5
		System.out.println("getStartPageOfPageGroup:" + p.getStartPageOfPageGroup());
		// 페이지 그룹의 마지막 페이지 : 7
		System.out.println("getEndPageOfPageGroup:" + p.getEndPageOfPageGroup());
		// 이전 페이지 그룹이 있는 지 : true
		System.out.println("isPreviousPageGroup:" + p.isPreviousPageGroup());
		// 다음 페이지 그룹이 있는 지 : false
		System.out.println("isNextPageGroup:" + p.isNextPageGroup());

	}

}
