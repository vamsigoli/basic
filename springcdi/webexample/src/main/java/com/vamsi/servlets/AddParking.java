package com.vamsi.servlets;

import java.io.IOException;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;

/**
 * Servlet implementation class AddParking
 */
@WebServlet(urlPatterns = { "/addparking", "/getavailablelocations" })
public class AddParking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddParking() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		
		int locationRequested ;

		String jsonInput = request.getParameter("reqOption");

		if (jsonInput == null || jsonInput.length() == 0) {
			throw new ServletException("parameters missing");
		}

		//all exceptions from readObject method are runtime exceptions. so no need to catch
		JsonObject parsed = null;
		try (JsonReader reader = Json.createReader(new StringReader(jsonInput))) {
			parsed = reader.readObject();
		}
		
		if (parsed!= null) {
			locationRequested = parsed.getInt("reqOption", -1);
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
