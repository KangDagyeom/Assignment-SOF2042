/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

/**
 *
 * @author Hyun
 */
public class UserSession {

    private static String username;
    private static String role;
    private static String gioiTinh;

    public static void setUser(String user, String r, String gt) {
        username = user;
        role = r;
        gioiTinh = gt;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static String getGioiTinh() {
        return gioiTinh;
    }
}
