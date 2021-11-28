package com.project.wwg.plan.dto;

import lombok.Data;

@Data
public class PageInfo {
    private int pageNum;
    private int div;
    private int startRow;
    private int endRow;
    private int count;
    private String keyword;

    public PageInfo() {
        this(1);
    }

    public PageInfo(int pageNum) {
        this("", pageNum);
    }

    public PageInfo(int pageNum, int searchCount) {
        this("", pageNum, searchCount);
    }

    public PageInfo(String keyword, int pageNum) {
        this(keyword, pageNum, 0);
    }

    public PageInfo(String keyword, int pageNum, int searchCount) {
        this.pageNum = pageNum;
        this.div = 8;
        this.startRow = (this.pageNum - 1) * div + 1;
        this.endRow = this.pageNum * div;
        this.keyword = keyword;
        this.count = searchCount;
    }
}
