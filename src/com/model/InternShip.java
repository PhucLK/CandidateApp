package com.model;

import java.util.List;
/**
 * 
 * @author PhucTV7
 * 
 */
import java.util.Scanner;

import com.enums.Candidate_type;

/**
 * @author PhucTV7
 *
 */
public class InternShip extends Candidate {

	private String majors;
	private String semester;
	private String university_name;

	/**
	 * 
	 */
	public InternShip() {
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
	 * @param majors
	 * @param semester
	 * @param university_name
	 */
	public InternShip(int candidateId, String fullName, String birthDay, String phone, String email,
			Candidate_type candidate_type, List<Certification> certifications, String majors, String semester,
			String university_name) {
		super(candidateId, fullName, birthDay, phone, email, candidate_type, certifications);
		this.majors = majors;
		this.semester = semester;
		this.university_name = university_name;
	}

	/**
	 * @return the majors
	 */
	public String getMajors() {
		return majors;
	}

	/**
	 * @param majors the majors to set
	 */
	public void setMajors(String majors) {
		this.majors = majors;
	}

	/**
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * @return the university_name
	 */
	public String getUniversity_name() {
		return university_name;
	}

	/**
	 * @param university_name the university_name to set
	 */
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}

	@Override
	public String showInfor() {
		// TODO Auto-generated method stub
		return super.toString() + ",Mafors=" + majors + ",Semester=" + semester + ",University=" + university_name + ","
				+ super.toStringCertificate();
	}

	@Override
	public void inputInf(Scanner sc) {
		// TODO Auto-generated method stub
		super.inputInf(sc);
		super.setCandidate_type(Candidate_type.INTERNSHIP);
		System.out.println(" Enter majors : ");
		majors = sc.nextLine();
		System.out.println(" Enter semester : ");
		semester = sc.nextLine();
		System.out.println(" Enter University Name : ");
		university_name = sc.nextLine();
	}
}
