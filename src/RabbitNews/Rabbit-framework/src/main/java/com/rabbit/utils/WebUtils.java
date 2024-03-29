package com.rabbit.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtils {

//    public static String renderString(HttpServletResponse response,String string){
//        try{
//            response.setStatus(200);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().print(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
        public static String renderString(HttpServletResponse response, String string) {
            try {
                response.setStatus(200);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                response.getWriter().print(string);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    response.getWriter().flush();
                    response.getWriter().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    public static void setDownLoadHeader(String filename,HttpServletResponse response)throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fname= URLEncoder.encode(filename,"UTF-8").replaceAll("\\+","%20");
        response.setHeader("Content-disposition","attachment；filename="+fname);
    }
}
