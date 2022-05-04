package ManHanLou.service;

import ManHanLou.dao.DiningTableDAO;
import ManHanLou.domain.DiningTable;

import java.util.List;

/**
 * 该类完成对餐桌的服务
 */
public class DiningTableService {
    private DiningTableDAO diningTableDAO = new DiningTableDAO();

    //编写方法返回所有餐桌的状态
    public List<DiningTable> getTableState(String sql) {
        return diningTableDAO.queryMulti(sql,DiningTable.class);
    }

    //编写方法，根据用户选择的餐桌id来返回是否有该餐桌编号
    public DiningTable checkTableId(int id) {
        return diningTableDAO.querySingle("select * from diningTable where id = ?",DiningTable.class,id);

    }

    //编写方法，改变餐桌状态为预定
    public void changeTableStateToReserve(int id) {
        diningTableDAO.update("update diningTable set state = '预定' where id = ?", id);
    }
    //编写方法，改变餐桌状态为就餐中
    public void changeTableStateToEating(int id) {
        diningTableDAO.update("update diningTable set state = '就餐中' where id = ?", id);

    }
    //编写方法，改变餐桌状态为空
    public void changeTableStateToNull(int id) {
        diningTableDAO.update("update diningTable set state = '空' where id = ?", id);

    }

    //编写方法，将预定人的信息传入到数据库中
    public void addCustomerMess(int id, String name, String tel) {
        diningTableDAO.update("update diningTable set orderName = ?, orderTel = ? where id = ?",name, tel, id);
    }

    //编写方法，可以根据传入的餐桌id来返回对应的餐桌状态
    public Object getIdTableState(int id, String sql) {
        return diningTableDAO.queryScalar(sql,id);
    }

}
