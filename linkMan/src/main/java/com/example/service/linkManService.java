package com.example.service;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-05 12:53:16
 */

import com.example.dao.linkManDao;
import com.example.entry.PageBean;
import com.example.pojo.LinkMan;

import java.sql.SQLException;
import java.util.List;

public class linkManService {

    private linkManDao linkManDao = new linkManDao();
    public List<LinkMan> findAll() throws SQLException {
        return linkManDao.findAll();
    }

    public void add(LinkMan linkMan) throws SQLException {
        linkManDao.add(linkMan);
    }

    public void delete(String id) throws SQLException {
        linkManDao.delete(id);
    }

    public LinkMan findById(String id) throws SQLException {
        return linkManDao.findById(id);
    }

    public void update(LinkMan linkMan) throws SQLException{
        linkManDao.update(linkMan);
    }

    public PageBean<LinkMan> findByPage(Long currentPage, Integer pageSize) throws SQLException {
        PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        Long totalSize = linkManDao.findTotalSize();
        pageBean.setTotalSize(totalSize);

        Long totalPage = totalSize % pageSize == 0 ? totalSize /pageSize : (totalSize/ pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        List<LinkMan> pageList = linkManDao.findPageList(currentPage, pageSize);
        pageBean.setList(pageList);
        return pageBean;

    }
}
