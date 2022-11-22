package sku.mvc.dao;

import java.sql.SQLException;

import sku.mvc.vo.UserDTO;

public interface UserDAO {
  /**
   * 로그인 기능
   * select user_id , pwd, name from users where user_id=? and pwd=?
   * */
	//저장해올 vo를 저장해 두었기 때문에
	UserDTO loginCheck(UserDTO userDTO)throws SQLException;
}
