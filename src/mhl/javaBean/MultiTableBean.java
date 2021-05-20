package mhl.javaBean;

import java.util.Date;


/**
 * @author 18190
 * @Date: 2021/5/20  17:45
 * @VERSION 1.0
 * 可以和多张表进行查询
 * utils工具包内被是通过 JDBC的结果集ResultSet获取列名
 * 然后再通过utils调用set列名（setXxx）的方法设置属性值
 * 如果多表查询中两个表中含有相同的列名 可以通过设置别名解决。
 * 例如：
 * select employee.name,menu.name as name1,dining.name as name2 from employee,menu,dining where
 * employee.name == menu.name and menu.name == dining.name
 * 此时只要建立与包含于别名相同的属性即可一一对应上。
 * public class complexBean{
 *     String name;
 *     String name1;
 *     String name2;
 * }
 */


public class MultiTableBean {
    /**
     * 多表查询
     */
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private  Integer diningTableId;
    private Date billDate;
    private String state;


    /**
     * 第二个表的属性 名称冲突 select table.name,menu.name as name1 from table,menu;
     * String name1;
     * String name;对应即可
     */
    private String name;
    private Double price;

    /**
     *bill表
     * @param id 主键id
     * @param billId 账单id名 利用UUID生成
     * @param menuId 与menu表的id相等 menu表示菜名的表
     * @param nums  菜的数量
     * @param money 菜的单价
     * @param diningTableId 该订单是哪个桌号生成的
     * @param billDate 账单的生成日期
     * @param state 订单的支付状态 有 未结账、支付宝、微信、现金、流帐等状态
     *
     * select bill.*,menu.name,menu.price from bill,menu where bill.menuId == menu.id;
     *
     * menu表
     * @param name  菜名
     * @param price 菜的单价
     */
    public MultiTableBean(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, Date billDate, String state, String name, Double price) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
        this.name = name;
        this.price = price;
    }

    /**
     * String.format格式化字符串
     * %tF 表示 yyyy-mm-dd
     */
    @Override
    public String toString() {
        return String.format("%3d\t\t%3d\t\t%-6s\t%4d\t\t%-2d\t\t\t%4d\t%-3d\t\t%tF\t\t%s",id,menuId,name,price.intValue(),nums,money.intValue(),diningTableId,billDate,state);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultiTableBean() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setName(String name) {
        this.name = name;
    }









}
