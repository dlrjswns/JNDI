package model;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JNDI {

	public static Connection getConnection() {
		Connection conn=null;
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext=(Context)initContext.lookup("java:comp/env");
			DataSource ds=(DataSource)envContext.lookup("jdbc/orcl"); //위 코드와 함께 같이 쓰지않고 이런식으로 나눠쓴 이유는 유지보수를 위해서이다
			conn=ds.getConnection();
		} catch (Exception e) {
			System.out.println("JNDI클래스에서 예외발생!");
			e.printStackTrace();
		}
		return conn;
	}
	
}
