package com.kb.www.atcion;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHisoryVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MemberHistoryAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BoardService svc=new BoardService();
        LoginManager lm=LoginManager.getInstance();
        String id=lm.getMemberId(request.getSession());
        if(id==null){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
        }
        ArrayList<MemberHisoryVO> list=svc.getMemberHistory(id);
        ActionForward forward=new ActionForward();
        request.setAttribute("list",list);
        forward.setPath("/views/MemberHistory.jsp");
        return forward;
    }
}
