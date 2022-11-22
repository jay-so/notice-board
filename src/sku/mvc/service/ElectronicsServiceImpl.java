package sku.mvc.service;

import java.sql.SQLException;
import java.util.List;

import sku.mvc.dao.ElectronicsDAO;
import sku.mvc.dao.ElectronicsDAOImpl;
import sku.mvc.vo.Electronics;

public class ElectronicsServiceImpl implements ElectronicsService {
	private ElectronicsDAO dao = new ElectronicsDAOImpl();


	@Override
	public List<Electronics> selectAll() throws SQLException{
		return dao.selectAll();
	}
	
	@Override
	public int insert(Electronics electronics) throws SQLException {
		int result = dao.insert(electronics);
		if(result == 0)throw new SQLException("등록되지 않았습니다.");
		return result;
	}

	@Override
	public Electronics selectByModelnum(String modelNum, boolean flag) throws SQLException {
		if(flag) {
			if(dao.increamentByReadnum(modelNum)==0)
				throw new SQLException("조회수 증가 오류로 상세보기 할 수 없습니다...");
		}
		Electronics elec = dao.selectByModelNum(modelNum);
		if(elec==null)
			throw new SQLException(modelNum+"에 해당하는 정보를 검색할 수 없습니다.");
		
		return elec;
	}

	@Override
	public int delete(String modelNum, String password) throws SQLException {
		//비밀번호 검증
				Electronics dbElec =dao.selectByModelNum(modelNum);
				
				if(!dbElec.getPassword().equals(password)) {
					throw new SQLException("비밀번호 오류입니다... (삭제..)");
				}
				int result = dao.delete(modelNum, password);
				if(result ==0) throw new SQLException(modelNum+"정보 삭제에 실패했습니다.");
		return result;
	}

	@Override
	public int update(Electronics electronics) throws SQLException {
		System.err.println(electronics.getPassword());
		//비밀번호 검증
		Electronics dbElec =dao.selectByModelNum(electronics.getModelNum());
		System.out.println(dbElec.getPassword());
		if(!dbElec.getPassword().equals(electronics.getPassword())) {
			throw new SQLException("비밀번호 오류입니다...");
		}
		
		//수정완료
		int result = dao.update(electronics);
		if(result ==0)throw new SQLException("수정되지 않았습니다.");
		
		return result;
	}
}
