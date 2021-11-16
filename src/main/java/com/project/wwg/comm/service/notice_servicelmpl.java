package com.project.wwg.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.wwg.comm.dao.notice_dao;

@Service
public class notice_servicelmpl implements notice_service {
	@Autowired
	private notice_dao nd;

}