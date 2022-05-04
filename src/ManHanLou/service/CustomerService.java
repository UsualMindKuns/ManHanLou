package ManHanLou.service;

import ManHanLou.dao.CustomerDAO;
import ManHanLou.domain.Customer;

public class CustomerService {
    CustomerDAO customerDAO = new CustomerDAO();

    //编写方法将顾客信息存放到数据库中
    public void addCustomer(String name, String tel) {
        customerDAO.update("insert into customer values (null, ?, ?)",name, tel);
    }
}
