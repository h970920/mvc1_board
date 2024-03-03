package common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {

	private static JdbcUtil instance = new JdbcUtil();
	
	private static DataSource ds;
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 로딩 성공");
			InitialContext ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/oracle");
			System.out.println("Connection Pool 생성!!");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//싱글톤 작업으로 외부에서의 인스턴스화를 방지한다 --> 싱글톤 작업
	private JdbcUtil() {}
	
	//getInstance() 메소드는 외부에서 유일하게 인스턴스에 접근할 수 있는 방법 제공함 , 이 메서드는 instance 변수를 반환해서 호출할때마다
	//같은 인스턴스를 반환하게 한다 . => 인스턴스는 자바에서 구체적인 객체,실제로 메모리에 할당된 객체
	public static JdbcUtil getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return ds.getConnection(); // 풀에서 커넥션을 반환한다
	}
}
