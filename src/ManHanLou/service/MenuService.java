package ManHanLou.service;

import ManHanLou.dao.MenuDAO;
import ManHanLou.domain.Menu;

import java.util.List;

public class MenuService {
    private MenuDAO menuDAO = new MenuDAO();

    //编写方法，返回数据库中所有的菜品
    public List<Menu> getAllMenu() {
        return menuDAO.queryMulti("select * from menu", Menu.class);
    }

    //编写方法，根据输入的菜品编号来选择
    public Menu getIdMenu(int id) {
        return menuDAO.querySingle("select * from menu where id = ?",Menu.class,id);
    }

    //编写方法，可以根据菜品的编号返回对应的菜品价格
    public Object getIdMenuPrice(int id) {
        return menuDAO.queryScalar("select price from menu where id = ?",id);
    }
}
