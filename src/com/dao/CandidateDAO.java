/**
 * 
 *
 */
package com.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.model.Candidate;
import com.model.Experience;
import com.model.Fresher;
import com.model.InternShip;

/**
 * @author PhucTV7
 *
 */
public class CandidateDAO implements Comparator<Candidate> {

	private List<Candidate> list;

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
			return date1.compareTo(date2);
		}

		return ascending;

	}

	/**
	 * @return all candidates in database that distinct id
	 */
	public List<Candidate> getAllWithDistinctId() {

		list = getAll();

		Set<Candidate> distinctList = new HashSet<>();
		list.forEach((c) -> distinctList.add(c));
		List<Candidate> listN = new ArrayList<>(distinctList);
		return listN;

	}

	/**
	 * @return
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
		list = getAll();
		List<String> str = list.stream().map((c) -> (c.getFullName() + ",")).collect(Collectors.toList());
		str.forEach((s) -> sb.append(s));

		return sb.toString();
	}

	/**
	 * @param list
	 * @return list candidates sorted by type of candidate and birthday
	 */
	public List<Candidate> sortList(List<Candidate> list) {
		Collections.sort(list, new CandidateDAO());
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
