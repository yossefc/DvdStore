package in.co.dvd.store.controller;

public interface OBSView {
	//fait
	public String APP_CONTEXT = "/DvdStore";
	public String PAGE_FOLDER = "/jsp";
	
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	
	public String WELCOME_VIEW =  APP_CONTEXT+PAGE_FOLDER + "/Welcome.jsp";
	public String WELCOME_CTL =  PAGE_FOLDER +"/welcome";
	
	public String USER_NEW =  APP_CONTEXT +PAGE_FOLDER+"/newUser.jsp";
	public String USER_REGISTRATION_VIEW = APP_CONTEXT +PAGE_FOLDER+ "/UserRegistrationView.jsp";
	public String USER_REGISTRATION_CTL = PAGE_FOLDER+ "/UserRegistrationView.jsp";
	
	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";
	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	
	
	public String BOOK_VIEW = PAGE_FOLDER + "/BookView.jsp";
	
	
	public String BOOK_LIST_VIEW = PAGE_FOLDER + "/BookListView.jsp";
	public String BOOKED_LIST_VIEW = PAGE_FOLDER + "/BookedListView.jsp";
	
	

	
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	
	
//pas trouve
	public String ERROR_CTL = "/ctl/ErrorCtl";

	
	//fait
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	
	public String BOOK_CTL = APP_CONTEXT + "/ctl/book";
	
	public String INDEX_CTL = APP_CONTEXT + "/index";
	
	public String BOOK_LIST_CTL = APP_CONTEXT + "/ctl/bookList";
	
	public String BOOKED_LIST_CTL = APP_CONTEXT + "/ctl/bookedList";
	
	
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";



}
