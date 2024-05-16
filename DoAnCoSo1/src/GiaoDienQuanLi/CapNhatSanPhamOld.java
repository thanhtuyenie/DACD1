package GiaoDienQuanLi;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class CapNhatSanPhamOld extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField txtDongia;
    private JTextField txtMachang;
    private DefaultTableModel model;
    private String machang;

    static Connection con;
    static Statement stmt;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CapNhatSanPhamOld frame = new CapNhatSanPhamOld();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CapNhatSanPhamOld() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 807, 532);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(417, 151, 346, 199);
        contentPane.add(scrollPane);
        
        JLabel lblCapNhat = new JLabel("Cập nhật sản phẩm");
        lblCapNhat.setForeground(new Color(255, 128, 0));
        lblCapNhat.setFont(new Font("Segoe UI Black", Font.PLAIN, 29));
        lblCapNhat.setBounds(308, 24, 282, 72);
        contentPane.add(lblCapNhat);

        JLabel lblDonGia = new JLabel("Đơn giá");
        lblDonGia.setForeground(new Color(255, 128, 0));
        lblDonGia.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
        lblDonGia.setBackground(Color.BLACK);
        lblDonGia.setBounds(43, 249, 77, 36);
        contentPane.add(lblDonGia);
        
        
        JLabel lblTenMatHang = new JLabel("Tên mặt hàng");
        lblTenMatHang.setForeground(new Color(255, 128, 0));
        lblTenMatHang.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
        lblTenMatHang.setBackground(Color.BLACK);
        lblTenMatHang.setBounds(43, 164, 138, 28);
        contentPane.add(lblTenMatHang);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 800, 500);
        contentPane.add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon("D:\\ĐACS1\\ảnh mới\\capnhatQL.png"));
        
        
        

        txtDongia = new JTextField();
        txtDongia.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        txtDongia.setColumns(10);
        txtDongia.setBounds(191, 252, 154, 37);
        contentPane.add(txtDongia);

        txtMachang = new JTextField();
        txtMachang.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        txtMachang.setColumns(10);
        txtMachang.setBounds(191, 164, 200, 37);
        contentPane.add(txtMachang);

        
        

        JButton btnThem = new JButton("Thêm");
        btnThem.setForeground(new Color(255, 128, 0));
        btnThem.setContentAreaFilled(false);
        btnThem.setBorderPainted(false);
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ThemDuLieuLenCSDLDonHang();
            	napDuLieuTuCSDL();
            }
        });
        btnThem.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        btnThem.setBounds(109, 448, 130, 37);
        contentPane.add(btnThem);
        
        

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setForeground(new Color(255, 128, 0));
        btnXoa.setContentAreaFilled(false);
        btnXoa.setBorderPainted(false);
        btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            String machang = (String) model.getValueAt(selectedRow, 0);		            
		            model.removeRow(selectedRow);
		            xoaDuLieuChoNutXoa(machang); 
		            JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
            }
        });
        btnXoa.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        btnXoa.setBounds(333, 448, 141, 37);
        contentPane.add(btnXoa);
        
        

        JButton btnSua = new JButton("Sửa");
        btnSua.setForeground(new Color(255, 128, 0));
        btnSua.setContentAreaFilled(false);
        btnSua.setBorderPainted(false);
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               suaDuLieu();
               // Cập nhật dữ liệu từ CSDL và hiển thị trên bảng
               napDuLieuTuCSDL();
            }
        });
        btnSua.setFont(new Font("Segoe UI Black", Font.BOLD, 22));
        btnSua.setBounds(557, 448, 141, 26);
        contentPane.add(btnSua);
        
        

        JButton btnThoat = new JButton("");
        btnThoat.setContentAreaFilled(false);
        btnThoat.setBorderPainted(false);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                GiaoDienChinh1 main = new GiaoDienChinh1();
                main.setVisible(true);
            }
        });
        btnThoat.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        btnThoat.setBounds(10, 14, 77, 37);
        contentPane.add(btnThoat);
        
        

        table = new JTable();
        table.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        table.setForeground(new Color(0, 0, 0));
        model = new DefaultTableModel();
        model.addColumn("Tên mặt hàng");
        model.addColumn("Đơn giá");
        table.setModel(model);
        scrollPane.setViewportView(table);
        

    }
    
    
    public static Connection ketNoiCSDL() {
  	  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_order?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con; 
    }
    

 
    public void napDuLieuTuCSDL() {

        con = ketNoiCSDL(); 
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM machang");
            DefaultTableModel model = (DefaultTableModel) table.getModel();   
            model.setRowCount(0);

            while (rs.next()) {
                String machang = rs.getString("machang");
                double dongia = rs.getDouble("dongia");
                model.addRow(new Object[]{machang, dongia});
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    public void xoaDuLieuChoNutXoa(String machang) {
        try {
            con = ketNoiCSDL();
            
            String sql = "DELETE FROM machang WHERE machang=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, machang);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private void capNhatDuLieuCSDL(String tenMatHangCu, String tenMatHangMoi, double donGiaMoi) {
        try {
            con = ketNoiCSDL();

            String sql = "UPDATE machang SET machang = ?, dongia = ? WHERE machang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMatHangMoi);
            ps.setDouble(2, donGiaMoi);
            ps.setString(3, tenMatHangCu);
            ps.executeUpdate();

            con.close();
            JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật dữ liệu");
        }
    }

    
    
    private void suaDuLieu() {
    	
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.");
            return;
        }

        String tenMatHangCu = model.getValueAt(selectedRow, 0).toString();
        double donGiaCu = Double.parseDouble(model.getValueAt(selectedRow, 1).toString());
        
        JTextField tenMatHangField = new JTextField(tenMatHangCu);
        JTextField donGiaField = new JTextField(String.valueOf(donGiaCu));
        
        Object[] message = {
            "Tên mặt hàng:", tenMatHangField,
            "Đơn giá:", donGiaField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Sửa dữ liệu", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String tenMatHangMoi = tenMatHangField.getText();
            double donGiaMoi = Double.parseDouble(donGiaField.getText());

            model.setValueAt(tenMatHangMoi, selectedRow, 0);
            model.setValueAt(donGiaMoi, selectedRow, 1);

            capNhatDuLieuCSDL(tenMatHangCu, tenMatHangMoi, donGiaMoi);
        }
    }

    
    	
    private void ThemDuLieuLenCSDLDonHang() {
    	    try {
    	        con = ketNoiCSDL();

    	        String sql = "INSERT INTO machang (machang, dongia) VALUES (?, ?)";
    	        PreparedStatement ps = con.prepareStatement(sql);
    	        ps.setString(1, txtMachang.getText());
    	        ps.setDouble(2, Double.parseDouble(txtDongia.getText()));
    	        
    	        ps.executeUpdate();
    	        
    	        ps.close();
    	        con.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }
    	}
}
