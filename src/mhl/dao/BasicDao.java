package mhl.dao;



import mhl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class BasicDao<T> {
    private static final QueryRunner runner = new QueryRunner(JDBCUtilsByDruid.getDs());
    public int update(String sql,Object ...parameters){
        int row;
        try {
           row = runner.update(sql,parameters);
           return row;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<?> queryMulti(String sql,Class<T> tClass,Object...parameters){
        try {
            return runner.query(sql,new BeanListHandler<>(tClass),parameters);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public T querySingle(String sql,Class<T> tClass,Object...parameters){
        try {
            return runner.query(sql,new BeanHandler<>(tClass),parameters);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
    public Object queryScalar(String sql,Object...parameters){
        try {
            return runner.query(sql,new ScalarHandler<>(),parameters);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public static QueryRunner getRunner() {
        return runner;
    }
}
