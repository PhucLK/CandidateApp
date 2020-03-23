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
import com.model.Fresher;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class FresherDAO {

	private static Connection conn;
	private static PreparedStatement preparedStmt;

	/**
	 * @param fs
	 * @return true if save Fresher success, otherwise false
	 * 
	 */
	public static boolean saveFs(Fresher fs) {
		try {
			conn = JdbcConection.getInstance().getConnection();
			// the sql insert statement
			String query = SQLCommand.FRESHER_QUERY_INSERT;
			String query2 = SQLCommand.CERTIFICATEFS_QUERY_INSERT;

			System.out.println(query);

			// create the sql insert preparedstatement
			preparedStmt = conn.prepareStatement(query);

			preparedStmt.setString(1, fs.getFullName());
			preparedStmt.setString(2, fs.getBirthDay());
			preparedStmt.setString(3, fs.getPhone());
			preparedStmt.setString(4, fs.getEmail());
			preparedStmt.setInt(5, fs.getGraduation_date());
			preparedStmt.setString(6, fs.getGraduation_rank());
			preparedStmt.setString(7, fs.getEducation());
			preparedStmt.setInt(8, fs.getCandidateId());
			int a = preparedStmt.executeUpdate();

			preparedStmt = conn.prepareStatement(query2);
			int b = 0;
			for (Certification c : fs.getCertifications()) {

				preparedStmt.setInt(1, fs.getCandidateId());
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
	 * @return
	 * @throws BirthdayException
	 * @throws ParseException
	 */
	public static List<Fresher> getFs() {
		List<Fresher> list = new ArrayList<Fresher>();
		try {
			conn = JdbcConection.getInstance().getConnection();

			String query = SQLCommand.FRESHER_QUERY_FIND_ALL;
			String query2 = SQLCommand.CERTIFICATEFS_QUERY_INSERT;

			List<Certification> listC = new ArrayList<Certification>();
			preparedStmt = conn.prepareStatement(query);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				Fresher fs = new Fresher();
				Certification certification = new Certification();

				fs.setFullName(rs.getString("fullname"));
				fs.setBirthDay(rs.getString("birthday"));
				fs.setPhone(rs.getString("phone"));
				fs.setEmail(rs.getString("email"));
				fs.setGraduation_date(rs.getInt("graduation_date"));
				fs.setGraduation_rank((rs.getString("Graduation_rank")));
				fs.setEducation((rs.getString("Graduation_rank")));

				preparedStmt = conn.prepareStatement(query2);
				preparedStmt.setInt(1, fs.getCandidateId());
				ResultSet rs2 = preparedStmt.executeQuery();

				while (rs2.next()) {
					certification = new Certification();
					certification.setCertificatedId(rs2.getInt(1));
					certification.setCertificateName(rs2.getString(2));
					certification.setCertificateRank(rs2.getString(3));
					certification.setCertificateDate(rs2.getString(4));
					listC.add(certification);
				}
				fs.setCertifications(listC);
				fs.setCandidate_type(Candidate_type.FRESHER);
				list.add(fs);
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
	 * @return
	 */
	public static boolean editFs(Fresher ex, int id) {
		PreparedStatement preparedStmt = null;
		try {
			conn = JdbcConection.getInstance().getConnection();

			String query = SQLCommand.FRESHER_QUERY_EDIT_BY_ID;
			String query2 = SQLCommand.CERTIFICATEFS_QUERY_EDIT_BY_ID;

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, ex.getFullName());
			preparedStmt.setString(2, ex.getBirthDay());
			preparedStmt.setString(3, ex.getPhone());
			preparedStmt.setString(4, ex.getEmail());
			preparedStmt.setInt(5, ex.getGraduation_date());
			preparedStmt.setString(6, ex.getGraduation_rank());
			preparedStmt.setString(7, ex.getEducation());
			preparedStmt.setInt(8, id);
			int a = preparedStmt.executeUpdate();

			PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
			int b = 0;
			for (Certification c : ex.getCertifications()) {
				preparedStmt2.setInt(1, c.getCertificatedId());
				preparedStmt2.setString(2, c.getCertificateName());
				preparedStmt2.setString(3, c.getCertificateDate());
				preparedStmt2.setString(4, c.getCertificateRank());
				preparedStmt2.setInt(5, id);

				b = preparedStmt2.executeUpdate();
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
