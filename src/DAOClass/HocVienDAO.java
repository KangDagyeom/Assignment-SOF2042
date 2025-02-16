/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

/**
 *
 * @author Hyun
 */
import Models.HocVien;
import java.sql.*;
import java.util.ArrayList;

import java.util.UUID;

public class HocVienDAO {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=SOF2042_ASSIGNMENT;user=sa;password=123;trustServerCertificate=true";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    private HocVien mapResult(ResultSet rs) throws SQLException {
        return new HocVien(
                UUID.fromString(rs.getString("IDHocVien")),
                rs.getString("MaHocVien"),
                rs.getString("HoTen"),
                rs.getDate("NgaySinh"),
                rs.getString("SoDienThoai"),
                rs.getString("DiaChi")
        );
    }

    public ArrayList<HocVien> executeQuery(String query, Object... params) {
        ArrayList<HocVien> list = new ArrayList<>();
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

    public ArrayList<HocVien> getList() {
        String query = "SELECT * FROM HocVien";
        return executeQuery(query);
    }

    public int insertHocVien(String maHocVien, String hoTen, Date ngaySinh, String soDienThoai, String diaChi) {
        String query = "INSERT INTO HocVien (MaHocVien, HoTen, NgaySinh, SoDienThoai, DiaChi) VALUES (?, ?, ?, ?, ?)";
        Object[] params = {maHocVien, hoTen, ngaySinh, soDienThoai, diaChi};
        return executeUpdate(query, params);
    }

    public int updateHocVien(String maHocVien, String hoTen, Date ngaySinh, String soDienThoai, String diaChi) {
        String query = "UPDATE HocVien SET HoTen = ?, NgaySinh = ?, SoDienThoai = ?, DiaChi = ? WHERE MaHocVien = ?";
        Object[] params = {hoTen, ngaySinh, soDienThoai, diaChi, maHocVien};
        return executeUpdate(query, params);
    }

    public int deleteHocVien(String maHocVien) {
        String query = "DELETE FROM HocVien WHERE MaHocVien = ?";
        Object[] params = {maHocVien};
        return executeUpdate(query, params);
    }
}
