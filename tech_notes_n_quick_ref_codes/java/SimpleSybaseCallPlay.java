import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.sybase.jdbcx.Debug;
import com.sybase.jdbcx.SybConnection;
import com.sybase.jdbcx.SybDriver;
import com.sybase.jdbcx.SybMessageHandler;

/*
 * To Play, and to keep other copy freezed
 * */
public class SimpleSybaseCallPlay {

    static Driver m_driver = null;
    static Connection m_connection;
    static String m_user = "user";
    static String m_pass = "pass";
    static String m_server = "hostname";
    static String m_port = "123"; 
    static String m_database = "dbname";
    static String m_driver_type = "sybase";
    static String m_address = "jdbc:sybase:Tds";
    static String m_application = "Test-mtk";
    static String m_version = "6";

    private static String getUrlFromServerAndPort() {

        String url = m_address + ":";
        url += m_server + ":" + m_port;
        if (m_database != null) {
            url += "/" + m_database;
        }
        System.out.println("DB URL :" + url);
        return url;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
                    SQLException {
        // Load Driver
        m_driver = (Driver) Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance();
        Debug sybDebug = ((SybDriver) m_driver).getDebug();
        try {
            sybDebug.debug(true, "ALL");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Load Driver -- ENDS

        Properties props = new Properties();
        props.put("user", m_user);
        props.put("password", m_pass);
        props.put("APPLICATIONNAME", m_application);
        props.put("JCONNECT_VERSION", m_version);

        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String hostname = inetAddress.getHostName();
            props.put("HOSTNAME", hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String url = getUrlFromServerAndPort();
        m_connection = m_driver.connect(url, props);
        System.out.println("Message Handler set");
        ((SybConnection) m_connection).setSybMessageHandler(new MsgHandlr());

        Statement stmt = m_connection.createStatement();

        stmt.executeQuery("{ call multi_result_set }");
        ResultSet rs = null;
        rs = stmt.getResultSet();
        System.out.println("Output: ");
        printResultSet(rs);

        while (stmt.getMoreResults()) {
            rs.close();
            rs = stmt.getResultSet();
            printResultSet(rs);
        }

        System.out.println();
        rs.close();
        stmt.close();
        m_connection.close();
    }

    private static void printResultSet(ResultSet rs) {
        ResultSetMetaData md;
        try {
            md = rs.getMetaData();
            int cols = md.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= cols; i++)
                    System.out.print(md.getColumnName(i) + " ");
                System.out.println();
                for (int i = 1; i <= cols; i++)
                    System.out.print(rs.getString(i) + " ");
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static class MsgHandlr implements SybMessageHandler {

        @Override
        public SQLException messageHandler(SQLException e) {
            System.out.println("ErrorCode" + e.getErrorCode());
            System.out.println("Message" + e.getMessage());
            return null;
        }

    }

}
