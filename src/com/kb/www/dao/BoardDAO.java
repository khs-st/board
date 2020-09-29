package com.kb.www.dao;

import com.kb.www.vo.ArticleVO;
import com.kb.www.vo.MemberHisoryVO;
import com.kb.www.vo.MemberVO;

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
            pstmt = con.prepareStatement("select " + "b.num" +
                    ",m.mb_id," +
                    "b.subject," +
                    "b.hit," +
                    "b.wdate," +
                    "b.udate," +
                    "b.ddate" +
                    " from boardtest b" +
                    " inner join member m on b.mb_sq = m.sq");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ArticleVO vo = new ArticleVO();
                vo.setArticleNum(rs.getInt("num"));
                vo.setArticleTitle(rs.getString("subject"));
                vo.setHit(rs.getInt("hit"));
                vo.setWriteDate(rs.getString("wdate"));
                vo.setUpdateDate(rs.getString("udate"));
                vo.setDeleteDate(rs.getString("ddate"));
                vo.setId(rs.getString("mb_id"));
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
            pstmt = con.prepareStatement("select" +
                    " b.num" +
                    ", b.mb_sq" +
                    ", b.subject" +
                    ", b.content" +
                    ", b.hit" +
                    ", b.wdate" +
                    ", b.udate" +
                    ", b.ddate" +
                    ", m.mb_id" +
                    " from boardtest b" +
                    " inner join member m on b.mb_sq = m.sq" +
                    " where num=?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vo2 = new ArticleVO();
                vo2.setArticleNum(rs.getInt("num"));
                vo2.setArticleTitle(rs.getString("subject"));
                vo2.setArticleContent(rs.getString("content"));
                vo2.setHit(rs.getInt("hit"));
                vo2.setWriteDate(rs.getString("wdate"));
                vo2.setUpdateDate(rs.getString("udate"));
                vo2.setDeleteDate(rs.getString("ddate"));
                vo2.setId(rs.getString("mb_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return vo2;
    }

    //조회수
    public int updateHitCount(int num) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("update boardtest set hit=hit+1 where num=?");
            pstmt.setInt(1, num);
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
        }
        return count;
    }


    public int insertArticle(ArticleVO vo) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("insert into boardtest (mb_sq,subject, content) values(?,?,?)");
            pstmt.setInt(1, vo.getMb_sq());
            pstmt.setString(2, vo.getArticleTitle());
            pstmt.setString(3, vo.getArticleContent());
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
        String NowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        vo.setUpdateDate(NowDate);
        int count = 0;
        try {
            pstmt = con.prepareStatement("update boardtest set subject=?, content=?, udate=? where num=?");
            pstmt.setString(1, vo.getArticleTitle());
            pstmt.setString(2, vo.getArticleContent());
            pstmt.setString(3, vo.getUpdateDate());
            //pstmt.setString(3, new SimpleDateFormat("yyyy-mm-dd HH:mm").format(new Date()));
            pstmt.setInt(4, vo.getArticleNum());
            count = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    //Member
    public int insertMember(MemberVO memberVO) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("insert into member(mb_id, mb_pw) values(?,?)");
            pstmt.setString(1, memberVO.getMb_id());
            pstmt.setString(2, memberVO.getMb_pwd());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    public int getMemberSequence(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int mb_sq = 0;
        try {
            pstmt = con.prepareStatement("select sq from member where mb_id=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                mb_sq = rs.getInt("sq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return mb_sq;
    }

    public int insertMemberHistory(MemberHisoryVO memberHisoryVO) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("insert into member_history (mb_sq,evt_type) values (?,?)");
            pstmt.setInt(1, memberHisoryVO.getMb_sq());
            pstmt.setInt(2, memberHisoryVO.getEvt_type());
            count = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    public MemberVO getMember(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO vo = null;
        try {
            //binary는 대소문자 구분 mysqldb는 대소문자 구분해야함
            pstmt = con.prepareStatement("select sq,mb_id,mb_pw from member where binary(mb_id)=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vo = new MemberVO();
                vo.setMb_sq(rs.getInt("sq"));
                vo.setMb_id(rs.getString("mb_id"));
                vo.setMb_pwd(rs.getString("mb_pw"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return vo;
    }

    public int updateLoginState(MemberVO memberVO) {
        PreparedStatement pstmt = null;
        int count = 0;
        try {
            pstmt = con.prepareStatement("update member set login_st=? where sq=?");
            pstmt.setBoolean(1, memberVO.isLogin_st());
            pstmt.setInt(2, memberVO.getMb_sq());
            count = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return count;
    }

    public String getWriterId(int num) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String id = null;
        try {
            pstmt = con.prepareStatement("select m.mb_id from boardtest b inner join member m on b.mb_sq = m.sq where num=?");
            pstmt.setInt(1, num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getString("mb_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
        }
        return id;
    }

    public ArrayList<MemberHisoryVO> getMemberHistory(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<MemberHisoryVO> list = new ArrayList<MemberHisoryVO>();
        try {
            pstmt = con.prepareStatement("select" +
                    " mh.evt_type, mh.dttm" +
                    " from member m" +
                    " left join member_history mh on m.sq=mh.mb_sq" +
                    " where mb_id=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MemberHisoryVO vo = new MemberHisoryVO();
                vo.setEvt_type(rs.getInt("evt_type"));
                vo.setDttm(rs.getString("dttm"));
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

}
