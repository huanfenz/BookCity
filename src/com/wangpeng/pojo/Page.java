package com.wangpeng.pojo;

import java.util.List;

/**
 * page是分页的模型对象
 * @param <T> javaBean类
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;  //每页的记录数

    private Integer pageNum;                    //当前页码
    private Integer pageTotal;                  //总页数
    private Integer pageSize = PAGE_SIZE;       //每页大小
    private Integer itemsCount;                 //总记录数
    private List<T> items;                      //当前页的数据
    private String url;                         //pageNav的响应服务器地址

    public Page() {
    }

    public Page(Integer pageNum, Integer pageTotal, Integer pageSize, Integer itemsCount, List<T> items, String url) {
        this.pageNum = pageNum;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.itemsCount = itemsCount;
        this.items = items;
        this.url = url;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
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

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", itemsCount=" + itemsCount +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
