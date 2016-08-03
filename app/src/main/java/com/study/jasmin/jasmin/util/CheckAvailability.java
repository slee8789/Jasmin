package com.study.jasmin.jasmin.util;

import android.widget.RadioButton;

import java.util.regex.Pattern;

/**
 * Created by leesc on 2016-07-31.
 */
public class CheckAvailability {

    // 라디오 버튼 체크 확인(단일 체크 라디오)
    public static boolean isRadioChk(RadioButton ... radioButtons) {
        for (RadioButton rB : radioButtons) {
            if (rB.isChecked()) return true;
        }
        return false;

    }

    // 이메일 확인
    public static boolean isEmail(String email) {
        if (email.isEmpty()) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return b;
    }

    //비밀번호 최소 글자수 확인
    public static boolean isMoreThan (int min, String text) {
        if(text.length() >= min) return true;
        else return false;
    }

    //비밀번호 비교
    public static boolean isSameString(String pwd1, String pwd2) {
        if (pwd1.isEmpty() || pwd2.isEmpty()) return false;
        boolean b = pwd1.equals(pwd2);
        return b;
    }

    // Null 확인(개별)
    public static boolean isNotNull(String text) {
        if (text.isEmpty()) return false;
        else return true;
    }

    // Null 확인(통합)
    public static boolean isNotNull(String ... text) {
        for (String s : text) {
            if (s.isEmpty()) return false;
        }
        return true;
    }

}
