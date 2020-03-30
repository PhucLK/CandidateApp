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
import com.model.Experience;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class ExpericenceDAO {
	
	final static Logger logger = Logger.getLogger(ExpericenceDAO.class);
	private static Connection conn;
	private static PreparedStatement preparedStmt;
	private static ResultSet resultSet;

	/**
	 * @param ex
	 * @return true if save candidate success, otherwise return false
	 */
	public static boolean saveEx(Experience ex) {

		
		int id = CandidateDAO.saveC(ex);
		try {
			if (id > 0) {
				conn = JdbcConection.getInstance().getConnection();
				String query = SQLCommand.EXPERIENCE_QUERY_FIND_ONE;
				preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				preparedStmt.setInt(1, id);
				resultSet = preparedStmt.executeQuery();

				resultSet.moveToInsertRow();
				resultSet.updateInt(1, id);
				resultSet.updateString(2, ex.getCandidate_type().toString());
				resultSet.updateInt(3, ex.getExpInYear());
				resultSet.updateString(4, ex.getProSkill());
				resultSet.insertRow();
				logger.info(query);
				CertificationDAO.saveCertificate(ex.getCertifications(), id);	
			}
			resultSet.close();
			return true;
		} catch (Exception e) {
			logger.error("Something Wrong!", e);
			e.printStackTrace();
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
	 * @return list experiences that get from database
	 */
	public static List<Experience> getEx() {

		List<Experience> list = new ArrayList<Experience>();

		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.EXPERIENCE_JOIN_CANDIDATE;
			preparedStmt = conn.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				List<Certification> certifications = new ArrayList<>();
				Experience ex = new Experience();
				int id = resultSet.getInt("idEx");
				ex.setCandidateId(resultSet.getInt("CandidateId"));
				ex.setFullName(resultSet.getString("fullname"));
				ex.setBirthDay(resultSet.getString("birthday"));
				ex.setPhone(resultSet.getString("phone"));
				ex.setEmail(resultSet.getString("email"));
				ex.setCandidate_type(Candidate_type.EXPERIENCE);
				ex.setExpInYear(resultSet.getInt("ExpInYear"));
				ex.setProSkill((resultSet.getString("ProSkill")));
				certifications = CertificationDAO.getCertifications(id);
				ex.setCertifications(certifications);
				list.add(ex);
			}
			resultSet.close();
			logger.info(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Something Wrong!", e);
			e.printStackTrace();
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

		return list;
	}

	/**
	 * @param ex
	 * @param id
	 * @return true if edit experience success, otherwise return false
	 */
	public static boolean editEx(Experience ex, int id) {
			
		try {
			if (CandidateDAO.editC(ex, id)) {
				String query = SQLCommand.EXPERIENCE_QUERY_FIND_ONE;
				conn = JdbcConection.getInstance().getConnection();
				preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				preparedStmt.setInt(1, id);
				resultSet = preparedStmt.executeQuery();

				while (resultSet.next()) {
					resultSet.updateInt(1, id);
					resultSet.updateString(2, ex.getCandidate_type().toString());
					resultSet.updateInt(3, ex.getExpInYear());
					resultSet.updateString(4, ex.getProSkill());
					resultSet.insertRow();

				}
				logger.info(query);
				CertificationDAO.editCertificate(ex.getCertifications(), id);
				resultSet.close();
			}
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Something Wrong!", e);
			e.printStackTrace();
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
}
