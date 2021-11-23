package com.project.wwg.plan.dto;

import lombok.Data;

@Data
public class Page {
    private int pageNum;
    private int div;
    private int startRow;
    private int endRow;
    private int count;
    private String keyword;

    public Page() {
        this(1);
    }

    public Page(int pageNum) {
        this("", pageNum);
    }

    public Page(String keyword, int pageNum) {
        this(keyword, pageNum, 0);
    }

    public Page(String keyword, int pageNum, int searchCount) {
        this.pageNum = pageNum;
        this.div = 8;
        this.startRow = (this.pageNum - 1) * div + 1;
        this.endRow = this.pageNum * div;
        this.keyword = keyword;
        this.count = searchCount;
    }
}
