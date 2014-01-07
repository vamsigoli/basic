package com.vamsi.servlets;


import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import javax.json.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vamsi.bean.ParkingBean;
import com.vamsi.spring.jpa.entities.ParkingSpace;
import com.vamsi.spring.jpa.types.ParkingLocation;

/**
 * Servlet implementation class AddParking
 */
@WebServlet(urlPatterns = { "/addparking", "/getavailablelocations" })
public class AddParking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @Inject
	 ParkingBean parkingBean;
	


	private static Logger logger = LoggerFactory.getLogger(AddParking.class);
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String jsonInput = request.getParameter("reqOption");

		if (jsonInput == null || jsonInput.length() == 0) {
			throw new ServletException("parameters missing");
		}

		//all exceptions from readObject method are runtime exceptions. so no need to catch
		//no need for the json object to be parsed. jquery returns the parsed string only. 
		//directly get the object you want
		//JsonObject parsed = null;
		//try (JsonReader reader = Json.createReader(new StringReader(jsonInput))) {
		//	parsed = reader.readObject();
		//}
		
		
		ParkingLocation pl = ParkingLocation.valueOf(jsonInput);
		
		List <ParkingSpace> slots = parkingBean.findAvailableParking(pl);
		
		JsonArrayBuilder jsonResponseArrayBuilder = Json.createArrayBuilder();
		
		
		for (ParkingSpace ps : slots) {
			jsonResponseArrayBuilder.add(Json.createObjectBuilder().add("lotnumber",ps.getLot())
																   .add("id", ps.getId()));
		}
		
		
		JsonObjectBuilder jsonResponseBuilder = Json.createObjectBuilder();
		jsonResponseBuilder.add("locations", jsonResponseArrayBuilder);
		
		JsonObject jsonResponse = jsonResponseBuilder.build();
		
		//jsonResponseArrayBuilder.build();
		
		
		response.setContentType("application/json");
		
		
		logger.info("sending response as " + jsonResponse.toString());
		
		response.getWriter().write(jsonResponse.toString());
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
