package com.hspedu.furns.service;

import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;

import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 14:56
 * @Description Java Lotus
 */
public interface FurnService {
    public List<Furn> queryFurns();

    public int addFurn(Furn furn);

    /**
     * 根据id删除家具信息
     * @param id
     * @return
     */
    public int deleteFurnById(int id);

    /**
     * 根据id返回对应的家具信息
     * @param id
     * @return
     */
    public Furn queryFurnById(int id);


    /**
     * 根据传入的furn对象进行修改
     * @param furn
     * @return
     */
    public int updateFurn(Furn furn);

    /**
     * 根据传入的pageNo和pageSize，返回对应的page对象
     * begin是需要我们计算的
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Furn> page(int pageNo,int pageSize);

    /**
     * 根据传入的值返回对应的对象
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Furn> pageByName(int pageNo,int pageSize,String name);

}
