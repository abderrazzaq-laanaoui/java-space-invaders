package com.enset.glsid.dao;

import com.enset.glsid.dao.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.enset.glsid.dao.DbManager.getConnection;

public class PlayerDao {
    private Connection connection;

    public PlayerDao() { this.connection = getConnection(); }

    public Player addPlayer(Player player) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO players (username, email, password, top_score, nbr_coins, bg, rocket) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        pstmt.setString(1, player.getUsername());
        pstmt.setString(2, player.getEmail());
        pstmt.setString(3, player.getPassword());
        pstmt.setInt(4, player.getTopScore());
        pstmt.setInt(5, player.getNbrCoins());
        pstmt.setInt(6, player.getBg());
        pstmt.setInt(7, player.getRocket());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            int id = rs.getInt(1);
            player.setId(id);
        }
        return player;
    }

    public Player getPlayer(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM players WHERE id = ?"
        );
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int topScore = rs.getInt("top_score");
            int nbrCoins = rs.getInt("nbr_coins");
            int bg = rs.getInt("bg");
            int rocket = rs.getInt("rocket");
            return new Player(id, username, email, password, topScore, nbrCoins, bg, rocket);
        }
        return null;
    }

    public Player getPlayer(String username, String password) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT * FROM players WHERE username = ? AND password = ?"
        );
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String email = rs.getString("email");
            int topScore = rs.getInt("top_score");
            int nbrCoins = rs.getInt("nbr_coins");
            int bg = rs.getInt("bg");
            int rocket = rs.getInt("rocket");
            return new Player(id, username, email, password, topScore, nbrCoins, bg, rocket);
        }
        return null;
    }

    public void updatePlayer(Player player) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(
                "UPDATE players SET username = ?, email = ?, password = ?, top_score = ?, nbr_coins = ?, bg = ?, rocket = ? WHERE id = ?"
        );
        pstmt.setString(1, player.getUsername());
        pstmt.setString(2, player.getEmail());
        pstmt.setString(3, player.getPassword());
        pstmt.setInt(4, player.getTopScore());
        pstmt.setInt(5, player.getNbrCoins());
        pstmt.setInt(6, player.getBg());
        pstmt.setInt(7, player.getRocket());
        pstmt.setInt(8, player.getId());
        pstmt.executeUpdate();
    }

    public void deletePlayer(int id) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement(
                "DELETE FROM players WHERE id = ?"
        );
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}

