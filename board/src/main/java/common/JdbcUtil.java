package common;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtil {

    private static JdbcUtil instance = new JdbcUtil();
    private DataSource dataSource;

    private JdbcUtil() {
        initializeDataSource();
    }

    public static JdbcUtil getInstance() {
        return instance;
    }

    private void initializeDataSource() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/oracle");
            System.out.println("Connection Pool 생성!!");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is null");
        }
        return dataSource.getConnection();
    }
}
