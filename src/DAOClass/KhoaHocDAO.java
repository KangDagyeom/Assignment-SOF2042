/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

import Models.KhoaHoc;
import java.util.ArrayList;
import java.sql.*;
import java.util.UUID;

/**
 *
 * @author Hyun
 */
public class KhoaHocDAO {

    private ArrayList<Course> courses = new ArrayList<>();

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=SOF2042_ASSIGNMENT;user=sa;password=123;trustServerCertificate=true";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    private KhoaHoc mapResult(ResultSet rs) throws SQLException {
        return new KhoaHoc(
                UUID.fromString(rs.getString("IDKhoaHoc")),
                rs.getString("MaKhoaHoc"),
                rs.getString("TenKhoaHoc"),
                rs.getString("MoTa"),
                rs.getString("HinhKhoaHoc"),
                rs.getDouble("HocPhi"),
                rs.getString("TrangThai")
        );
    }

    public ArrayList<KhoaHoc> executeQuery(String query, Object... params) {
        ArrayList<KhoaHoc> list = new ArrayList<>();
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

    public ArrayList<KhoaHoc> getListKhoaHoc() {
        String query = "SELECT * FROM KhoaHoc";
        return executeQuery(query);
    }

    public ArrayList<Course> loadCoursesFromDatabase() {
        try (Connection conn = getConnect(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT MaKhoaHoc, TenKhoaHoc, MoTa, HinhKhoaHoc, HocPhi, TrangThai FROM KhoaHoc"); ResultSet rs = stmt.executeQuery()) {

            courses.clear();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getString("MaKhoaHoc"),
                        rs.getString("TenKhoaHoc"),
                        rs.getString("MoTa"),
                        rs.getString("HinhKhoaHoc"),
                        rs.getDouble("HocPhi"),
                        rs.getString("TrangThai")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

}
