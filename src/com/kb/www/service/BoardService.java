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
    public ArticleVO getArticleDetail(String num){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        ArticleVO article=dao.getArticleDetail(num);
        close(con);
        return article;
    }
    public void getWriteArticle(String subject,String content){
        BoardDAO dao=BoardDAO.getInstance();
        Connection con=getConnection();
        dao.setConnection(con);
        dao.getWriteArticle(subject,content);
        close(con);
    }
}
