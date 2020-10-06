package com.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank
	private String loginid;
	private String loginpass;
	private String uname;
	private String usex;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ubirth;
	private String uemail;
	private int upost;
	private String uaddress;
	private long ucredit;


}
