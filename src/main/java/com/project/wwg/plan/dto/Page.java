package com.project.wwg.plan.dto;

public class Page {
    private int pageNum;
    private int div;
    private int startRow;
    private int endRow;
    private int count;
    private String keyword;


    public Page() {
        this("", 1);
    }

    public Page(int pageNum) {
        this("", pageNum);
    }

    public Page(String keyword, int pageNum) {
        this(keyword, pageNum, 0);
    }

    public Page(String keyword, int pageNum, int count) {
        this.pageNum = pageNum;
        this.div = 10;
        this.startRow = (this.pageNum - 1) * div + 1;
        this.endRow = this.pageNum * div;
        this.keyword = keyword;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getDiv() {
        return div;
    }

    public void setDiv(int div) {
        this.div = div;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
