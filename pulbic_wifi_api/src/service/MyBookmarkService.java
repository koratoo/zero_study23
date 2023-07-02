package service;

import java.util.List;

import dao.MyBookmarkDAO;
import dto.MyBookmark;

public class MyBookmarkService implements MyBookmarkServiceInterface {

    private MyBookmarkDAO dao;

    public MyBookmarkService() {
        dao = new MyBookmarkDAO();
    }

    public void insert(String bmk_name, String X_SWIFI_MAIN_NM) {
        dao.insertMyBookmark(bmk_name, X_SWIFI_MAIN_NM);
    }

    public List<MyBookmark> getAll() {
        return dao.getMyBookmark();
    }

    public void delete(String mybmk_no) {
        dao.deleteMybmk(mybmk_no);
    }
}
