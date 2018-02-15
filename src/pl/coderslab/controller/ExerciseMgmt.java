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

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.model.Exercise;
import pl.coderslab.model.User;
import pl.coderslab.services.DbUtil;
import pl.coderslab.services.MultiHelper;

/**
 * Servlet implementation class ExerciseMgmt
 */
@WebServlet("/ExerciseMgmt")
public class ExerciseMgmt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerciseMgmt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			ArrayList<Exercise> exercises = ExerciseDao.loadAllExercises(conn);
			if (exercises.isEmpty()) {
				String defaultMsg = "No exercises to display";
				request.setAttribute("defaultMsg", defaultMsg);
			}
			else {
				request.setAttribute("exercises", exercises);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/ExerciseMgmt.jsp")
		.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConn();
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("editedTitle");
			String description = request.getParameter("editedDescription");
			String editExerciseSubmit = request.getParameter("editExerciseSubmit");
			if (editExerciseSubmit.equals("Submit edition")) {
				if (MultiHelper.atLeastOneChar(title) &&
					MultiHelper.atLeastOneChar(description)) {
					Exercise exercise = new Exercise(title, description);
					exercise.setId(id);
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
