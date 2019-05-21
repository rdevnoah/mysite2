package com.cafe24.mysite.vo;

import java.util.List;

public class BoardListVo {
	private List<BoardVo> list;
	private PagingBean pb;

	public BoardListVo() {
	}

	public BoardListVo(List<BoardVo> list, PagingBean pb) {
		this.list = list;
		this.pb = pb;
	}

	public List<BoardVo> getList() {
		return list;
	}

	public void setList(List<BoardVo> list) {
		this.list = list;
	}

	public PagingBean getPb() {
		return pb;
	}

	public void setPb(PagingBean pb) {
		this.pb = pb;
	}

	@Override
	public String toString() {
		return "BoardListVo [list=" + list + ", pb=" + pb + "]";
	}

}
