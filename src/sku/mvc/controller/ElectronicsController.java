package sku.mvc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sku.mvc.service.ElectronicsService;
import sku.mvc.service.ElectronicsServiceImpl;
import sku.mvc.vo.Electronics;

public class ElectronicsController implements Controller {
	private ElectronicsService service = new ElectronicsServiceImpl();	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 등록하기
	 */
	public ModelAndView insert(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//실제 프로젝트가 실행되고 있는 경로 가져오기	
			String saveDir=request.getServletContext().getRealPath("/save");
			
			int maxSize = 1024*1024*100;
			String encoding = "UTF-8";
				
			MultipartRequest m = new MultipartRequest(request, saveDir,maxSize, encoding, new DefaultFileRenamePolicy());	
			
			String modelNum = m.getParameter("model_num");
			String modelName = m.getParameter("model_name");
			String price = m.getParameter("price");
			String description = m.getParameter("description");
			
			description=description.replaceAll("<", "&lt;");
			
			String password = m.getParameter("password");
			
			
			Electronics elec = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
			//값 입력 유무에 해당하는 유효성 체크... 권장
			
			//파일이 첨부되었다면..
			if(m.getFilesystemName("file")!=null) {
				//파일이름
				elec.setFname(m.getFilesystemName("file"));
				//파일크기 저장
				elec.setFsize((int)m.getFile("file").length());
			}
			
			service.insert(elec);
			//service.selectAll();
			ModelAndView mv = new ModelAndView();
			mv.setViewName("front?key=elec&methodName=select");
			mv.setRedirect(true);
			return mv;
	}
	
	/**
	 * 전체 검색
	 */

	public ModelAndView select(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		List<Electronics> list = service.selectAll();
		request.setAttribute("list", list);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elec/list.jsp");

		return mv;
	}
	
	/**
	 * 상세보기
	 */
	public ModelAndView selectByModelNum(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String modelNum = request.getParameter("modelNum");
		Electronics elec =service.selectByModelnum(modelNum, true);
		
		request.setAttribute("elec", elec);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elec/read.jsp");
		                                           
		return mv;
	}
	
	/**
	 * 수정하기를 클릭했을때 수정폼에 출력할 데이터 검색
	 */
	public ModelAndView updateForm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 모델 번호 받기, 비밀번호
		String modelNum = request.getParameter("modelNum");
		
		Electronics elec =service.selectByModelnum(modelNum, false);
		request.setAttribute("elec", elec);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elec/update.jsp");
		
		return mv;
	}
	/**
	 * 수정하기 완료
	 */
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 
		String modelNum = request.getParameter("modelNum");
		String modelName = request.getParameter("modelName");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String password = request.getParameter("password");
		
		Electronics elec = new Electronics(modelNum, modelName, Integer.parseInt(price), description, password);
		
		service.update(elec);
		
		Electronics dbElec = service.selectByModelnum(modelNum, false);
		request.setAttribute("elec", dbElec);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("elec/read.jsp");
		
		return mv;
	}
	
	/**
	 * 삭제하기 
	 */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String modelNum = request.getParameter("modelNum");
		String password = request.getParameter("password");
		
		service.delete(modelNum, password);
		
		ModelAndView mv = new ModelAndView("front",true);
		return mv;
	}
}
