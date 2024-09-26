package model.repository;

import model.DBManager;
import model.dto.StationDto;
import model.exception.RepositoryException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Un seul dao pr toutes les langue car av= classe dupliqué jute pour nomTable !=
public class StationDao implements Dao<Integer, StationDto>{
    private Connection connexion;
    private StationRepository.Language language;

    public StationDao(StationRepository.Language language) throws RepositoryException {
        //veut 1..1 connexion BD | accès n'imp ou/ds nimp quelle classe!
        connexion = DBManager.getInstance().getConnection();
        this.language = language;
    }
    @Override
    public Integer insert(StationDto item) throws RepositoryException {
        try {
            throw new UnsupportedOperationException();
        }catch (UnsupportedOperationException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        try {
            throw new UnsupportedOperationException();
        }catch (UnsupportedOperationException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public void update(StationDto item) throws RepositoryException {
        try {
            throw new UnsupportedOperationException();
        }catch (UnsupportedOperationException e){
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        var tableName = languageToTable();
        String sql = "SELECT id, name FROM "+tableName;

        List<StationDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StationDto dto = new StationDto(rs.getInt(1), rs.getString(2));
                dtos.add(dto);
            }
        } catch (SQLException | RepositoryException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StationDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No provided key");
        }
        var tableName = languageToTable();
        String sql = "SELECT id, name FROM " + tableName + " WHERE id = ?";
        StationDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException(" Non unique record " + key);
            }
        } catch (SQLException | RepositoryException  e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private String languageToTable() {
        switch (language) {
            case FRENCH:
                return "STATIONS";
            case DUTCH:
                return "STATIONS_NL";
            default:
                throw new RepositoryException("Unsupported language: " + language);
        }
    }

    public void setLanguage(StationRepository.Language language) {
        this.language = language;
    }
}

