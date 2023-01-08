package com.enset.glsid.service;

import com.enset.glsid.dao.PlayerDao;
import com.enset.glsid.dao.entity.Player;

public class AuthService {
    private static AuthService instance = null;
    private static Player player = null;
    private final static PlayerDao playerDao ;
    static {
        playerDao = new PlayerDao();
    }

    private AuthService() {
    }

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public static Player getPlayer() {
        return player;
    }

   // login
    public static Player login(String username, String password) {
        try {
            player = playerDao.getPlayer(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }

    // register
    public static Player register(String username, String email, String password) {
        try {
            player = playerDao.addPlayer(new Player(username, email, password, 0, 0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }
}
