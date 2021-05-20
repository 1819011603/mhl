package mhl.javaBean;

/**
 * @author 18190
 * @Date: 2021/5/20  9:33
 * @VERSION 1.0
 * 本次程序的第一个职工表的javabean
 * 该类建立了与 mysql中的employee表的映射
 */


public class Employee {

    /**
     * 为什么使用Integer、Double 不使用int、double？
     * 因为在mysql中是会出现null值 int、double接收到null值会报错。
     * 但是类的引用可以接受null值。
     */
    private Integer id;
    private String empId;
    private String pwd;
    private String name;
    private String job;

    /**
     * 一定要声明为public因为apache的utils工具类底层是通过反射调用无参构造器
     * 默认修饰符也不可以 utils工具包的类照样访问不到该构造器，会报无访问权限的错误
     */
    public Employee() {
    }

    /**
     * 有参构造器 其实可以省去 apache-utils工具包不会用到此构造方法
     * @param id 主键id 没有特别的含义
     * @param empId 雇员id  注意mysql在建表时要将其声明为unique
     * @param pwd 雇员登录密码
     * @param name 雇员名称
     * @param job 雇员的职务
     */
    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empId='" + empId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    /**
     * get方法全删除也不会影响工具包的使用 下面的get方法省去了一些
     * 但是删除setXxx则那个该属性值将会null，获取不到mysql中的值
     * mysql的列名与类的setXxx方法在底层建立了映射关系。
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
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
     *

     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }



    public void setEmpId(String empId) {
        this.empId = empId;
    }



    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setJob(String job) {
        this.job = job;
    }
}
