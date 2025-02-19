/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAOClass.ChuyenDeDAO;
import DAOClass.LopHocDAO;
import DAOClass.Topic;
import DAOClass.UserSession;
import Models.ChuyenDe;
import Models.LopHoc;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.metal.MetalIconFactory;

/**
 *
 * @author Hyun
 */
public class HomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form HomeFrame
     */
    private Map<String, Font> fontCache = new HashMap<>();
    private ChuyenDeDAO chuyenDeDAO = new ChuyenDeDAO();
    private LopHocDAO lopHocDAO = new LopHocDAO();
    private ArrayList<ChuyenDe> list = chuyenDeDAO.getListChuyenDe();
    private ArrayList<Integer> lopHocs = lopHocDAO.getSiSo();
    private static HomeFrame instance;

    private ArrayList<Topic> topics = chuyenDeDAO.loadTopicsFromDatabase();
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 4;

    public HomeFrame() {

        initComponents();
        this.setLocationRelativeTo(null);

        updateUI();
        updateSiSo();
        loadUserData();

        // Tải các font từ thư mục resources
        loadFont("Poppins-SemiBold", "/fonts/FZ Poppins-SemiBold.ttf");
        loadFont("Poppins-Regular", "/fonts/FZ Poppins-Regular.ttf");

        // Đặt font cho nhiều thành phần giao diện
        test1.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 20));
        lbtitle1.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 13));
        lbdescription1.setFont(getCustomFont("Poppins-Regular", Font.PLAIN, 12));

        lbtitle2.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 13));
        lbdescription2.setFont(getCustomFont("Poppins-Regular", Font.PLAIN, 12));

        lbtitle3.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 13));
        lbdescription3.setFont(getCustomFont("Poppins-Regular", Font.PLAIN, 12));

        lbtitle4.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 13));
        lbdescription4.setFont(getCustomFont("Poppins-Regular", Font.PLAIN, 12));

        date.setFont(getCustomFont("Poppins-Regular", Font.PLAIN, 14));
        txtcourses.setFont(getCustomFont("Poppins-Regular", Font.PLAIN, 16));
        txtcalendar.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 20));
        txtusername.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 16));
        txtrole.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 14));
        lbsiso1.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 22));
        lbsiso2.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 22));
        lbsiso3.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 22));
        lbsiso4.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 22));

        System.out.println("Font dang dung: " + txtcourses.getFont().getFontName());
        Date d = new Date();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat dayFormat = new SimpleDateFormat("d");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        String month = monthFormat.format(d);
        int day = Integer.parseInt(dayFormat.format(d));
        String year = yearFormat.format(d);

        String suffix = "th";
        String formattedDate = month + " " + day + suffix + ", " + year;

        date.setText(formattedDate);
        System.out.println(formattedDate);
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

    public static HomeFrame getInstance() {
        if (instance == null) {
            instance = new HomeFrame();
        }
        return instance;
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

    // In danh sách font có sẵn để debug
    private void listAvailableFonts() {
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        System.out.println("Available Fonts:");
        for (String font : fonts) {
            System.out.println(font);
        }
    }

    private void updateUI() {
        int start = currentPage * ITEMS_PER_PAGE;
        JLabel[] titleLabels = {lbtitle1, lbtitle2, lbtitle3, lbtitle4};
        JLabel[] descLabels = {lbdescription1, lbdescription2, lbdescription3, lbdescription4};
        JLabel[] iconLabels = {lbicon1, lbicon2, lbicon3, lbicon4};

        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            int index = start + i;
            if (index < topics.size()) {
                Topic topic = topics.get(index);
                titleLabels[i].setText(null);
                descLabels[i].setText(null);
                titleLabels[i].setText(topic.getTitle());
                descLabels[i].setText(topic.getDescription());

                // Set icon từ đường dẫn
                ImageIcon icon = new ImageIcon(topic.getIconPath());
                iconLabels[i].setIcon(icon);
            } else {
                titleLabels[i].setText("Fail");
                descLabels[i].setText("");
                iconLabels[i].setIcon(null);
            }
        }

    }

    private void updateSiSo() {
        int start = currentPage * ITEMS_PER_PAGE;
        JLabel[] titleLabels = {lbsiso1, lbsiso2, lbsiso3, lbsiso4};

        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            int index = start + i;
            if (index < lopHocs.size()) {
                titleLabels[i].setText(String.valueOf(lopHocs.get(index)));

            } else {
                titleLabels[i].setText("N/A");
            }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbtitle1 = new javax.swing.JLabel();
        lbicon4 = new javax.swing.JLabel();
        lbicon3 = new javax.swing.JLabel();
        lbicon1 = new javax.swing.JLabel();
        lbicon2 = new javax.swing.JLabel();
        lbsiso2 = new javax.swing.JLabel();
        lbdescription1 = new javax.swing.JLabel();
        lbtitle2 = new javax.swing.JLabel();
        lbsiso3 = new javax.swing.JLabel();
        lbdescription2 = new javax.swing.JLabel();
        lbsiso4 = new javax.swing.JLabel();
        lbtitle3 = new javax.swing.JLabel();
        lbdescription3 = new javax.swing.JLabel();
        lbtitle4 = new javax.swing.JLabel();
        lbdescription4 = new javax.swing.JLabel();
        lbsiso1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtcourses = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        test1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcalendar = new javax.swing.JLabel();
        lbavatar = new javax.swing.JLabel();
        txtrole = new javax.swing.JLabel();
        txtusername = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        Container_course = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coursera");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbtitle1.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle1.setText("title");
        jPanel1.add(lbtitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));
        jPanel1.add(lbicon4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 530, -1, -1));

        lbicon3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lbicon3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, -1, -1));

        lbicon1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lbicon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, -1, -1));

        lbicon2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(lbicon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        lbsiso2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbsiso2.setForeground(new java.awt.Color(51, 51, 51));
        lbsiso2.setText("12");
        jPanel1.add(lbsiso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 270, -1, -1));

        lbdescription1.setForeground(new java.awt.Color(255, 255, 255));
        lbdescription1.setText("jLabel15");
        lbdescription1.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel1.add(lbdescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 160, 40));

        lbtitle2.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle2.setText("jLabel12");
        jPanel1.add(lbtitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, -1, -1));

        lbsiso3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbsiso3.setForeground(new java.awt.Color(51, 51, 51));
        lbsiso3.setText("12");
        jPanel1.add(lbsiso3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, -1, -1));

        lbdescription2.setForeground(new java.awt.Color(255, 255, 255));
        lbdescription2.setText("jLabel15");
        lbdescription2.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel1.add(lbdescription2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 160, -1));

        lbsiso4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbsiso4.setForeground(new java.awt.Color(51, 51, 51));
        lbsiso4.setText("12");
        jPanel1.add(lbsiso4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 530, -1, -1));

        lbtitle3.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle3.setText("jLabel12");
        jPanel1.add(lbtitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, -1, -1));

        lbdescription3.setForeground(new java.awt.Color(255, 255, 255));
        lbdescription3.setText("jLabel15");
        lbdescription3.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel1.add(lbdescription3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 420, 160, -1));

        lbtitle4.setForeground(new java.awt.Color(255, 255, 255));
        lbtitle4.setText("jLabel12");
        jPanel1.add(lbtitle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 540, -1, -1));

        lbdescription4.setForeground(new java.awt.Color(255, 255, 255));
        lbdescription4.setText("jLabel15");
        lbdescription4.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel1.add(lbdescription4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 550, 160, -1));

        lbsiso1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbsiso1.setForeground(new java.awt.Color(51, 51, 51));
        lbsiso1.setText("12");
        jPanel1.add(lbsiso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/App-logo-homeview.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 28, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Courses-unclick-btn.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Performance-clicked-btn.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Logout-unclick-btn.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Web-full-container.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-container.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Python-container.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Js-container.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, -1, -1));

        txtcourses.setForeground(new java.awt.Color(153, 153, 153));
        txtcourses.setText("Courses");
        jPanel1.add(txtcourses, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        date.setForeground(new java.awt.Color(153, 204, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, 20));

        test1.setForeground(new java.awt.Color(255, 255, 255));
        test1.setText("Course Activity");
        jPanel1.add(test1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Calendar-img.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Thanks-banner-img.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Blue-line-img.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, -1, -1));

        txtcalendar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtcalendar.setForeground(new java.awt.Color(0, 0, 0));
        txtcalendar.setText("Calendar");
        jPanel1.add(txtcalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, -1, -1));

        lbavatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Male-user-img.png"))); // NOI18N
        jPanel1.add(lbavatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        txtrole.setForeground(new java.awt.Color(153, 204, 255));
        txtrole.setText("Role");
        jPanel1.add(txtrole, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, -1));

        txtusername.setForeground(new java.awt.Color(0, 0, 0));
        txtusername.setText("Username");
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));

        btnBack.setText("Back");
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setDefaultCapable(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 60, -1));

        btnNext.setText("Next");
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setDefaultCapable(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 60, -1));

        Container_course.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Courses-container.png"))); // NOI18N
        jPanel1.add(Container_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 904, 664));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel7MouseClicked

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if ((currentPage + 1) * ITEMS_PER_PAGE < topics.size()) {
            currentPage++;
            updateUI();
            updateSiSo();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (currentPage > 0) {
            currentPage--;
            updateUI();
            updateSiSo();
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        String role = UserSession.getRole();
        if (role.equals("Manager")) {
            this.dispose();
            new CoursesFrameMn().setVisible(true);
        } else {
            this.dispose();
            new CoursesFrame().setVisible(true);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new GoodbyeFrame().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(HomeFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Container_course;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbavatar;
    private javax.swing.JLabel lbdescription1;
    private javax.swing.JLabel lbdescription2;
    private javax.swing.JLabel lbdescription3;
    private javax.swing.JLabel lbdescription4;
    private javax.swing.JLabel lbicon1;
    private javax.swing.JLabel lbicon2;
    private javax.swing.JLabel lbicon3;
    private javax.swing.JLabel lbicon4;
    private javax.swing.JLabel lbsiso1;
    private javax.swing.JLabel lbsiso2;
    private javax.swing.JLabel lbsiso3;
    private javax.swing.JLabel lbsiso4;
    private javax.swing.JLabel lbtitle1;
    private javax.swing.JLabel lbtitle2;
    private javax.swing.JLabel lbtitle3;
    private javax.swing.JLabel lbtitle4;
    private javax.swing.JLabel test1;
    private javax.swing.JLabel txtcalendar;
    private javax.swing.JLabel txtcourses;
    private javax.swing.JLabel txtrole;
    private javax.swing.JLabel txtusername;
    // End of variables declaration//GEN-END:variables
}
