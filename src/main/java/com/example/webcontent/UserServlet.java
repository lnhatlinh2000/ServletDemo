package com.example.webcontent;

import com.example.dao.AbstractDAO;
import com.example.dao.UserDAO;
import com.example.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
         String action = request.getServletPath();

         try {
             switch (action) {
                 case "/new":
                     showNewForm(request, response);
                     break;
                 case "/insert":
                     insertUser(request, response);
                     break;
                 case "/delete":
                     deleteUser(request, response);
                     break;
                 case "/edit":
                     showEditForm(request, response);
                     break;
                 case "/update":
                     updateUser(request, response);
                     break;
                 default:
                     listUser(request, response);
                     break;
             }
         } catch (Exception e){
             e.printStackTrace();
         }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> userList = userDao.getAll("Users");
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Users usersId = userDao.findById(Users.class, id);
        request.setAttribute("user", usersId);
        request.getRequestDispatcher("user-form.jsp").forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pwd");

        Users users = new Users(name, email, password);
        userDao.save(users);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pwd");

        Users users = new Users(id, name, email, password);
        userDao.update(users);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id =  Integer.parseInt(request.getParameter("id"));
        Users users = userDao.findById(Users.class, id);

        userDao.delete(users);
        response.sendRedirect("list");
    }
}
