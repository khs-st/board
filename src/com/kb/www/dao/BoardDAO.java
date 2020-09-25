package com.kb.www.dao;

import com.kb.www.vo.ArticleVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static com.kb.www.common.JdbcUtil.close;
import static com.kb.www.common.JdbcUtil.commit;

public class BoardDAO {
    private Connection con;

    private BoardDAO() {

    }

    public static BoardDAO getInstance() {

        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BoardDAO INSTANCE = new BoardDAO();
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public ArrayList<ArticleVO> getArticleList() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
        try {
            pstmt = con.prepareStatement("select * from boardtest");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleVO vo = new ArticleVO();
                vo.setArticleNum(rs.getInt("num"));
                vo.setArticleTitle(rs.getString("subject"));
                vo.setArticleContent(rs.getString("content"));
                vo.setHit(rs.getInt("hit"));
                vo.setWriteDate(rs.getString("wdate"));
                vo.setUpdateDate(rs.getString("udate"));
                vo.setDeleteDate(rs.getString("ddate"));
                list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return list;
    }

    public ArticleVO getArticleDetail(int num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArticleVO vo2 = null;
        try {
            pstmt = con.prepareStatement("select * from boardtest where num=" + num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vo2 = new ArticleVO();
                vo2.setArticleNum(rs.getInt("num"));
                vo2.setArticleTitle(rs.getString("subject"));
                vo2.setArticleContent(rs.getString("content"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return vo2;
    }

    public int insertArticle(ArticleVO vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("insert into boardtest (subject, content) values(?,?)");
            pstmt.setString(1, vo.getArticleTitle());
            pstmt.setString(2, vo.getArticleContent());
            count = pstmt.executeUpdate();
            commit(con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    public int deleteArticle(int num) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("delete from boardtest where num=?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
            commit(con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    public int updateArticle(ArticleVO vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("update boardtest set subject=?, content=? where num=?");
            pstmt.setString(1, vo.getArticleTitle());
            pstmt.setString(2, vo.getArticleContent());
            //pstmt.setString(3, new SimpleDateFormat("yyyy-mm-dd HH:mm").format(new Date()));
            pstmt.setInt(3,vo.getArticleNum());
            count = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }
}
