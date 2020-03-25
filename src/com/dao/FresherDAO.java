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
import com.model.Fresher;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class FresherDAO {

	final static Logger logger = Logger.getLogger(FresherDAO.class);
	private static Connection conn;
	private static PreparedStatement preparedStmt;
	private static ResultSet resultSet;

	/**
	 * @param fs
	 * @return true if save fresher success, otherwise return false
	 * @
	 */
	public static boolean saveFs(Fresher fs) {
		int id = CandidateDAO.saveC(fs);
		try {
			if (id > 0) {
				conn = JdbcConection.getInstance().getConnection();
				// the mysql insert statement
				String query = SQLCommand.FRESHER_QUERY_FIND_ONE;

				preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				preparedStmt.setInt(1, id);
				resultSet = preparedStmt.executeQuery();

				resultSet.moveToInsertRow();
				resultSet.updateInt(1, id);
				resultSet.updateString(2, fs.getCandidate_type().toString());
				resultSet.updateInt(3, fs.getGraduation_date());
				resultSet.updateString(4, fs.getGraduation_rank());
				resultSet.updateString(5, fs.getEducation());
				resultSet.insertRow();
				logger.info(query);
				CertificationDAO.saveCertificate(fs.getCertifications(), id);

			}
			return true;
		} catch (Exception e) {
			logger.error(e.toString());
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
				e.printStackTrace();
			}
		}

	}

	/**
	 * @return list Freshers that get from database
	 * 
	 * 
	 */
	public static List<Fresher> getFs() {

		List<Fresher> list = new ArrayList<>();
		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.FRESHER_JOIN_CANDIDATE;
			preparedStmt = conn.prepareStatement(query);
			resultSet = preparedStmt.executeQuery();

			while (resultSet.next()) {
				List<Certification> certifications = new ArrayList<>();
				Fresher fs = new Fresher();
				int id = resultSet.getInt("idFs");
				fs.setCandidateId(resultSet.getInt("CandidateId"));
				fs.setFullName(resultSet.getString("fullname"));
				fs.setBirthDay(resultSet.getString("birthday"));
				fs.setPhone(resultSet.getString("phone"));
				fs.setEmail(resultSet.getString("email"));
				fs.setCandidate_type(Candidate_type.FRESHER);
				fs.setGraduation_date(resultSet.getInt("Graduation_date"));
				fs.setGraduation_rank((resultSet.getString("Graduation_rank")));
				fs.setEducation((resultSet.getString("Education")));
				certifications = CertificationDAO.getCertifications(id);
				fs.setCertifications(certifications);
				list.add(fs);
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
	 * @param ex, id
	 * @return true if edit Fresher success, otherwise return false
	 */
	public static boolean editFs(Fresher fs, int id) {

		try {
			if (CandidateDAO.editC(fs,id)) {
				conn = JdbcConection.getInstance().getConnection();
				String query = SQLCommand.FRESHER_QUERY_FIND_ONE;
				preparedStmt = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_UPDATABLE);
				preparedStmt.setInt(1, id);
				resultSet = preparedStmt.executeQuery();

				resultSet.updateInt(1, id);
				resultSet.updateString(2, fs.getCandidate_type().toString());
				resultSet.updateInt(3, fs.getGraduation_date());
				resultSet.updateString(4, fs.getGraduation_rank());
				resultSet.updateString(5, fs.getEducation());
				resultSet.insertRow();
				logger.info(query);
				if (CertificationDAO.editCertificate(fs.getCertifications(), id))
					return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
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
				e.printStackTrace();
			}
		}
		return false;

	}
}
