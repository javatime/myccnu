package study.score;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.ccnu.academy.ManageAcademy;
import tool.ccnu.student.detailInfo.ManageStudentAllInfo;
import tool.ccnu.student.detailInfo.StudentAllInfoEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wuhaolin on 8/30/14.
 * :管理同学的平均学分成绩
 */
public class ManageMyPjxfScore {

	/**
	 * 所有的任选课的编号(所有的任选课都会被排除在计算平均学分成绩里)
	 */
	public static final String RenXuanKeClassNumber[] = {
			"32032071", "32032071", "32032071", "32032071", "32032071", "32032071", "32032103", "32032104", "32032105", "32032106", "32032163", "32032163", "34031042", "34031042", "34032001", "34032001", "34032001", "34032001", "34032001", "34032001", "34032001", "34032001", "35031052", "35031053", "35031053", "35031054", "35031054", "35031055", "35032093", "35032093", "35032094", "35032094", "39031001", "39031002", "39031003", "39031004", "39031005", "40132085", "40732077", "41232075", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41831047", "41831048", "41831049", "41831050", "41831050", "41831050", "41831051", "41832092", "42031057", "42031164", "42031165", "42031166", "42431058", "42431058", "42431059", "42831064", "42831064", "42831064", "42831064", "42831065", "42831065", "43031043", "43031045", "43131044", "43433151", "43433152", "43433154", "43532080", "43833155", "43833156", "43833157", "43833159", "43833161", "43833161", "43833161", "44133109", "44233110", "44233110", "44331046", "44333091", "44333091", "44333120", "44333121", "44333121", "44333121", "44333122", "44333123", "44333124", "44333125", "44333126", "44333127", "44333127", "44333128", "44333128", "44333128", "44333129", "44333129", "44333132", "44333133", "44333134", "44333135", "44333136", "44333137", "44333137", "44333138", "44333139", "44333139", "44333139", "44333140", "44333141", "44333141", "44333142", "44333143", "44333144", "44333144", "44333144", "44333144", "44333145", "44333145", "44333146", "44333146", "44333146", "44333146", "44333147", "44333147", "44333147", "44333147", "44333148", "44333148", "44333149", "44333149", "44333150", "44531003", "44631001", "44732069", "44932097", "44932097", "44932098", "44932099", "44933162", "44933162", "45231013", "45231017", "45231018", "45231019", "45231023", "45231027", "45231028", "45231031", "45231032", "45231033", "45231034", "45231036", "45231037", "45231038", "45231039", "45231040", "45231041", "45232081", "45232082", "47331020", "47331022", "47331029", "47331035", "47831012", "47832072", "47832073", "47832074", "32032100", "32032100", "32032101", "32032101", "32032101", "32032102", "32032102", "32032102", "32032102", "32032102", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "31001001", "32031007", "32031007", "42031177", "42031177", "32031008", "32031008", "32032071", "32032071", "32032071", "32032071", "32032071", "32032071", "32032105", "32032163", "32032163", "34032084", "34032084", "34032084", "34032084", "34032084", "34032084", "35031005", "35031052", "35031053", "35031054", "35031055", "35031176", "35032093", "35032094", "40132085", "40132170", "40133169", "40133169", "40133169", "40732077", "40732078", "40932088", "41232075", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41431060", "41432095", "41831047", "41831047", "41831048", "41831049", "41831050", "41831050", "41831050", "41831050", "41831051", "41832092", "42031164", "42031165", "42232175", "42431058", "42431058", "42431059", "42431059", "42631056", "42831064", "42831064", "42831064", "42831064", "42831064", "42831064", "42831064", "42831064", "42831065", "42831065", "42831065", "42831065", "42831172", "42831172", "42831173", "42831173", "43031043", "43031043", "43031044", "43031045", "43031045", "43031045", "43031174", "43231068", "43433151", "43433152", "43433153", "43433154", "43532076", "43532079", "43532080", "44133109", "44331046", "44331046", "44333091", "44333091", "44333120", "44333120", "44333121", "44333121", "44333122", "44333123", "44333124", "44333125", "44333126", "44333127", "44333128", "44333128", "44333129", "44333130", "44333131", "44333132", "44333133", "44333134", "44333137", "44333137", "44333138", "44333139", "44333139", "44333140", "44333140", "44333140", "44333140", "44333140", "44333140", "44333140", "44333141", "44333142", "44333143", "44333144", "44333144", "44333144", "44333145", "44333145", "44333146", "44333146", "44333147", "44333147", "44333147", "44333147", "44333147", "44333147", "44333147", "44333147", "44333148", "44333149", "44333149", "44333150", "44333167", "44333168", "44531003", "44531004", "44531004", "44533108", "44631001", "44631002", "44732069", "44732070", "44733107", "44932096", "44932097", "44932097", "44932098", "44932098", "44932099", "44933162", "44933162", "45031061", "45033171", "45231014", "45231017", "45231018", "45231019", "45231027", "45231028", "45231032", "45231033", "45232081", "45232082", "45232083", "47331020", "47331021", "47331029", "47331030", "47831009", "47831010", "47831012", "47832072", "47832073", "47832074", "32032101", "32032101", "32032101", "44833112", "44833113", "44833114", "44833115", "44833116", "44833117", "44833118", "44833119", "31001001", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001002", "31001003", "31001003", "31001003", "31001003", "31001003", "31001003", "31001003", "31001003", "31001003", "31001003", "31001004", "31001004", "31001004", "31001004", "31001004", "31001004", "31001004", "31001004", "32032100", "32032100", "32032100", "32032102", "32032102", "32032102", "32032102", "32032102",
	};

	/**
	 * 获得学号为xh的同学的平均学分成绩
	 *
	 * @param xh 学号
	 * @return 成绩
	 */
	public static MyPjxfScoreEntity get(String xh) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyPjxfScoreEntity where xh=?");
		query.setString(0, xh);
		MyPjxfScoreEntity re = (MyPjxfScoreEntity) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得所有的一个学院的同学的一个年级的所有同学的平均学分成绩
	 * 对于知道该同学专业的会限制为同一个专业的
	 * 安装分数排序
	 *
	 * @param xh 同学的学号
	 * @return 所有的成绩
	 */
	public static List<MyPjxfScoreEntity> list(String xh) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyPjxfScoreEntity where academy=? and xh like ? order by score desc ");
		query.setInteger(0, ManageAcademy.isWhichAcademy(xh));
		query.setString(1, xh.substring(0, 4) + "%");
		List<MyPjxfScoreEntity> re = query.list();
		query = session.createQuery("from StudentAllInfoEntity where xh=?");
		query.setString(0, xh);
		StudentAllInfoEntity studentAllInfoEntity = (StudentAllInfoEntity) query.uniqueResult();
		String myMajor = studentAllInfoEntity.getMajor();
		List<MyPjxfScoreEntity> rere = new LinkedList<>();
		for (MyPjxfScoreEntity one : re) {
			StudentAllInfoEntity you = ManageStudentAllInfo.get_XH(one.getXh());
			if (you == null) {
				continue;
			}
			String youMajor = you.getMajor();
			if (youMajor != null && youMajor.equals(myMajor)) {
				rere.add(one);
			}
		}
		HibernateUtil.closeSession(session);
		return rere;
	}


	/**
	 * 从数据库中取出该同学的成绩然后计算出一个同学的平均学分成绩
	 * 然后把新计算出的结果更新到数据库中
	 *
	 * @param xh 同学的学号
	 */
	public static void update(String xh) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyScoreEntity where xh=?");
		query.setString(0, xh);
		List<MyScoreEntity> allScore = query.list();
		HibernateUtil.closeSession(session);
		MyPjxfScoreEntity newOne = new MyPjxfScoreEntity();
		newOne.setXh(xh);
		newOne.setAcademy(ManageAcademy.isWhichAcademy(xh));
		float scoreSum = 0, xuefenSum = 0;
		for (MyScoreEntity one : allScore) {
			//所有的非任选的成绩才计入
			if (!isRenXuan(one.getClassNo())) {
				scoreSum += one.getSumScore() * one.getXuefen();
				xuefenSum += one.getXuefen();
			}
		}
		if (xuefenSum == 0) {
			newOne.setScore(0);
		} else {
			newOne.setScore(scoreSum / xuefenSum);
		}
		HibernateUtil.addOrUpdateEntity(newOne);
	}

	/**
	 * 判断是否是任选课
	 *
	 * @param classNumber 要判断的课的编号
	 * @return 是否是任选课
	 */
	private static boolean isRenXuan(String classNumber) {
		for (String one : RenXuanKeClassNumber) {
			if (classNumber.equals(one)) {
				return true;
			}
		}
		return false;
	}
}
