package com.bjsxt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.pojo.PageInfo;
import com.bjsxt.serviceimp.StudentServiceimpl;
import com.bjsxt.serviceimp.Studentservice;
@WebServlet("/show")
public class showservlet extends HttpServlet{
	private Studentservice stuservice = new StudentServiceimpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 		String sname = req.getParameter("sname");
		/*if(sname !=null && !sname.equals("")){
			sname = new String(sname.getBytes("iso-8859-1"),"utf-8");
		}*/
		String tname = req.getParameter("tname");
		/*if (tname != null && !tname.equals("")){
			tname = new String(tname.getBytes("iso-8859-1"),"utf-8");
		}*/
		String pagesizestr = req.getParameter("pagesizestr");
		String pagenumstr =req.getParameter("pagenumstr");
		
		PageInfo pi = stuservice.showpage(sname, tname, pagesizestr, pagenumstr);
		
		req.setAttribute("pageinfo", pi);
		req.getRequestDispatcher("index.jsp").forward(req, res);
		
	}
}
