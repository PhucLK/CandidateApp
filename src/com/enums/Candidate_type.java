package com.enums;

/**
 * 
 * @author PhucTV7
 * 
 */
public enum Candidate_type {

	EXPERIENCE(3), INTERNSHIP(2), FRESHER(1);

	private final int level;

	private Candidate_type(int level) {
		this.level = level;
	}

	/**
	 * @return  enum level 
	 */
	public int getLevel() {
		return level;
	}
}
