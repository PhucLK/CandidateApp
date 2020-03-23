/**
 * 
 *
 */
package com.util;

/**
 * @author PhucTV7
 *
 */
public class SQLCommand {

	// ----------------------SELECT

	// experience
	public static String EXPERIENCE_QUERY_FIND_ALL = "select * from [LK].[dbo].experience";
	public static String CERTIFICATEEX_QUERY_FIND_ALL = "select * from [LK].[dbo].certificationEx where CandidateId=?";

	// internship
	public static String INTERNSHIP_QUERY_FIND_ALL = "select * from internship";
	public static String CERTIFICATEIS_QUERY_FIND_ALL = "select * from [LK].[dbo].certificationIs where CandidateId=?";

	// fresher
	public static String FRESHER_QUERY_FIND_ALL = "select * from [LK].[dbo].fresher";
	public static String CERTIFICATEFS_QUERY_FIND_ALL = "select * from [LK].[dbo].certificationFs where CandidateId=?";

	// -----------------------INSERT

	// experience
	public static String EXPERIENCE_QUERY_INSERT = "insert into [experience] (fullname, BirthDay, Phone, Email, experien_in_year,profecsional_skill,CandidateId) values (?, ?, ?, ?, ?, ?, ?)";
	public static String CERTIFICATEEX_QUERY_INSERT = "insert into certificationEx (CandidateId,CertificatedID,CertificateName,CertificatedDate,CertificateRank) values (?, ?, ?, ?, ?)";

	// internship
	public static String INTERNSHIP_QUERY_INSERT = "insert into [experience] (fullname, BirthDay, Phone, Email, Majors,Semester,University,CandidateId) values (?, ?, ?, ?, ?, ?, ?)";
	public static String CERTIFICATEIS_QUERY_INSERT = "insert into certificationEx (CandidateId,CertificatedID,CertificateName,CertificatedDate,CertificateRank) values (?, ?, ?, ?, ?)";

	// fresher
	public static String FRESHER_QUERY_INSERT = "insert into [fresher] (fullname, BirthDay, Phone, Email, Graduation_date,Graduation_rank,Education,CandidateId) values (?, ?, ?, ?, ?, ?, ?)";
	public static String CERTIFICATEFS_QUERY_INSERT = "insert into certificationFs (CandidateId,CertificatedID,CertificateName,CertificatedDate,CertificateRank) values (?, ?, ?, ?, ?)";

	// -------------------------EDIT

	// experience
	public static String EXPERIENCE_QUERY_EDIT_BY_ID = "update [LK].[dbo].experience set fullname=?,birthday=?,phone=?,email=?,experien_in_year=?,profecsional_skill=? where CandidateId=?";
	public static String CERTIFICATEEX_QUERY_EDIT_BY_ID = "update [LK].[dbo].certificationEx set CertificatedID=?,CertificateName=?,CertificateRank=?,CertificatedDate=? where CandidateId=?";

	// internship
	public static String INTERNSHIP_QUERY_EDIT_BY_ID = "update [LK].[dbo].InternShip set fullname=?,birthday=?,phone=?,email=?,Majors=?,Semester=?,University=? where CandidateId=?";
	public static String CERTIFICATEIS_QUERY_EDIT_BY_ID = "update [LK].[dbo].certificationIs set CertificatedID=?,CertificateName=?,CertificateRank=?,CertificatedDate=? where CandidateId=?";

	// fresher
	public static String FRESHER_QUERY_EDIT_BY_ID = "update [LK].[dbo].fresher set fullname=?,birthday=?,phone=?,email=?,Graduation_date=?,Graduation_rank=?,Education=? where CandidateId=?";
	public static String CERTIFICATEFS_QUERY_EDIT_BY_ID = "update [LK].[dbo].certificationFs set CertificatedID=?,CertificateName=?,CertificateRank=?,CertificatedDate=? where CandidateId=?";

}
