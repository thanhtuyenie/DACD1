package GiaoDienOrder;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;


public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSoLuong;
	private JTextField tfmasanpham;
	private JTable tableMenu;

    static Connection con;
    static Statement stmt;
    private JTextField tfTenmon;
    private JTextField tfDongia;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
	}

	
	public Menu() {
		setTitle("Bạn có thể chọn món tại đây");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 703, 405);
		contentPane.add(layeredPane);
		

		JLabel lblTenMon = new JLabel("Tên món");
		lblTenMon.setBackground(new Color(0, 0, 0));
		lblTenMon.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblTenMon.setForeground(new Color(0, 0, 0));
		lblTenMon.setBounds(24, 139, 77, 27);
		layeredPane.add(lblTenMon);
		
		JLabel lblSoLuong = new JLabel("Số lượng");
		lblSoLuong.setForeground(Color.BLACK);
		lblSoLuong.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblSoLuong.setBackground(Color.BLACK);
		lblSoLuong.setBounds(24, 228, 77, 27);
		layeredPane.add(lblSoLuong);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Calibri Light", Font.PLAIN, 25));
		lblMenu.setBounds(88, 52, 77, 27);
		layeredPane.add(lblMenu);

		JLabel lblAnhgdMenu = new JLabel("");
		lblAnhgdMenu.setBounds(0, 0, 703, 394);
		lblAnhgdMenu.setIcon(new ImageIcon("D:\\ĐACS1\\Menu.png"));
		contentPane.add(lblAnhgdMenu);
		
		JLabel lblMaSanPham = new JLabel("Mã sản phẩm");
		lblMaSanPham.setForeground(Color.BLACK);
		lblMaSanPham.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblMaSanPham.setBackground(Color.BLACK);
		lblMaSanPham.setBounds(27, 101, 100, 27);
		layeredPane.add(lblMaSanPham);
		
		JLabel lblDongia = new JLabel("Đơn giá");
		lblDongia.setForeground(Color.BLACK);
		lblDongia.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		lblDongia.setBackground(Color.BLACK);
		lblDongia.setBounds(24, 187, 100, 27);
		layeredPane.add(lblDongia);
		
		tfTenmon = new JTextField();
		tfTenmon.setColumns(10);
		tfTenmon.setBounds(118, 140, 100, 19);
		layeredPane.add(tfTenmon);
		
		tfSoLuong = new JTextField();
		tfSoLuong.setBounds(116, 229, 102, 19);
		layeredPane.add(tfSoLuong);
		tfSoLuong.setColumns(10);
		
		tfDongia = new JTextField();
		tfDongia.setColumns(10);
		tfDongia.setBounds(117, 187, 98, 19);
		layeredPane.add(tfDongia);
		
		tfmasanpham = new JTextField();
		tfmasanpham.setColumns(10);
		tfmasanpham.setBounds(116, 102, 103, 19);
		layeredPane.add(tfmasanpham);
		
		
		JButton btnOK = new JButton("OK");
		btnOK.setForeground(new Color(255, 255, 255));
		btnOK.setBounds(88, 279, 77, 36);
		btnOK.setBorderPainted(false);
		btnOK.setContentAreaFilled(false);
		layeredPane.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Ban1 ban1 = new Ban1();
				 ban1.setVisible(true);
				 ChuyenDuLieuQuaBan();
				 ban1.napDuLieuTuCSDL();

				 
				int soluong = Integer.parseInt(tfSoLuong.getText());
				if (soluong == 0) {
		            JOptionPane.showMessageDialog(contentPane, "Số lượng không được bằng 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setIcon(new ImageIcon("D:\\ĐACS1\\quaylai.png"));
		btnThoat.setBounds(0, 0, 98, 21);
		layeredPane.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Ban1 ban1 = new Ban1();
				ban1.setVisible(true);
			}
		});
		
		 JScrollPane scrollPane = new JScrollPane();
		 scrollPane.setBorder(null);
	     scrollPane.setBounds(265, 46, 402, 269);
	     layeredPane.add(scrollPane);

	     tableMenu = new JTable();
	     tableMenu.setOpaque(false); 
	     scrollPane.setViewportView(tableMenu);
	     tableMenu.setModel(new DefaultTableModel(
	         new Object[][] {},
	         new String[] {"Mã sản phẩm", "Tên món", "Đơn giá/ món"}
	         
	     ));
	     tableMenu.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int row = tableMenu.getSelectedRow();
	                if (row >= 0) {
	                    String masanpham = (String) tableMenu.getValueAt(row, 0);
	                    String tenmon = (String) tableMenu.getValueAt(row, 1);
	                    String dongia = String.valueOf(tableMenu.getValueAt(row, 2));

	                    tfmasanpham.setText(masanpham);
	                    tfTenmon.setText(tenmon);
	                    tfDongia.setText(dongia);
	                }
	            }
	        });
	
}


	
	//Xử lí sự kiện 
	public void ChuyenDuLieuQuaBan() {
	 	String tenmon = tfTenmon.getText();
        int soluong = Integer.parseInt(tfSoLuong.getText());
        double dongia = Double.parseDouble(tfDongia.getText());
        String masanpham = tfmasanpham.getText();
     // Kiểm tra món đã tồn tại trong đơn hàng chưa
        if (kiemTraMonTonTai(masanpham)) {
            JOptionPane.showMessageDialog(contentPane, "Món này đã chọn trước đó, Vui lòng thoát trang và chỉnh sửa số lượng!", "Thông Báo"
            		+ "", JOptionPane.WARNING_MESSAGE);
        } else {
            double thanhtien = soluong * dongia;
            themDuLieuVaoCSDL(masanpham, tenmon, soluong, dongia, thanhtien);
        }
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


	private void themDuLieuVaoCSDL(String masanpham, String tenmon, int soluong, double dongia, double thanhtien) {
		
		try {
			con = ketNoiCSDL();
			
	        String sql = "INSERT INTO danhsach (masanpham, tenmon, soluong, dongia, thanhtien) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, masanpham);
	        ps.setString(2, tenmon);
	        ps.setInt(3, soluong);
	        ps.setDouble(4, dongia);
	        ps.setDouble(5, thanhtien);
	        
	        ps.executeUpdate();
	       
	        con.close();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
	
	
	private boolean kiemTraMonTonTai(String masanpham) {
	    try {
	        con = ketNoiCSDL();
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM danhsach WHERE masanpham = ?");
	        ps.setString(1, masanpham);
	        ResultSet rs = ps.executeQuery();
	        boolean tonTai = rs.next(); 
	        con.close();
	        return tonTai;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false; 
	    }
	}
	
	
	
	public void getDataToMenu() {
		con = ketNoiCSDL();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM machang");
            DefaultTableModel model = (DefaultTableModel) tableMenu.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                String masanpham = rs.getString("masanpham");
                String tenmon = rs.getString("tenmon");
                double dongia = rs.getDouble("dongia");
                model.addRow(new Object[]{masanpham, tenmon, dongia});
            }
            con.close();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
	}


	
	
	
}