package mhl.javaBean;

import java.util.Date;

/**
 * @author 18190
 * @Date: 2021/5/20  17:45
 * @VERSION 1.0
 * 可以和多张表进行查询
 */
public class MultiTableBean {
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private  Integer diningTableId;
    private Date billDate;
    private String state;
    private String name;

    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public MultiTableBean() {
    }

    @Override
    public String toString() {


        return String.format("%3d\t\t%3d\t\t%-6s\t%4d\t\t%-2d\t\t\t%4d\t%-3d\t\t%tF\t\t%s",id,menuId,name,price.intValue(),nums,money.intValue(),diningTableId,billDate,state);
//        (id +
//                "\t\t" + menuId +
//                "\t\t" + name+
//                "\t\t" + price +
//                "\t\t" + nums +
//                "\t\t\t" + money +
//                "\t" + diningTableId +
//                "\t\t" + billDate +
//                "\t\t" + state)
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
