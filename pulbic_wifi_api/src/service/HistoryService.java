package service;

import java.util.List;

import dao.HistoryDAO;
import dto.History;

public class HistoryService implements HistoryServiceInterface{

	HistoryDAO dao = new HistoryDAO();
	
	public void insert(String lat, String lnt) {
		dao.insertHistory(lat, lnt);
	}
	
	public List<History> getAll(){
		return dao.getHistory();
	}
	
	public void delete(String hno) {
		dao.deleteBookmark(hno);
	}
}
