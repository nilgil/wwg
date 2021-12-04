package com.project.wwg.plan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageInfo {
    private int page;
    private int divPlans;
    private int divPages;
    private int startRow;
    private int endRow;
    private int count;
    private String keyword;
    private int startPage;
    private int endPage;
    private int totalPage;

    public PageInfo(int divPlans, int page, int count) {
        this(divPlans, page, count, "");
    }

    public PageInfo(int divPlans, int page, String keyword) {
        this(divPlans, page, 0, keyword);
    }

    public PageInfo(int divPlans, int page, int count, String keyword) {
        this(divPlans, page, count, keyword, 5);
    }

    public PageInfo(int divPlans, int page, int count, String keyword, int divPages) {
        this.page = page;
        this.divPlans = divPlans;
        this.divPages = divPages;

        this.startRow = (page - 1) * divPlans + 1;
        this.endRow = page * divPlans;

        this.keyword = keyword;
        this.count = count;

        this.totalPage = (int) Math.ceil((double) count / divPlans);
        this.startPage = page - (page - 1) % divPages;
        this.endPage = startPage + divPages - 1;

        if (endPage > totalPage)
            endPage = totalPage;
    }
}
