package model.repository;

import model.DBManager;
import model.dto.FavoriteDto;
import model.exception.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavoriteDao implements Dao<Integer, FavoriteDto> {
    private Connection connexion;
    public FavoriteDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }
    @Override
    public Integer insert(FavoriteDto item) throws RepositoryException {
        if (item == null) throw new NullPointerException("No favorite provided!");

        if (item.getName() == null) {
            throw new NullPointerException("No given Favorite's name");
        }
        if (item.getId_origin() == null) {
            throw new NullPointerException("No given Origin");
        }
        if (item.getId_destination() == null) {
            throw new NullPointerException("No given Destination");
        }

        try {
            PreparedStatement stmt = connexion.prepareStatement("INSERT INTO FAVORITES (name, id_origin, id_destination) VALUES (?, ?, ?)");
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getId_origin());
            stmt.setInt(3, item.getId_destination());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                throw new SQLException("Failed to get the generated ID for the new favorite");
            }
            throw new SQLException("Failed to insert favorite ");

        } catch (SQLException | NullPointerException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) throw new IllegalArgumentException("No key provided!");

        try {
            PreparedStatement stmt = connexion.prepareStatement("DELETE FROM FAVORITES WHERE id_favorite = ?");
            stmt.setInt(1, key);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new SQLException("Failed to delete favorite or item not found");
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(FavoriteDto item) throws RepositoryException {
        if (item == null) {
            throw new IllegalArgumentException("No favorite provided!");
        }

        if (item.getName() == null) {
            throw new IllegalArgumentException("No given Favorite's name");
        }
        if (item.getId_origin() == null) {
            throw new IllegalArgumentException("No given Origin");
        }
        if (item.getId_destination() == null) {
            throw new IllegalArgumentException("No given Destination");
        }

        try {
            PreparedStatement stmt = connexion.prepareStatement("UPDATE FAVORITES SET name = ?, id_origin = ?, id_destination = ? WHERE id_favorite = ?");
            stmt.setString(1, item.getName());
            stmt.setInt(2, item.getId_origin());
            stmt.setInt(3, item.getId_destination());
            stmt.setInt(4, item.getKey());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new SQLException("Failed to update favorite or item not found");
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RepositoryException(e);
        }
    }


    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        String sql = "SELECT *  FROM FAVORITES";
        List<FavoriteDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                FavoriteDto dto = new  FavoriteDto( rs.getInt(1),
                        rs.getString(2),rs.getInt(3),rs.getInt(4));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public FavoriteDto select(Integer key) throws RepositoryException {
        if (key == null) throw new RepositoryException("no lign and no stop  were provided! ");
        try {
            PreparedStatement stmt = connexion.prepareStatement("SELECT * FROM FAVORITES WHERE id_favorite =?");
            stmt.setInt(1, key);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new FavoriteDto( rs.getInt(1),
                        rs.getString(2),rs.getInt(3),rs.getInt(4));
            }
            return null;
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
}
