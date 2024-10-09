/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class SaveServlet extends HttpServlet {

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
        //b1. lấy giá trị tham số từ client
        String uname= request.getParameter("uname");
        String upass = request.getParameter("upass");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        //b2. xử lý yêu cầu(truy cập CSDL để thêm mới user)
        Connection conn=null;
        PreparedStatement ps =null;
        try{
            //1 nạp driver
            Class.forName("com.micrsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Nap driver ok");
            //2. thiết lập kết nối CSDL
            conn= DriverManager.getConnection("jdbc:sqlserver://pc319;databaseName=demodb" ,"sa", "sa");
            System.out.println("Ket noi ok");
            //3. tạo dối tượng thi hành truy vấn
            ps = conn.prepareStatement("insert into user(name,password, email,country) values(?,?,?,?)");
            //truyền giá rtij cho các tham số trong câu lệnh
            ps.setString(1, uname);
            ps.setString(2, upass);
            ps.setString(3, email);
            ps.setString(4, country);
            //4. thi hành truy vấn
            int kq= ps.executeUpdate();
            //5. xử lý ket qua tra ve
            if(kq>0){
                out.println("<h2>Thêm user thành công</h2>");
            }else{
                out.println("<h2>Thêm user thất bại</h2>");
            }
            //6. dong kết nối
            conn.close();
        }catch (Exception e ){
            System.out.println("Loi:" + e.toString());
            out.println("<h2>Them user thất bại</h2>");
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveServlet at " + request.getContextPath() + "</h1>");
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
