package com.vkl.ckts.rgsy.record.entity;

import java.util.List;

/**
 * 树实体
 *
 * @author hwf
 * @version 1.0
 */
public class Tree {
	private int id;
	private String text;
	private List<Tree> children;
	private String checked;
	private String state; 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
