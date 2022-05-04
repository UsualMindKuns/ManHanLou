package ManHanLou.dao;
import ManHanLou.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();

    //显示多行多列
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), parameters);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //显示单行数据
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<T>(clazz),parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    //显示单行单列数据
    public Object queryScalar(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler(),parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //执行dml语句
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            return queryRunner.update(connection, sql, parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
