package ManHanLou.view;

import ManHanLou.dao.BillDAO;
import ManHanLou.domain.*;
import ManHanLou.service.*;
import ManHanLou.utils.Utility;

import java.util.List;

public class MhlView {
    //控制是否退出菜单
    private boolean loop = true;
    //用于接收用户的按键输入选择
    private String key;
    //定义一个员工服务对象employeeService
    EmployeeService employeeService = new EmployeeService();
    //初始化DiningTableService对象
    DiningTableService diningTableService = new DiningTableService();
    //初始化MenuService对象
    MenuService menuService = new MenuService();
    //初始化BillService对象
    BillService billService = new BillService();
    //初始化MultiBeanService对象
    MultiBeanService multiBeanService = new MultiBeanService();

    public MhlView() {
        mainMenu();
    }

    public void mainMenu() {
        while (loop) {
            //登录界面
            System.out.println("===============满汉楼===============");
            System.out.println("\t\t1 登录满汉楼");
            System.out.println("\t\t2 退出满汉楼");
            System.out.print("请输入你的选择：");
            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("请输入员工号：");
                    String empId = Utility.readString(50);
                    System.out.print("请输入密  码：");
                    String pwd = Utility.readString(50);
                    //这边就应该调用一个方法可以去数据库验证用户信息
                    Employee employee = employeeService.checkEmployee(empId, pwd);
                    //登录成功就进入二级菜单
                    if (employee != null) {
                        System.out.println("======================" + "(" + employee.getName() + ")" + "登录成功====================");
                        while (loop) {
                            System.out.println("================满汉楼二级菜单=============");
                            System.out.println("\t\t1 显示餐桌状态");
                            System.out.println("\t\t2 预定餐桌");
                            System.out.println("\t\t3 显示所有菜品");
                            System.out.println("\t\t4 点餐服务");
                            System.out.println("\t\t5 查看账单");
                            System.out.println("\t\t6 结账");
                            System.out.println("\t\t9 退出满汉楼");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    System.out.println("================显示餐桌状态=============");
                                    showTableState();
                                    System.out.println("===============显示结束================");
                                    break;
                                case "2":
                                    System.out.println("================预定餐桌================");
                                    reserveTable();
                                    break;
                                case "3":
                                    System.out.println("=================显示所有菜品===============");
                                    System.out.println("菜品编号\t\t菜品名\t\t类别\t\t价格");
                                    getAllMenus();
                                    break;
                                case "4":
                                    System.out.println("==================点餐服务==================");
                                    completeOrderingService();
                                    break;
                                case "5":
                                    System.out.println("==================查看账单===================");
                                    showAllBill();
                                    break;
                                case "6":
                                    System.out.println("==================结账======================");
                                    CompleteSettleAccount();
                                    break;
                                case "9":
                                    loop = false;
                                    System.out.println("退出满汉楼系统...");
                                    break;
                                default:
                                    System.out.println("你的选择有误，请重新选择...");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("==============================登录失败========================");
                    }
                    break;
                case "2":
                    loop = false;
                    System.out.println("退出满汉楼系统");
                    break;
                default:
                    System.out.println("你的输入有误，请重新输入...");
                    break;
            }
        }
    }

    //编写方法返回所有餐桌的状态
    public void showTableState() {
        System.out.println("餐桌号" + "\t\t" + "状态");
        String sql = "select id, state from diningTable";
        List<DiningTable> list = diningTableService.getTableState(sql);
        for (DiningTable diningTable : list) {
            System.out.println(diningTable);
        }

    }

    //编写方法预定餐桌号
    public void reserveTable() {
        System.out.print("请输入要预定的餐桌编号(-1退出)：");
        int id = Utility.readInt();
        if (id == -1) { //如果输入(-1)就退出预定餐桌界面
            System.out.println("==============你退出了预定餐桌============");
            return;
        } else { //如果不退出就要验证餐桌号是否存在和餐桌状态是否是可以预定的
            //验证餐桌号是否存在此时应该调用业务层的方法来验证
            DiningTable diningTable = diningTableService.checkTableId(id);
            if (diningTable != null) { //餐桌号存在
                if (diningTable.getState().equals("空")) { //当餐桌号存在时还要查询餐桌状态是否可以预定

                    System.out.print("确认是否预定(Y/N)：");
                    String temp = Utility.readString(1);
                    //判断用户是否输入的是Y/N/y/n
                    while (!(temp.equals("y") || temp.equals("Y") || temp.equals("N") || temp.equals("n"))) {
                        System.out.print("请重新输入Y/N来确认是否预定：");
                        temp = Utility.readString(1);
                    }
                    if (temp.equals("y") || temp.equals("Y")) {
                        System.out.print("请输入预定人的名字：");
                        String reserveName = Utility.readString(20);
                        System.out.print("请输入预订人的电话：");
                        String reserveTel = Utility.readString(20);
                        //此时要将预定人的信息存放到数据库中
                        //因此要调用一个方法可以将数据存放到数据库中
                        diningTableService.addCustomerMess(id, reserveName, reserveTel);
                        System.out.println("预订成功");
                        //预定成功后要将预定的餐桌状态置为预定状态
                        //因此调用方法改变数据库中该餐桌的数据
                        diningTableService.changeTableStateToReserve(id);
                    } else {
                        System.out.println("预定失败");
                        return;
                    }

                } else {
                    System.out.println("已有人在" + id + "餐桌台就餐，请重新预定");
                }
            } else {
                System.out.println("输入的餐桌号不存在，请重新选择预定");
            }

        }
    }

    //编写方法，得到所有的菜品
    public void getAllMenus() {
        //调用一个方法可以显示所有的菜品
        List<Menu> list = menuService.getAllMenu();
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

    //编写方法，完成点餐服务
    public void completeOrderingService() {
        System.out.print("请输入点餐的桌号(-1退出)：");
        int tableId = Utility.readInt();
        if (tableId == -1) {
            System.out.println("退出点餐服务");
            return;
        }
        System.out.print("请输入选择的菜品编号(-1)：");
        int menuId = Utility.readInt();
        if (menuId == -1) { //-1表示退出点餐
            System.out.println("退出点餐服务");
            return;
        }
        //判断菜品的编号是否存在
        Menu menu = menuService.getIdMenu(menuId);
        if (menu != null) {
            System.out.print("请输入需要该菜品的数量(-1)：");
            int vegetableNums = Utility.readInt();
            if (vegetableNums == -1) {
                System.out.println("退出点餐服务");
                return;
            }
            System.out.println("请输入是否确认要点该菜(Y/N):");
            char key = Utility.readConfirmSelection();
            if (key == 'Y') {
                System.out.println("===========点餐成功========");
                //点餐成功后要将点餐记录记入到账单中
                //调用方法可以将所点的菜品的价格返回
                double price = (double) menuService.getIdMenuPrice(menuId);
                //调用方法可以将点餐记录加入到数据库中
                billService.addBill(menuId, vegetableNums, price, tableId);
                //点餐成功后要将餐桌的状态改为就餐中
                diningTableService.changeTableStateToEating(tableId);
            } else {
                System.out.println("退出点餐服务，请重新选择");
            }
        } else {
            System.out.println("菜品编号不存在，点餐失败，请重新点餐");
            return;
        }


    }

    //编写方法，可以完成显示账单
    public void showAllBill() {
        //调用方法显示账单
        List<MultiBean> allBill = multiBeanService.getAllBill();
        System.out.println("餐桌号\t菜品编号\t菜品名\t数量\t总价格\t点餐时间\t\t\t\t\t状态");
        for (MultiBean bill : allBill) {
            System.out.println(bill);
        }
    }

    //编写方法完成结账服务
    public void CompleteSettleAccount() {
        String settleMethod = "";
        System.out.println("请输入结账的餐桌号(-1退出)：");
        int diningTableId = Utility.readInt();
        if (diningTableId == -1) { //-1表示退出结账服务
            System.out.println("==========退出结账服务=======");
            return;
        }
        //判断输入的餐桌号是否存在,并且当前的餐桌应为就餐状态
        DiningTable diningTable = diningTableService.checkTableId(diningTableId);
        if (diningTable != null) {
            if (diningTable.getState().equals("就餐中")) {
                System.out.print("请输入结账方式(支付宝/微信/现金)直接回车可退出结账服务：");
                settleMethod = Utility.readString(10, "");
                if (settleMethod.equals("")) {
                    System.out.println("============退出结账服务==========");
                    return;
                }
                //只能输入支付宝/微信/现金三种结账方式
                while (!(settleMethod.equals("支付宝") || settleMethod.equals("微信") || settleMethod.equals("现金"))) {
                    System.out.print("结账方式有误，请重新输入(直接回车可以退出)：");
                    settleMethod = Utility.readString(10, "");
                    if (settleMethod.equals("")) { //默认空值则退出结账服务
                        return;
                    }
                }
                System.out.print("请输入是否结账(Y/N)：");
                char key = Utility.readConfirmSelection();
                if (key == 'Y') {
                    System.out.println("================结账完成===========");
                    //结账完成后应该修改账单状态和修改餐桌的状态
                    diningTableService.changeTableStateToNull(diningTableId);
                    billService.changeBillState(settleMethod, diningTableId);
                    return;
                } else {
                    System.out.println("退出了结账服务");
                }
            } else {
                System.out.println(diningTableId + "号餐桌号不在'就餐中'状态,结账失败，请重新结账");
                return;
            }
        } else {
            System.out.println("输入的餐桌号不存在，选择结账服务并输入正确的餐桌号");
        }
    }

}
