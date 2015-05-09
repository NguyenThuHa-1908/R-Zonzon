<%-- 
    Document   : add
    Created on : Mar 24, 2015, 1:16:47 PM
    Author     : NGUYEN THU HA
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <h1>Enter the information of Product</h1>
            <table >

                <tr>
                    <td>Cat id:</td>
                    <td>
                           <input type="text" name="txtCatid" value="<%=request.getParameter("id")%>" />
                    </td>

                </tr>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="txtProductName" value="" /></td>
                </tr>
                <tr>
                    <td>Available</td>
                    <td><input type="text" name="txtAvai" value="" /></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><textarea name="txtDescription" rows="4" cols="20">
                        </textarea></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="text" name="txtPrice" value="" /></td>
                </tr>

            </table>

            <input type="submit" name="btnSave" value="Save" />
            <input type="button" value="All Categories" onclick="window.location = './index.jsp'"/>
            <%
                if (request.getParameter("btnSave") != null) {
                    int id = Integer.parseInt(request.getParameter("txtCatid"));
                    String name = request.getParameter("txtProductName");

                    String avai = request.getParameter("txtAvai");
                    String des = request.getParameter("txtDescription");
                    double price = Double.parseDouble(request.getParameter("txtPrice"));
                    try {
                        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                        Connection conn = DriverManager.getConnection("jdbc:odbc:WorkshopJava");
                        String sql = "INSERT INTO Pro_Details(Category_ID,Name,Available,Description,Price) VALUES (?,?,?,?,?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setString(3, avai);
                        ps.setString(4,des );
                        ps.setDouble(5, price);

                        ps.executeUpdate();
                        conn.close();
                        //  response.sendRedirect("CustomerDetail.jsp");
                        out.println("Input sucessful!");
                    } catch (Exception ex) {
                        out.println("Input unsucessful!");
                        out.println(ex);
                    }
                }
            %>
        </form>
    </body>
</html>
