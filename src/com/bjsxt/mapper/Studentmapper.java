package com.bjsxt.mapper;

import java.util.List;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.pojo.student;

public interface Studentmapper {
	List<student> selbypage(PageInfo pageInfo);
	
	long selcountbypageinfo(PageInfo pageInfo);
}
