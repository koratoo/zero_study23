package service;

import java.util.List;

import dao.BookmarkGroupDAO;
import dto.BookmarkGroupDTO;

public class BookmarkService implements BookmarkServiceInterface{

	BookmarkGroupDAO dao = new BookmarkGroupDAO();
	
	//BookmarkAdd
	public void insert(String bmk_name, String bmk_sequence) {
		dao.insertBookmark(bmk_name, bmk_sequence);
	}
	
	//BookmarkDel Post
	public void delete(String bmk_mno) {
		dao.deleteBookmark(bmk_mno);
	}
	
	//BookmarkDel Get
	public BookmarkGroupDTO getById(String bmk_mno) {
		return dao.getOneBmkInfo(bmk_mno);
	}
	
	public void update(String bmk_name,String bmk_sequence, String bmk_mno) {
		 dao.modifyBookmark(bmk_name, bmk_sequence, bmk_mno);
	}
	public List<BookmarkGroupDTO> getAll(){
		 return dao.getBookmarkList();
	}
	
	
}
