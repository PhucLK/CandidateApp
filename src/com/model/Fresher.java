package com.model;

import java.util.List;
import java.util.Scanner;

import com.enums.Candidate_type;
import com.util.UserInputUtil;

/**
 * @author PhucTV7
 *
 */
public class Fresher extends Candidate {

	private int graduation_date;
	private String graduation_rank;
	private String education;

	public Fresher() {
		super();
	}

	/**
	 * @param candidateId
	 * @param fullName
	 * @param birthDay
	 * @param phone
	 * @param email
	 * @param candidate_type
	 * @param certifications
	 * @param graduation_date
	 * @param graduation_rank
	 * @param education
	 */
	public Fresher(int candidateId, String fullName, String birthDay, String phone, String email,
			Candidate_type candidate_type, List<Certification> certifications, int graduation_date,
			String graduation_rank, String education) {
		super(candidateId, fullName, birthDay, phone, email, candidate_type, certifications);
		this.graduation_date = graduation_date;
		this.graduation_rank = graduation_rank;
		this.education = education;
	}

	/**
	 * @return the graduation_date
	 */
	public int getGraduation_date() {
		return graduation_date;
	}

	/**
	 * @param graduation_date the graduation_date to set
	 */
	public void setGraduation_date(int graduation_date) {
		this.graduation_date = graduation_date;
	}

	/**
	 * @return the graduation_rank
	 */
	public String getGraduation_rank() {
		return graduation_rank;
	}

	/**
	 * @param graduation_rank the graduation_rank to set
	 */
	public void setGraduation_rank(String graduation_rank) {
		this.graduation_rank = graduation_rank;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String showInfor() {
		// TODO Auto-generated method stub
		return super.toString() + ",Graduation Date=" + graduation_date + ",Rank=" + graduation_rank + ",Education="
				+ education + "," + super.toStringCertificate();
	}

	@Override
	public void inputInf(Scanner sc) {
		// TODO Auto-generated method stub
		super.inputInf(sc);
		super.setCandidate_type(Candidate_type.FRESHER);
		System.out.println(" Enter Graduation Date : ");
		graduation_date = UserInputUtil.inputTypeInt(sc);
		System.out.println(" Graduation Rank : ");
		graduation_rank = sc.nextLine();
		System.out.println(" Enter Education : ");
		education = sc.nextLine();
	}

}
