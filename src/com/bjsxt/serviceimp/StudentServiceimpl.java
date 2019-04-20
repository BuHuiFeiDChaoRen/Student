package com.bjsxt.serviceimp;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bjsxt.mapper.Studentmapper;
import com.bjsxt.mapper.Teachermapper;
import com.bjsxt.pojo.PageInfo;
import com.bjsxt.pojo.student;
import com.bjsxt.utils.MybatisUtils;

public class StudentServiceimpl implements Studentservice {

	@Override
	public PageInfo showpage(String sname, String tname, String pagesizestr, String pagenumstr) {
		int pagesize = 2;
		if(pagesizestr!=null && !pagesizestr.equals("")){
			pagesize = Integer.parseInt(pagesizestr);
		}
		int pagenum = 1;
		if(pagenumstr !=null && !pagenumstr.equals("")){
			pagenum = Integer.parseInt(pagenumstr);
		}
		SqlSession session = MybatisUtils.getsqlsession();
		Studentmapper studentmapper = session.getMapper(Studentmapper.class);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPagenum(pagenum);
		pageInfo.setPagesize(pagesize);
		pageInfo.setPagestart(pagesize*(pagenum-1));
		pageInfo.setSname(sname);
		pageInfo.setTname(tname);
		
		List<student> list = studentmapper.selbypage(pageInfo);
	
		Teachermapper teachermapper = session.getMapper(Teachermapper.class);
		for (student student : list) {
			student.setTeacher(teachermapper.selbyid(student.getTid()));
		}
		pageInfo.setList(list);
		//×ÜÒ³Êý
		long count = studentmapper.selcountbypageinfo(pageInfo);
		pageInfo.setTotal(count%pagesize==0?count/pagesize:count/pagesize+1);
		return pageInfo;
		
	}

}
