package in.co.dvd.store.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import in.co.dvd.store.bean.BaseBean;
import in.co.dvd.store.bean.UserBean;
import in.co.dvd.store.util.DataUtility;
import in.co.dvd.store.util.DataValidator;
import in.co.dvd.store.util.ServletUtility;



/**
 * Servlet implementation class BaseCtl
 */
/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 * 
 */
@WebServlet("/BaseCtl")
public abstract class BaseCtl extends HttpServlet
{
	/**
	 * Generic message key constant
	 */
	
	public static final String OP_SAVE = "Save";
	public static final String OP_CANCEL = "Cancel";
	public static final String OP_DELETE = "Delete";
	public static final String OP_LIST = "List";
	public static final String OP_SEARCH = "Search";
	public static final String OP_VIEW = "View";
	public static final String OP_NEXT = "Next";
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_GO = "Go";
	public static final String OP_BACK = "Back";
	public static final String OP_LOG_OUT = "Logout";
	public static final String OP_RESET = "Reset";
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_SUBMIT = "Submit";
	/**
	 * Success message key constant
	 */
	public static final String MSG_SUCCESS = "success";

	/**
	 * Error message key constant
	 */
	public static final String MSG_ERROR = "error";


	public BaseCtl() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return 
	 */

	protected boolean validate(HttpServletRequest request) {
		return true;
		
	}

	/**
	 * Loads list and other data required to display at HTML form
	 * 
	 * @param request
	 */
	protected void preload(HttpServletRequest request) {
	}

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */

	protected in.co.dvd.store.bean.BaseBean populateBean(HttpServletRequest request) {
		return null;
	}




	

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {	
		// Load the preloaded data required to display at HTML form
		preload(request);
		String op = DataUtility.getString(request.getParameter("operation"));

		// Check if operation is not DELETE, VIEW, CANCEL, and NULL then
		// perform input data validation

		System.out.println("operation ="+op);
		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)&& !OP_VIEW.equalsIgnoreCase(op)&& !OP_DELETE.equalsIgnoreCase(op)&&!OP_RESET.equalsIgnoreCase(op)) {
			// Check validation, If fail then send back to page with error
			// messages
			if (!validate(request)) {
				System.out.println("1");
				BaseBean bean = (BaseBean) populateBean(request);
				ServletUtility.setBean(bean, request);
				System.out.println(getView());
				ServletUtility.forward(getView(), request, response);	
				return;
			}/*
			else if(OP_SIGN_UP.equalsIgnoreCase(op)){
				System.out.println("je rentre dans la nv fenetre pour enregisrer");
				ServletUtility.redirect(OBSView.USER_REGISTRATION_VIEW, request, response);
				return;
			}
			else if (OP_SUBMIT.equalsIgnoreCase(op)) {
				System.out.println("2");
				ServletUtility.redirect(OBSView.USER_REGISTRATION_CTL, request, response);
				return;
			}*/
		}
		
		super.service(request, response);
	}

	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	protected abstract String getView();
	
}
