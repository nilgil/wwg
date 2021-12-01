package com.project.wwg.plan.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class PlanReply {
    private int re_no;
    private int plan_no;
    private String username;
    private String content;
    private Date regdate;
}
