package com.bitacademy.guestbook.vo;

public class GuestbookVo {
	private String name;
	private String password;
	private String contents;
	private int no;
	private String reg_date;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "GuestbookVo [name=" + name + ", password=" + password + ", contents=" + contents + ", no=" + no
				+ ", reg_date=" + reg_date + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	

}
