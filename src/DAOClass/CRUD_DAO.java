package DAOClass;

import Model.NhanVien;
import java.sql.*; // chỉ cho nhanh & lười
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CRUD_DAO {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=SOF2042_ASSIGNMENT;user=sa;password=123;trustServerCertificate=true";

    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    private NhanVien mapResult(ResultSet rs) throws SQLException {
        return new NhanVien(
                UUID.fromString(rs.getString("IDNhanVien")),
                rs.getString("MaNhanVien"),
                rs.getString("HoTen"),
                rs.getString("GioiTinh"),
                rs.getString("SoDienThoai"),
                rs.getString("DiaChi"),
                rs.getString("VaiTro"),
                rs.getString("MatKhau")
        );
    }

    public ArrayList<NhanVien> executeQuery(String query, Object... params) {
        ArrayList<NhanVien> al = new ArrayList<>();
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                al.add(mapResult(rs));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return al;
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

    public ArrayList<NhanVien> getList() {
        String query = "SELECT * FROM NhanVien";
        return executeQuery(query);
    }

}
// Viết 1 hàm duy nhất để thực thi tất cả các truy 
// vấn SQL cho mọi bảng
