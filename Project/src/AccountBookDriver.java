
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AccountBookDriver{//장부 화면

	JFrame frame;
	
	String[] headings = {"기록 시간", "수입/지출", "금액", "내용", "남은 돈"};
	DefaultTableModel dt = new DefaultTableModel(headings, 0);
	JTable table = new JTable(dt);
	
	getDbConnection db = new getDbConnection();//getDbConnection 불러옴
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountBookDriver window = new AccountBookDriver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccountBookDriver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);//contentPane : 일반적인 컴포넌트들을 가질 수 있는 패널
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Image img = toolkit.getImage("pig.png");
	    frame.setIconImage(img);
	      
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("== 장부 내용 ==");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(290, 10, 358, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 37, 660, 480);
		
		JPanel buttonP = new JPanel();
		buttonP.setBounds(12, 518, 660, 43);
		JButton deleteB = new JButton("삭제");//삭제 버튼
		buttonP.setBackground(Color.ORANGE);
		buttonP.add(deleteB);
		
		deleteB.addActionListener(new ActionListener() {//삭제 버튼 기능
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "데이터를 삭제하겠습니까?", "메세지창", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {//예를 누른경우
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					int row = table.getSelectedRow();
					if(row<0) {
						JOptionPane.showMessageDialog(null, "삭제할 데이터를 클릭해주세요", "오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String query = "DELETE FROM book WHERE date = ?";
					
					try {
						db.DbConnection();
						db.ps = db.con.prepareStatement(query);
						
						//물음표가 1개 이므로 4개 각각 입력해야 한다.
						db.ps.setString(1, (String)model.getValueAt(row, 0));
						int count = db.ps.executeUpdate();
					}catch(Exception e2) {
						System.out.println(e2.getMessage());
					}finally {
						try {
							db.dbClose();
						}catch(Exception e3) {}
					}
					model.removeRow(row);//테이블의 한 줄 삭제
				}
				else {//아니오를 누른 경우
					
				}
			}
		});
		
		table.setPreferredScrollableViewportSize(new Dimension(650,600));//(table)표의 사이즈 설정
		JScrollPane scroll = new JScrollPane(table);//table을 scoll을 사용할 수 있게 설정
		
		db.getSelectAll(dt);// db의 모든 데이터를 dt에 올리기
		
		frame.getContentPane().add(scroll, "Center");
		panel.add(scroll);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(buttonP);
		frame.setTitle("==장부==");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setBounds(100, 100, 700, 600);
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
