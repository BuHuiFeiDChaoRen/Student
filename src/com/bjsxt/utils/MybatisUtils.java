package com.bjsxt.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
	
	static{
		try {
			InputStream is = Resources.getResourceAsStream("mybatis.xml");
			factory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getsqlsession(){
		SqlSession session = tl.get();
		if(session==null){
			tl.set(factory.openSession());
		}
		return tl.get();
	}
	
	public static void closeSession(){
		SqlSession session = tl.get();
		if(session!=null){
			session.close();
		}
		tl.set(null);
		
		
		
	}
}
