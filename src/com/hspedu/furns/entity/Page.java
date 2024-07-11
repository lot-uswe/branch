package com.hspedu.furns.entity;

import java.util.List;

/**
 * Page是一个分页数据模型，是一个JavaBean，（包含了分页的各种信息）
 * @author xiaowang
 * @creat 2024/7/8 10:42
 * @Description Java Lotus
 */
//T表示泛型，因为将来分页的模型是不确定的
    //数据模型，MVC
public class Page<T> {

    //因为每页显示的多少条记录，是其他的地方也可能去使用的

    public static final Integer PAGE_SIZE = 3;

    //表示显示的当前页【表示显示的第几页】
    //前端页面返回来的
    private Integer pageNo;
    //表示每页显示的几条记录
    private Integer pageSize = PAGE_SIZE;
    //表示的共有多少页，这个属性是计算所得到的
    private Integer pageTotalCount;
    //表示一共有多少条记录，通过这pageSize
    // 和pageTotalCount所可以计算到
    //totalRow是可以在数据库DB中所获取的
    private Integer totalRow;
    //当前页所要显示的数据
    //items是在数据库所查询的
    private List<T> items;
    //用来做分页导航的字符串
    private String url;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalRow(int totalRow) {
        return this.totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
