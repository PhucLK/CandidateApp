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

	private static Connection conn;
	private static PreparedStatement preparedStmt;

	/**
	 * @param is
	 * @return true if save Internship success, otherwise return false
	 * @throws ClassNotFoundException
	 */
	public static boolean saveIs(InternShip is) {
		try {
			conn = JdbcConection.getInstance().getConnection();
			// the sql insert statement
			String query = SQLCommand.INTERNSHIP_QUERY_FIND_ALL;
			String query2 = SQLCommand.CERTIFICATEIS_QUERY_FIND_ALL;

			System.out.println(query);

			// create the sql insert preparedstatement
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, is.getFullName());
			preparedStmt.setString(2, is.getBirthDay());
			preparedStmt.setString(3, is.getPhone());
			preparedStmt.setString(4, is.getEmail());
			preparedStmt.setString(5, is.getMajors());
			preparedStmt.setString(6, is.getSemester());
			preparedStmt.setString(7, is.getUniversity_name());
			preparedStmt.setInt(8, is.getCandidateId());
			preparedStmt.executeUpdate();

			preparedStmt = conn.prepareStatement(query2);
			for (Certification c : is.getCertifications()) {
				preparedStmt.setInt(1, c.getCertificatedId());
				preparedStmt.setString(2, c.getCertificateName());
				preparedStmt.setString(3, c.getCertificateRank());
				preparedStmt.setString(4, c.getCertificateDate());
				preparedStmt.setInt(5, is.getCandidateId());
				preparedStmt.executeUpdate();
			}

		} catch (Exception e) {
			System.err.println("Got an exception!");
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

		return true;
	}

	/**
	 * @return list InternShip
	 */
	public static List<InternShip> getIs() {
		List<InternShip> list = new ArrayList<InternShip>();
		try {
			conn = JdbcConection.getInstance().getConnection();

			String query = SQLCommand.INTERNSHIP_QUERY_FIND_ALL;
			String query2 = SQLCommand.CERTIFICATEIS_QUERY_FIND_ALL;

			List<Certification> listC = new ArrayList<Certification>();
			preparedStmt = conn.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				InternShip ex = new InternShip();
				Certification certification = new Certification();
				ex.setFullName(rs.getString("fullname"));
				ex.setBirthDay(rs.getString("birthday"));
				ex.setPhone(rs.getString("phone"));
				ex.setEmail(rs.getString("email"));
				ex.setMajors(rs.getString("Majors"));
				ex.setSemester((rs.getString("Semester")));
				ex.setUniversity_name(rs.getString("University_name"));

				preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setInt(1, ex.getCandidateId());
				ResultSet rs2 = preparedStmt.executeQuery();
				// rs = preparedStmt2.executeQuery();

				while (rs2.next()) {
					certification = new Certification();
					certification.setCertificatedId(rs2.getInt(1));
					certification.setCertificateName(rs2.getString(2));
					certification.setCertificateRank(rs2.getString(3));
					certification.setCertificateDate(rs2.getString(4));
					listC.add(certification);
				}
				ex.setCandidate_type(Candidate_type.INTERNSHIP);
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
	 * @param ex
	 * @param id
	 * @return true if edit success , otherwise return false
	 */
	public static boolean editIs(InternShip ex, int id) {
		try {
			conn = JdbcConection.getInstance().getConnection();

			String query = SQLCommand.INTERNSHIP_QUERY_EDIT_BY_ID;
			String query2 = SQLCommand.CERTIFICATEIS_QUERY_EDIT_BY_ID;

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, ex.getFullName());
			preparedStmt.setString(2, ex.getBirthDay());
			preparedStmt.setString(3, ex.getPhone());
			preparedStmt.setString(4, ex.getEmail());
			preparedStmt.setString(5, ex.getMajors());
			preparedStmt.setString(6, ex.getSemester());
			preparedStmt.setString(7, ex.getUniversity_name());
			preparedStmt.setInt(8, id);
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
