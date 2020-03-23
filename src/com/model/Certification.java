package com.model;

/**
 * 
 * @author PhucTV7
 * 
 */
public class Certification {

	private int certificatedId;
	private String certificateName;
	private String certificateRank;
	private String certificateDate;

	/**
	 * 
	 */
	public Certification() {
		super();
	}

	/**
	 * @param certificatedId
	 * @param certificateName
	 * @param certificateRank
	 * @param certificateDate
	 */
	public Certification(int certificatedId, String certificateName, String certificateRank, String certificateDate) {
		super();
		this.certificatedId = certificatedId;
		this.certificateName = certificateName;
		this.certificateRank = certificateRank;
		this.certificateDate = certificateDate;
	}

	/**
	 * @return
	 */
	public int getCertificatedId() {
		return certificatedId;
	}

	/**
	 * @param certificatedId
	 */
	public void setCertificatedId(int certificatedId) {
		this.certificatedId = certificatedId;
	}

	/**
	 * @return certificateName
	 */
	public String getCertificateName() {
		return certificateName;
	}

	/**
	 * @param certificateName
	 */
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	/**
	 * @return certificateRank
	 */
	public String getCertificateRank() {
		return certificateRank;
	}

	/**
	 * @param certificateRank
	 */
	public void setCertificateRank(String certificateRank) {
		this.certificateRank = certificateRank;
	}

	/**
	 * @return certificateDate
	 */
	public String getCertificateDate() {
		return certificateDate;
	}

	/**
	 * @param certificateDate
	 */
	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
	}

}
