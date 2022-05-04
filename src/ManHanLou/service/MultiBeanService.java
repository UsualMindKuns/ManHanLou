package ManHanLou.service;

import ManHanLou.dao.MultiDAO;
import ManHanLou.domain.MultiBean;

import java.util.List;

public class MultiBeanService {
    MultiDAO multiDAO = new MultiDAO();

    //编写方法可以返回所有的账单
    public List<MultiBean> getAllBill() {
        return multiDAO.queryMulti("SELECT * FROM bill,menu WHERE bill.menuId = menu.id",MultiBean.class);
    }
}
