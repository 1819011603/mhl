package mhl.service;

import jdk.internal.dynalink.linker.LinkerServices;
import mhl.dao.BillDao;
import mhl.dao.MenuDao;
import mhl.dao.MultiTableDao;
import mhl.javaBean.Bill;
import mhl.javaBean.MultiTableBean;

import java.util.List;
import java.util.UUID;

/**
 * @author 18190
 * @Date: 2021/5/20  11:56
 * @VERSION 1.0
 */

@SuppressWarnings({"all"})
public class BillService {
    private BillDao billDao = new BillDao();
    private MenuService menuService = new MenuService();
    private DiningTableService diningTableService = new DiningTableService();
    private MultiTableDao multiTableDao = new MultiTableDao();


    public boolean orderMenu(Integer menuId,Integer num,Integer diningTableId){
        String billID = UUID.randomUUID().toString();
        Double money = menuService.getMenuById(menuId).getPrice() * num;
        Integer rows = billDao.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",billID,menuId,num,money,diningTableId);
        if(rows <= 0)return false;
        return diningTableService.orderToDining(diningTableId);
    }

    public List<MultiTableBean> getList(){
        return (List<MultiTableBean>) multiTableDao.queryMulti("select bill.*,name,price from bill,menu where bill.menuId= menu.id",MultiTableBean.class);
    }
    public boolean setState(Integer diningTableId,String state){
        return billDao.update("update bill set state=? where diningTableId=? and state=?",state,diningTableId,"未结账") > 0;
    }

    public Double total(Integer diningTableId) {
        List<Double> list = (List<Double>) billDao.getMoneyColumn("select money from bill where diningTableId=? and state = ?",diningTableId,"未结账");
        Double t= 0.0;
        for (Double d:list){
            t+=d;
        }
        return t;
    }
}
