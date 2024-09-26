package model.repository;

import javafx.util.Pair;
import model.DBManager;
import model.dto.StopDto;
import model.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StopDao implements Dao <Pair<Integer, Integer>, StopDto> {
    private Connection connexion;
    public StopDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    @Override
    public Pair<Integer, Integer> insert(StopDto item) throws RepositoryException {
            try {
            throw new UnsupportedOperationException();
        }catch (UnsupportedOperationException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void delete(Pair<Integer, Integer> key) throws RepositoryException {
            try {
            throw new UnsupportedOperationException();
        }catch (UnsupportedOperationException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void update(StopDto item) throws RepositoryException {
            try {
            throw new UnsupportedOperationException();
        }catch (UnsupportedOperationException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<StopDto> selectAll() throws RepositoryException {
        String sql = "SELECT id_line, id_station,id_order  FROM STOPS";
        List<StopDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StopDto dto = new StopDto( new Pair<>(rs.getInt(1),
                        rs.getInt(2)),rs.getInt(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StopDto select(Pair<Integer, Integer> key) throws RepositoryException {

        if (key == null) throw new RepositoryException("no lign and no stop  were provided! ");
        if (key.getKey() == null) throw new RepositoryException("no station  was provided! ");
        if (key.getValue() == null) throw new RepositoryException("no stop  was provided! ");

        try {
            PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM STOPS WHERE id_line =? AND id_station=?");
            stmt.setInt(1, key.getKey());
            stmt.setInt(2, key.getValue());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
               return new StopDto( new Pair<>(rs.getInt(1),
                        rs.getInt(2)),rs.getInt(3));
            }
            return null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
    public List<StopDto> selectCompleteLign(Integer lign) throws RepositoryException  {
        if (lign == null) throw new RepositoryException("no lign was provided! ");

        try {
            List<Integer> validLigns = List.of(1, 2, 5, 6);
            if(!validLigns.contains(lign)){
                throw new IllegalArgumentException("Non existant lign!");
            }
            List<StopDto> dtos = new ArrayList<>();
            PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM STOPS WHERE id_line = ? ORDER BY id_order");
            stmt.setInt(1, lign);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StopDto dto = new StopDto( new Pair<>(rs.getInt(1),
                        rs.getInt(2)),rs.getInt(3));
                dtos.add(dto);
            }
            return dtos;
        }
        catch (RuntimeException | SQLException e){
            throw new RepositoryException(e);
        }
    }
    public List<Integer> selectLineOfStop(Integer key) throws RepositoryException {

        if (key == null) throw new RepositoryException("no stop was provided! ");

        String sql = "SELECT id_line FROM STOPS WHERE id_station = ?";
        List<Integer> stationIds = new ArrayList<>();

        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                stationIds.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return stationIds;
    }
    public List<Integer> selectLines() throws RepositoryException {
        try {
            String sql = "SELECT DISTINCT id_line FROM STOPS";
            Statement stmt = connexion.createStatement();
            ResultSet res =  stmt.executeQuery(sql);

            List<Integer> stationIds = new ArrayList<>();
            while (res.next()) {
                stationIds.add(res.getInt(1));
            }
            return stationIds;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
