package com.project.wwg.info.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReFoodBoard {
private int food_re_no;
private int rfood_no;
private String username;
private String food_re_content;
private Date food_re_regdate;
}
