package com.example.demo.login.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.db.controller.Dbc;
import com.example.demo.login.domain.model.LoginInfo;
import com.example.demo.login.domain.service.UserAccService;


@Controller
public class LoginController {

	@Autowired
	private UserAccService uservice;

	@GetMapping("/login")
	public String getLogin(@ModelAttribute LoginInfo li, Model model) {
		return "login/login_main";
	}


	@PostMapping("/login")
	public String postLogin(@ModelAttribute LoginInfo li, BindingResult bindingResult, Model model) {

		if ( bindingResult.hasErrors() ) {
			return getLogin(li, model);
		}
		int uid = uservice.logincheck(li);
		if (uid > 0) {
			model.addAttribute("result", "login success");
			model.addAttribute("sql", uid);
		} else {
			model.addAttribute("result", "login failed");
			model.addAttribute("sql", "null");
		}
//		Dbc dbc = new Dbc();
//		try {
//			dbc.getDBC();
//			model = dbc.getLogin(li, model);
//		} catch (SQLException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
		System.out.println(li);
		return "main/HomePage";
	}

}
