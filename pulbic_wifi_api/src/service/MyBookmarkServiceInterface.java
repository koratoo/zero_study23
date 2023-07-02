package service;

import java.util.List;

import dto.MyBookmark;

public interface MyBookmarkServiceInterface {
    void insert(String bmk_name, String X_SWIFI_MAIN_NM);
    List<MyBookmark> getAll();
    void delete(String mybmk_no);
}
