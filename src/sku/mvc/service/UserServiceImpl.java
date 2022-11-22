package sku.mvc.service;

import java.sql.SQLException;

import sku.mvc.dao.UserDAO;
import sku.mvc.dao.UserDAOIpml;
import sku.mvc.exception.LoginCheckException;
import sku.mvc.vo.UserDTO;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOIpml();
	
	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException {
		
		UserDTO dbDTO = userDAO.loginCheck(userDTO);
		if(dbDTO == null) {
			throw new LoginCheckException("정보가 일치하지 않습니다.");
		}
		
		return dbDTO;
	}

}
