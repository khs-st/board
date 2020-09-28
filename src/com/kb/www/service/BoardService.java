package com.kb.www.service;

import com.kb.www.dao.BoardDAO;
import com.kb.www.vo.ArticleVO;
import com.kb.www.vo.MemberHisoryVO;
import com.kb.www.vo.MemberVO;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kb.www.common.JdbcUtil.*;

//ArticleListAction에서 호출해야한다.
public class BoardService {
    public ArrayList<ArticleVO> getArticleList() {
        BoardDAO dao = BoardDAO.getInstance();
        //JdbcUtil의 getConnection을 이용해서 mysqldb와 연결
        Connection con = getConnection();
        //dao와 mysqldb의 데이터를 con을 이용해서 공유
        dao.setConnection(con);
        ArrayList<ArticleVO> list = dao.getArticleList();
        close(con);
        return list;
    }

    public ArticleVO getArticleDetail(int num) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArticleVO article = dao.getArticleDetail(num);
        close(con);
        return article;
    }

    public boolean insertArticle(ArticleVO vo) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess = false;
        int count = dao.insertArticle(vo);
        if (count > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
            return isSuccess;
        }
        close(con);
        return isSuccess;
    }

    public boolean deleteArticle(int num) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess = false;
        int count = dao.deleteArticle(num);
        if (count > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
            return isSuccess;
        }
        close(con);
        return isSuccess;
    }

    public boolean updateArticle(ArticleVO vo) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess = false;
        int count = dao.updateArticle(vo);
        if (count > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSuccess;
    }

    public boolean joinMember(MemberVO memberVO, MemberHisoryVO memberHisoryVO) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess = false;

        int count_01 = dao.insertMember(memberVO);
        memberHisoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
        //auto increment인 mb_sq를 Memberhistory 테이블에 저장!
        int count_02 = dao.insertMemberHistory(memberHisoryVO);
        //둘중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSuccess;
    }

    public MemberVO getMember(String id) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        MemberVO vo = dao.getMember(id);
        close(con);
        return vo;
    }

    //로그인
    public boolean loginMember(MemberVO memberVO, MemberHisoryVO memberHisoryVO) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess = false;
        int count_01 = dao.updateLoginState(memberVO);
        memberHisoryVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
        //auto increment인 mb_sq를 Memberhistory 테이블에 저장!
        int count_02 = dao.insertMemberHistory(memberHisoryVO);
        //둘중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSuccess;
    }

    //로그아웃
    public boolean logoutMember(MemberVO memberVO, MemberHisoryVO memberHisoryVO) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess = false;
        memberVO.setMb_sq(dao.getMemberSequence(memberVO.getMb_id()));
        memberHisoryVO.setMb_sq(memberVO.getMb_sq());
        int count_01 = dao.updateLoginState(memberVO);
        //auto increment인 mb_sq를 Memberhistory 테이블에 저장!
        int count_02 = dao.insertMemberHistory(memberHisoryVO);
        //둘중 하나라도 0보다 작으면 member 테이블에 커밋이 안됨
        if (count_01 > 0 && count_02 > 0) {
            commit(con);
            isSuccess = true;
        } else {
            rollback(con);
        }
        close(con);
        return isSuccess;
    }

    public int getMemberSequence(String id) {
        BoardDAO dao = BoardDAO.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        int sq=dao.getMemberSequence(id);
        close(con);
        return sq;
    }
}
