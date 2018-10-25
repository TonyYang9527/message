package com.alpha.message.model.page;

import java.io.Serializable;

public class Page  implements Serializable{

	private static final long serialVersionUID = 8165154226240621769L;
	
    protected int pageNo = 1;
    protected int pageSize = 15;
    protected boolean needTotalRecord = false; 
    protected int totalRecord;
    protected int totalPage;

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isNeedTotalRecord() {
        return needTotalRecord;
    }

    public void setNeedTotalRecord(boolean needTotalRecord) {
        this.needTotalRecord = needTotalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public int getStart() {
        return (pageNo - 1) * pageSize;
    }

    @Override
    public String toString() {
        return "Page [pageNo=" + pageNo + ", pageSize=" + pageSize + ", needTotalRecord=" + needTotalRecord + ", totalRecord="
                + totalRecord + ", totalPage=" + totalPage + ", start=" + getStart() + "]";
    }
}
