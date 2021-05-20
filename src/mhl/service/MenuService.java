package mhl.service;

import mhl.dao.MenuDao;
import mhl.javaBean.Menu;

import java.util.List;

/**
 * @author 18190
 * @Date: 2021/5/20  11:21
 * @VERSION 1.0
 */
public class MenuService {
    MenuDao menuDao = new MenuDao();

    @SuppressWarnings("unchecked")
    public List<Menu> getMenu(){
        return (List<Menu>) menuDao.queryMulti("select * from menu",Menu.class);
    }

    public List<?> getFood(){
        return menuDao.getFoodName();
    }

    public Menu getMenuById(Integer id){
        return menuDao.querySingle("select * from menu where id = ?",Menu.class,id);
    }
}
