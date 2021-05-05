package com.dlp.heroku;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnect {

	public static void main(String[] args) throws URISyntaxException {
		/*
		 * String dbUri = System.getenv("JDBC_DATABASE_URL"); 
		 * String dbURL = "jdbc:postgresql://ec2-54-216-17-9.eu-west-1.compute.amazonaws.com:5432/d1fd9aa08ke6vm";
		 * String username = "pjmxmueyokcrsz"; 
		 * String password = "2fc4629c034930453c6f2f1dde682b4c833d88c0fa77a9af0f67e485a7aebcb6";
		 * Connection conn = DriverManager.getConnection(dbURL,username,password);
		 */

		try {
			
			URI dbUri = new URI(System.getenv("DATABASE_URL"));

		    String username = dbUri.getUserInfo().split(":")[0];
		    String password = dbUri.getUserInfo().split(":")[1];
		    String dbURL = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			
			if (conn != null) {
				System.out.println("Connected to Heroku Postgres");
			}
			
			Statement stmt = conn.createStatement();
			String sql = "SELECT id, firstname, lastname, age, gender, booking_date, amount FROM BOOKING";
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
				System.out.print(", LastName: " + lastname);
				System.out.print(", Age: " + age);
				System.out.print(", Gender: " + gender);
				System.out.print(", BookingDate: " + booking_date);
				System.out.print(", Amount: " + amount);
				System.out.println();
			}
			
			/*
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
				System.out.print(", LastName: " + lastname);
				System.out.print(", Address: " + address);
				System.out.println();
			}
			*/
			
			//String insert = "INSERT INTO salesforce.testcustomobject__c(id, name, last_name__c, address__c) VALUES (26, 'test26', 'last26', 'address26')";
			//int result = stmt.executeUpdate(insert);
			
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error occurred");
			e.printStackTrace();
		}

	}

}
