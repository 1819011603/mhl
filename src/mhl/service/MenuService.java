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

    /**
     *
     * @return 所有菜，menu表中的所有
     */
    @SuppressWarnings("unchecked")
    public List<Menu> getMenu(){
        return (List<Menu>) menuDao.queryMulti("select * from menu",Menu.class);
    }

    /**
     *
     * @return 菜名的集合
     */
    public List<?> getFood(){
        return menuDao.getFoodName();
    }

    /**
     *
     * @param id 菜id
     * @return 获取该条菜的数据
     */
    public Menu getMenuById(Integer id){
        return menuDao.querySingle("select * from menu where id = ?",Menu.class,id);
    }
}
