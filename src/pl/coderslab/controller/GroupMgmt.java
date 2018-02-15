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

import pl.coderslab.dao.GroupDao;
import pl.coderslab.model.Group;
import pl.coderslab.services.DbUtil;
import pl.coderslab.services.MultiHelper;

/**
 * Servlet implementation class GroupMgmt
 */
@WebServlet("/GroupMgmt")
public class GroupMgmt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupMgmt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			ArrayList<Group> groups = GroupDao.loadAllGroups(conn);
			if (groups.isEmpty()) {
				String defaultMsg = "No groups to display";
				request.setAttribute("defaultMsg", defaultMsg);
			}
			else {
				request.setAttribute("groups", groups);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/GroupMgmt.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Connection conn = DbUtil.getConn();
			String editedName = request.getParameter("editedName");
			int id = Integer.parseInt(request.getParameter("id"));
			String editGroupSubmit = request.getParameter("editGroupSubmit"); //value="Submit edition"
			if (editGroupSubmit.equals("Submit edition")) {
				if (MultiHelper.atLeastOneChar(editedName)) {
					Group group = new Group(editedName);
					group.setId(id);
					GroupDao.saveGroupToDB(conn, group);
					response.sendRedirect("GroupMgmt");
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
