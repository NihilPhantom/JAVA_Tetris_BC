package model;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class PlayerData {
    Connection c;
    Statement stmt;
    List<String> playersName;
    List<Integer> playerScore;
    String currentname;
    String password;

    PlayerData(String name, String password) {
        currentname = name;
        this.password = password;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data/player.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        createTable();
        getPlayerInfor(5);
    }

    boolean createTable() {
        try {
            String sql = "CREATE TABLE Players " + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " name           TEXT    NOT NULL, " + " password       CHAR(20)     NOT NULL, "
                    + " score          INT)";
            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    void getPlayerInfor(int num) {
        playersName = new ArrayList<String>();
        playerScore = new ArrayList<Integer>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT name,score  FROM Players ORDER BY score DESC LIMIT 4");
            while (rs.next()) {
                System.out.println(rs.getString("NAME") + rs.getInt("SCORE"));
                playersName.add(rs.getString("NAME"));
                playerScore.add(rs.getInt("SCORE"));
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    boolean savedata(int score) {
        System.out.println(currentname + password);
        try {
            stmt.executeQuery("INSERT INTO Players (name, password, score) VALUES ('" + this.currentname + "', '"
                    + this.password + "', '" + score + "')");
            c.commit();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public boolean signin(String name, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT password FROM Players WHERE name = '" + name + "'");
            if(rs.next()){
                if(!(""+rs.getString("password")).equals(pass)){
                    System.out.println(rs.getString("password") + " " + pass);
                    return false;
                }
            }
        } catch (SQLException e) {
            // TODO 修改下面的检测函数
            e.printStackTrace();
        }
        this.currentname = name;
        this.password = pass;
        return true;
    }
}

class Player {
    Player(String name, int score){
        this.name = name;
        this.score = score;
    }
    String name;
    int score;
}