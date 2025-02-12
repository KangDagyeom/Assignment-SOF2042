/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

import Models.ChuyenDe;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class ChuyenDeDAO {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=SOF2042_ASSIGNMENT;user=sa;password=123;trustServerCertificate=true";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    private ChuyenDe mapResult(ResultSet rs) throws SQLException {
        return new ChuyenDe(
                UUID.fromString(rs.getString("IDChuyenDe")),
                rs.getString("MaChuyenDe"),
                rs.getString("TenChuyenDe"),
                rs.getString("HinhChuyenDe"),
                rs.getString("MoTa"),
                rs.getInt("HocPhi")
        );
    }

    public ArrayList<ChuyenDe> executeQuery(String query, Object... params) {
        ArrayList<ChuyenDe> list = new ArrayList<>();
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapResult(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int executeUpdate(String query, Object... params) {
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<ChuyenDe> getHinhChuyenDe() {
        String query = "SELECT HinhChuyenDe FROM ChuyenDe";
        return executeQuery(query);
    }

    public ArrayList<ChuyenDe> getListChuyenDe() {
        String query = "SELECT * FROM ChuyenDe";
        return executeQuery(query);
    }

    private void loadTopicsFromDatabase() {
        try {
            Connection conn = getConnect();
            String sql = "SELECT TenChuyenDe,MoTa,HinhChuyenDe FROM ChuyenDe";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Topic> topics = new ArrayList<>();
            while (rs.next()) {
                topics.add(new Topic(rs.getString("title"), rs.getString("description"), rs.getString("iconPath")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
