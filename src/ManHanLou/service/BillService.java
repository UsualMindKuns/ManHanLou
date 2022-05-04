package ManHanLou.service;

import ManHanLou.dao.BillDAO;
import ManHanLou.domain.Bill;

import java.util.List;

public class BillService {
    BillDAO billDAO = new BillDAO();
    //编写方法，可以将账单信息加入到数据库中
    public void addBill(int menuId,int nums, double money, int diningTableId) {
        billDAO.update("insert into bill values (null, floor(Rand()*50000),?,?,?,?,now(), '未结账')",menuId,nums,money * nums,diningTableId);

    }
    //编写方法，可以返回所有账单
    public List<Bill> getAllBill() {
        return billDAO.queryMulti("select * from bill", Bill.class);
    }
    //编写方法可以将改变账单的状态
    public void changeBillState(String state, int diningTableId) {
        billDAO.update("update bill set state = ? where diningTableId = ?",state, diningTableId);
    }
}
