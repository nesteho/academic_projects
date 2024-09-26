package model.repository;

import javafx.util.Pair;
import model.dto.StopDto;
import model.exception.RepositoryException;

import java.util.List;

public class StopRepository implements Repository<Pair<Integer, Integer>, StopDto> {
        StopDao dao;

        public StopRepository() throws RepositoryException {
            this.dao = new StopDao();

        }
        @Override
        public List<StopDto> selectAll() throws RepositoryException {
            return dao.selectAll();
        }

        public StopDto select(Pair<Integer, Integer> key) throws RepositoryException {
            return dao.select(key);
        }

        @Override
        public Pair<Integer, Integer> insert(StopDto item) throws RepositoryException {
                return dao.insert(item);
        }

        @Override
        public void delete(Pair<Integer, Integer> key) throws RepositoryException {
                dao.delete(key);
        }

        @Override
        public void update(StopDto item) throws RepositoryException {
                dao.update(item);
        }

        public List<StopDto> selectCompleteLign(Integer lign) throws RepositoryException {
            return dao.selectCompleteLign(lign);
        }
        public List<Integer> selectLineOfStop(Integer key) throws RepositoryException {
            return dao.selectLineOfStop(key);
        }
        public List<Integer> selectLines() throws RepositoryException {
            return dao.selectLines();
        }
    }
