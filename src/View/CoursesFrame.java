/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAOClass.ChuyenDeDAO;
import DAOClass.Course;
import DAOClass.KhoaHocDAO;
import DAOClass.UserSession;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Hyun
 */
public class CoursesFrame extends javax.swing.JFrame {

    /**
     * Creates new form CoursesFrame
     */
    private Map<String, Font> fontCache = new HashMap<>();
    private KhoaHocDAO khoaHocDAO = new KhoaHocDAO();
    private ArrayList<Course> courses = khoaHocDAO.loadCoursesFromDatabase();
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 3;

    public CoursesFrame() {
        initComponents();
        updateUI(courses);
        loadFont("Poppins-SemiBold", "/fonts/FZ Poppins-SemiBold.ttf");
        loadFont("Poppins-Regular", "/fonts/FZ Poppins-Regular.ttf");
        txtusername.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 16));
        txtrole.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 14));
        loadUserData();

    }

    private void loadFont(String fontName, String path) {
        try (InputStream fontStream = getClass().getResourceAsStream(path)) {
            if (fontStream == null) {
                System.err.println("Font file not found: " + path);
                return;
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            fontCache.put(fontName, font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    private Font getCustomFont(String fontName, int style, float size) {
        Font baseFont = fontCache.get(fontName);
        return (baseFont != null) ? baseFont.deriveFont(style, size) : new Font("SansSerif", style, (int) size);
    }

    private void loadUserData() {
        String username = UserSession.getUsername();
        String role = UserSession.getRole();
        String gioiTinh = UserSession.getGioiTinh();

        txtusername.setText(username);
        txtrole.setText(role);
        setAvatar(gioiTinh);
        if (username == null || role == null || gioiTinh == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Chưa có thông tin đăng nhập!");
            return;
        }
    }

    private void setAvatar(String gioiTinh) {
        String imagePath = "";
        if (gioiTinh.equals("Nam")) {
            imagePath = "C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Male-user-img.png"; // Thay đường dẫn file thật
        } else if (gioiTinh.equals("Nu")) {
            imagePath = "C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Female-user-img.png"; // Thay đường dẫn file thật
        } else {
            imagePath = "C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Unknow-user-img.png"; // Avatar mặc định
        }

        // Kiểm tra file tồn tại trước khi đặt icon
        File file = new File(imagePath);
        if (file.exists()) {
            lbavatar.setIcon(new ImageIcon(imagePath));
        } else {
            System.out.println("⚠ Lỗi: Không tìm thấy hình ảnh tại " + imagePath);
        }
    }

    private void updateUI(ArrayList<Course> allCourses) {
        int start = currentPage * ITEMS_PER_PAGE;
        JTextField[] code = {txtcode1, txtcode2, txtcode3};
        JTextField[] name = {txtname1, txtname2, txtname3};
        JTextField[] price = {txtprice1, txtprice2, txtprice3};
        JTextArea[] des = {txtdes1, txtdes2, txtdes3};
        JTextField[] status = {txtstatus1, txtstatus2, txtstatus3};
        JLabel[] iconLabels = {lbpr1, lbpr2, lbpr3};

        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            int index = start + i;
            if (index < allCourses.size()) {
                Course course = allCourses.get(index);
                code[i].setText(course.getMaKhoaHoc());
                name[i].setText(course.getTenKhoaHoc());
                des[i].setText(course.getMoTa());
                price[i].setText(course.getHocPhi().toString());
                status[i].setText(course.getTrangThai());
                ImageIcon icon = new ImageIcon(course.getHinhKhoaHoc());
                iconLabels[i].setIcon(icon);

                code[i].setVisible(true);
                name[i].setVisible(true);
                des[i].setVisible(true);
                price[i].setVisible(true);
                status[i].setVisible(true);
                iconLabels[i].setVisible(true);
            } else {
                code[i].setEditable(false);
                name[i].setEditable(false);
                des[i].setEditable(false);
                price[i].setEditable(false);
                status[i].setEditable(false);
                code[i].setText(null);
                name[i].setText(null);
                des[i].setText(null);
                price[i].setText(null);
                status[i].setText(null);
                ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\Assignment-SOF2042\\src\\Resources\\Unknow-icon.png");
                iconLabels[i].setIcon(icon);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbpr3 = new javax.swing.JLabel();
        lbpr2 = new javax.swing.JLabel();
        lbpr1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbavatar = new javax.swing.JLabel();
        txtrole = new javax.swing.JLabel();
        txtusername = new javax.swing.JLabel();
        lbcourse1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtname1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtstatus1 = new javax.swing.JTextField();
        txtcode1 = new javax.swing.JTextField();
        txtprice1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes1 = new javax.swing.JTextArea();
        lbcourse3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtcode3 = new javax.swing.JTextField();
        txtname3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdes3 = new javax.swing.JTextArea();
        txtprice3 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtstatus3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        lbpr3container = new javax.swing.JLabel();
        lbpr1container = new javax.swing.JLabel();
        lbcourse2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcode2 = new javax.swing.JTextField();
        txtname2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdes2 = new javax.swing.JTextArea();
        txtprice2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtstatus2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        lbpr2container = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtsearchbar = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coursera");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(lbpr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, -1, -1));
        jPanel1.add(lbpr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));
        jPanel1.add(lbpr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/App-logo-homeview.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 28, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Courses-clicked-btn.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Performance-unclick-btn.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Logout-unclick-btn.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Thanks-banner-img.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        lbavatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Male-user-img.png"))); // NOI18N
        jPanel1.add(lbavatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        txtrole.setForeground(new java.awt.Color(153, 204, 255));
        txtrole.setText("Role");
        jPanel1.add(txtrole, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, -1));

        txtusername.setForeground(new java.awt.Color(0, 0, 0));
        txtusername.setText("Username");
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        lbcourse1.setForeground(new java.awt.Color(255, 255, 255));
        lbcourse1.setText("Course code:");
        jPanel1.add(lbcourse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Course name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Description:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course price:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        txtname1.setBackground(new java.awt.Color(255, 255, 255));
        txtname1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 120, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        txtstatus1.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtstatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 120, -1));

        txtcode1.setBackground(new java.awt.Color(255, 255, 255));
        txtcode1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtcode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 120, -1));

        txtprice1.setBackground(new java.awt.Color(255, 255, 255));
        txtprice1.setForeground(new java.awt.Color(0, 0, 0));
        txtprice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprice1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtprice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 120, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Remove");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, -1, -1));

        txtdes1.setBackground(new java.awt.Color(255, 255, 255));
        txtdes1.setColumns(20);
        txtdes1.setForeground(new java.awt.Color(0, 0, 0));
        txtdes1.setLineWrap(true);
        txtdes1.setRows(5);
        txtdes1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtdes1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 290, 40));

        lbcourse3.setForeground(new java.awt.Color(255, 255, 255));
        lbcourse3.setText("Course code:");
        jPanel1.add(lbcourse3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, -1, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Course name:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, -1, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Description:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, -1));

        txtcode3.setBackground(new java.awt.Color(255, 255, 255));
        txtcode3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtcode3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 120, -1));

        txtname3.setBackground(new java.awt.Color(255, 255, 255));
        txtname3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 120, -1));

        txtdes3.setBackground(new java.awt.Color(255, 255, 255));
        txtdes3.setColumns(20);
        txtdes3.setForeground(new java.awt.Color(0, 0, 0));
        txtdes3.setLineWrap(true);
        txtdes3.setRows(5);
        txtdes3.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtdes3);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, 290, 40));

        txtprice3.setBackground(new java.awt.Color(255, 255, 255));
        txtprice3.setForeground(new java.awt.Color(0, 0, 0));
        txtprice3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprice3ActionPerformed(evt);
            }
        });
        jPanel1.add(txtprice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 120, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Course price:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Status:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, -1, -1));

        txtstatus3.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtstatus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 120, -1));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, -1, -1));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setForeground(new java.awt.Color(0, 0, 0));
        jButton7.setText("Remove");
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, -1, -1));

        lbpr3container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-Course3-container.png"))); // NOI18N
        jPanel1.add(lbpr3container, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        lbpr1container.setForeground(new java.awt.Color(0, 0, 0));
        lbpr1container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-Course1-container.png"))); // NOI18N
        jPanel1.add(lbpr1container, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        lbcourse2.setForeground(new java.awt.Color(255, 255, 255));
        lbcourse2.setText("Course code:");
        jPanel1.add(lbcourse2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Course name:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Description:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, -1));

        txtcode2.setBackground(new java.awt.Color(255, 255, 255));
        txtcode2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtcode2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 120, -1));

        txtname2.setBackground(new java.awt.Color(255, 255, 255));
        txtname2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 120, -1));

        txtdes2.setBackground(new java.awt.Color(255, 255, 255));
        txtdes2.setColumns(20);
        txtdes2.setForeground(new java.awt.Color(0, 0, 0));
        txtdes2.setLineWrap(true);
        txtdes2.setRows(5);
        txtdes2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtdes2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 290, 40));

        txtprice2.setBackground(new java.awt.Color(255, 255, 255));
        txtprice2.setForeground(new java.awt.Color(0, 0, 0));
        txtprice2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprice2ActionPerformed(evt);
            }
        });
        jPanel1.add(txtprice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 120, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Course price:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Status:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, -1, -1));

        txtstatus2.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtstatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 120, -1));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, -1, -1));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Remove");
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        lbpr2container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-Course2-container.png"))); // NOI18N
        jPanel1.add(lbpr2container, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 60, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 60, -1));

        jButton3.setText("Inert course, topic");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));
        jPanel1.add(txtsearchbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 200, -1));

        btnsearch.setText("Search");
        btnsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearchMouseClicked(evt);
            }
        });
        jPanel1.add(btnsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        HomeFrame.getInstance().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtprice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprice1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprice1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtprice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprice2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprice2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtprice3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprice3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprice3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton1ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if ((currentPage + 1) * ITEMS_PER_PAGE < courses.size()) {
            currentPage++;
            updateUI(courses);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (currentPage > 0) {
            currentPage--;
            updateUI(courses);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new GoodbyeFrame().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btnsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearchMouseClicked
        // TODO add your handling code here:
        String searchContent = txtsearchbar.getText();
        ArrayList<Course> getCourse = khoaHocDAO.loadCourseFromDatabase(searchContent);
        if (getCourse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong tim thay gi het!");
        } else {
            JOptionPane.showMessageDialog(this, "Da tim thay !");
            updateUI(getCourse);
        }
    }//GEN-LAST:event_btnsearchMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoursesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbavatar;
    private javax.swing.JLabel lbcourse1;
    private javax.swing.JLabel lbcourse2;
    private javax.swing.JLabel lbcourse3;
    private javax.swing.JLabel lbpr1;
    private javax.swing.JLabel lbpr1container;
    private javax.swing.JLabel lbpr2;
    private javax.swing.JLabel lbpr2container;
    private javax.swing.JLabel lbpr3;
    private javax.swing.JLabel lbpr3container;
    private javax.swing.JTextField txtcode1;
    private javax.swing.JTextField txtcode2;
    private javax.swing.JTextField txtcode3;
    private javax.swing.JTextArea txtdes1;
    private javax.swing.JTextArea txtdes2;
    private javax.swing.JTextArea txtdes3;
    private javax.swing.JTextField txtname1;
    private javax.swing.JTextField txtname2;
    private javax.swing.JTextField txtname3;
    private javax.swing.JTextField txtprice1;
    private javax.swing.JTextField txtprice2;
    private javax.swing.JTextField txtprice3;
    private javax.swing.JLabel txtrole;
    private javax.swing.JTextField txtsearchbar;
    private javax.swing.JTextField txtstatus1;
    private javax.swing.JTextField txtstatus2;
    private javax.swing.JTextField txtstatus3;
    private javax.swing.JLabel txtusername;
    // End of variables declaration//GEN-END:variables
}
