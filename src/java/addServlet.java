/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NGUYEN THU HA
 */
public class addServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<td>");
            out.println("Cat id :");
            out.println("</td>");
            out.println("<td>");
            out.println("<input type='text' name='txtCatid' value='"+request.getParameter("slcName")+"'>");
             out.println("</td>");
            out.println("</tr>");
            out.println("<td>Product Name</td>");
            out.println("<td><input type=\"text\" name=\"txtProductName\" value=\"\" /></td>");
            out.println(" <tr>\n" +
"                    <td>Available</td>\n" +
"                    <td><input type=\"text\" name=\"txtAvai\" value=\"\" /></td>\n" +
"                </tr>");
            out.println("<tr>\n" +
"                    <td>Description</td>\n" +
"                    <td><textarea name=\"txtDescription\" rows=\"4\" cols=\"20\">\n" +
"                        </textarea></td>\n" +
"                </tr>");
            out.println("  <tr>\n" +
"                    <td>Price:</td>\n" +
"                    <td><input type=\"text\" name=\"txtPrice\" value=\"\" /></td>\n" +
"                </tr>");         
            out.println("</table>");
            
            out.println("<input type=\"submit\" name=\"btnSave\" value=\"Save\" />");
            out.println("  <input type=\"button\" value=\"All Categories\" onclick=\"window.location = './index.jsp'\"/>");
             if (request.getParameter("btnSave") != null) {
                    int id = Integer.parseInt(request.getParameter("txtCatid"));
                    String name = request.getParameter("txtProductName");

                    String avai = request.getParameter("txtAvai");
                    String des = request.getParameter("txtDescription");
                    double price = Double.parseDouble(request.getParameter("txtPrice"));

    //                    if(request.getParameter("Hardware").equals("ON")){
    //                        ProblemEncountered = "Hardware";
    //                    }else{
    //                        ProblemEncountered = "Software";
    //                    }
    //                    String status = request.getParameter("slcstatus");
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
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
