package mhl.javaBean;

import java.sql.DatabaseMetaData;
import java.util.Date;

/**
 * @author 18190
 * @Date: 2021/5/20  11:53
 * @VERSION 1.0
 * 账单的javaBean
 */
public class Bill {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private  Integer diningTableId;
    private Date billDate;
    private String state;

    /**
     *
     * @param id 主键id
     * @param billId 账单id名 利用UUID生成
     * @param menuId 与menu表的id相等 menu表示菜名的表
     * @param nums  菜的数量
     * @param money 菜的单价
     * @param diningTableId 该订单是哪个桌号生成的
     * @param billDate 账单的生成日期
     * @param state 订单的支付状态 有 未结账、支付宝、微信、现金、流帐等状态
     */
    public Bill(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, Date billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    public Bill() {
    }
    @Override
    public String toString() {
        return
                id +
                "\t\t" + menuId +
                "\t\t\t" + nums +
                "\t\t\t" + money +
                "\t" + diningTableId +
                "\t\t" + billDate +
                "\t\t" + state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



}
