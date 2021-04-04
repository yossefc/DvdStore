package in.co.dvd.store.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import in.co.dvd.store.controller.BaseCtl;
import in.co.dvd.store.bean.BaseBean;
import in.co.dvd.store.bean.UserBean;
import in.co.dvd.store.exception.ApplicationException;
import in.co.dvd.store.model.UserModel;
import in.co.dvd.store.util.DataUtility;
import in.co.dvd.store.util.DataValidator;
import in.co.dvd.store.util.ServletUtility;



/**
 * Servlet implementation class LoginCtl
 */
/**
 * Login functionality Controller. Performs operation for Authentication, and
 * Session Creation and Give permission to access all Functionality regarding
 * Role

 * 
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl  extends BaseCtl {

	private static final long serialVersionUID = 1L;
	
	public static final String OP_REGISTER = "Register";
	public static final String OP_SIGN_IN = "SignIn";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_LOG_OUT = "logout";
	public static String HIT_URI = null;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	protected boolean validate(HttpServletRequest request) {

		
		boolean pass = true;
		String op = request.getParameter("operation");
		
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}
		
		
		String login = request.getParameter("login");
		
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", "Login Id is required");
			pass = false;
		
		} /*
			 * else if (!DataValidator.isEmail(login)) { request.setAttribute("login",
			 * "Login Id is invalid"); pass = false; }
			 */
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "Password is required");
			pass = false;
		}
		return pass;
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		
		bean.setPassword(DataUtility.getString(request.getParameter("password")));


		return bean;
	}

	/**
	 * Display Login form
	 * 
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		HttpSession session = request.getSession(true);
		String op = DataUtility.getString(request.getParameter("operation"));
		
		UserModel model = new UserModel();
		
		
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			UserBean userBean;
			try {
				userBean = model.findByPK(id);
				ServletUtility.setBean(userBean, request);
		
			} catch (Exception e) {
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_LOG_OUT.equals(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility.setSuccessMessage("You have been logged out successfully", request);
			
			ServletUtility.forward(OBSView.LOGIN_VIEW, request, response);
			return;
		}
		//si le client est connecte et on rentree dans le site /loginctl il le renvoie dans welcom
		if (session.getAttribute("user") != null) {
			ServletUtility.redirect(OBSView.WELCOME_CTL, request, response);
			return;
		}
		//il renvoie dans login avec tt les parametres
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Submit Logic
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get Model
		UserModel model = new UserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			System.out.println(bean.getLogin());
			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				
				if (bean != null) {
					session.setAttribute("user", bean);
					session.setMaxInactiveInterval(10 * 6000);

					
					// save state of session remember URL
					String uri = request.getParameter("uri");
					
					
					if (uri == null || "null".equalsIgnoreCase(uri)) {
						System.out.println("welcome1");
						ServletUtility.redirect(OBSView.WELCOME_VIEW, request, response);
						return;
					} else {
						ServletUtility.redirect(uri, request, response);
					}
					return;
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
					ServletUtility.forward(getView(), request, response);
					return;
				}

			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				
				return;
			}
		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			System.out.println("ici je doit rentrer");
			ServletUtility.redirect(OBSView.USER_REGISTRATION_VIEW, request, response);
			
		return;
		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return OBSView.LOGIN_VIEW;
	}

}
