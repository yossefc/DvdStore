package in.co.dvd.store.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import in.co.dvd.store.bean.DvdBean;
import in.co.dvd.store.exception.ApplicationException;
import in.co.dvd.store.exception.DatabaseException;
import in.co.dvd.store.exception.DuplicateRecordException;
import in.co.dvd.store.model.DvdModel;
import in.co.dvd.store.util.JDBCDataSource;

public class DvdModel {
	
	public Integer nextPK() throws DatabaseException {
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM BS_Dvd");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public DvdBean findByName(String login) throws ApplicationException {
		//StringBuffer sql = new StringBuffer("SELECT * FROM BS_DVD WHERE NAME=?");
		DvdBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BS_DVD WHERE NAME=?");
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DvdBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSaleType(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setImageName(rs.getString(5));
				bean.setPdfName(rs.getString(6));
				bean.setPrice(rs.getLong(7));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Dvd by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public DvdBean findByPK(long pk) throws ApplicationException {
		//StringBuffer sql = new StringBuffer("SELECT * FROM BS_Dvd WHERE ID=?");
		DvdBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM BS_Dvd WHERE ID=?");
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DvdBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSaleType(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setImageName(rs.getString(5));
				bean.setPdfName(rs.getString(6));
				bean.setPrice(rs.getLong(7));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public long add(DvdBean bean) throws ApplicationException, DuplicateRecordException {

		DvdBean existbean = findByName(bean.getName());

		if (existbean != null) {
			throw new DuplicateRecordException("Dvd Is already exists");
		}

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BS_DVD VALUES(?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getSaleType());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getImageName());
			pstmt.setString(6, bean.getPdfName());
			pstmt.setLong(7, bean.getPrice());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Dvd");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void delete(DvdBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM BS_DVD WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	
	public void update(DvdBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		DvdBean beanExist = findByName(bean.getName());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Dvd is already exist");
		}

		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE BS_DVD SET NAME=?,saleType=?,description=?,imageName=?,PdfName=?,price=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getSaleType());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getImageName());
			pstmt.setString(5, bean.getPdfName());
			pstmt.setLong(6, bean.getPrice());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating DVD ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	
	public List search(DvdBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(DvdBean bean, int pageNo, int pageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("SELECT * FROM BS_Dvd WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getSaleType() != null && bean.getSaleType().length() > 0) {
				sql.append(" AND SaleType like '" + bean.getSaleType() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DvdBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSaleType(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setImageName(rs.getString(5));
				bean.setPdfName(rs.getString(6));
				bean.setPrice(rs.getLong(7));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;
	}
	
	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from BS_DVD");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		System.out.println("sql in list user :"+sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from BS_DVD");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				DvdBean bean = new DvdBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSaleType(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setImageName(rs.getString(5));
				bean.setPdfName(rs.getString(6));
				bean.setPrice(rs.getLong(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting list of Dvd");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return list;

	}
	

	
}
