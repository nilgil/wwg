package com.project.wwg.plan.dto;

public class Paging {
    private int page;
    private int div;

    public Paging() {
        this.page = 1;
        this.div = 10;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setDiv(int div) {
        this.div = div;
    }

    public int getPage() {
        return page;
    }

    public int getDiv() {
        return div;
    }

    public int getStartRow() {
        return (this.page - 1) * div + 1;
    }

    public int getEndRow() {
        return this.page * div;
    }

}
