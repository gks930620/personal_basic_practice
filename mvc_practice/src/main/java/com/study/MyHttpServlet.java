package com.study;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//이거 예전에 학생질문한거 이해시켜줄려고 임시로 만든거
@WebServlet("/my.wowowowowow")
public class MyHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html> <body> hyobin ");
        for(int i=0 ;  i<10 ; i++){
            out.print("hyobin....");
        }
        out.print("</body></html>");
    }
    // 처음에 개발자가 웹 개발한다는건
    // 톰캣 다운받음
    // 브라우저로 /my.wowowowowow로 요청하면 거기에 응답하는 MyServlet만들면 됨   (HttpServlet 상속받음)
    // out.print() 안에 원하는 html 내용을 '문자열'로 작성하면 됨. 자바언어니까 동적으로 이것저것 할 수 있음
    // 근데  문자열로 작성하니까  작업힘들어
    // 그래서 그냥 html 쓰는 곳에는 html 쓰고
    // 자바로 작성해야되는 부분은 <% %>해서 쓰자 ==> JSP





}
