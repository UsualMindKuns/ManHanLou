package ManHanLou.domain;

import java.util.Date;

public class MultiBean {
    private Integer id;
    private String billId;
    private String menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;

    //添加菜品名
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultiBean() {
    }

    public MultiBean(Integer id, String billId, String menuId, Integer nums, Double money,
                Integer diningTableId, Date billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuId = menuId;
        this.nums = nums;
        this.money = money;
        this.diningTableId = diningTableId;
        this.billDate = billDate;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return diningTableId + "\t\t\t" + menuId + "\t" + name + "\t" + nums + "\t" + money
                + "\t" + billDate + "\t" + state;
    }
}
