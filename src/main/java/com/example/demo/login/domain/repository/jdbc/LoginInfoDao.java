package com.example.demo.login.domain.repository.jdbc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.login.domain.model.LoginInfo;

@Repository
public class LoginInfoDao{

	@Autowired
	JdbcTemplate jdbc;

	public int loginCheck(LoginInfo li) throws DataAccessException{

		List<Map<String, Object>> list = jdbc.queryForList("SELECT * from upasslist where uacc=? and upass=?", li.getLoginid(), li.getLoginpass());
		int uid;
		if (!list.isEmpty()) {
			uid = (int)list.get(0).get("uid");
		}else {
			uid = 0;
		}
		return uid;
	}
}
