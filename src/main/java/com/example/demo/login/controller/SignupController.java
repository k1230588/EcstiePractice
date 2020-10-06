package com.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.login.domain.model.SignupForm;

@Controller
public class SignupController {

	private Map<String, String> radioSex;

	// ラジオボタンの初期化メソッド
	private Map<String, String> initRadioSex() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("男性", "Male");
		radio.put("女性", "Female");
		return radio;
	}


	// ユーザー登録画面のGET用コントローラー.
	@GetMapping("/signup")
	public String getSignUp(@ModelAttribute SignupForm form, Model model) {
		radioSex = initRadioSex();
		model.addAttribute("radioSex", radioSex);
		return "signup/SignUp";
	}

	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {

		// 入力チェックに引っかかった場合、ユーザー登録画面に戻る
		if ( bindingResult.hasErrors() ) {
			// GETリクエスト用のメソッドを呼び出して、ユーザー登録画面に戻ります
			return getSignUp(form, model);
		}
		// formの中身をコンソールに出して確認します
		System.out.println(form);
		// login.htmlにリダイレクト
		return "signup/SignUp";
	}
}
