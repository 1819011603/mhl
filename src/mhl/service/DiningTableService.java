package mhl.service;

import mhl.dao.DiningTableDao;
import mhl.javaBean.DiningTable;
import org.junit.Test;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/5/20  9:57
 * @VERSION 1.0
 */
public class DiningTableService {
    private DiningTableDao diningTableDao = new DiningTableDao();
    @SuppressWarnings("unchecked")
    public List<DiningTable> getTables(){
        return  (List<DiningTable>) diningTableDao.queryMulti("select * from diningTable",DiningTable.class);
    }


    public DiningTable getTableById(Integer id){
        return diningTableDao.querySingle("select * from diningTable where id=?",DiningTable.class,id);

    }

    @Test
    public void clearTable(){
        diningTableDao.update("update diningTable set state=?,orderName=?,orderTel=? where id>=1","空","","");
    }

    public String getState(Integer id){
        return (String) diningTableDao.queryScalar("select state from diningTable where id=?",id);
    }

    public boolean orderToDining(Integer id) {
        return diningTableDao.update("update diningTable set state=? where id=?","用餐中",id) >0;
    }


    public boolean freeToOrder(Integer id,String orderName,String orderTel){

            return diningTableDao.update("update diningTable set orderName=?,orderTel=?,state=? where id=?",orderName,orderTel,"已预订",id) >0;


    }
    public boolean orderToFree(Integer id) {
        return diningTableDao.update("update diningTable set orderName=?,orderTel=?,state=? where id=?","","","空",id) >0;

    }
}
