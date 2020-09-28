package com.kb.www.atcion;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.RegExp;
import com.kb.www.service.BoardService;
import com.kb.www.vo.ArticleVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.RegExp.ARTICLE_CONTENT;
import static com.kb.www.common.RegExp.ARTICLE_SUBJECT;


public class MemberJoinFormAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward=new ActionForward();
        forward.setPath("/views/joinForm.jsp");
        forward.setRedirect(true);
        return forward;
    }
}
