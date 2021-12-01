package com.project.wwg.plan.dto;

import lombok.Data;

@Data
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

    public PageInfo() {
        this(1);
    }

    public PageInfo(int page) {
        this("", page);
    }

    public PageInfo(int page, int count) {
        this(10, "", page, count);
    }

    public PageInfo(String keyword, int page) {
        this(10, keyword, page, 0);
    }

    public PageInfo(int divPlans, int page, int count) {
        this(divPlans, "", page, count);
    }

    public PageInfo(int divPlans, String keyword, int page) {
        this(divPlans, keyword, page, 0);
    }

    public PageInfo(int divPlans, String keyword, int page, int count) {
        this(divPlans, 5, keyword, page, count);
    }

    public PageInfo(int divPlans, int divPages, String keyword, int page, int count) {
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
