package com.kb.www.atcion;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
        BoardService svc=new BoardService();
        String num=request.getParameter("num");
        ArticleVO article=svc.getArticleDetail(num);
        ActionForward forward=new ActionForward();
        request.setAttribute("num",article);
        forward.setPath("/views/detail.jsp");
        return forward;
    }
}
