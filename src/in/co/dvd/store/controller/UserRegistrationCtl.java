package in.co.dvd.store.controller;

//import java.awt.print.book;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import in.co.dvd.store.bean.BaseBean;
//import in.co.dvd.store.bean.BookBean;
//import in.co.dvd.store.bean.BookedBean;
import in.co.dvd.store.bean.UserBean;
import in.co.dvd.store.exception.ApplicationException;
import in.co.dvd.store.exception.DuplicateRecordException;
//import in.co.dvd.store.model.BookModel;
//import in.co.dvd.store.model.BookedModel;
import in.co.dvd.store.model.UserModel;
import in.co.dvd.store.util.DataUtility;
import in.co.dvd.store.util.DataValidator;
import in.co.dvd.store.util.PropertyReader;
import in.co.dvd.store.util.ServletUtility;


/**
 * Servlet implementation class UserRegistrationCtl
 */

/**
 * UserRegistration functionality Controller. Performs operation for Validate and add a User 
 * As Student Role
 * 
 * 
 */

@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/newUser" })
public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "Submit";

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {


		boolean pass = true;

		String login = request.getParameter("login");
		

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName",
					PropertyReader.getValue("error.name", "First Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName",
					PropertyReader.getValue("error.name", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email",
					PropertyReader.getValue("error.require", "Email Address"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email",
					PropertyReader.getValue("error.email", "Email Address"));
			pass = false;
		}
		if (DataValidator.isNull(login)) {
			request.setAttribute("login",
					PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password",
					PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} 

		
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require","Mobile No"));
			pass = false;
		}
		
		
		
		return pass;
	}
	
	/**
	 * Populates bean object from request parameters
	 * il enregistre les donnes du monsieur dans bean
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

			bean.setId(DataUtility.getLong(request.getParameter("id")));
		    bean.setRoleId(2L);
		    bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
	    	bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		    bean.setLogin(DataUtility.getString(request.getParameter("login")));
		    bean.setPassword(DataUtility.getString(request.getParameter("password")));
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
			bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
			bean.setEmailId(DataUtility.getString(request.getParameter("email")));
	
			return bean;
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistrationCtl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Contains display logic
	 * pas fait!!
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName=DataUtility.getString(request.getParameter("biNa"));
		HttpSession session=request.getSession(true);
		session.setAttribute("BkName",bookName);
		ServletUtility.forward(getView(), request, response);

	}
	/**
	 * Contains submit logic
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method");
	
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		UserModel model = new UserModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		HttpSession session=request.getSession();
		String BkName=(String)session.getAttribute("BkName");
		//si le bouton est submit alors ou il veut sautentifie ou il veut senregister
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			//cree le client
			UserBean bean = (UserBean) populateBean(request);
			try {
				long pk=0;
			//	System.out.println("in try sign up");
				UserBean uBean= model.authenticate(bean.getLogin(), bean.getPassword());
				if(uBean==null) {
				 pk = model.add(bean);
				System.out.println("register");
				bean.setId(pk);
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility.redirect(OBSView.USER_NEW, request, response);
				return;
				}else {
					request.getSession().setAttribute("UserBean", uBean);
				}
			

				//BookedModel beModel=new BookedModel();
				//BookModel bModel=new BookModel();
				//BookBean bBean=bModel.findByName(BkName);
				//BookedBean beBean=new BookedBean();
				//if(uBean==null) {
				//beBean.setUserId(pk);
				//}else {
					//beBean.setUserId(uBean.getId());
				//}
				//System.out.println("Book Id=====+"+bBean.getId());
				//beBean.setBookId(bBean.getId());
				//beBean.setImage(bBean.getImageName());
				//beBean.setPdf(bBean.getPdfName());
				//beModel.add(beBean);
				//session.invalidate();
				//ServletUtility.setSuccessMessage("User Successfully Registered", request);
				//ServletUtility.redirect(OBSView.APP_CONTEXT+"/pdfs/"+bBean.getPdfName(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),request);
				System.out.println("p1");
				ServletUtility.forward(getView(), request, response);
				
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				System.out.println("p2");
				return;
			}
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OBSView.USER_REGISTRATION_CTL, request, response);
			return;
		}
		request.setAttribute("BkName",BkName);
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return OBSView.USER_REGISTRATION_CTL;
	}

		}
	
