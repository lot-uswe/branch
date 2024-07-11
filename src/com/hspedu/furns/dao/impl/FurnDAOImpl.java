package com.hspedu.furns.dao.impl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.entity.Furn;

import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 14:46
 * @Description Java Lotus
 */
public class FurnDAOImpl extends BasicDAO<Furn> implements FurnDAO {

    @Override
    public List<Furn> queryFurns() {
        String sql="SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, \n" +
                "                `img_path` imgPath FROM furn";
        return queryMulti(sql,Furn.class);

    }

    @Override
    public int addFurn(Furn furn) {
        //把这个sql先在sqlyong测试

        String sql="INSERT INTO furn(`id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path`) " +
                "VALUES(NULL , ? , ? , ? , ? , ? , ?)";
        return update
                (sql,furn.getName(),furn.getMaker(),
                        furn.getPrice(),furn.getSales(),furn.getStock(),
                        furn.getImgPath());
    }

    @Override
    public int deleteFurnById(int id) {
        String sql = "DELETE FROM `furn` WHERE id = ?";
        return update(sql,id);
    }

    @Override
    public Furn queryFurnById(int id) {
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn WHERE id = ?";
        return querySingle(sql,Furn.class,id);
    }

    /**
     * 对于这一块，我们要知道，当我们在数据库中测试完代码之后
     * 复制粘贴在impl中时，我们要注意这个sql语句中的单引号‘’要删除
     * 不然的话，会报格式错误
     * @param furn
     * @return
     */
    @Override
    public int updateFurn(Furn furn) {
        String sql="UPDATE `furn` SET `name` = ? , `maker` = ?, `price` = ? , " +
                "`sales` = ? , `stock` = ? , `img_path` = ? " +
                "WHERE id = ? ";
        return update(sql,furn.getName(),furn.getMaker(),furn.getPrice(),
                furn.getSales(),furn.getStock(),furn.getImgPath(),furn.getId());
    }

    @Override
    public int getTotalRow() {
        String sql="SELECT COUNT(*) FROM `furn`";
//        return (Integer) queryScalar(sql);//==>这个地方可能回返回一个cast异常
        return ((Number)queryScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItems(int begin,int pageSize) {
        //limit是分页
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn LIMIT ?, ?";


        return queryMulti(sql,Furn.class,begin,pageSize);
    }

    @Override
    public int getTotalRowByName(String name) {
        String sql="SELECT COUNT(*) FROM `furn` WHERE `name` LIKE ?";
        return ((Number)queryScalar(sql,"%"+name+"%")).intValue();

    }


    @Override
    public List<Furn> getPageItemsByName(int begin, int pageSize, String name) {
        String sql = "SELECT `id`, `name` , `maker`, `price`, `sales`, `stock`, `img_path` imgPath FROM furn WHERE `name` LIKE ? LIMIT ?, ?";
        return queryMulti(sql,Furn.class,"%"+name+"%",begin,pageSize);
    }

//    @Override
//    public int addFurn(Furn furn) {
//        return 0;
//    }
}
