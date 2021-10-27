package com.example.dao;
/*
 * @Author: Weiyang Jiang
 * @Date: 2021-10-05 12:55:01
 */

import com.example.pojo.LinkMan;
import com.example.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class linkManDao {
    private QueryRunner runner = new QueryRunner(DruidUtil.getDataSource());
    public List<LinkMan> findAll() throws SQLException {
        String sql = "select * from linkman";
        List<LinkMan> LinkManList = runner.query(sql, new BeanListHandler<>(LinkMan.class));
        return LinkManList;
    }

    public void add(LinkMan linkMan) throws SQLException {
        String sql = "insert into linkman values (null, ?,?,?,?,?,?)";
        runner.update(sql, linkMan.getName(),linkMan.getSex(),linkMan.getAge(),linkMan.getAddress(),linkMan.getQq(),linkMan.getEmail());
    }

    public void delete(String id) throws SQLException {
        String sql = "delete from linkman where id = ?";
        runner.update(sql, id);
    }

    public LinkMan findById(String id) throws SQLException {
        String sql = "select * from linkman where id = ?";
        LinkMan linkMan = runner.query(sql, new BeanHandler<>(LinkMan.class), id);
        return linkMan;
    }

    public void update(LinkMan linkMan) throws SQLException {
        String sql = "update linkman set name = ? , sex = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        runner.update(sql, linkMan.getName(), linkMan.getSex(), linkMan.getAge(), linkMan.getAddress(), linkMan.getQq(), linkMan.getEmail(), linkMan.getId());
    }

    public List<LinkMan> findPageList(Long currentPage, Integer pageSize) throws SQLException {
        String sql = "select * from linkman limit ?, ?";
        currentPage = (currentPage - 1) * pageSize;
        List<LinkMan> linkManList = runner.query(sql, new BeanListHandler<>(LinkMan.class), currentPage, pageSize);
        return linkManList;
    }

    public Long findTotalSize() throws SQLException {
        String sql = "select count(*) from linkman";
        Long totalSize = (Long) runner.query(sql, new ScalarHandler());
        return totalSize;
    }
}
