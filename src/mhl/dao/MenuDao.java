package mhl.dao;

import mhl.javaBean.Menu;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/5/20  11:20
 * @VERSION 1.0
 */
public class MenuDao extends BasicDao<Menu>{
    public List<?> getFoodName(){
        try {
            return getRunner().query("select name from menu",new ColumnListHandler<>());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
