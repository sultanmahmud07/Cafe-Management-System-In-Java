package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.java1234.model.Book;
import com.java1234.util.StringUtil;

public class BookDao {

    /**
     * Add Book
     * @param con Connection
     * @param book Book
     * @return int
     * @throws Exception
     */
    public int add(Connection con, Book book) throws Exception {
        String sql = "insert into t_book values(null,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, book.getBookName());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3, book.getSex());
        pstmt.setFloat(4, book.getPrice());
        pstmt.setString(5, book.getBookDesc());
        pstmt.setInt(6, book.getBookTypeId());
        return pstmt.executeUpdate();
    }

    /**
     * Query Books
     * @param con Connection
     * @param book Book
     * @return ResultSet
     * @throws Exception
     */
    public ResultSet list(Connection con, Book book) throws Exception {
        StringBuffer sb = new StringBuffer("select * from t_book b,t_booktype bt where b.bookTypeId=bt.id");
        if (StringUtil.isNotEmpty(book.getBookName())) {
            sb.append(" and b.bookName like '%" + book.getBookName() + "%'");
        }
        if (StringUtil.isNotEmpty(book.getAuthor())) {
            sb.append(" and b.author like '%" + book.getAuthor() + "%'");
        }
        if (book.getBookTypeId() != null && book.getBookTypeId() != -1) {
            sb.append(" and b.bookTypeId=" + book.getBookTypeId());
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    /**
     * Delete Book
     * @param con Connection
     * @param id String
     * @return int
     * @throws Exception
     */
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from t_book where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /**
     * Update Book
     * @param con Connection
     * @param book Book
     * @return int
     * @throws Exception
     */
    public int update(Connection con, Book book) throws Exception {
        String sql = "update t_book set bookName=?,author=?,sex=?,price=?,bookDesc=?,bookTypeId=? where id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, book.getBookName());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3, book.getSex());
        pstmt.setFloat(4, book.getPrice());
        pstmt.setString(5, book.getBookDesc());
        pstmt.setInt(6, book.getBookTypeId());
        pstmt.setInt(7, book.getId());
        return pstmt.executeUpdate();
    }
    
    /**
     * Check if books exist under a specific category
     * @param con Connection
     * @param bookTypeId String
     * @return boolean
     * @throws Exception
     */
    public boolean existsBookByBookTypeId(Connection con, String bookTypeId) throws Exception {
        String sql = "select * from t_book where bookTypeId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, bookTypeId);
        ResultSet rs = pstmt.executeQuery();
        return rs.next();
    }
}
