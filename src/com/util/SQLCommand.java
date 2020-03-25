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
	public static final String CANDIDATE_QUERY_FIND_ONE = "select * from [candidate].[dbo].candidates where CandidateId=?";

	// experience
	public static final String EXPERIENCE_JOIN_CANDIDATE = "SELECT idEx,CandidateId, fullname, birthday, phone, email, ExpInYear, ProSkill FROM [candidate].[dbo].candidates c INNER JOIN [candidate].[dbo].experience e ON c.id = e.idEx";
	public static final String EXPERIENCE_QUERY_FIND_ONE = "SELECT idEx,CandidateId, fullname, birthday, phone, email, ExpInYear, ProSkill FROM [candidate].[dbo].candidates c  INNER JOIN [candidate].[dbo].experience e ON c.id = e.idEx where CandidateId=?";
	
	// internship
	public static final String INTERNSHIP_JOIN_CANDIDATE = "SELECT idIs,CandidateId, fullname, birthday, phone, email, majors, semester,university FROM [candidate].[dbo].candidates c INNER JOIN [candidate].[dbo].internship i ON c.id = i.idIs";
	public static final String INTERNSHIP_QUERY_FIND_ONE = "select * from [candidate].[dbo].internship where idIs=?";

	// fresher
	public static final String FRESHER_JOIN_CANDIDATE = "SELECT idFs,CandidateId, fullname, birthday, phone, email,Graduation_date, Graduation_rank,Education FROM [candidate].[dbo].candidates c INNER JOIN [candidate].[dbo].fresher f ON c.id = f.idFs";
	public static final String FRESHER_QUERY_FIND_ONE = "select * from [candidate].[dbo].fresher where idFs=?";

	// certificate
	public static final String CERTIFICATION_QUERY_FIND_ONE = "select * from [candidate].[dbo].certification where id=?";

}
