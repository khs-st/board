package com.kb.www.dao;

import com.kb.www.vo.ArticleVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public ArticleVO getArticleDetail(String num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArticleVO vo2 = new ArticleVO();
        try {
            pstmt = con.prepareStatement("select * from boardtest where num=" + num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
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

    public void getWriteArticle(String subject,String content) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement("insert into boardtest (subject, content) values(?,?)");
            pstmt.setString(1,subject);
            pstmt.setString(2,content);
            pstmt.executeUpdate();
            commit(con);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
    }
}
