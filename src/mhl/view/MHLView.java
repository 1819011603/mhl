package mhl.view;

import jdk.nashorn.internal.scripts.JD;
import mhl.dao.DiningTableDao;
import mhl.javaBean.*;
import mhl.service.BillService;
import mhl.service.DiningTableService;
import mhl.service.EmployeeService;
import mhl.service.MenuService;
import mhl.utils.JDBCUtilsByDruid;
import mhl.utils.Utility;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

public class MHLView {
    private boolean loop = true;
    private String key = "";
    private EmployeeService employeeService = new EmployeeService();
    private DiningTableService diningTableService = new DiningTableService();
    private MenuService menuService = new MenuService();
    private BillService billService = new BillService();
    public static void main(String[] args) {
        new MHLView().testMHL();
    }

    public void testMHL(){

        while (loop){
            print1();
            key = Utility.readString(1);
            switch (key){
                case "1":
                    System.out.println("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.println("请输入密码：");
                    String pwd = Utility.readString(50);
                    Employee employee =  employeeService.checkEmployeeIdAndPassword(empId,pwd);
                    if(employee!=null){
                        System.out.println("==============登陆成功["+ employee.getName() +"]================");
                        while (loop){
                            print2();
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    displayDiningTable();

                                    break;
                                case "2":
                                    orderTable();
                                    break;
                                case "3":
                                    displayMenu();
                                    break;
                                case "4":
                                    orderMenu();
                                    break;
                                case "5":
                                    displayBills();
                                    break;
                                case "6":
                                    checkOutBills();
                                    break;
                                case "9":

                                    loop = false;
                                    break;
                                default:
                                    System.out.println("输入有误，请重新输入:");
                                    break;

                            }
                        }

                    }else {
                        System.out.println("==============登陆失败================");
                    }
                    break;
                case "2":
                    loop = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入:");

            }
        }
        System.out.println("退出满汉楼");

    }
    public void checkOutBills(){
        while (true){
            System.out.println("===============结账服务===================");
            System.out.print("请选择要结账的餐桌编号（-1退出）：");
            Integer diningTableId = Utility.readInt(2);
            if(diningTableId == -1){
                break;
            }


            System.out.print("结账方式（现金/支付宝/微信）-1表示退出：");
            String ways = Utility.readString(3);
            if ("-1".equals(ways)){
                break;
            }

            System.out.print("确认是否结账(Y/N):");
            String f = Utility.readString(1);
            if("y".equalsIgnoreCase(f)){
                if(!"现金".equals(ways) && !"支付宝".equals(ways) && !"微信".equals(ways)){
                    System.out.println("支付方式有误,请重新输入");
                    continue;
                }
                DiningTable diningTable = diningTableService.getTableById(diningTableId);
                if (diningTable == null){
                    System.out.println("餐桌号不存在,请重新输入！");
                }else if(!"用餐中".equals(diningTable.getState())){
                    System.out.println("并未点餐,无需结账");
                }else {
                    Double money = billService.total(diningTableId);
                    Savepoint savepoint = null;
                    try {


                        // 这两条语句需要事务支持  ThreadLocalhost

                        billService.setState(diningTableId,ways);

                        diningTableService.orderToFree(diningTableId);
                        System.out.println("===============结账完成[总额："+money+"]===================");

                    }catch (Exception e){

                        System.out.println("===============结账失败[总额："+money+"]===================");

                    }


                    break;
                }
            }else {
                System.out.println("===============取消结账===================");
            }

        }

    }


    public void orderMenu(){
        System.out.println("=============点餐服务===============");
        boolean bill = false;
        while (true){
            System.out.print("请输入点餐的桌号(-1退出)");
            Integer diningTableId = Utility.readInt(2);
            if (diningTableId == -1){
                break;
            }
            System.out.print("请输入菜品编号(-1退出)");
            Integer menuId = Utility.readInt(2);
            if (menuId == -1){
                break;
            }
            System.out.print("请输入商品数量(-1退出)");
            Integer num = Utility.readInt(2);
            if (num == -1){
                break;
            }
            System.out.print("确认是否点这个菜(Y/N)");
            String f = Utility.readString(1);
            if("y".equalsIgnoreCase(f)){
                DiningTable diningTable = diningTableService.getTableById(diningTableId);
                if(diningTable == null){
                    System.out.println("桌号不存在，请重新输入");
                    continue;
                }
                else {

                    Menu menu = menuService.getMenuById(menuId);
                    if(menu == null){
                        System.out.println("菜号不存在，请重新输入");
                        continue;
                    }else {
                        bill = billService.orderMenu(menuId,num,diningTableId);
                        System.out.println("点餐结果=" + (bill?1:0));
                        break;

                    }
                }

            }else {

                System.out.println("取消预订成功");
            }

        }

        if (bill){
            System.out.println("==========点餐成功=============");
        }
        else {
            System.out.println("==========取消点餐=============");
        }
    }

    @SuppressWarnings("unchecked")
    public void displayBills(){
        System.out.println("\n编号\t\t菜品号\t菜品名\t\t菜品价格\t\t菜品量\t\t金额\t\t桌号\t\t日期\t\t\t\t状态");
        List<MultiTableBean> list = billService.getList();
        for (MultiTableBean bill:list){
            System.out.println(bill);
        }
        System.out.println("==========显示完毕============");
    }

    public void print1(){
        System.out.println("=============满汉楼===============");
        System.out.println("         1. 登录满汉楼             ");
        System.out.println("         2. 退出满汉楼             ");
        System.out.println("请输入你的选择： ");
    }

    public void print2(){
        System.out.println("=============满汉楼二级菜单===============");
        System.out.println("\t\t1. 显示餐桌状态             ");
        System.out.println("\t\t2. 预定餐桌\n" +
                "\t\t3.显示所有菜品\n" +
                "\t\t4.点单服务\n" +
                "\t\t5.查看账单\n" +
                "\t\t6.结账\n" +
                "\t\t9.退出满汉楼");
        System.out.print("请输入你的选择： ");
    }
    public void displayDiningTable(){
        List<DiningTable> list =diningTableService.getTables();
        System.out.println("菜单编号\t餐桌状态\t");
        for (DiningTable diningTable: list){
            System.out.println(diningTable.getId() + "\t\t" + diningTable.getState());
        }
        System.out.println("=============显示完毕=================");
    }
    public void orderTable(){
        System.out.println("==============预定餐桌============");
        while (true){
            System.out.print("请选择要预定的餐桌编号(-1退出：)");
            Integer id = Utility.readInt(3);
            if(id == -1){
                System.out.println("=====预定退出成功======");
                break;
            }
            System.out.print("确定是否预定(Y/N):");
            String f = Utility.readString(1);
            if ("n".equalsIgnoreCase(f)){
                System.out.println("=====取消本次预订======");
            }else if("y".equals(f)) {
                String state = diningTableService.getState(id);
                if(state == null){
                    System.out.println(id + "号餐桌不存在,请重新输入！");
                }else if("空".equals(state)){
                    System.out.print("预订人名字: ");
                    String orderName = Utility.readString(20);
                    System.out.print("预订人电话: ");
                    String tel = Utility.readString(20);
                    boolean s = diningTableService.freeToOrder(id,orderName,tel);
                    if(s){
                        System.out.println("==========预订成功============");
                        System.out.println(diningTableService.getTableById(id));
                        break;
                    }else {
                        System.out.println("==========预订失败============");
                    }
                }else {
                    System.out.println(id+"号餐桌已被使用");
                }

            }else {
                System.out.println("输入有误，取消预定");
            }

        }
    }

    public void displayMenu(){
        List<Menu> list = menuService.getMenu();
        System.out.println("\n菜品编号\t菜品名\t类别\t\t价格");
        for (Menu menu:list){
            System.out.println(menu);
        }
        System.out.println("================显示完毕==================");
    }
}
