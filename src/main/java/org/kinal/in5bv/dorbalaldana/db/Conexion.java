package org.kinal.in5bv.dorbalaldana.db;

/**
 *
 * @author USUARIO
 * @date 27/09/2022
 * @time 23:38:37 Carné 2021604 Código técnico: IN5BV Grupo: 1
 */
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static final String HOST = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DB = "db_control_materia_in5bv";
    private static final String USER = "kinal";
    private static final String PASS = "admin";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB + "?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static BasicDataSource instanceBds;

    private static DataSource getInstanceDS() {
        if (instanceBds == null)
        {
            instanceBds = new BasicDataSource();
            instanceBds.setUrl(URL);
            instanceBds.setUsername(USER);
            instanceBds.setPassword(PASS);
            instanceBds.setInitialSize(25);
        }
        return instanceBds;
    }

    public static Connection getConnection() throws SQLException {
        return getInstanceDS().getConnection();
    }

    public static void close(ResultSet rs) {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
        } catch (SQLException e)
        {
            System.err.println("Se produjo un error al intentar cerra el objeto ResultSet");
            e.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement pstmt) {
        try
        {
            if (pstmt != null)
            {
                pstmt.close();
            }
        } catch (SQLException e)
        {
            System.err.println("Se produjo un error al intentar cerra el objeto PreparedStatement");
            e.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn) {
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        } catch (SQLException e)
        {
            System.err.println("Se produjo un error al intentar cerra el objeto Connection");
            e.printStackTrace(System.out);
        }
    }
}
