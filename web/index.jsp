<%-- 
    Document   : index
    Created on : Mar 24, 2015, 1:04:02 PM
    Author     : NGUYEN THU HA
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="addServlet"> 
            <p>Select a category: <select name="slcName">
<!--          <option value="All"></option>-->
            <%
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                Connection conn = DriverManager.getConnection("jdbc:odbc:WorkshopJava");
                String sql = "select * from Pro_Categories";
                ResultSet rs = conn.createStatement().executeQuery(sql);
                while(rs.next()){
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                %>
                <option value="<%=id%>"> <%=name%> </option>
                <%
                }
                conn.close();
            %>
            </select></p>
            <input type="submit" value="Go" name="btnGo"/>
          <%
              if(request.getParameter("btnGo") != null){
//                  response.sendRedirect("./add.jsp?id="+request.getParameter("slcName"));
                  response.sendRedirect("./addServlet?id="+request.getParameter("slcName"));
            }   
            %>

            
        </form>
    </body>
</html>
