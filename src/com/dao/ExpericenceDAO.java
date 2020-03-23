/**
 * 
 *
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.enums.Candidate_type;
import com.exceptions.BirthdayException;
import com.model.Certification;
import com.model.Experience;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class ExpericenceDAO {

	private static Connection conn;
	private static PreparedStatement preparedStmt;

	/**
	 * @param ex
	 * @return true if save candidate success, otherwise return false
	 * @throws ClassNotFoundException
	 */
	public static boolean saveEx(Experience ex) {

		try {
			conn = JdbcConection.getInstance().getConnection();
			// the mysql insert statement
			String query = SQLCommand.INTERNSHIP_QUERY_INSERT;
			String query2 = SQLCommand.CERTIFICATEEX_QUERY_INSERT;

			// create the sql insert preparedstatement
			preparedStmt = conn.prepareStatement(query);
			preparedStmt = conn.prepareStatement(query2);

			preparedStmt.setString(1, ex.getFullName());
			preparedStmt.setString(2, ex.getBirthDay());
			preparedStmt.setString(3, ex.getPhone());
			preparedStmt.setString(4, ex.getEmail());
			preparedStmt.setInt(5, ex.getExpInYear());
			preparedStmt.setString(6, ex.getProSkill());
			preparedStmt.setInt(7, ex.getCandidateId());
			int a = preparedStmt.executeUpdate();

			int b = 0;
			for (Certification c : ex.getCertifications()) {

				preparedStmt.setInt(1, ex.getCandidateId());
				preparedStmt.setInt(2, c.getCertificatedId());
				preparedStmt.setString(3, c.getCertificateName());
				preparedStmt.setString(4, c.getCertificateDate());
				preparedStmt.setString(5, c.getCertificateRank());
				b = preparedStmt.executeUpdate();
			}
			if (a > 0 && b > 0) {
				return true;
			} else
				return false;
			// execute the preparedstatement

		} catch (Exception e) {
			System.err.println("Got an exception!");
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
	 * @return list experiences that get from database @throws
	 * @throws BirthdayException
	 * @throws ParseException
	 */
	public static List<Experience> getEx() {

		List<Experience> list = new ArrayList<Experience>();
		try {
			conn = JdbcConection.getInstance().getConnection();
			String query1 = SQLCommand.EXPERIENCE_QUERY_FIND_ALL;
			String query2 = SQLCommand.CERTIFICATEEX_QUERY_FIND_ALL;

			Experience ex;
			Certification certification;
			List<Certification> listC = new ArrayList<Certification>();
			preparedStmt = conn.prepareStatement(query1);

			ResultSet rs = preparedStmt.executeQuery();

			// get Experiences
			while (rs.next()) {
				ex = new Experience();
				ex.setCandidateId(rs.getInt("CandidateId"));
				ex.setFullName(rs.getString("fullname"));
				ex.setBirthDay(rs.getString("birthday"));
				ex.setPhone(rs.getString("phone"));
				ex.setEmail(rs.getString("email"));
				ex.setExpInYear(rs.getInt("experien_in_year"));
				ex.setProSkill((rs.getString("profecsional_skill")));

				// get certifications
				preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setInt(1, ex.getCandidateId());

				ResultSet rs2 = preparedStmt.executeQuery();

				while (rs2.next()) {
					certification = new Certification();
					certification.setCertificatedId(rs2.getInt(1));
					certification.setCertificateName(rs2.getString(2));
					certification.setCertificateRank(rs2.getString(3));
					certification.setCertificateDate(rs2.getString(4));
					listC.add(certification);
				}
				ex.setCertifications(listC);
				ex.setCandidate_type(Candidate_type.EXPERIENCE);
				list.add(ex);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	 * @return true if edit success, otherwise return false
	 */
	public static boolean editEx(Experience ex, int id) {

		try {
			conn = JdbcConection.getInstance().getConnection();

			String query = SQLCommand.EXPERIENCE_QUERY_EDIT_BY_ID;
			String query2 = SQLCommand.CERTIFICATEEX_QUERY_EDIT_BY_ID;

			preparedStmt = conn.prepareStatement(query);
			// PreparedStatement

			preparedStmt.setString(1, ex.getFullName());
			preparedStmt.setString(2, ex.getBirthDay());
			preparedStmt.setString(3, ex.getPhone());
			preparedStmt.setString(4, ex.getEmail());
			preparedStmt.setInt(5, ex.getExpInYear());
			preparedStmt.setString(6, ex.getProSkill());
			preparedStmt.setInt(7, id);
			int a = preparedStmt.executeUpdate();

			preparedStmt = conn.prepareStatement(query2);
			int b = 0;

			for (Certification c : ex.getCertifications()) {
				preparedStmt.setInt(1, c.getCertificatedId());
				preparedStmt.setString(2, c.getCertificateName());
				preparedStmt.setString(3, c.getCertificateDate());
				preparedStmt.setString(4, c.getCertificateRank());
				preparedStmt.setInt(5, id);
				b = preparedStmt.executeUpdate();
			}
			if (a > 0 && b > 0) {
				return true;
			} else
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
