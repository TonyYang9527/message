package com.alpha.message.model.page;

import java.io.Serializable;
import java.util.List;

public class PageResult <T>implements Serializable{

	private static final long serialVersionUID = -4452337586380958083L;

	  protected Page page;

	    protected List<T> result;

	    public Page getPage() {
	        return page;
	    }

	    public void setPage(Page page) {
	        this.page = page;
	    }

	    public List<T> getResult() {
	        return result;
	    }

	    public void setResult(List<T> result) {
	        this.result = result;
	    }

	    @Override
	    public String toString() {
	        return "PageResult [page=" + page + ", result=" + result + "]";
	    }
}
