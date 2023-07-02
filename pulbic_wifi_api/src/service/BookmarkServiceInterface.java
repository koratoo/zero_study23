package service;

import java.util.List;

import dto.BookmarkGroupDTO;

public interface BookmarkServiceInterface {
    void insert(String bmk_name, String bmk_sequence);
    void delete(String bmk_mno);
    BookmarkGroupDTO getById(String bmk_mno);
    void update(String bmk_name, String bmk_sequence, String bmk_mno);
    List<BookmarkGroupDTO> getAll();
}
