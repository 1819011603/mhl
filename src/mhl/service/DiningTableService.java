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

    /**
     *
     * @return 获得所有桌子
     */
    @SuppressWarnings("unchecked")
    public List<DiningTable> getTables(){
        return  (List<DiningTable>) diningTableDao.queryMulti("select * from diningTable",DiningTable.class);
    }

    /**
     *
     * @param id 桌子id
     * @return 获得该桌子
     */
    public DiningTable getTableById(Integer id){
        return diningTableDao.querySingle("select * from diningTable where id=?",DiningTable.class,id);

    }

    /**
     * 关门了 把桌子状态全清了
     */
    @Test
    public void clearTable(){
        diningTableDao.update("update diningTable set state=?,orderName=?,orderTel=? where id>=1","空","","");
    }

    /**
     *
     * @param id 桌子id
     * @return 桌子id的状态
     */
    public String getState(Integer id){
        return (String) diningTableDao.queryScalar("select state from diningTable where id=?",id);
    }

    /**
     *
     * @param id 桌子id
     * @return 桌子id的状态设为  "用餐中"
     */
    public boolean orderToDining(Integer id) {
        return diningTableDao.update("update diningTable set state=? where id=?","用餐中",id) >0;
    }

    /**
     * 预定桌子使用
     * @param id 预约的桌子id
     * @param orderName 预约人
     * @param orderTel 预约人电话
     * @return 是否操作成功
     */
    public boolean freeToOrder(Integer id,String orderName,String orderTel){

            return diningTableDao.update("update diningTable set orderName=?,orderTel=?,state=? where id=?",orderName,orderTel,"已预订",id) >0;


    }

    /**
     *
     * @param id 桌子id
     * @return 桌子id的状态设为  "空"
     */
    public boolean orderToFree(Integer id) {
        return diningTableDao.update("update diningTable set orderName=?,orderTel=?,state=? where id=?","","","空",id) >0;

    }
}
