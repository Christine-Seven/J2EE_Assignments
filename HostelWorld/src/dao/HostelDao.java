package dao;

import model.Hostel;

import java.util.List;

/**
 * Created by Seven on 2017/2/14.
 */
public interface HostelDao {
    //检查是否存在该客栈
    public boolean checkHostel(String hostelNum);

    //检查用户名与密码是否正确
    public boolean checkPassword(String hostelNum,String hostelPassword);

    //添加客栈
    public boolean addHostel(Hostel hostel);

    //删除客栈
    public boolean deleteHostel(String hostelNum);

    //修改客栈信息
    public boolean updateHostel(Hostel hostel);

    //查询客栈
    public List<Hostel> queryHostelByProvince(String province);
    public List<Hostel> queryHostelByCity(String city);
    public Hostel queryHostelByNum(String hostelNum);

    //获得所有客栈
    public List<Hostel> queryAll();
}
