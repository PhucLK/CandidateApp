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
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;

import com.model.Candidate;
import com.model.Experience;
import com.model.Fresher;
import com.model.InternShip;
import com.util.JdbcConection;
import com.util.SQLCommand;

/**
 * @author PhucTV7
 *
 */
public class CandidateDAO {
	final static Logger logger = Logger.getLogger(CandidateDAO.class);
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	private static Connection conn;

	/**
	 * @param candidate
	 * @return primary key of candidate after insert candidate to table on database
	 */
	public static int saveC(Candidate candidate) {
		int id = 0;
		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.CANDIDATE_QUERY_FIND_ONE;
			preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setInt(1, candidate.getCandidateId());
			resultSet = preparedStatement.executeQuery();
			resultSet.moveToInsertRow();

			resultSet.updateInt(2, candidate.getCandidateId());
			resultSet.updateString(3, candidate.getFullName());
			resultSet.updateString(4, candidate.getBirthDay());
			resultSet.updateString(5, candidate.getPhone());
			resultSet.updateString(6, candidate.getEmail());
			resultSet.insertRow();

			while (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			resultSet.close();
			logger.info(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Something Wrong!", e);
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return id;
	}

	/**
	 * @param candidate
	 * @param oldId
	 * @return true if edit candidate success, otherwise return false
	 */
	public static boolean editC(Candidate candidate, int oldId) {

		try {
			conn = JdbcConection.getInstance().getConnection();
			String query = SQLCommand.CANDIDATE_QUERY_FIND_ONE;
			PreparedStatement preparedStatement = conn.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			preparedStatement.setInt(1, oldId);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				resultSet.updateInt(2, candidate.getCandidateId());
				resultSet.updateString(3, candidate.getFullName());
				resultSet.updateString(4, candidate.getBirthDay());
				resultSet.updateString(5, candidate.getPhone());
				resultSet.updateString(6, candidate.getEmail());
				resultSet.updateRow();
			}
			resultSet.close();
			logger.info(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Something Wrong!", e);
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;

	}

	/**
	 * @return all candidates in database that distinct id
	 */
	public List<Candidate> getAllWithDistinctId() {

		return getAll().stream().distinct().collect(Collectors.toList());
	}

	/**
	 * @return all candidates in database
	 */
	public List<Candidate> getAll() {
		List<Experience> list1 = ExpericenceDAO.getEx();
		List<Fresher> list2 = FresherDAO.getFs();
		List<InternShip> list3 = IntershipDAO.getIs();

		return Stream.of(list1, list2, list3).flatMap(Collection::stream).collect(Collectors.toList());

	}

	/**
	 * @return all fullName of candidates in database
	 */
	public String getAllFullName() {
		StringBuffer sb = new StringBuffer();
		List<String> str = getAll().stream().map((c) -> (c.getFullName() + ",")).collect(Collectors.toList());
		str.forEach((s) -> sb.append(s));

		return sb.toString();
	}

	/**
	 * @param list
	 * @return list candidates sorted by type of candidate and birthday
	 */
	public List<Candidate> sortList(List<Candidate> list) {
		Collections.sort(list, new CandidateSort());
		return list;
	}

	/**
	 * @param id
	 * @return list candidates in database
	 */
	public Candidate getCandidateByIDs(int id) {

		Candidate c = getAll().stream().filter((i) -> (i.getCandidateId() == id)).distinct().findFirst().get();
		return c;
	}

	/**
	 * @param id
	 * @return true if find any candidate, otherwise return false
	 */
	public boolean searchID(int id) {
		return getAll().stream().filter((i) -> (i.getCandidateId() == id)).findAny().isPresent();
		
	}

}

class CandidateSort implements Comparator<Candidate> {

	@Override
	public int compare(Candidate o1, Candidate o2) {
		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy/MM/dd").parse(o1.getBirthDay());
			date2 = new SimpleDateFormat("yyyy/MM/dd").parse(o2.getBirthDay());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int ascending = Integer.valueOf(o1.getCandidate_type().getLevel())
				.compareTo(Integer.valueOf(o2.getCandidate_type().getLevel()));
		if (ascending == 0) {
			return -date1.compareTo(date2);
		}

		return ascending;

	}

}
