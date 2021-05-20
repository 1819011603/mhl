package mhl.javaBean;

/**
 * @author 18190
 * @Date: 2021/5/20  11:19
 * @VERSION 1.0
 */
public class Menu {

    private Integer id;
    private String name;
    private String type;
    private Double price;

    public Menu() {
    }

    /**
     *
     * @param id 菜的id
     * @param name 菜名
     * @param type 菜的类型 主食、热菜、冷菜、汤、甜品等
     * @param price 菜的单价
     */
    public Menu(Integer id, String name, String type, Double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }


    @Override
    public String toString() {
        return
                id +
                "\t\t" + name +
                "\t" + type +
                "\t\t" + price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
