package mhl.service;

import mhl.dao.EmployeeDao;
import mhl.javaBean.Employee;

/**
 * @author 18190
 * @Date: 2021/5/20  9:36
 * @VERSION 1.0
 */
public class EmployeeService {
    EmployeeDao employeeDao = new EmployeeDao();

    /**
     *
     * @param id 雇员登录id
     * @param pwd 雇员密码
     * @return 返回是否有该雇员
     */
    public Employee checkEmployeeIdAndPassword(String id,String pwd){
        return employeeDao.querySingle("select * from employee where empId=? and pwd=md5(?)",Employee.class,id,pwd);

    }
}
