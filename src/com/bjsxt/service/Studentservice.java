package com.bjsxt.service;

import com.bjsxt.pojo.PageInfo;

public interface Studentservice {
	PageInfo showpage(String sname,String tname,String pagesizestr,String pagenumstr);
}
