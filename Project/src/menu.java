
import javax.swing.*;
//가능한 한 모든 플랫폼에서 동일하게 작동하는 "경량"(모든 Java 언어) 구성 요소 세트를 제공
import java.awt.*;
//자바 시스템에서 실행된다고 할 경우에는 자바 컴포넌트를 사용하기 때문에 여러 컴포넌트에서 AWT를 사용한다고 할 경우 
//실행하는 컴포넌트의 모습이 서로 달라 일관된 화면을 제공하는 데에 어려움이 따른다. 자바 초기 버전에 제공되던 GUI이다.
import java.awt.event.ActionEvent;
//구성 요소 정의 조치가 발생했음을 나타내는 의미 론적 이벤트. 
import java.awt.event.ActionListener;
//버튼 또는 메뉴 항목을 클릭 할 때마다 Java ActionListener에 알린다. ActionEvent에 대해 통지

public class menu {

	private JFrame frame;// JFrame frame정의

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { menu window = new menu();
	 * window.frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); }
	 * } }); }
	 * 
	 * /** Create the application.
	 */
	public menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();// frame 생성
		frame.setBounds(100, 100, 816, 497);
		//// 컴포넌트들을 배치 관리자 없이 배치해야되는 경우, 해당 컴포넌트의 절대 위치와 크기 지정
		frame.setLocationRelativeTo(null);// 가운데 정렬
		frame.setResizable(false);// 창 늘리기 불가능 하게 할 수 있는것
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// X버튼 누르면 프로그램이 닫힘
		frame.getContentPane().setLayout(null);// Contentpane에 나의 마음대로 컴포넌트를 배치하겠다.
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();//아이콘 넣기
	    Image img = toolkit.getImage("pig.png");
	    frame.setIconImage(img);


		JLabel lblNewLabel = new JLabel("New label");// JLabel lblNewLabel에 "New label"멘트 넣기
		lblNewLabel.setBounds(0, 0, 805, 468);// lblNewLabel를 배치 할 위치
		lblNewLabel.setIcon(new ImageIcon("MENU.JPG"));// lblNewLabel에 이미지 삽입, 이미지 경로
		frame.getContentPane().add(lblNewLabel);// 이미지를 getContentPane()에 부착

		JButton money = new JButton("money");// JButton money에 "getmoney"멘트 넣기
		money.setBounds(640, 48, 144, 45);// money를 배치 할 위치
		money.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		money.setContentAreaFilled(false);// money의 내용영역 채우기 없음
		frame.getContentPane().add(money);// (money)이미지를 getContentPane()에 부착
		money.addActionListener(new ActionListener() {
			// 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Money(); // 클래스 newWindow를 새로 만들어낸다
			}
		});
		
		JButton goal = new JButton("goal");
		goal.setBounds(640, 103, 144, 45);// goal을 배치 할 위치
		goal.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		goal.setContentAreaFilled(false);// goal의 내용영역 채우기 없음
		frame.getContentPane().add(goal);// (goal)이미지를 getContentPane()에 부착
		goal.addActionListener(new ActionListener() {
			// 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MyGoal(); // 클래스 newWindow를 새로 만들어낸다
			}
		});

		JButton book = new JButton("book");
		book.setBounds(640, 163, 144, 45);// book을 배치 할 위치
		book.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		book.setContentAreaFilled(false);// book의 내용영역 채우기 없음
		frame.getContentPane().add(book);// (book)이미지를 getContentPane()에 부착
		book.addActionListener(new ActionListener() {
			// 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new AccountBookDriver();
			}
		});

		JButton calcu = new JButton("calcu");
		calcu.setBounds(640, 228, 144, 45);// calcu을 배치 할 위치
		calcu.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		calcu.setContentAreaFilled(false);// calcu의 내용영역 채우기 없음
		frame.getContentPane().add(calcu);// (calcu)이미지를 getContentPane()에 부착
		calcu.addActionListener(new ActionListener() {
			// 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Calculator(); // 클래스 newWindow를 새로 만들어낸다
			}
		});

		JButton calender = new JButton("calender");
		calender.setBounds(640, 293, 144, 45);// calender을 배치 할 위치
		calender.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		calender.setContentAreaFilled(false);// calender의 내용영역 채우기 없음
		frame.getContentPane().add(calender);// (calender)이미지를 getContentPane()에 부착
		calender.addActionListener(new ActionListener() {
			// 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SwingCalender(); // 클래스 newWindow를 새로 만들어낸다
			}
		});

		JButton Memo = new JButton("memo");
		Memo.setBounds(640, 359, 144, 45);// memo을 배치 할 위치
		Memo.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		Memo.setContentAreaFilled(false);// memo의 내용영역 채우기 없음
		frame.getContentPane().add(Memo);// (memo)이미지를 getContentPane()에 부착
		frame.setVisible(true);
		Memo.addActionListener(new ActionListener() {
			// 만들어진 버튼 "새 창 띄우기"에 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new NotePad(); // 클래스 newWindow를 새로 만들어낸다
			}
		});
	}

}
