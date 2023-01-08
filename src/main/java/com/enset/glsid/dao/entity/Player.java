package com.enset.glsid.dao.entity;

public class Player {
    private int id;
    private String username;
    private String email;
    private String password;
    private int topScore;
    private int nbrCoins;
    private int bg;
    private int rocket;

    public Player( String username, String email, String password, int topScore, int nbrCoins, int bg, int rocket) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.topScore = topScore;
        this.nbrCoins = nbrCoins;
        this.bg = bg;
        this.rocket = rocket;
    }
 public Player(int id, String username, String email, String password, int topScore, int nbrCoins, int bg, int rocket) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.topScore = topScore;
        this.nbrCoins = nbrCoins;
        this.bg = bg;
        this.rocket = rocket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTopScore() {
        return topScore;
    }

    public void setTopScore(int topScore) {
        this.topScore = topScore;
    }

    public int getNbrCoins() {
        return nbrCoins;
    }

    public void setNbrCoins(int nbrCoins) {
        this.nbrCoins = nbrCoins;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getRocket() {
        return rocket;
    }

    public void setRocket(int rocket) {
        this.rocket = rocket;
    }
}
