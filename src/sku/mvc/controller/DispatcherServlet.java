package sku.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자의 모든 요청을 중앙집중적으로 관리해줄 진입점
 * Controller 역할이다.
 */
@WebServlet(urlPatterns = "/front", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String,Controller> map = new HashMap<String,Controller>();
	private Map<String,Class<?>> clsMap = new HashMap<String,Class<?>>();
      /**
       * 필요한 객체를 미리 생성해서 저장해둔다.
       **/
	@Override
	public void init(ServletConfig config) throws ServletException{
		
		//ResourceBundle은 ~.properties 파일을 로딩하는 전용 클래스
		//파일명을 설정할때 확장자는 생략한다.
		ResourceBundle rb = 
		//ResourceBundle.getBundle("sku/mvc/controller/ationMapping");	
		ResourceBundle.getBundle("ationMapping");
		for(String key : rb.keySet()) {
			String value = rb.getString(key);
			//System.out.println(key+ " = " + value);
			try {
			//문자열인 클래스를 객체로 생성한다...
		 Class<?> cls = Class.forName(value); //forName메소드는 인수로 전달된 문자열을 하나의 객체로 만들수 있도록 도와주는 메소드
		 Controller controller = (Controller)cls.newInstance(); //객체 생성
		 System.out.println(controller);
		 
		 map.put(key, controller);
		 clsMap.put(key,cls);
		 
			}catch(Exception e){
				e.printStackTrace();
	
			}
		}
	}
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String methodName = request.getParameter("methodName");
		//System.out.println("front 진입... key = " + key);
		//System.out.println("front 진입... methodName = " + methodName);
		
		if(key==null  || key.equals("")) {
			key="elec";
			methodName="select";
		}
		
		
		//메소드를 객체로 변환 과정
		Controller controller = map.get(key);
		Class<?> cls = clsMap.get(key);
		try {
		Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		
		ModelAndView mv = (ModelAndView)method.invoke(controller,request,response);
		
		if(mv.isRedirect()) { //redirect 방식 이동
			response.sendRedirect(mv.getViewName());
		}else {
			request.getRequestDispatcher(mv.getViewName()).forward(request, response);
		}
		
	}catch(Exception e) {
			e.printStackTrace();
			//request.setAttribute("errorMsg2", e.getCause().getMessage()); //뷰 페이지에서 ${errorMsg}
			//request.setAttribute("errorMsg",e.getCause().getMessage());
			request.setAttribute("errorMsg",e.getMessage());
			request.getRequestDispatcher("error/error.jsp").forward(request, response); 
		}
	}
}
