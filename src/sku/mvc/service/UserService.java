 package sku.mvc.service;

import java.sql.SQLException;
import sku.mvc.vo.UserDTO;

import sku.mvc.vo.UserDTO;

public interface UserService {
	/**
	 * �α��� üũ
	 * */
   UserDTO loginCheck(UserDTO userDTO)throws SQLException;
}
