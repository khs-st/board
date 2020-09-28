package com.kb.www.common;

import com.kb.www.atcion.MemberLogoutAction;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Enumeration;
import java.util.Hashtable;

public class LoginManager implements HttpSessionBindingListener {
    private static Hashtable LoginUsers = new Hashtable<>();

    private LoginManager() {
        super();
    }

    public static LoginManager getInstance() {
        return LoginManager.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LoginManager INSTANCE = new LoginManager();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        LoginUsers.put(event.getSession(), event.getName());
    }

    //로그인 되어있는 리스트에서 제거하는건 valueUnbound에서 함
    //Unbound에서 하는 이유는 로그아웃 버튼을 누를때 뿐만 아니라 웹브라우저 종료나 컴퓨터를 종료할때도 있기 때문에
    //전부 다 처리하기 위해서 Unbound로 묶어 event를 처리한다.
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        Action action=new MemberLogoutAction();
        ((MemberLogoutAction) action).logoutProc(getMemberId(event.getSession()));
        LoginUsers.remove(event.getSession());
    }

    //로그아웃버튼을 직접눌렀을때 사용하는 함수
    public void removeSession(String id) {
        Enumeration e = LoginUsers.keys();
        HttpSession session = null;
        while (e.hasMoreElements()) {
            session = (HttpSession) e.nextElement();
            //session파괴하고 다시 세션 넣음
            if (LoginUsers.get(session).equals(id)) {
                session.invalidate();
            }
        }
    }

    //sessionId가 key값 (접속하면 sessionId가 발급이 된다.)
    public boolean isLogin(String sessionId) {
        boolean isLogin = false;
        Enumeration e = LoginUsers.keys();
        String key = "";
        while (e.hasMoreElements()) {
            key = (String) e.nextElement();
            // 발급받은 sessionId와 가지고 있는 것과 같으면 true로 리턴되어 세션 확인 가능
            if (sessionId.equals(key)) {
                isLogin = true;
            }
        }
        return isLogin;
    }

    public void setSession(HttpSession session, String id){
        session.setAttribute(id,this);  //session에 있는 모든 정보들을 아이디로 저장한다는 의미 (this는 session을 의미한다.)
    }
    public String getMemberId(HttpSession session){
        return (String) LoginUsers.get(session);
    }
}
