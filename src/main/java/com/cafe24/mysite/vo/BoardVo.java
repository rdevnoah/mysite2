package com.cafe24.mysite.vo;

public class BoardVo {

	private Long no;
	private String title;
	private String contents;
	private int hit;
	private int groupNo;
	private int orderNo;
	private int depth;
	private Long userNo;
	private String regDate;
	private String name; // user name
	
	public BoardVo() {}
	
	public BoardVo(String title, String contents, int groupNo, int orderNo, Long userNo ) {
		this.title = title;
		this.contents = contents;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.userNo = userNo;
	}
	
	
	public BoardVo(String title, String contents, Long userNo) {
		this.title = title;
		this.contents = contents;
		this.userNo = userNo;
	}



	public BoardVo(String title, String contents, int hit, int groupNo, 
			int orderNo, int depth, Long userNo) {
		this.title = title;
		this.contents = contents;
		this.hit = hit;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.userNo = userNo;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", hit=" + hit + ", groupNo="
				+ groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userNo=" + userNo + ", regDate=" + regDate
				+ ", name=" + name + "]";
	}

		

}
