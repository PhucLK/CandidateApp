package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.enums.Candidate_type;
import com.util.UserInputUtil;

/**
 * @author PhucTV7
 *
 */
public abstract class Candidate {

	private int candidateId;
	private String fullName;
	private String birthDay;
	private String phone;
	private String email;
	private Candidate_type candidate_type;
	private List<Certification> certifications;
	private static int candidate_count = 0;

	/**
	 * 
	 */
	public Candidate() {
		super();
		candidate_count++;
	}

	/**
	 * @param candidateId
	 * @param fullName
	 * @param birthDay
	 * @param phone
	 * @param email
	 * @param candidate_type
	 * @param certifications
	 * @param certification
	 */
	public Candidate(int candidateId, String fullName, String birthDay, String phone, String email,
			Candidate_type candidate_type, List<Certification> certifications) {
		super();
		candidate_count++;
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.birthDay = birthDay;
		this.phone = phone;
		this.email = email;
		this.certifications = certifications;
		this.candidate_type = candidate_type;
	}

	/**
	 * @return the candidateId
	 */
	public int getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId the candidateId to set
	 */
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 * 
	 * 
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the candidate_type
	 */
	public Candidate_type getCandidate_type() {
		return candidate_type;
	}

	/**
	 * @param candidate_type
	 */
	public void setCandidate_type(Candidate_type candidate_type) {
		this.candidate_type = candidate_type;
	}

	/**
	 * @return the certifications
	 */
	public List<Certification> getCertifications() {
		return certifications;
	}

	/**
	 * @param certifications the certifications to set
	 */
	public void setCertifications(List<Certification> certifications) {
		this.certifications = certifications;
	}

	/**
	 * @return
	 */
	public static int getCandidate_count() {
		return candidate_count;
	}

	/**
	 * @param candidate_count
	 */
	public static void setCandidate_count(int candidate_count) {
		Candidate.candidate_count = candidate_count;
	}

	/**
	 * @param sc
	 * 
	 *
	 */
	public void inputInf(Scanner sc) {

		System.out.println("Enter ID : ");
		candidateId = UserInputUtil.inputTypeInt(sc);

		System.out.println("Enter FullName : ");
		fullName = sc.nextLine();

		System.out.println("Enter BirthDay : ");
		birthDay = UserInputUtil.inputBirthDay(sc);

		System.out.println("Enter Phone : ");
		phone = UserInputUtil.inputPhone(sc);

		System.out.println("Enter Email : ");
		email = UserInputUtil.inputEmail(sc);

		System.out.println("Enter number Certificate : ");
		int n = UserInputUtil.inputTypeInt(sc);
		certifications = new ArrayList<Certification>();
		Certification certification;

		for (int i = 0; i < n; i++) {
			certification = new Certification();
			System.out.println("Certificate " + (i + 1) + " :");
			System.out.println("Enter Certification Id : ");
			certification.setCertificatedId(Integer.valueOf(sc.nextLine()));

			System.out.println("Enter Certification Date : ");
			certification.setCertificateDate(sc.nextLine());

			System.out.println("Enter Certification Name : ");
			certification.setCertificateName(sc.nextLine());

			System.out.println("Enter Certification Id : ");
			certification.setCertificateRank(sc.nextLine());

			certifications.add(certification);

		}

	}

	/**
	 * @return all certificate of candidate about name, rank and date
	 */
	public String toStringCertificate() {
		String result = null;
		if (certifications != null) {
			result = certifications
					.stream().map(c -> "CertificateName=" + c.getCertificateName() + ",CertificateRank="
							+ c.getCertificateRank() + ",CertificateDate=" + c.getCertificateDate())
					.collect(Collectors.joining("|"));
		}
		return result;
	}

	public String toString() {
		return "ID=" + this.getCandidateId() + ",FullName=" + this.getFullName() + ",BirthDay=" + this.getBirthDay()
				+ ",Phone=" + this.getPhone() + ",Type=" + this.getCandidate_type().name();

	}

	public abstract String showInfor();

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(candidateId);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;

		Candidate c = (Candidate) obj;
		return this.candidateId == c.getCandidateId();
	}

}
