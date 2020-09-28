package com.io.fizmat.stringparse;

import android.util.Log;

public class DisplayString {

    public static String fixString(String st)
    {
        StringBuilder strBuffer = new StringBuilder(st);
        String thi = "15.00 (3)";
        Integer count;
        String t1 = "(1)";
        String t2 = "(2)";

        if(st.contains(thi) && (st.contains(t1) || st.contains(t2))) {
                count = st.indexOf(thi);
                strBuffer.replace(count - 1, count, "\n");
                if (st.contains(t2) && st.contains(t1))
                {
                    count = st.indexOf(t2);
                    strBuffer.replace(count - 1, count, "\n");
                }
            }

        Integer len = 35;
        String sec = "15.00 (2)";
        String lk = "(ЛК)";
        String t3 = "(3)";
        String lab = "(ЛАБ)";
        String pr = "(ПР)";
        String ct = "ст";
        String doc = "доц";
        String prof = "проф";
        String inz = "ИНОСТРАННЫЙ ЯЗЫК";

        if(st.contains(sec) && (st.contains(t1) || st.contains(t3))) {
                if (st.contains(t1) ) {
                    count = st.indexOf(sec);
                    strBuffer.replace(count - 1, count, "\n");
                }else{
                    count = st.indexOf(t3);
                    strBuffer.replace(count - 1, count, "\n");
                }
        } else if (st.contains(t1) && st.contains(t2)) {
                count = st.indexOf(t2);
                strBuffer.replace(count - 1, count, "\n");
                if (st.contains(t3)) {
                    count = st.indexOf(t3);
                    strBuffer.replace(count - 1, count, "\n");
                }
        } else if (st.contains(t3) && (st.contains(t2) || st.contains(t1))) {
                count = st.indexOf(t3);
                strBuffer.replace(count - 1, count, "\n");
        } else if (st.contains(lab) || st.contains(pr) || st.contains(lk)) {
            String scon = ")";
            if ((count = st.indexOf(scon)) < len) {
                    strBuffer.replace(count + 1, count + 2, "\n");
                }
        } else if (st.contains(doc)) {
                if ((count = st.indexOf(doc)) < len) {
                    strBuffer.replace(count - 1, count, "\n");
                }
        } else if (st.contains(prof)) {
                if ((count = st.indexOf(prof)) < len) {
                    strBuffer.replace(count - 1, count, "\n");
                }
        } else if (st.contains(ct)) {
                if ((count = st.indexOf(ct)) < len) {
                    strBuffer.replace(count - 1, count, "\n");
                }
            }
        else if(st.contains(inz)) {
                strBuffer.replace(16, 17, "\n");
        }

        return strBuffer.toString();
    }
}
