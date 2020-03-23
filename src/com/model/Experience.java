package com.model;

import java.util.List;
/**
 * 
 * @author PhucTV7
 * 
 */
import java.util.Scanner;

import com.enums.Candidate_type;
import com.util.UserInputUtil;

/**
 * @author PhucTV7
 *
 */
public class Experience extends Candidate {

	private int expInYear;
	private String proSkill;

	/**
	 * 
	 */
	public Experience() {
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
	 * @param expInYear
	 * @param proSkill
	 */
	public Experience(int candidateId, String fullName, String birthDay, String phone, String email,
			Candidate_type candidate_type, List<Certification> certifications, int expInYear, String proSkill) {
		super(candidateId, fullName, birthDay, phone, email, candidate_type, certifications);
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}

	/**
	 * @param expInYear
	 * @param proSkill
	 */
	public Experience(int expInYear, String proSkill) {
		super();
		this.expInYear = expInYear;
		this.proSkill = proSkill;
	}

	/**
	 * @return experience year
	 */
	public int getExpInYear() {
		return expInYear;
	}

	/**
	 * @param expInYear
	 */
	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}

	/**
	 * @return professional skills
	 */
	public String getProSkill() {
		return proSkill;
	}

	/**
	 * @param proSkill
	 */
	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}

	@Override
	public String showInfor() {
		// TODO Auto-generated method stub
		return super.toString() + ",Experien=" + expInYear + ",Skill=" + proSkill + "," + super.toStringCertificate();
	}

	@Override
	public void inputInf(Scanner sc) {
		// TODO Auto-generated method stub
		super.inputInf(sc);
		super.setCandidate_type(Candidate_type.EXPERIENCE);

		System.out.println(" Enter Ex Year : ");
		expInYear = UserInputUtil.inputTypeInt(sc);

		System.out.println(" Enter pro skill : ");
		proSkill = sc.nextLine();
	}
}
