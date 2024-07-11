package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.FurnDAO;
import com.hspedu.furns.dao.impl.FurnDAOImpl;
import com.hspedu.furns.entity.Furn;
import com.hspedu.furns.entity.Page;
import com.hspedu.furns.service.FurnService;

import java.util.List;

/**
 * @author xiaowang
 * @creat 2024/7/7 14:59
 * @Description Java Lotus
 */
public class FurnServiceImpl implements FurnService {

    //定义一个属性 FurnDAO对象
    private FurnDAO furnDAO = new FurnDAOImpl();
    @Override
    public List<Furn> queryFurns() {
        return furnDAO.queryFurns();
    }

    @Override
    public int addFurn(Furn furn) {

        return furnDAO.addFurn(furn);
    }

    @Override
    public int deleteFurnById(int id) {
        return furnDAO.deleteFurnById(id);
    }

    @Override
    public Furn queryFurnById(int id) {
        return furnDAO.queryFurnById(id);
    }

    @Override
    public int updateFurn(Furn furn) {
        return furnDAO.updateFurn(furn);
    }

    @Override
    public Page<Furn> page(int pageNo, int pageSize) {

        //这个方法要去返回一个page对象
        //然后根据实际情况填充属性
        Page<Furn> page = new Page<>();

//        private Integer pageNo;
//        //表示每页显示的几条记录
//        private Integer pageSize = PAGE_SIZE;
//        //表示的共有多少页，这个属性是计算所得到的
//        private Integer pageTotalCount;
//        //表示一共有多少条记录，通过这pageSize
//        // 和pageTotalCount所可以计算到
//        //totalRow是可以在数据库DB中所获取的
//        private Integer totalRow;
//        //当前页所要显示的数据
//        //items是在数据库所查询的
//        private List<T> items;
//        //用来做分页导航的字符串
//        private String url;



        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        int totalRow = furnDAO.getTotalRow();
        page.getTotalRow(totalRow);
        //pageTotalCount 是计算得到的

        int pageTotalCount = totalRow/pageSize;
        if (totalRow%pageSize>0){
            pageTotalCount+=1;
        }

        page.setPageTotalCount(pageTotalCount);

        //private List<T> items;
        //begin是要计算出来的
        //取决于pageNO和pageSize
        //pageNo=1,pageSize=3
        //这里存在着一个问题 problem
        int begin = (pageNo-1)*pageSize;
        List<Furn> pageItems = furnDAO.getPageItems(begin, pageSize);
        page.setItems(pageItems);
        //还差一个url
        


        return page;

    }

    @Override
    public Page<Furn> pageByName(int pageNo, int pageSize, String name) {

        Page<Furn> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //根据名字来返回总的记录数
        int totalRow = furnDAO.getTotalRowByName(name);
        page.setTotalRow(totalRow);
        //pageTotalCount 是计算得到的

        int pageTotalCount = totalRow/pageSize;
        if (totalRow%pageSize>0){
            pageTotalCount+=1;
        }

        page.setPageTotalCount(pageTotalCount);

        //private List<T> items;
        //begin是要计算出来的
        //取决于pageNO和pageSize
        //pageNo=1,pageSize=3
        //这里存在着一个问题 problem
        int begin = (pageNo-1)*pageSize;
        List<Furn> pageItems = furnDAO.getPageItemsByName(begin, pageSize,name);
        page.setItems(pageItems);
        //还差一个url



        return page;
    }
}
