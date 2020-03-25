/**
 * 
 *
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.model.Certification;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class CertificationDAO {

	final static Logger logger = Logger.getLogger(CertificationDAO.class);
	private static Connection conn;
	private static PreparedStatement preparedStmt;
	private static ResultSet resultSet;

	/**
	 * @param certifications
	 * @param id
	 * @return true if save certificate success, otherwise return false
	 */
	public static boolean saveCertificate(List<Certification> certifications, int id) {

		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.CERTIFICATION_QUERY_FIND_ONE;
			preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1, id);

			resultSet = preparedStmt.executeQuery();
			resultSet.moveToInsertRow();

			for (Certification certification : certifications) {
				resultSet.updateInt(1, id);
				resultSet.updateInt(2, certification.getCertificatedId());
				resultSet.updateString(3, certification.getCertificateName());
				resultSet.updateString(4, certification.getCertificateRank());
				resultSet.updateString(5, certification.getCertificateDate());
				resultSet.insertRow();
			}
			logger.info(query);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Something Wrong!", e);
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Something Wrong!", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param certifications
	 * @param id
	 * @return true if edit certificate success, otherwise return false
	 */
	public static boolean editCertificate(List<Certification> certifications, int id) {

		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.CERTIFICATION_QUERY_FIND_ONE;
			preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			preparedStmt.setInt(1, id);

			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				for (Certification certification : certifications) {
					resultSet.updateInt(2, certification.getCertificatedId());
					resultSet.updateString(3, certification.getCertificateName());
					resultSet.updateString(4, certification.getCertificateRank());
					resultSet.updateString(5, certification.getCertificateDate());
					resultSet.insertRow();
				}
			}
			logger.info(query);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Something Wrong! : ", e);
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("Something Wrong!", e);
			}
		}
	}

	/**
	 * @param id
	 * @return certificate's list that get from database
	 */
	public static List<Certification> getCertifications(int id) {
		List<Certification> certifications = new ArrayList<>();
		String query = SQLCommand.CERTIFICATION_QUERY_FIND_ONE;
		try {
			conn = JdbcConection.getInstance().getConnection();
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, id);
			resultSet = preparedStmt.executeQuery();
			while (resultSet.next()) {
				Certification certification = new Certification();
				certification.setCertificatedId(resultSet.getInt(2));
				certification.setCertificateName(resultSet.getString(3));
				certification.setCertificateRank(resultSet.getString(4));
				certification.setCertificateDate(resultSet.getString(5));
				certifications.add(certification);
			}
			logger.info(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Something Wrong!", e);
			e.printStackTrace();
		}
		return certifications;
	}
}
