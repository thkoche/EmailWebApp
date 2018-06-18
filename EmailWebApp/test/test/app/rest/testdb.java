package test.app.rest;

import java.sql.SQLException;

import com.google.gson.Gson;

import app.database.DBConn;

public class testdb {

	public static void main(String[] args) {
		DBConn conn = new DBConn();
		Gson gson = new Gson();
		try {
			System.out.println(conn.getEmails("Koche").get(0).getTitle());
			
			System.out.println(gson.toJson(conn.getEmails("Koche")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
