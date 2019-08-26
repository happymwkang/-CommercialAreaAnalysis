package model;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.ChannelDAO;
import model.dto.ChannelDTO;
import model.dto.TestDTO;

public class CommertialService {

	//상권-점포 데이터 검색
	public static ArrayList<ChannelDTO> getAllChannel() throws SQLException{
		return ChannelDAO.getAllChannel();
	}
	
	//상권-점포  test 데이터 검색
		public static ArrayList<TestDTO> testChannel() throws SQLException{
			return ChannelDAO.testChannel();
		}
}
