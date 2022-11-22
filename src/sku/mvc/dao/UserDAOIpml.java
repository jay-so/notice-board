package sku.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sku.mvc.util.DbUtil;
import sku.mvc.vo.Electronics;
import sku.mvc.vo.UserDTO;

public class UserDAOIpml implements UserDAO {

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException {
		// 로드,연결,실행,닫기
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			UserDTO dbDTO = null;
			
				
			String sql = "select user_id, pwd, name from users where user_id=? and pwd=?";
			try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			//?가 있다면 ?의 순서대로 setXxx()가 필요하다.
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getPwd());
				
			rs = ps.executeQuery();
			if(rs.next()) { //커서를 앞으로(행) 이동
				dbDTO = new UserDTO(rs.getString(1),rs.getString(2), rs.getString(3));
				
			}
				
			}finally{
				DbUtil.dbClose(rs,ps, con);
			}
				
			return dbDTO;
			}
}
