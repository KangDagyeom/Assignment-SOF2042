package DAOClass;

import Models.DangKyKhoaHoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DangKyKhoaHocDAO {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=SOF2042_ASSIGNMENT;user=sa;password=123;trustServerCertificate=true";

    private Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    public ArrayList<DangKyKhoaHoc> getAllDangKy() {
        ArrayList<DangKyKhoaHoc> list = new ArrayList<>();
        String query = "SELECT * FROM DangKyKhoaHoc";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DangKyKhoaHoc dk = new DangKyKhoaHoc(
                        UUID.fromString(rs.getString("IDHocVien")),
                        UUID.fromString(rs.getString("IDKhoaHoc")),
                        UUID.fromString(rs.getString("IDLopHoc")),
                        rs.getTimestamp("NgayDangKy"),
                        rs.getDouble("HocPhi"),
                        rs.getString("TrangThai"),
                        rs.getDouble("Diem")
                );
                list.add(dk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertDangKy(String maHocVien, String maKhoaHoc, String tenLop, Timestamp ngayDangKy, double hocPhi, String trangThai, double diem) {
        String query = "INSERT INTO DangKyKhoaHoc (IDHocVien, IDKhoaHoc, IDLopHoc, NgayDangKy, HocPhi, TrangThai, Diem) "
                + "VALUES ((SELECT IDHocVien FROM HocVien WHERE MaHocVien = ?), "
                + "        (SELECT IDKhoaHoc FROM KhoaHoc WHERE MaKhoaHoc = ?), "
                + "        (SELECT IDLopHoc FROM LopHoc WHERE TenLop = ?), ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maHocVien);
            ps.setString(2, maKhoaHoc);
            ps.setString(3, tenLop);
            ps.setTimestamp(4, ngayDangKy);
            ps.setDouble(5, hocPhi);  // Đã thêm đúng vị trí
            ps.setString(6, trangThai);
            ps.setDouble(7, diem);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateDangKy(String idHocVien, String idKhoaHoc, String idLopHoc, Timestamp ngayDangKy, double hocPhi, String trangThai, double diem) {
        String query = "UPDATE DangKyKhoaHoc SET NgayDangKy=?, HocPhi=?, TrangThai=?, Diem=? WHERE IDHocVien=? AND IDKhoaHoc=? AND IDLopHoc=?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setTimestamp(1, ngayDangKy);
            ps.setDouble(2, hocPhi);
            ps.setString(3, trangThai);
            ps.setDouble(4, diem);
            ps.setObject(5, idHocVien);
            ps.setObject(6, idKhoaHoc);
            ps.setObject(7, idLopHoc);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteDangKy(String maHocVien, String maKhoaHoc, String tenLop) {
        String query = "DELETE FROM DangKyKhoaHoc WHERE IDHocVien IN (SELECT IDHocVien FROM HocVien WHERE MaHocVien = ?) "
                + "AND IDKhoaHoc IN (SELECT IDKhoaHoc FROM KhoaHoc WHERE MaKhoaHoc = ?) "
                + "AND IDLopHoc IN (SELECT IDLopHoc FROM LopHoc WHERE TenLop = ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, maHocVien);
            ps.setString(2, maKhoaHoc);
            ps.setString(3, tenLop);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
