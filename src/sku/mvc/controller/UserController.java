package sku.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import sku.mvc.service.UserService;
import sku.mvc.service.UserServiceImpl;
import sku.mvc.vo.UserDTO;

/**
 * 
 * 로그인,로그아웃
 *
 */
public class UserController implements Controller {

	private UserService userService = new UserServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 로그인
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,SQLException,IOException {
		//서비스 -> dao
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		
		UserDTO dbDTO = userService.loginCheck(new UserDTO(userId, pwd));
		
		//세션에 정보를 저장
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", dbDTO.getUserId());	
		session.setAttribute("loginName", dbDTO.getName());	
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.jsp");
		mv.setRedirect(true); //redirect방식
		
		return mv;
	}
	
	/**
	 * 로그아웃
	 */
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException {
		
		//모든 세션의 정보를 지우기
		request.getSession().invalidate();
		
		return new ModelAndView("index.jsp",true);
	}

}
