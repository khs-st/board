<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
<head>
    <title>회원가입</title>
</head>
<script src="https://code.jquery.com/jquery-3.5.1.js"
        integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<script>
    function validateCheck() {
        var id = $('#id').val();
        var pwd = $('#pwd').val();
        var pwd_cofonrim = $('#pwd_confirm').val();

        if (!id) {
            alert("아이디를 입력해주세요.");
            $('#id').focus();
            return false;
        }
        if (!pwd) {
            alert("비밀번호를 입력해주세요.");
            $('#pwd').focus();
            return false;
        }
        if (!pwd_cofonrim) {
            alert("비밀번호확인을 입력해주세요.");
            $('#pwd_confirm').focus();
            return false;
        }
        if (!pwd_cofonrim) {
            alert("비밀번호가 다릅니다.");
            $('#pwd').val("");
            $('#pwd_confirm').val("");
            $('#pwd').focus();
            return false;
        }
        //아이디 길이, 영,소문자 체크(정규표현식)
        var regExpId=new RegExp("^[a-z0-9]{4,20}$","g");
        //아이디로 실행
        if(regExpId.exec(id)==null){
            alert("잘못된 아이디 형식입니다.");
            $('#id').val("");
            $('#id').focus();
            return false;
        }

        //비밀번호 길이, 영,소문자 체크(정규표현식)
        var regExpPwd=new RegExp("^{4,30}$","g");
        //비밀번호로 실행
        if(regExpPwd.exec(pwd)==null){
            alert("잘못된 비밀번호 형식입니다.");
            $('#pwd').val("");
            $('#pwd_confirm').val("");
            $('#pwd').focus();
            return false;
        }
    }

</script>

<body>
<form action="/joinProc.do" method="post" onsubmit="return validateCheck()">
    아이디: <input type="text" name="id" id="id" maxlength="20"><br/>
    비밀번호: <input type="password" name="pwd" id="pwd" minlength="4" maxlength="20"><br/>
    비밀번호 확인: <input type="password" name="pwd_confirm" id="pwd_confirm" minlength="4" maxlength="20">
    <br/>
    <input type="submit" value="회원가입">
    <br/>
    <button onclick="location.href='/'">취소</button>
</form>
</body>
</html>
