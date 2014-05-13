package tool;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/11/14
 * Time: 10:04 PM
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate框架
 */
public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * 已经打开传送
	 * @return
	 */
	public static Session getSession(){
		Session session= sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}

	public static void closeSession(Session session){
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * 向数据库中添加这个对象
	 * @param o
	 */
	public static boolean addEntity(Object o){
		Session session=getSession();
		session.save(o);
		closeSession(session);
		return true;
	}

	/**
	 * 从数据库中修改这个对象
	 * @param o
	 */
	public static boolean updateEntity(Object o){
		Session session=getSession();
		session.update(o);
		closeSession(session);
		return true;
	}

	/**
	 * 从数据库中删除这个对象
	 * @param o
	 */
	public static boolean removeEntity(Object o){
		Session session=getSession();
		session.delete(o);
		closeSession(session);
		return true;
	}
}