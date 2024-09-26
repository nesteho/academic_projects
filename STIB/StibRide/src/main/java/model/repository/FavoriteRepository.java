package model.repository;

import javafx.util.Pair;
import model.dto.Dto;
import model.dto.FavoriteDto;
import model.dto.StopDto;
import model.exception.RepositoryException;

import java.util.List;

public class FavoriteRepository implements Repository<Integer,FavoriteDto> {
    FavoriteDao dao;

    public FavoriteRepository() throws RepositoryException {
        this.dao = new FavoriteDao();
    }

    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto select(Integer key) throws RepositoryException {
        return dao.select(key);
    }

    @Override
    public Integer insert(FavoriteDto item) throws RepositoryException {
        return dao.insert(item);
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        dao.delete(key);
    }

    @Override
    public void update(FavoriteDto item) throws RepositoryException {
        dao.update(item);
    }
}
