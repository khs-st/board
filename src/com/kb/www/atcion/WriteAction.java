package com.kb.www.atcion;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class WriteAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
        BoardService svc=new BoardService();
        String subject=request.getParameter("subject");
        String content=request.getParameter("content");
        svc.getWriteArticle(subject,content);
        ActionForward forward=new ActionForward();
        forward.setPath("/list");
        return forward;
    }
}
