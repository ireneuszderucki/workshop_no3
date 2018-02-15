package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;
import pl.coderslab.services.DbUtil;
import pl.coderslab.services.MultiHelper;

/**
 * Servlet implementation class UserEdit
 */
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			int id = Integer.parseInt(request.getParameter("id"));
			User user = UserDao.loadUserById(conn, id);
			request.setAttribute("user", user);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/UserEdit.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			int groupId = Integer.parseInt(request.getParameter("newGroupId"));
			String username = request.getParameter("newUsername");
			String password = request.getParameter("newPassword");
			String email = request.getParameter("newEmail");
			String newUserSubmit = request.getParameter("newUserSubmit");
			if (newUserSubmit.equals("Submit new user")) {
				if (MultiHelper.atLeastOneChar(username) &&
					MultiHelper.atLeastOneChar(password) &&
					MultiHelper.checkEmail(email)) {
					User user = new User(groupId, username, password, email);
					UserDao.saveUserToDB(conn, user);
					response.sendRedirect("UserMgmt");
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

}
