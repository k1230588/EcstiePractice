package com.example.demo.db.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.login.domain.model.LoginInfo;

@Controller
public class Dbc {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rset = null;
	String DSN = "jdbc:mysql://localhost:3306/ecsite1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=JST";
	String USER = "root";
	String PASSWORD = "";

	//SQL Database接続
	public Statement getDBC() throws SQLException{
		if (conn == null) {
                conn = DriverManager.getConnection(DSN, USER, PASSWORD);
                //自動コミットOFF
                conn.setAutoCommit(false);
                stmt = conn.createStatement();
        }
        return stmt;
	}
	//SQL　Database接続を切る
    public void closeDBC() throws SQLException {
        if (conn != null) {
            stmt.close();
            conn.close();
        }
    }

    public Model getLogin(@ModelAttribute LoginInfo li, Model model) throws SQLException {
    	String psql = "select * from upasslist where uacc=? and upass=?";
//    	String sqlc = "select count(*) from upasslist where uacc=? and upass=?";

    	PreparedStatement pstmt = conn.prepareStatement(psql);
    	pstmt.setString(1, li.getLoginid());
    	pstmt.setString(2, li.getLoginpass());
    	System.out.println(pstmt.toString());
    	rset = pstmt.executeQuery();

    	if (rset != null) {
    		if (rset.next()) {
    			model.addAttribute("result", "login success");
    			model.addAttribute("sql", rset.getString("uid"));
    		} else {
    			model.addAttribute("result", "login failed");
    			model.addAttribute("sql", "null");
    		}
    	}


    	return model;
    }
}
