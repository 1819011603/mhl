package mhl.dao;



import mhl.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * @author 18190
 * 使用apache-dbutils 获取更加容易操作的类型 因为jdbc的PreparedStatement中的ResultSet不好操作。
 * 使用dbutils可以获取更加容易操作的List
 * ArrayHandler：把结果集中的第一行数据转成对象数组。
 * ArrayListHandler：把结果集中的每一行数据都转成一个对象数组，再存放到List中。
 * BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。 （BeanHandler 和 BeanListHandler封装成javaBean 最常用的就是下面这两种）
 * BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
 * ColumnListHandler：将结果集中某一列的数据存放到List中。（将一列的结果转成list）
 * KeyedHandler：将结果集中的每一行数据都封装到一个Map里，然后再根据指定的key把每个Map再存放到一个Map里。
 *  MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
 *  MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List。
 *  ScalarHandler：将结果集中某一条记录的其中某一列的数据存成Object。
 *
 *   单行数据处理：ScalarHandler    ArrayHandler    MapHandler    BeanHandler
 *     多行数据处理：BeanListHandler    AbstractListHandler（ArrayListHandler MapListHandler ColumnListHandler）
 *
 *                           AbstractKeyedHandler（KeyedHandler BeanMapHandler）
 *      可供扩展的类：BaseResultSetHandler
 */

    // 泛型
    // 对数据库的一些基本操作 下一级dao表公共的操作放在这个类中
public class BasicDao<T> {
    /**
     * QueryRunner对象可以执行sql语句并返回你想要的数据类型
     */
    private static final QueryRunner runner = new QueryRunner(JDBCUtilsByDruid.getDs());

    /**
     * 更新数据库 insert delete update
     * @param sql sql语句
     * @param parameters sql语句的可变参数
     * @return 返回本次操作的影响行数 >0 表示影响到表 ==0表示表并未修改
     */
    public int update(String sql,Object ...parameters){
        int row;
        try {
           row = runner.update(sql,parameters);
           return row;
        }catch (Exception e){

            throw new RuntimeException(e);
        }
    }

    /**
     * 返回一个List<javaBean>
     * @param sql sql
     * @param tClass 传入要返回的javaBean.class
     * @param parameters sql可变参数
     * @return 返回一个查询结果的JavaBean List数组
     */
    public List<?> queryMulti(String sql,Class<T> tClass,Object...parameters){
        try {
            return runner.query(sql,new BeanListHandler<>(tClass),parameters);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * 返回满足查询的一个JavaBean对象
     * @param sql sql
     * @param tClass javaBean.class
     * @param parameters 可变参数
     * @return 返回满足查询的一个JavaBean对象
     */
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

    /**
     *
     * @return 传回runner 供给下一级的dao类使用
     */
    public static QueryRunner getRunner() {
        return runner;
    }
}
