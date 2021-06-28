package reebok.servletapp;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "secondservlet", value = "/second-servlet")

/*WebServlet(urlPatterns = {"/cool-servlet", "/my-cool-servlet/*"})*/
public class MainServlet extends HttpServlet {
    private ArrayList<String> numbers;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        numbers = new ArrayList<>();
        log("Method init ");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<html><body>");
        printWriter.write("<h1> Second Servlet </h1>");
        printWriter.write("<br>");
        printWriter.write("</body></html>");
        response.getWriter().write("Method doGet");
        printWriter.close();
    }

    public void destroy() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String uri = request.getRequestURI();
        String params = formatParams(request);
        PrintWriter printWriter = response.getWriter();
        printWriter.write("Method doPost\n");
        printWriter.write("URI:" + uri + "\nParams:\n" + params + "\n");
    }

    private String formatParams (HttpServletRequest req){
        return req.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> {
                    String param = String.join(" and ", entry.getValue());
                    return entry.getKey() + " => " + param;
                })
                .collect(Collectors.joining("\n"));
    }




}