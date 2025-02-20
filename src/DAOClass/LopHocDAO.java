package DAOClass;

import Models.LopHoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LopHocDAO {

    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=SOF2042_ASSIGNMENT;user=sa;password=123;trustServerCertificate=true";

    private Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    public List<LopHoc> getAllLopHoc() {
        List<LopHoc> list = new ArrayList<>();
        String query = "SELECT * FROM LopHoc";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LopHoc lopHoc = new LopHoc(
                        UUID.fromString(rs.getString("IDLopHoc")),
                        rs.getString("TenLop"),
                        rs.getInt("SiSo"),
                        rs.getDate("ThoiGianBatDau"),
                        rs.getDate("ThoiGianKetThuc"),
                        rs.getString("CaHoc"),
                        UUID.fromString(rs.getString("IDGiangVien"))
                );
                list.add(lopHoc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insertLopHoc(String tenLop, int siSo, java.sql.Date thoiGianBatDau, java.sql.Date thoiGianKetThuc, String caHoc, UUID idGiangVien) {
        String query = "INSERT INTO LopHoc (TenLop, SiSo, ThoiGianBatDau, ThoiGianKetThuc, CaHoc, IDGiangVien) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tenLop);
            ps.setInt(2, siSo);
            ps.setDate(3, thoiGianBatDau);
            ps.setDate(4, thoiGianKetThuc);
            ps.setString(5, caHoc);
            ps.setObject(6, idGiangVien);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int updateLopHoc(UUID idLopHoc, String tenLop, int siSo, java.sql.Date thoiGianBatDau, java.sql.Date thoiGianKetThuc, String caHoc, UUID idGiangVien) {
        String query = "UPDATE LopHoc SET TenLop=?, SiSo=?, ThoiGianBatDau=?, ThoiGianKetThuc=?, CaHoc=?, IDGiangVien=? WHERE IDLopHoc=?";
        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, tenLop);
            ps.setInt(2, siSo);
            ps.setDate(3, thoiGianBatDau);
            ps.setDate(4, thoiGianKetThuc);
            ps.setString(5, caHoc);
            ps.setObject(6, idGiangVien);
            ps.setObject(7, idLopHoc);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteLopHocByTen(String tenLop) {

        String deleteFromChild = "DELETE FROM DangKyKhoaHoc WHERE IDLopHoc IN (SELECT IDLopHoc FROM LopHoc WHERE TenLop=?)";

        String deleteFromParent = "DELETE FROM LopHoc WHERE TenLop=?";

        try (Connection conn = getConnect(); PreparedStatement ps1 = conn.prepareStatement(deleteFromChild); PreparedStatement ps2 = conn.prepareStatement(deleteFromParent)) {

            ps1.setString(1, tenLop);
            ps1.executeUpdate();

            ps2.setString(1, tenLop);
            return ps2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public ArrayList<Integer> getSiSo() {
        ArrayList<Integer> siSoList = new ArrayList<>();
        String query = "SELECT SiSo FROM LopHoc";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                siSoList.add(rs.getInt("SiSo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return siSoList;
    }

    public ArrayList<String> getTenLop() {
        ArrayList<String> getTenLop = new ArrayList<>();

        String query = "SELECT TenLop FROM LopHoc";

        try (Connection conn = getConnect(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                getTenLop.add(rs.getString("TenLop"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getTenLop;
    }

}
