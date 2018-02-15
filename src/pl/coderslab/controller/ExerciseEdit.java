package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.services.DbUtil;
import pl.coderslab.services.MultiHelper;

/**
 * Servlet implementation class ExerciseEdit
 */
@WebServlet("/ExerciseEdit")
public class ExerciseEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseEdit() {
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
			Exercise exercise = ExerciseDao.loadExerciseById(conn, id);
			request.setAttribute("exercise", exercise);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/ExerciseEdit.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			String title = request.getParameter("newTitle");
			String description = request.getParameter("newDescription");
			String newExerciseSubmit = request.getParameter("newExerciseSubmit");
			if (newExerciseSubmit.equals("Submit new exercise")) {
				if (MultiHelper.atLeastOneChar(title) &&
					MultiHelper.atLeastOneChar(description)) {
					Exercise exercise = new Exercise(title, description);
					ExerciseDao.saveExerciseToDB(conn, exercise);
					response.sendRedirect("ExerciseMgmt");
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
