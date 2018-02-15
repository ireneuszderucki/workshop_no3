package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class UserMgmt
 */
@WebServlet("/UserMgmt")
public class UserMgmt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMgmt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			ArrayList<User> users = UserDao.loadAllUsers(conn);
			if (users.isEmpty()) {
				String defaultMsg = "No users to display";
				request.setAttribute("defaultMsg", defaultMsg);
			}
			else {
				request.setAttribute("users", users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/UserMgmt.jsp")
		.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			int id = Integer.parseInt(request.getParameter("id"));
			int groupId = Integer.parseInt(request.getParameter("editedGroupId"));
			String username = request.getParameter("editedUsername");
			String password = request.getParameter("editedPassword");
			String email = request.getParameter("editedEmail");
			String editUserSubmit = request.getParameter("editUserSubmit");
			if (editUserSubmit.equals("Submit edition")) {
				if (MultiHelper.atLeastOneChar(username) &&
					MultiHelper.atLeastOneChar(password) &&
					MultiHelper.checkEmail(email)) {
					User user = new User(groupId, username, password, email);
					user.setId(id);
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
