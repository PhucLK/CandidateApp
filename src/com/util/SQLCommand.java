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

	// candidate
	public static final String CANDIDATE_QUERY_FIND_ONE = "select * from [dbo].candidates where CandidateId=?";

	// experience
	public static final String EXPERIENCE_JOIN_CANDIDATE = "SELECT idEx,CandidateId, fullname, birthday, phone, email, ExpInYear, ProSkill FROM [dbo].candidates c INNER JOIN [dbo].experience e ON c.id = e.idEx";
	public static final String EXPERIENCE_QUERY_FIND_ONE = "SELECT idEx,CandidateId, fullname, birthday, phone, email, ExpInYear, ProSkill FROM [dbo].candidates c  INNER JOIN [dbo].experience e ON c.id = e.idEx where CandidateId=?";
	
	// internship
	public static final String INTERNSHIP_JOIN_CANDIDATE = "SELECT idIs,CandidateId, fullname, birthday, phone, email, majors, semester,university FROM [dbo].candidates c INNER JOIN [dbo].internship i ON c.id = i.idIs";
	public static final String INTERNSHIP_QUERY_FIND_ONE = "select * from [dbo].internship where idIs=?";

	// fresher
	public static final String FRESHER_JOIN_CANDIDATE = "SELECT idFs,CandidateId, fullname, birthday, phone, email,Graduation_date, Graduation_rank,Education FROM [dbo].candidates c INNER JOIN [dbo].fresher f ON c.id = f.idFs";
	public static final String FRESHER_QUERY_FIND_ONE = "select * from [dbo].fresher where idFs=?";

	// certificate
	public static final String CERTIFICATION_QUERY_FIND_ONE = "select * from [dbo].certification where id=?";

}
