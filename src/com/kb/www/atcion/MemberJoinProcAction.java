package com.kb.www.atcion;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.BCrypt;
import com.kb.www.common.RegExp;
import com.kb.www.service.BoardService;
import com.kb.www.vo.MemberHisoryVO;
import com.kb.www.vo.MemberVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.kb.www.common.BCrypt.*;
import static com.kb.www.common.RegExp.*;
import static com.kb.www.constants.Constants.*;


public class MemberJoinProcAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mb_id = request.getParameter("id");
        String mb_pwd = request.getParameter("pwd");
        String pwd_confirm = request.getParameter("pwd_confirm");
        //sce개념 null검사부터 해야함. 만약 안하면 프로그램이 멈춘다.
        //중요!!!!!! 정규표현식 검사하는 클래스파일 만들어 한번에 사용하기 RegExp클래스파일로 사용
        if (mb_id == null || mb_id.equals("")
                || !RegExp.checkString(MEMBER_ID, mb_id)
                || mb_pwd == null
                || mb_pwd.equals("")
                || !RegExp.checkString(MEMBER_PWD, mb_pwd)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
            out.close();
            return null;
        }

        if (!mb_pwd.equals(pwd_confirm)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
            out.close();
            return null;
        }

        //Member객체이용
        MemberVO memberVO = new MemberVO();
        memberVO.setMb_id(mb_id);
        //비밀번호 암호화(BCrypt 자바클래스 활용)
        memberVO.setMb_pwd(BCrypt.hashpw(mb_pwd,gensalt(12)));

        MemberHisoryVO memberHisoryVO=new MemberHisoryVO();
        memberHisoryVO.setEvt_type(MEMBER_HISTORY_EVENT_JOIN);

        BoardService svc=new BoardService();
        if(!svc.joinMember(memberVO,memberHisoryVO)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.println("<script>alert('회원가입에 실패하였습니다.'); location.href='/';</script>");
            out.close();
        }
        ActionForward forward = new ActionForward();
        forward.setPath("/list.do");
        forward.setRedirect(true);
        return forward;
    }
}
