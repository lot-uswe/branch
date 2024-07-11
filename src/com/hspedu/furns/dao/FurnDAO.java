package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Furn;

import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 14:43
 * @Description Java Lotus
 */
public interface FurnDAO {
    /**
     * 返回所有的家具信息集合，在后面我们再去考虑分页
     * @return
     */

    public List<Furn> queryFurns();

    /**
     * 将传入的Furn对象，保存到DB
     * @param furn
     * @return
     */
    public int addFurn(Furn furn);

    /**
     * 根据传入的家具的id，删除DB中对应的家具
     * @param id
     * @return 是受影响的
     */
    public int deleteFurnById(int id);

    /**
     *
     * @param id
     * @return
     */
    public Furn queryFurnById(int id);

    /**
     * 将传入的furn对象，更新到数据库，根据我们的id去更新查找
     * @param furn
     * @return
     */
    public int updateFurn(Furn furn);

    //Page哪些属性是在数据库中直接可以拿出来的
    //就把这个数据放在DAO层
    public int getTotalRow();

    //获取当前页所要显示的数据进行返回
    //begin是在第几条数据开始获取，从0开始,从哪里开始获取数据的
    //pageSize就要取出多少条数据，表示要取出多少条数据的
    public List<Furn> getPageItems(int begin,int pageSize);

    /**
     * 根据名字来获取总记录数
     * @return
     */
    public int getTotalRowByName(String name);


    /**
     * 根据这三个条件获取家具信息
     * @param begin
     * @param pageSize
     * @param name
     * @return
     */
    public List<Furn> getPageItemsByName(int begin,int pageSize,String name);

}
