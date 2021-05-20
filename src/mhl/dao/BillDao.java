package mhl.dao;

import mhl.javaBean.Bill;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 18190
 * @Date: 2021/5/20  11:56
 * @VERSION 1.0
 */
public class BillDao extends BasicDao<Bill> {

    /**
     *
     * @param sql sql
     * @param parameters 可变参数
     * @return 返回某个桌子所有账单的集合 用来结账 只需money列 所以用的 new ColumnListHandler<>()
     */
    public List<?> getMoneyColumn(String sql,Object...parameters)  {
    try {
        return getRunner().query(sql,new ColumnListHandler<>(),parameters);
    }catch (Exception e){
        throw new RuntimeException(e);
    }
    }
}
