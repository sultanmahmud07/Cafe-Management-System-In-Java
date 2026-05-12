package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.BookType;
import com.java1234.util.StringUtil;

public class BookTypeDao {

    /**
     * Add Book Category
     * @param con Connection
     * @param bookType BookType
     * @return int
     * @throws Exception
     */
    public int add(Connection con, BookType bookType) throws Exception {
        String sql = "insert into t_booktype values(null,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, bookType.getBookTypeName());
        pstmt.setString(2, bookType.getBookTypeDesc());
        return pstmt.executeUpdate();
    }

    /**
     * Query Book Categories
     * @param con Connection
     * @param bookType BookType
     * @return ResultSet
     * @throws Exception
     */
    public ResultSet list(Connection con, BookType bookType) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_booktype");
        if (bookType != null && StringUtil.isNotEmpty(bookType.getBookTypeName())) {
            sb.append(" and bookTypeName like '%" + bookType.getBookTypeName() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /**
     * Delete Book Category
     * @param con Connection
     * @param id String
     * @return int
     * @throws Exception
     */
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from t_booktype where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /**
     * Update Book Category
     * @param con Connection
     * @param bookType BookType
     * @return int
     * @throws Exception
     */
    public int update(Connection con, BookType bookType) throws Exception {
        String sql = "update t_booktype set bookTypeName=?,bookTypeDesc=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, bookType.getBookTypeName());
        pstmt.setString(2, bookType.getBookTypeDesc());
        pstmt.setInt(3, bookType.getId());
        return pstmt.executeUpdate();
    }
}
