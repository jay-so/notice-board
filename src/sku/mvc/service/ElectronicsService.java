package sku.mvc.service;

import java.sql.SQLException;
import java.util.List;

import sku.mvc.vo.Electronics;

public interface ElectronicsService {
	/**
	 * ElectronicsDAOImpl의 모든레코드 검색하는 메소드 호출
	 * */
  List<Electronics> selectAll() throws SQLException;
	  
	  /**
	   * ElectronicsDAOImpl의 레코드 삽입하는 메소드 호출
	   * */
	  int insert(Electronics electronics) throws SQLException;
	 
	  
	  /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 검색하는 메소드 호출
	   * @param : boolean flag - 조회수 증가 여부를 판별하는 매개변수임(true이면 조회수증가 / false이면 조회수 증가 안함)
	   * */
	  Electronics selectByModelnum(String modelNum, boolean flag)
			throws SQLException;
		 
		 
	 /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 삭제 메소드 호출
	   * */
	    int delete(String modelNum, String password) throws SQLException;
	  
	  
	  /**
	   * ElectronicsDAOImpl의 모델번호에 해당하는 레코드 수정  메소드 호출
	   * */
	   int update(Electronics electronics) throws SQLException;
	  
}
