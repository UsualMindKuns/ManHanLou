package ManHanLou.service;

import ManHanLou.dao.EmployeeDAO;
import ManHanLou.domain.Employee;

/**
 * 该类为员工业务类
 */
public class EmployeeService {
    //定义一个EmployeeDAO对象，方便调用连接数据库的方法
    EmployeeDAO employeeDAO = new EmployeeDAO();
    //编写方法将输入的员工信息通过sql语句来返回员工对象
    //如果员工不存在则返回null
    public Employee checkEmployee(String empId, String pwd) {
        //验证员工信息返回单行的员工信息即可
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = MD5(?)",Employee.class,empId,pwd);

    }

}
