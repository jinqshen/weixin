package com.jinqshen.weixin.vo;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * 分页
 * @author jinqshen
 *
 * @param <T>
 */
import java.util.List;

import com.jinqshen.weixin.pojo.Information;
public class PageBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4598970543476322954L;
	//当前页
	private int currentPage;
	//总页数
	private int totalPage;
	//每页条数
	private int pageSize = 8;
	//总条数
	private int totalSize;
	//查询页面条数
	private List<T> informations = new ArrayList<>();
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	public List<T> getInformations() {
		return informations;
	}
	
	public void setInformations(List<T> informations) {
		this.informations = informations;
	}
	

}
