package GiaoDienOrder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
//import java.sql.*;




public class Ban2 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfTongCong;
    private DefaultTableModel model;
	private JLabel lblBan2;
	private JTable table;
	
	
    static Connection con;
    static Statement stmt;

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Ban2 frame = new Ban2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    //GiaoDien
    public Ban2() {
    	
    	setTitle("BÀN  2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));   
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 786, 473);
		contentPane.add(layeredPane);
        
		lblBan2 = new JLabel("2");
        lblBan2.setForeground(new Color(236, 112, 19));
        lblBan2.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
        lblBan2.setBounds(37, 35, 29, 46);
        layeredPane.add(lblBan2);
        
        
        tfTongCong = new JTextField();
		tfTongCong.setFont(new Font("Arial", Font.BOLD, 19));
		tfTongCong.setForeground(new Color(255, 255, 255));
		tfTongCong.setBounds(363, 301, 266, 38);
		tfTongCong.setBorder(null);
		tfTongCong.setOpaque(false);
		tfTongCong.setColumns(10);
		layeredPane.add(tfTongCong);
        
        JLabel lblDanhSach = new JLabel("Danh sách");
		lblDanhSach.setForeground(new Color(236, 112, 19));
		lblDanhSach.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblDanhSach.setBounds(293, 20, 272, 46);
		layeredPane.add(lblDanhSach);
        
		JLabel lblTongcong = new JLabel("Tổng cộng:");
		lblTongcong.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		lblTongcong.setForeground(new Color(255, 255, 255));
		lblTongcong.setBounds(160, 298, 145, 37);
		layeredPane.add(lblTongcong);
        
		JLabel lblAnhNen = new JLabel("");
		lblAnhNen.setIcon(new ImageIcon("D:\\ĐACS1\\ban1NV.png"));
		lblAnhNen.setBounds(0, 0, 800, 483);
		contentPane.add(lblAnhNen);
        
		
		JButton btnThoat = new JButton("Quay lại");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThoat.setIcon(new ImageIcon("D:\\ĐACS1\\ảnh mới\\muiten.png"));
		btnThoat.setForeground(new Color(236, 112, 19));
		btnThoat.setBounds(76, 20, 130, 25);
		btnThoat.setBorderPainted(false);
		btnThoat.setContentAreaFilled(false);
		layeredPane.add(btnThoat);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                GiaoDienChinh main = new GiaoDienChinh();
               	main.capNhatMauSauKhiLuu(); 
               	main.capNhatSoButtonMauXanh();
                main.setVisible(true);
            }
        });
        

        JButton btnMenu = new JButton("Menu");
		btnMenu.setForeground(new Color(255, 255, 255));
		btnMenu.setFont(new Font("Calibri Light", Font.PLAIN, 23));
		btnMenu.setBounds(91, 66, 109, 37);
		btnMenu.setBorderPainted(false);
		btnMenu.setContentAreaFilled(false);
		layeredPane.add(btnMenu);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
                
        
        JButton btnThem1 = new JButton("Thêm");
		btnThem1.setForeground(new Color(255, 128, 0));
		btnThem1.setFont(new Font("Calibri Light", Font.BOLD, 22));
		btnThem1.setBounds(36, 411, 145, 52);
		btnThem1.setBorderPainted(false);
		btnThem1.setContentAreaFilled(false);
		layeredPane.add(btnThem1);
        btnThem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });
        
        
        JButton btnXoa1 = new JButton("Xóa");
		btnXoa1.setForeground(new Color(255, 128, 0));
		btnXoa1.setFont(new Font("Calibri Light", Font.BOLD, 22));
		btnXoa1.setBounds(230, 413, 121, 50);
		btnXoa1.setBorderPainted(false);
		btnXoa1.setContentAreaFilled(false);
		layeredPane.add(btnXoa1);
		btnXoa1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            String maSanPham = (String) model.getValueAt(selectedRow, 0);		            
		            model.removeRow(selectedRow);
		            xoaDuLieuChoNutXoa(maSanPham); 
		            JOptionPane.showMessageDialog(null, "Xóa thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
        
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setForeground(new Color(255, 128, 0));
		btnSua.setFont(new Font("Calibri Light", Font.BOLD, 22));
		btnSua.setContentAreaFilled(false);
		btnSua.setBorderPainted(false);
		btnSua.setBounds(406, 413, 122, 50);
		layeredPane.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Edit();
		    }
		});


        

     
        JButton btnThanhtoan1 = new JButton("Thanh toán");
		btnThanhtoan1.setForeground(new Color(255, 128, 0));
		btnThanhtoan1.setHorizontalAlignment(SwingConstants.LEFT);
		btnThanhtoan1.setFont(new Font("Calibri Light", Font.BOLD, 22));
		btnThanhtoan1.setBounds(575, 410, 157, 53);
		btnThanhtoan1.setBorderPainted(false);
		btnThanhtoan1.setContentAreaFilled(false);
		layeredPane.add(btnThanhtoan1);
		btnThanhtoan1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       
		        java.util.Date date = new java.util.Date();
		        Timestamp timestamp = new Timestamp(date.getTime());
		        int soBan = Integer.parseInt(lblBan2.getText());
		        double tongTien = Double.parseDouble(tfTongCong.getText());
		        
		        DuaDuLieuLenCSDLDonHang();
		        xoaDuLieuChoNutThanhToan();
		        
		        GiaoDienChinh main = new GiaoDienChinh();
		        main.capNhatMauSauKhiThanhToan();
		        dispose(); 
		        DonHang donHang = new DonHang();
		        donHang.setVisible(true);
		       
		    }

			
		});


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(150, 120, 521, 169);
        layeredPane.add(scrollPane);
        
        table = new JTable();
        table.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
        	new Object[][] {
      
        	},
        	new String[] {
        		"Mã sản phẩm", "Tên món", "Số lượng", "Đơn giá/ món", "Thành tiền"
        	}
        ));
    }
    


    //Xử lí sự kiện
    public void TongCong() {
    	
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        double tongCong = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double thanhTien = (double) model.getValueAt(i, 4); 
            tongCong += thanhTien;
        }
        tfTongCong.setText(String.valueOf(tongCong));
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM danhsach");
            DefaultTableModel model = (DefaultTableModel) table.getModel();   
            model.setRowCount(0);

            while (rs.next()) {
                String maSanPham = rs.getString("masanpham");
                String tenMon = rs.getString("tenmon");
                int soLuong = rs.getInt("soluong");
                double donGia = rs.getDouble("dongia");
                double thanhTien = rs.getDouble("thanhtien");
                model.addRow(new Object[]{maSanPham, tenMon, soLuong, donGia, thanhTien});
            }
            con.close();
            TongCong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    public void xoaDuLieuChoNutXoa(String maSanPham) {
        try {
            con = ketNoiCSDL();
            String sql = "DELETE FROM danhsach WHERE masanpham=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maSanPham);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void capNhatDuLieuCSDL(String masanpham, String tenmon, int soluong, double dongia, double thanhtien) {
    	try {
    		con = ketNoiCSDL();

    		
            for (int row = 0; row < table.getRowCount(); row++) {
                String maSanPham = (String) table.getValueAt(row, 0);
                String tenMon = (String) table.getValueAt(row, 1);
                int soLuong = (int) table.getValueAt(row, 2);
                double donGia = (double) table.getValueAt(row, 3);
                double thanhTien = (double) table.getValueAt(row, 4);
               
                
                String sql = "UPDATE danhsach SET tenmon = ?, soluong = ?, dongia = ?, thanhtien = ? WHERE masanpham = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, tenMon);
                ps.setInt(2, soLuong);
                ps.setDouble(3, donGia);
                ps.setDouble(4, thanhTien);
                ps.setString(5, maSanPham);
                ps.executeUpdate();
            }
            con.close();
            JOptionPane.showMessageDialog(null, "Dữ liệu đã được cập nhật thành công.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật dữ liệu");
        }
    }
    
    
    public void Edit() {
    	 model = (DefaultTableModel) table.getModel();
    	 int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            String maSanPham = (String) table.getValueAt(selectedRow, 0);
	            String tenMon = (String) table.getValueAt(selectedRow, 1);
	            int soLuong = (int) table.getValueAt(selectedRow, 2);
	            double donGia = (double) table.getValueAt(selectedRow, 3);
	            double thanhTien = (double) table.getValueAt(selectedRow, 4);

	            String newSoLuongString = JOptionPane.showInputDialog("Nhập số lượng mới:", soLuong);

	            if (newSoLuongString != null) {
	                int newSoLuong = Integer.parseInt(newSoLuongString);
	                model.setValueAt(newSoLuong, selectedRow, 2);
	                double newThanhTien = newSoLuong * donGia;
	                model.setValueAt(newThanhTien, selectedRow, 4);
	                capNhatDuLieuCSDL(maSanPham, tenMon, newSoLuong, donGia, newThanhTien);
	                napDuLieuTuCSDL();
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	
    
    
    
    	public void xoaDuLieuChoNutThanhToan() {
			
    		try {
    	        con = ketNoiCSDL();
    	        Statement stmt = con.createStatement();
    	      
    	        String sql = "DELETE FROM danhsach";
    	        stmt.executeUpdate(sql);
    	        con.close();
    	    } catch (SQLException ex) {
    	        ex.printStackTrace();
    	    }
    		
			
		}
    	
    	
    	private void DuaDuLieuLenCSDLDonHang(){
    		    try {
    		        con = ketNoiCSDL();

    		        // Sử dụng câu lệnh INSERT INTO để chèn dữ liệu vào bảng donhang
    		        String sql = "INSERT INTO donhang (soban, tongtien, thoigian, manhanvien) VALUES (?, ?, ?, ?)";
    		        PreparedStatement ps = con.prepareStatement(sql);

    		        // Đặt giá trị cho các tham số của câu lệnh INSERT INTO
    		        ps.setInt(1, Integer.parseInt(lblBan2.getText())); // Giả sử lblBan1 là số thứ tự bạn muốn chèn
    		        ps.setDouble(2, Double.parseDouble(tfTongCong.getText()));
    		        ps.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
    		        // Tiếp tục đặt giá trị cho các tham số khác của câu lệnh INSERT INTO
    		        ps.setString(4, DangNhap.manhanvien); // Thay "Mã nhân viên" bằng mã nhân viên thực tế

    		        ps.executeUpdate();

    		        ps.close();
    		        con.close();
    		    } catch (SQLException ex) {
    		        ex.printStackTrace();
    		    }
    		}
    	
    	
    	
    	
    	
//    	public boolean kiemTraBangDuLieu() {
//    	    DefaultTableModel model = (DefaultTableModel) table.getModel();
//    	    
//    	    return model.getRowCount() > 0; // Trả về true nếu có dữ liệu trong bảng, ngược lại trả về false
//    	}
    	
    	
}

    