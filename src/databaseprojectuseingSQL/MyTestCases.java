package databaseprojectuseingSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	WebDriver driver = new ChromeDriver();
	Connection con;
	Statement stmt;
	ResultSet rs;
	
@BeforeTest
public void mysetup() throws SQLException {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","mosab331994J");
}
@Test(priority = 1)
public void addDataToDB() throws SQLException {
String query="INSERT INTO customers (customerNumber,customerName, contactLastName, contactFirstName, phone, addressLine1, city, country) \r\n"
		+ "VALUES (3394, 'Aya', 'Doe', 'John', '+1-555-1234567', '123 Tech Ave', 'Silicon Valley', 'USA');\r\n"
		+ "";

stmt=con.createStatement();
int dataRes=stmt.executeUpdate(query);
System.out.println(dataRes);

}


@Test (priority = 2)
public void updateData() throws SQLException {
	
	
	String query = "update customers set contactFirstName='halaa' where customerNumber=3394";
	
	stmt=con.createStatement();
	int rowEffected = stmt.executeUpdate(query);
	
	System.out.println(rowEffected);
	
}

@Test(priority = 3)
public void readData() throws SQLException {
	String query = "select * from customers where customerNumber=3394";
	stmt=con.createStatement();
	rs=stmt.executeQuery(query);
	
	while(rs.next()) {
		String customerFirstNameInDataBase =rs.getString("contactFirstName");
		String customerNumberInDataBase =rs.getString("customerNumber");
		
		System.out.println(customerFirstNameInDataBase);
		System.out.println(customerNumberInDataBase);
	}
}

@Test (priority = 4)
public void deleteData() throws SQLException {
String query = "delete from customers where customerNumber=3394";
	
	stmt=con.createStatement();
	int rowEffected = stmt.executeUpdate(query);
	
	System.out.println(rowEffected);
}

}
