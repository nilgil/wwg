package com.project.wwg.plan.dao;

import com.project.wwg.plan.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JdbcItemsDao implements ItemsDao {

    private final DataSource dataSource;

    @Autowired
    public JdbcItemsDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public List<Item> searchItems(String keyword) {
        return null;
    }

    @Override
    public int insertItem(Item item) {
        String sql = "insert into plan_item (title, info, lat, lng, address, photo, phone) values (?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getInfo());
            pstmt.setBigDecimal(3, item.getLat());
            pstmt.setBigDecimal(4, item.getLng());
            pstmt.setString(5, item.getAddress());
            pstmt.setString(6, item.getPhoto());
            pstmt.setString(7, item.getPhone());
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertItems(List<Item> items) {

        String sql = "insert into plan_item (title, info, lat, lng, address, photo, phone) values (?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;
        try {
            conn = getConnection();
            for (Item item : items) {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, item.getTitle());
                pstmt.setString(2, item.getInfo());
                pstmt.setBigDecimal(3, item.getLat());
                pstmt.setBigDecimal(4, item.getLng());
                pstmt.setString(5, item.getAddress());
                pstmt.setString(6, item.getPhoto());
                pstmt.setString(7, item.getPhone());
                result += pstmt.executeUpdate();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteAllItems() {
        String sql = "delete from plan_item";
        Connection conn = null;
        Statement st = null;
        int result = 0;
        try {
            conn = getConnection();
            st = conn.createStatement();
            result = st.executeUpdate(sql);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
