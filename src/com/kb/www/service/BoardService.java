package com.kb.www.service;

import com.kb.www.dao.BoardDAO;
import com.kb.www.vo.ArticleVO;

import java.sql.Connection;
import java.util.ArrayList;

import static com.kb.www.common.JdbcUtil.*;

//ArticleListAction에서 호출해야한다.
public class BoardService {
    public ArrayList<ArticleVO> getArticleList(){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        ArrayList<ArticleVO> list=dao.getArticleList();
        close(con);
        return list;
    }
    public ArticleVO getArticleDetail(int num){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        ArticleVO article=dao.getArticleDetail(num);
        close(con);
        return article;
    }
    public boolean insertArticle(ArticleVO vo){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess=false;
        int count=dao.insertArticle(vo);
        if(count>0){
            commit(con);
            isSuccess=true;
        }
        else{
            rollback(con);
            return isSuccess;
        }
        close(con);
        return isSuccess;
    }

    public boolean deleteArticle(int num){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess=false;
        int count=dao.deleteArticle(num);
        if(count>0){
            commit(con);
            isSuccess=true;
        }
        else{
            rollback(con);
            return isSuccess;
        }
        close(con);
        return isSuccess;
    }

    public boolean updateArticle(ArticleVO vo){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        //isSucess만든이유: count로 넘기면 boolean타입도 바꾸고 데이터가 잘안나옴. 디자인패턴 적용위해서
        boolean isSuccess=false;
        int count=dao.updateArticle(vo);
        if(count>0){
            commit(con);
            isSuccess=true;
        }
        else{
            rollback(con);
        }
        close(con);
        return isSuccess;
    }
}
