package service;

import java.util.List;

import dto.History;

public interface HistoryServiceInterface {
    void insert(String lat, String lnt);
    List<History> getAll();
    void delete(String hno);
}
