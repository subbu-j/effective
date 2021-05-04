package com.dlp.heroku;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnect {

	public static void main(String[] args) {
		/*
		 * String dbUri = System.getenv("JDBC_DATABASE_URL"); 
		 * String dbURL = "jdbc:postgresql://ec2-54-74-156-137.eu-west-1.compute.amazonaws.com:5432/d7iqsm73upk6s5";
		 * String username = "zlloqwdfjecgxu"; 
		 * String password = "fdc280399a51d7d96299a2a4b2ff5bb89a7681b18885c283b16a3e281fdcbab4";
		 * Connection conn = DriverManager.getConnection(dbURL,username,password);
		 */

		try {
			String dbURL = "jdbc:postgresql://ec2-54-74-156-137.eu-west-1.compute.amazonaws.com:5432/d7iqsm73upk6s5?sslmode=require&user=zlloqwdfjecgxu&password=fdc280399a51d7d96299a2a4b2ff5bb89a7681b18885c283b16a3e281fdcbab4";

			Connection conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connected to Heroku Postgres");
			}
			
			Statement stmt = conn.createStatement();
			String sql = "select id, firstname, lastname, age, gender, booking_date, amount from booking";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				float amount = rs.getFloat("amount");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				String booking_date = rs.getString("booking_date");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", FirstName: " + firstname);
				System.out.println(", LastName: " + lastname);
				System.out.print(", Age: " + age);
				System.out.print(", Gender: " + gender);
				System.out.print(", BookingDate: " + booking_date);
				System.out.print(", Amount: " + amount);
				System.out.println();
			}
			//rs.close();
			String salesforce = "select id, name, last_name__c, address__c from salesforce.testcustomobject__c";
			rs = stmt.executeQuery(salesforce);
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String lastname = rs.getString("last_name__c");
				String address = rs.getString("address__c");

				// Display values
				System.out.print("ID: " + id);
				System.out.print(", Name: " + name);
				System.out.println(", LastName: " + lastname);
				System.out.print(", Address: " + address);
				System.out.println();
			}
			
			
			//String insert = "INSERT INTO salesforce.testcustomobject__c(id, name, last_name__c, address__c) VALUES (26, 'test26', 'last26', 'address26')";
			//int result = stmt.executeUpdate(insert);
			
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error in connecting to Postgres");
			e.printStackTrace();
		}

	}

}
