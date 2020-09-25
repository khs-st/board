package com.kb.www.common;

import java.util.regex.Pattern;

//정규 표현식 검사
public class RegExp {
    public static final int PAGE_NUM = 0;
    public static final int ARTICLE_SUBJECT = 1;
    public static final int ARTICLE_CONTENT=2;


    public static final String EXP_PAGE_NUM = "[0-9]$";
    public static final String EXP_ARTICLE_SUBJECT = "^.{1,100}$";
    public static final String EXP_ARTICLE_CONTENT="^.{1,65535}$";

    public static boolean checkString(int type, String data) {
        boolean result = false;
        switch (type) {
            case PAGE_NUM:
                result = Pattern.matches(EXP_PAGE_NUM, data);
                break;
            case ARTICLE_SUBJECT:
                result=Pattern.matches(EXP_ARTICLE_SUBJECT,data);
                break;
            case ARTICLE_CONTENT:
                result=Pattern.matches(EXP_ARTICLE_CONTENT,data);
                break;
        }
        return result;
    }
}
