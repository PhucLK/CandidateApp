package com.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dao.CandidateDAO;
import com.dao.ExpericenceDAO;
import com.dao.FresherDAO;
import com.dao.IntershipDAO;
import com.enums.Candidate_type;
import com.exceptions.MailException;
import com.model.Candidate;
import com.model.Experience;
import com.model.Fresher;
import com.model.InternShip;
import com.util.UserInputUtil;

/**
 * @author PhucTV7
 *
 */
public class CandidateManagement {

	/**
	 * @param args
	 * @throws MailException
	 * @throws MyException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String choice = "";
		
		do {
			getMenu();
			choice = sc.nextLine();
			choice = choice.trim();
			
			;
			CandidateDAO candidateDao;
			List<Candidate> list;
			Experience experience;
			InternShip intership;
			Fresher fresher;

			switch (choice) {

			// input candidate
			case ("1"):
				System.out.println("Enter candidate type : ");
				String cadidate_type = sc.nextLine();

				switch (cadidate_type) {

				case ("1"):
					experience = new Experience();
					experience.inputInf(sc);
					if (ExpericenceDAO.saveEx(experience)) {
						System.out.println("Success!");
					} else
						System.out.println("Fail");
					break;

				case ("2"):
					intership = new InternShip();
					intership.inputInf(sc);
					if (IntershipDAO.saveIs(intership)) {
						System.out.println("Success!");
					} else
						System.out.println("Fail");
					break;

				case ("3"):
					fresher = new Fresher();
					fresher.inputInf(sc);
					if (FresherDAO.saveFs(fresher)) {
						System.out.println("Success!");
					} else
						System.out.println("Fail");
					break;

				default:
					System.out.println("Please choose a correct candidate type !");
					System.out.println("Experience(1) | Internship(2) | Fresher(3)");
					break;
				}
				break;

			// get all candidates with distinct id
			case ("2"):
				candidateDao = new CandidateDAO();
				list = candidateDao.getAllWithDistinctId();

				if (!list.isEmpty()) {
					list.forEach((c) -> System.out.println(c.showInfor()));
				} else
					System.out.println("Empty lists!");
				break;

			// sort all candidates with distinct id
			case ("3"):
				candidateDao = new CandidateDAO();
				list = candidateDao.sortList(candidateDao.getAllWithDistinctId());

				if (!list.isEmpty()) {
					list.forEach((c) -> System.out.println(c.showInfor())); 
				} else
					System.out.println("Empty lists!");
				break;

			// Edit candidate
			case ("4"):

				System.out.println("Enter CandidateID : ");
				int id = UserInputUtil.inputTypeInt(sc);

				// check if id is exist -> edit
				candidateDao = new CandidateDAO();
				if (candidateDao.searchID(id)) {
					Candidate c = candidateDao.getCandidateByIDs(id);
					System.out.println("Change information of candidate : ");

					if (c.getCandidate_type().equals(Candidate_type.EXPERIENCE)) {
						experience = new Experience();
						experience.inputInf(sc);
						boolean a = ExpericenceDAO.editEx(experience, id);

						if (a) {
							System.out.println("Edit success !");
						} else
							System.err.println("Edit fail !");
					}

					if (c.getCandidate_type().equals(Candidate_type.INTERNSHIP)) {

						intership = new InternShip();
						intership.inputInf(sc);
						boolean a = IntershipDAO.editIs(intership, id);

						if (a) {
							System.out.println("Edit success !");
						} else
							System.err.println("Edit fail !");
					}

					if (c.getCandidate_type().equals(Candidate_type.FRESHER)) {

						fresher = new Fresher();
						fresher.inputInf(sc);
						boolean a = FresherDAO.editFs(fresher, id);

						if (a) {
							System.out.println("Edit success !");
						} else
							System.err.println("Edit fail !");
					}
				} else
					System.out.println("CandidateID not exist in list. Try again !");

				break;

			// find candidate by ID
			case ("5"):
				System.out.println("Enter CandidateID : ");
				int candidateId = UserInputUtil.inputTypeInt(sc);

				candidateDao = new CandidateDAO();
				if (candidateDao.searchID(candidateId)) {

					System.out.println(candidateDao.getCandidateByIDs(candidateId).showInfor());
				} else
					System.out.println("Not Found!");

				break;

			// get all full name of candidates
			case ("6"):
				candidateDao = new CandidateDAO();
				System.out.println(candidateDao.getAllFullName());
				break;

			// end program
			case ("7"):
				System.out.println("Program has ended");
				break;

			default:
				System.out.println("Your choice not correct");
				break;
			}
		} while (!choice.equalsIgnoreCase("7"));

	}

	/**
	 * get Menu
	 */
	public static void getMenu() {
		System.out.println("=================MENU================");
		System.out.println("1. Save a candidate");
		System.out.println("2. Show list the candidates");
		System.out.println("3. Sort list the candidates");
		System.out.println("4. Edit a candidate");
		System.out.println("5. Find candidate by CandidateID");
		System.out.println("6. Print full name of the candidates");
		System.out.println("7. Exit");
		System.out.print("Your choice : ");
	}
}
