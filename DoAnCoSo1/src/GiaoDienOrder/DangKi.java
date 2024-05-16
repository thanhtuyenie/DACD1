package GiaoDienOrder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DangKi extends JFrame {

	private JPanel contentPane;
	private JTextField tfTenDK;
	private JPasswordField passDK;
	private JTextField tfSDT;
	private JTextField tfMaNV;
	private JTextField tfMaCuaHang;
	

	static Connection con;
	static Statement stmt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKi frame = new DangKi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private void themDuLieuVaoCSDL() {
	    try {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_order?useUnicode=yes&characterEncoding=UTF-8", "root", "");
	        stmt = con.createStatement();

	        String sql = "INSERT INTO nhanvien (manhanvien, macuahang, tennhanvien, matkhau, sdt) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, tfMaNV.getText());
	        ps.setString(2, tfMaCuaHang.getText());
	        ps.setString(3, tfTenDK.getText());
	        ps.setString(4, passDK.getText());
	        ps.setString(5, tfSDT.getText());
	 
	        if (tfMaNV.getText().isEmpty() || tfMaCuaHang.getText().isEmpty() ||  tfTenDK.getText().isEmpty() || passDK.getPassword().length == 0 || tfSDT.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Yêu cầu điền đầy đủ thông tin");
	        } else {
	            if (KiemTraMaCuaHangTonTai(tfMaCuaHang.getText())) {
	                // Thực hiện đăng kí sau khi kiểm tra mã cửa hàng tồn tại
	                int n = ps.executeUpdate();
	                if (n != 0) {
	                    JOptionPane.showMessageDialog(this, "Đăng kí thành công");
	                } else {
	                    JOptionPane.showMessageDialog(this, "Đăng kí thất bại");
	                }
	            } else {
	                JOptionPane.showMessageDialog(this, "Mã cửa hàng " + tfMaCuaHang.getText() + " không tồn tại trong cơ sở dữ liệu. Vui lòng nhập lại!");
	            }
	        }
	    } catch (SQLIntegrityConstraintViolationException e) {
	        // Xử lý ngoại lệ xảy ra khi có trùng lặp khóa chính
	        JOptionPane.showMessageDialog(this, "Lỗi: Mã nhân viên đã tồn tại. Vui lòng kiểm tra lại.");
	    } catch (SQLException e) {
	        // Xử lý các lỗi SQL khác
	        JOptionPane.showMessageDialog(this, "Lỗi: Không thể thêm dữ liệu vào cơ sở dữ liệu. Vui lòng thử lại sau.");
	        e.printStackTrace(); // In ra lỗi chi tiết
	    }
	}
	
	
	public void addPlaceholderStyles(JTextField textFiled) {
		Font font = textFiled.getFont();
		font = font.deriveFont(Font.ITALIC);
		textFiled.setFont(font);
		textFiled.setForeground(Color.gray);
	}
	
	public void removePlaceholderStyles(JTextField textFiled) {
		Font font = textFiled.getFont();
		font = font.deriveFont(Font.PLAIN|Font.BOLD);
		textFiled.setFont(font);
		textFiled.setForeground(Color.black);	
	}
	
	
	
	public DangKi() {
		
		setTitle("ĐĂNG KÍ tại đây ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 819, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 812, 513);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblDANG_NHAP = new JLabel("ĐĂNG KÍ");
		lblDANG_NHAP.setBounds(337, 51, 172, 64);
		lblDANG_NHAP.setForeground(new Color(189, 9, 45));
		lblDANG_NHAP.setFont(new Font("Calibri", Font.BOLD, 39));
		lblDANG_NHAP.setBackground(new Color(218, 165, 32));
		panel.add(lblDANG_NHAP);
		
		JLabel lblTaoMoi = new JLabel("OR   Bạn chưa có tài khoản mới ?");
		lblTaoMoi.setBounds(355, 123, 237, 27);
		lblTaoMoi.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblTaoMoi.setForeground(new Color(255, 255, 255));
		panel.add(lblTaoMoi);
		
		JButton btnThoat = new JButton("");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				TrangThai.TrangThai trangthai = new TrangThai.TrangThai();
				trangthai.setVisible(true);
			}
		});
		

		
		btnThoat.setOpaque(false);
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setBorder(null);
		btnThoat.setBounds(18, 27, 55, 36);
		panel.add(btnThoat);
		
		
		JButton btnDangKi = new JButton("ĐĂNG KÍ");
		btnDangKi.setContentAreaFilled(false);
		btnDangKi.setBorderPainted(false);
		btnDangKi.setBounds(240, 424, 335, 36);
		btnDangKi.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnDangKi.setFont(new Font("Calibri", Font.BOLD, 18));
		btnDangKi.setForeground(new Color(0, 0, 0));
		panel.add(btnDangKi);
		btnDangKi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themDuLieuVaoCSDL();
			}
		});
		
		
		JButton btnDN_ofDK = new JButton("Đăng nhập");
		btnDN_ofDK.setIcon(new ImageIcon("D:\\ĐACS1\\AnhChung\\muiten.png"));
		btnDN_ofDK.setBounds(249, 125, 107, 23);
		btnDN_ofDK.setBorder(null);
		btnDN_ofDK.setForeground(new Color(255, 255, 255));
		btnDN_ofDK.setContentAreaFilled(false);
		btnDN_ofDK.setBorderPainted(false);
		btnDN_ofDK.setOpaque(false); //trong suôt
		btnDN_ofDK.setFont(new Font("Calibri", Font.PLAIN, 15));
		panel.add(btnDN_ofDK);
		btnDN_ofDK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DangNhap dangnhapgd = new DangNhap();
				dangnhapgd.setVisible(true);
			}
		});
		
		
		
		tfTenDK = new JTextField();
		tfTenDK.setText("Tên đăng kí");
		tfTenDK.setForeground(new Color(0, 0, 0));
		tfTenDK.setBounds(258, 183, 290, 27);
		tfTenDK.setOpaque(false);
		tfTenDK.setFont(new Font("Calibri", Font.PLAIN, 19));
		tfTenDK.setBorder(null);
		panel.add(tfTenDK);
		tfTenDK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfTenDK.getText().equals("Tên đăng kí")) {
					tfTenDK.setText(null);
					tfTenDK.requestFocus();
					removePlaceholderStyles(tfTenDK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfTenDK.getText().length()==0) {
					addPlaceholderStyles(tfTenDK);
					tfTenDK.setText("Tên đăng kí");
				}
			}
		});
		
		
		passDK = new JPasswordField();
		passDK.setText("Mật khẩu");
		passDK.setEchoChar('\u0000');
		passDK.setForeground(new Color(128, 128, 128));
		passDK.setBounds(258, 333, 299, 27);
		passDK.setOpaque(false);
		passDK.setFont(new Font("Calibri", Font.ITALIC, 19));
		passDK.setBorder(null);
		panel.add(passDK);
		passDK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passDK.getPassword()).equals("Mật khẩu")) {
					passDK.setText(null);
					passDK.requestFocus();
					passDK.setEchoChar('\u25cf');
					removePlaceholderStyles(passDK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (String.valueOf(passDK.getPassword()).length()==0) {
					addPlaceholderStyles(passDK);
					passDK.setText("Mật khẩu");
					passDK.setEchoChar('\u0000');
				}
			}
		});
		
		
		
		tfMaNV = new JTextField();
		tfMaNV.setText("Mã nhân viên");
		tfMaNV.setOpaque(false);
		tfMaNV.setForeground(new Color(128, 128, 128));
		tfMaNV.setFont(new Font("Calibri", Font.ITALIC, 19));
		tfMaNV.setBorder(null);
		tfMaNV.setBounds(258, 235, 299, 23);
		panel.add(tfMaNV);
		tfMaNV.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfMaNV.getText().equals("Mã nhân viên")) {
					tfMaNV.setText(null);
					tfMaNV.requestFocus();
					removePlaceholderStyles(tfMaNV);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfMaNV.getText().length()==0) {
					addPlaceholderStyles(tfMaNV);
					tfMaNV.setText("Mã nhân viên");
				}
			}
		});
		
		
		tfMaCuaHang = new JTextField();
		tfMaCuaHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		tfMaCuaHang.setText("Mã cửa hàng: DN9***");
		tfMaCuaHang.setOpaque(false);
		tfMaCuaHang.setForeground(Color.GRAY);
		tfMaCuaHang.setFont(new Font("Calibri", Font.ITALIC, 19));
		tfMaCuaHang.setBorder(null);
		tfMaCuaHang.setBounds(258, 284, 299, 27);
		panel.add(tfMaCuaHang);
		tfMaCuaHang.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tfMaCuaHang.getText().equals("Mã cửa hàng: DN9***")) {
					tfMaCuaHang.setText(null);
					tfMaCuaHang.requestFocus();
					removePlaceholderStyles(tfMaCuaHang);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfMaCuaHang.getText().length()==0) {
					addPlaceholderStyles(tfMaCuaHang);
					tfMaCuaHang.setText("Mã cửa hàng: DN9***");
				}
			}
		});

		
		tfSDT = new JTextField();
		tfSDT.setText("Số điện thoại");
		tfSDT.setForeground(new Color(128, 128, 128));
		tfSDT.setBounds(258, 379, 299, 27);
		tfSDT.setOpaque(false);
		tfSDT.setFont(new Font("Calibri", Font.ITALIC, 19));
		tfSDT.setBorder(null);
		panel.add(tfSDT);
		tfSDT.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(tfSDT.getText().equals("Số điện thoại")) {
					tfSDT.setText(null);
					tfSDT.requestFocus();
					removePlaceholderStyles(tfSDT);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tfSDT.getText().length()==0) {
					addPlaceholderStyles(tfSDT);
					tfSDT.setText("Số điện thoại");	
				}
			}
		});

		
		
		
		
		
		JSeparator separator_password = new JSeparator();
		separator_password.setForeground(new Color(255, 255, 255));
		separator_password.setBackground(new Color(255, 255, 255));
		separator_password.setBounds(273, 142, 64, 17);
		panel.add(separator_password);
		
		JLabel lblAnhDK = new JLabel("");
		lblAnhDK.setForeground(new Color(255, 255, 255));
		lblAnhDK.setBounds(10, 0, 802, 520);
		lblAnhDK.setIcon(new ImageIcon("D:\\ĐACS1\\dangkyNV.png"));
		panel.add(lblAnhDK);
		
		
		
		
		addPlaceholderStyles(tfTenDK);
		addPlaceholderStyles(tfMaNV);
		addPlaceholderStyles(tfMaCuaHang);
		addPlaceholderStyles(passDK);
		addPlaceholderStyles(tfSDT);
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
	 
	 
	 
	
	 public static boolean KiemTraMaCuaHangTonTai(String maCuaHang) {
		    ResultSet resultSet = LayToanBoDuLieuCuaCotMaCH(); // Lấy toàn bộ dữ liệu của cột macuahang từ cơ sở dữ liệu
		    try {
		        while (resultSet.next()) {
		            String maCuaHangDB = resultSet.getString("macuahang"); // Lấy giá trị của cột macuahang từ kết quả trả về
		            if (maCuaHang.equals(maCuaHangDB)) {
		                return true; // Trả về true nếu tìm thấy mã cửa hàng trùng khớp
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false; // Trả về false nếu không tìm thấy mã cửa hàng trùng khớp
		}

	 
	 
	 
	 
	 public static ResultSet LayToanBoDuLieuCuaCotMaCH() {
		    Connection conn = ketNoiCSDL(); // Sử dụng hàm ketNoiCSDL để lấy kết nối
		    ResultSet result = null;
		    
		    try {
		        String sql = "SELECT macuahang FROM quanli"; // Chọn cột macuahang từ bảng quanli
		        PreparedStatement statement = conn.prepareStatement(sql);
		        result = statement.executeQuery();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return result;
		}


	 

	
}