package com.vamsi.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

import com.vamsi.bean.EmployeeBean;
import com.vamsi.spring.jpa.entities.Employee;
import com.vamsi.stateless.EmployeeSessionBean;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Servlet implementation class CreateEmploeeServlet
 */
@WebServlet("/employeecreate")
public class CreateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	private static Logger logger = LoggerFactory.getLogger(CreateEmployee.class);
	
	 @Resource
	    TransactionSynchronizationRegistry tsr;
	 
	 //both session bean and the no session bean will work without any problem. 
	 //session bean has transaction automatically injected while cdi bean has to explicitly 
	 //start the transaction using @Transactional annotation
	 
	 //@Transactional on servlet method did not work.
	 
	 @Inject
	 EmployeeBean empNoSessionBean;
	
	@EJB 
	EmployeeSessionBean empBean;

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(SimpleDateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@PersistenceContext(unitName="entities")
	private EntityManager em;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateEmployee() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int employeeId;
		
	try {	
		
		
		employeeId = Integer.parseInt(request.getParameter("employeeid"));

		
	}catch(Exception e) {
		throw new ServletException("Please enter a valid identification number");
	}
		
		
		Employee employee = getEmployee(employeeId);
			logger.info("employee retrieved successfully with empid " + employee.getId());
			
			request.setAttribute("employee", employee);
			
			request.getRequestDispatcher("/create.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Employee employee = new Employee();

		try {
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String jobtitle = request.getParameter("jobtitle");
			String dateofhire = request.getParameter("dateofhire");
			String salary = request.getParameter("salary");

			
			employee.setLastName(lastname);
			employee.setEmailAddress(email);
			employee.setJobTitle(jobtitle);

			employee.setDateOfHire(getDateFormat().parse(dateofhire));
			employee.setSalary(Float.valueOf(salary));
			saveEmployee(employee);
			
			logger.info("new employee created successfully with empid " + employee.getId());
			
			request.setAttribute("employee", employee);
			
			request.getRequestDispatcher("/create.jsp").forward(request, response);
			


		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Exception in creating employee ", e);

		}
		
		

	}
	
	@Transactional
	public void saveEmployee(Employee e) {
		
		if (tsr.getTransactionStatus() == Status.STATUS_ACTIVE) {
			System.out.println("transaction active. new transaction started");
		}
		else {
			System.out.println("transaction not active");
		}
		
		//empBean.createEmployee(e);
		//em.persist(e);
		
		empNoSessionBean.saveEmployee(e);
	}
	
	public Employee getEmployee(int employeeId) {
		
		return empNoSessionBean.findById(employeeId);
	}
}
