package in.co.dvd.store.bean;

public class UserBean extends BaseBean{
		
	

		/**
		 * First Name of User
		 */
		private String firstName;
		/**
		 * Last Name of User
		 */
		private String lastName;
		/**
		 * Login of User
		 */
		private String login;
		/**
		 * Password of User
		 */
		private String password;
		/**
		 * MobielNo of User
		 */
		private String mobileNo;
		/**
		 * Role of User
		 */
		private long roleId;
		
		private String emailId;
		/**
		 * @return FirstName Of User
		 */

		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param FirstName
		 *            To set User FirstName
		 */
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return LastName Of User
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param LastName
		 *            To set User LastName
		 */
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		/**
		 * @return Login id Of User
		 */
		public String getLogin() {
			return login;
		}

		/**
		 * @param Login
		 *            Id To set User Login ID
		 */
		public void setLogin(String login) {
			this.login = login;
		}

		/**
		 * @return Password Of User
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param Password
		 *            To set User Password
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return Confirm Password Of User
		 */
		

		

		/**
		 * @return Mobile No Of User
		 */
		public String getMobileNo() {
			return mobileNo;
		}

		/**
		 * @param Mobile
		 *            No To set User Mobile No
		 */
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		/**
		 * @return ROle Id Of User
		 */
		public long getRoleId() {
			return roleId;
		}

		/**
		 * @param Role
		 *            Id To set User ROle Id
		 */
		public void setRoleId(long roleId) {
			this.roleId = roleId;
		}

		

		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getKey() {
			return String.valueOf(id);
		}

		public String getValue() {

			return firstName + " " + lastName;
		}
		
		@Override
		public String toString() {
			return "UserBean [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password="
					+ password + ", mobileNo=" + mobileNo + ", roleId=" + roleId + ", emailId=" + emailId + "]";
		}
	}


