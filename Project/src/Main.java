
//메인 화면

import javax.swing.*;
//가능한 한 모든 플랫폼에서 동일하게 작동하는 "경량"(모든 Java 언어) 구성 요소 세트를 제공
import java.awt.*;
//자바 시스템에서 실행된다고 할 경우에는 자바 컴포넌트를 사용하기 때문에 여러 컴포넌트에서 AWT를 사용한다고 할 경우 
//실행하는 컴포넌트의 모습이 서로 달라 일관된 화면을 제공하는 데에 어려움이 따른다. 자바 초기 버전에 제공되던 GUI이다.
import java.awt.event.ActionEvent;
//구성 요소 정의 조치가 발생했음을 나타내는 의미 론적 이벤트. 
import java.awt.event.ActionListener;

//버튼 또는 메뉴 항목을 클릭 할 때마다 Java ActionListener에 알린다. ActionEvent에 대해 통지
public class Main {

	private JFrame frame;// JFrame frame정의

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			// 스윙 컴포넌트를 제어하기 위해 java.awt.EventQueue.invokeLater를 사용
			// 이벤트 디스패칭 쓰레드에 의해 Runnalbe.run()을 실행한다.
			// 이 메소드를 호출하면 이벤트 큐에 runnable.run()의 내용을 넣고 바로 리턴한다.
			// Swing을 이용해 여려 스레드에서 동시에 GUI작업을 하면 에러가 발생하기 때문에 사용함.

			public void run() {
				try {// try - catch문 : 예외 처리
					Main window = new Main();// Main_1을 호출
					window.frame.setVisible(true);// 프레임을 보이게 함
				} catch (Exception e) {// () => 에러 발생 부분
					e.printStackTrace();// 에러 메세지 대신 띄움
					// printStackTrace는 리턴값이 없다. 이 메소드를 호출하면 메소드가 내부적으로 예외 결과를 화면에 출력한다.
					// printStackTrace는 가장 자세한 예외 정보를 제공
				}
			}// end of run()
		});// end of EventQueue
	}// end of main

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();// initialize 호출
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();// frame 생성
		frame.setTitle("시작화면");// frame제목 : 시작화면
		frame.setBounds(100, 100, 775, 501);
		// 컴포넌트들을 배치 관리자 없이 배치해야되는 경우, 해당 컴포넌트의 절대 위치와 크기 지정
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();//아이콘 넣기
	      Image img = toolkit.getImage("pig.png");
	      frame.setIconImage(img);

		frame.setLocationRelativeTo(null);// 가운데 정렬
		frame.setResizable(false);// 창 늘리기 불가능 하게 할 수 있는것
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// X버튼 누르면 프로그램이 닫힘
		frame.getContentPane().setLayout(null);// Contentpane에 나의 마음대로 컴포넌트를 배치하겠다.
		// getContentPane() 화면에 출력될 모든 컴포넌트들이 부착되는 공간
		// setLayout(null) 레이아웃 관리자없이 컨테이너를 만들 때 사용

		JLabel lblNewLabel = new JLabel("New label");// JLabel lblNewLabel에 "New label"멘트 넣기
		lblNewLabel.setBounds(0, -22, 776, 500);// lblNewLabel를 배치 할 위치
		lblNewLabel.setIcon(new ImageIcon("main.JPG"));// lblNewLabel에 이미지 삽입, 이미지 경로
		frame.getContentPane().add(lblNewLabel);// (lblNewLabel)이미지를 getContentPane()에 부착

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(527, 12, 211, 126);// btnNewButton를 배치 할 위치
		btnNewButton.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		btnNewButton.setContentAreaFilled(false);// btnNewButton의 내용영역 채우기 없음
		frame.getContentPane().add(btnNewButton);// (btnNewButton)이미지를 getContentPane()에 부착

		JButton button = new JButton("New button");// JButton button에 "New button"멘트 넣기
		button.setBounds(527, 157, 211, 126);// button을 배치 할 위치
		button.setBorderPainted(false);// 맨 앞 위에꺼 이름이랑 동일하게 만들기 안그러면 오류남 //투명화
		button.setContentAreaFilled(false);// button의 내용영역 채우기 없음
		frame.getContentPane().add(button);// 이미지를 getContentPane()에 부착

		btnNewButton.addActionListener(new ActionListener() {
			// 만들어진 버튼 "시작"에 버튼이 눌러지면 발생하는 행동을 정의
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("실행");
				new menu();// 다음으로 넘어가는 클래스 이름
			}
		});

		button.addActionListener(new ActionListener() {
			// 만들어진 버튼 "종료"에 버튼이 눌러지면 프로그램 종료하는 행동을 정의
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("종료");
				System.exit(0);// 종료
			}
		});
	}
}

