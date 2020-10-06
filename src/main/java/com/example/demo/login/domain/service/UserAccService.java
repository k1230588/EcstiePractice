package com.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.login.domain.model.LoginInfo;
import com.example.demo.login.domain.repository.jdbc.LoginInfoDao;

@Service
public class UserAccService {

	@Autowired
	LoginInfoDao dao;

	//Login Check
	public int logincheck(LoginInfo li) {
		int uid = dao.loginCheck(li);
		return uid;
	}

}
