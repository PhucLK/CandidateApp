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

import com.enums.Candidate_type;
import com.model.Certification;
import com.model.InternShip;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class IntershipDAO {

	final static Logger logger = Logger.getLogger(FresherDAO.class);

	private static Connection conn;
	private static PreparedStatement preparedStmt;
	private static ResultSet resultSet;

	/**
	 * @param is
	 * @return true if save internship success, otherwise return false
	 */
	public static boolean saveIs(InternShip is) {
		int id = CandidateDAO.saveC(is);
		try {
			if (id > 0) {
				conn = JdbcConection.getInstance().getConnection();
				String query = SQLCommand.INTERNSHIP_QUERY_FIND_ONE;

				preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				preparedStmt.setInt(1, id);
				resultSet = preparedStmt.executeQuery();

				resultSet.moveToInsertRow();
				resultSet.updateInt(1, id);
				resultSet.updateString(2, is.getCandidate_type().toString());
				resultSet.updateString(3, is.getMajors());
				resultSet.updateString(4, is.getSemester());
				resultSet.updateString(5, is.getUniversity_name());
				resultSet.insertRow();

				logger.info(query);
				CertificationDAO.saveCertificate(is.getCertifications(), id);
				return true;
			}

		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();

		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	/**
	 * @return internship's list from database
	 */
	public static List<InternShip> getIs() {

		List<InternShip> list = new ArrayList<>();
		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.INTERNSHIP_JOIN_CANDIDATE;
			preparedStmt = conn.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				List<Certification> certifications = new ArrayList<>();
				InternShip is = new InternShip();
				int id = resultSet.getInt("idIs");
				is.setCandidateId(resultSet.getInt("CandidateId"));
				is.setFullName(resultSet.getString("fullname"));
				is.setBirthDay(resultSet.getString("birthday"));
				is.setPhone(resultSet.getString("phone"));
				is.setEmail(resultSet.getString("email"));
				is.setCandidate_type(Candidate_type.INTERNSHIP);
				is.setMajors(resultSet.getString("Majors"));
				is.setSemester(resultSet.getString("Semester"));
				is.setUniversity_name((resultSet.getString("University")));
				certifications = CertificationDAO.getCertifications(id);
				is.setCertifications(certifications);
				list.add(is);
			}
			logger.info(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * @param is
	 * @param id
	 * @return true if edit internship success, otherwise return false
	 */
	public static boolean editIs(InternShip is, int id) {

		try {
			if (CandidateDAO.editC(is, id)) {
				conn = JdbcConection.getInstance().getConnection();
				String query = SQLCommand.EXPERIENCE_QUERY_FIND_ONE;

				preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				preparedStmt.setInt(1, id);
				resultSet = preparedStmt.executeQuery();

				resultSet.updateInt(1, id);
				resultSet.updateString(2, is.getCandidate_type().toString());
				resultSet.updateString(3, is.getMajors());
				resultSet.updateString(4, is.getSemester());
				resultSet.updateString(5, is.getUniversity_name());
				resultSet.insertRow();

				logger.info(query);
				if (CertificationDAO.editCertificate(is.getCertifications(), id))
					return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStmt != null)
					preparedStmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}
}
