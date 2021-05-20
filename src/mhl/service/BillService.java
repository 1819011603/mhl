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


    /**
     *
     * @param menuId 菜id
     * @param num 菜数
     * @param diningTableId 桌子的id
     * @return 增加一个菜的账单
     */
    public boolean orderMenu(Integer menuId,Integer num,Integer diningTableId){
        /**
         * 为每一个订单生成一个UUID
         */
        String billID = UUID.randomUUID().toString();
        Double money = menuService.getMenuById(menuId).getPrice() * num;
        Integer rows = billDao.update("insert into bill values(null,?,?,?,?,?,now(),'未结账')",billID,menuId,num,money,diningTableId);
        if(rows <= 0)return false;
        return diningTableService.orderToDining(diningTableId);
    }

    /**
     * 多表查询
     * @return 返回多表MultiTableBean的List
     */
    public List<MultiTableBean> getList(){
        return (List<MultiTableBean>) multiTableDao.queryMulti("select bill.*,name,price from bill,menu where bill.menuId= menu.id",MultiTableBean.class);
    }
    public boolean setState(Integer diningTableId,String state){
        return billDao.update("update bill set state=? where diningTableId=? and state=?",state,diningTableId,"未结账") > 0;
    }

    /**
     *
     * @param diningTableId 桌子id
     * @return 该桌子的总花费
     */
    public Double total(Integer diningTableId) {
        List<Double> list = (List<Double>) billDao.getMoneyColumn("select money from bill where diningTableId=? and state = ?",diningTableId,"未结账");
        Double t= 0.0;
        for (Double d:list){
            t+=d;
        }
        return t;
    }
}
