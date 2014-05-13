package play.movie;

import tool.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/11/14
 * Time: 10:09 PM
 */
public class ManageMovie {

	public static final String Target_HDQNJC = "HDQNJC";
	public static final String Target_LTDYC = "LTDYC";
	public static final String Target_iWANT="iWANT";
	public static final String Password_HDQNJC = "HDQNJC";
	public static final String Password_LTDYC = "LTDYC";
	public static final String Password_iWANT = "iWANT";
	public static final String Cmd_add = "add";
	public static final String Cmd_change = "change";
	public static final String Cmd_delete="delete";
	public static final int ChangeCount=5;

	/**
	 * 向数据库中添加一电影
	 * @param movieEntity
	 */
	public static void add(MyMovieEntity movieEntity) {
		HibernateUtil.addEntity(movieEntity);
	}

	/**
	 * 改变一电影的内容
	 * @param movieEntity
	 * @return
	 */
	public static void change(MyMovieEntity movieEntity){
		HibernateUtil.updateEntity(movieEntity);
	}

	/**
	 * 用唯一表示电影的Id获得一电影
	 * @param id
	 * @return 如果不存在该Id的movie就返回null
	 */
	public static MyMovieEntity get(int id){
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("from MyMovieEntity as movies where movies.id=?");
		query.setInteger(0,id);
		MyMovieEntity re= (MyMovieEntity)query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得目标为target的所有电影
	 * @param target
	 * @return
	 */
	public static List<MyMovieEntity> get(String target){
		if (target.equals(Target_HDQNJC) || target.equals(Target_LTDYC) || target.equals(Target_iWANT)){
			Session session=HibernateUtil.getSession();
			Query query= session.createQuery("from MyMovieEntity as movie where movie.target=?");
			query.setMaxResults(15);
			query.setString(0,target);
			List<MyMovieEntity> re=query.list();
			HibernateUtil.closeSession(session);
			return re;
		}else {
			return null;
		}
	}

	/**
	 * 用于分页查询
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyMovieEntity> get_page(int from, String target){
		Session session=HibernateUtil.getSession();
		Query query= session.createQuery("from MyMovieEntity as movie where movie.target=? order by id desc ");
		query.setString(0,target);
		query.setFirstResult(from);
		query.setMaxResults(ChangeCount);
		List<MyMovieEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 把这个电影从数据库中删除
	 * @param movieEntity
	 */
	public static void remove(MyMovieEntity movieEntity){
		HibernateUtil.removeEntity(movieEntity);
	}

	/**
	 * 把这个电影从数据库中删除
	 * @param id
	 */
	public static void remove(int id){
		Session session=HibernateUtil.getSession();
		Query query= session.createQuery("DELETE MyMovieEntity WHERE id = "+id);
		query.executeUpdate();
		HibernateUtil.closeSession(session);
	}

	/**
	 * 检验管理员身份合法性
	 * @param password
	 * @param target
	 * @return 如果验证合法返回true否则返回false
	 */
	public static boolean ManagePasswordisOk(String password, String target){
		if (target.equalsIgnoreCase(Target_HDQNJC)){
			if (password.equals(Password_HDQNJC)){
				return true;
			}else {
				return false;
			}
		}else if (target.equalsIgnoreCase(Target_LTDYC)){
			if (password.equals(Password_LTDYC)){
				return true;
			}else {
				return false;
			}
		}else if (target.equals(Target_iWANT)){
			if (password.equals(Password_iWANT)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	public static String targetToChineseString(String target){
		String re="校园电影";
		if (target.equals(ManageMovie.Target_HDQNJC)){
			re="华大青年剧场";
		}else if (target.equals(ManageMovie.Target_LTDYC)){
			re="露天电影场";
		}else if (target.equals(ManageMovie.Target_iWANT)){
			re="同学们分享的电影";
		}
		return re;
	}

	public static void main(String[] args) {
		MyMovieEntity movieEntity=new MyMovieEntity();
		movieEntity.setName("你");
		movieEntity.setPicUrl("picUrl");
		movieEntity.setPay("pay");
		movieEntity.setOther("other");
		movieEntity.setDes("des");
		movieEntity.setDate("dedefdeep");
		movieEntity.setTarget(Target_HDQNJC);
		add(movieEntity);


		get(Target_HDQNJC);
//		MyMovieEntity movieEntity=getRemain(2);
//		movieEntity.setDes("newDes");
//		movieEntity.setName("newMane");
//		movieEntity.setOther("newOther");
//		movieEntity.setPay("newPay");
//		movieEntity.setPicUrl("newPicUrl");
//		change(movieEntity);
	}
}
