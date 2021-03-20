//달력
import java.util.Calendar;
//특정 시점과 MONTH, YEAR, HOUR 등과 같은 일정 필드 세트간에 날짜를 변환하는 메소드를 제공하는 추상 클래스
import java.awt.*;
//Java에서 GUI 또는 창 기반 애플리케이션을 개발하기위한 API 
import java.awt.event.*;
//이벤트가 AWT와 스윙에 처리하기 위해 사용되는 클래스 및 인터페이스를 정의 
import javax.swing.*;

//가능한 한 모든 플랫폼에서 동일하게 작동하는 "경량"(모든 Java 언어) 구성 요소 세트를 제공
public class SwingCalender implements ActionListener {
	Calendar cal;
	int int_Year, int_Month;
	String year, month;
	StringBuffer sb_Print;

	JLabel lb_title, lb_YearSpace, lb_MonthSpace;
	JTextField textField_Year, textField_Month;
	JTextPane textPane;
	// JEditorPane 클래스의 서브 클래스이다. JTextPane은 이미지와 컴포넌트가 포함 된 스타일 문서에 사용
	// JEditorPane :다양한 종류의 컨텐츠를 편집하는 텍스트 구성 요소
	// 텍스트, 이미지처리, 폰트크기, 굵기 등도 처리가능 하다는 점에서 JTestArea와 비교됨.
	JPanel panel_North, panel_NorthFloor;
	JButton bt_Input;

	public SwingCalender() {
		cal = Calendar.getInstance();
		// Calendar getInstance() : 현재 날짜와 시간 정보를 가진 Calendar 객체를 생성한다.
		setCalFrame();// setCalFrame() 호출함
	}

	public void setCalendar() {
		sb_Print = new StringBuffer();
		// StringBuffer 자료형은 append 라는 메소드를 이용하여 계속해서 문자열을 추가해 나갈 수 있다.
		sb_Print.append("    일   월   화   수   목    금   토\n");

		cal.set(int_Year, int_Month - 1, 1);
		// 월은 0으로 설정해야 하므로 0이 1월이 됨.
		int cal_Field = cal.get(7);
		for (int j = 1; j < cal_Field; j++) {
			sb_Print.append("       ");
			// sb_Print에 공백을 더함
		}

		int cal_Gam = cal.getActualMaximum(5);
		// getActualMaximum() :날짜가 셋팅 된 Calender가 가질수 있는 값
		// 현재 객체의 특정 필드의 최대 값을 반환한다.
		// cal_Gam은 한 달의 '주'의 수

		int comp = 1;
		for (int k = cal_Field; comp <= cal_Gam; k++)
		// k = cal_Field, comp는 cal_Gam보다 작거나 같을 때까지 k를 더함
		{
			if (comp >= cal_Gam) {
				if (comp < 10) {
					sb_Print.append("    ");
					// sb_Print에 공백을 더함
				} else
					sb_Print.append("   ");
				// sb_Print에 공백을 더함

				sb_Print.append(comp);
				// sb_Print에 comp를 더함
				textPane.setText(sb_Print.toString());
				// textPane의 텍스트를 sb_Print의 문자열들을 리턴해 준 것으로 지정함.
			} else {
				if (comp < 10) {
					sb_Print.append("     ");
					// sb_Print에 공백을 더함
				} else
					sb_Print.append("   ");
				// sb_Print에 공백을 더함

				sb_Print.append(comp);
				// sb_Print에 comp를 더함
				textPane.setText(sb_Print.toString());
				// textPane의 텍스트를 sb_Print의 문자열들을 리턴해 준 것으로 지정함.
			}

			if (k % 7 == 0)
				sb_Print.append("\n");
			// k(일)이 7로 나누어 떨어지면 sb_Print에 줄바꿈을 더함.
			comp++;// comp를 더함
		}
	}

	public void setCalFrame() {
		lb_title = new JLabel("연도와 달을 입력하세요 -> ex) xxxx xx", SwingConstants.CENTER);
		// SwingConstants :화면상에서 컴퍼넌트의 배치 및 방향 지정을 실시하기 위해서(때문에) 사용
		// lb_title의 위치를 center로 지정함
		lb_YearSpace = new JLabel("년 ");
		// lb_YearSpace는 "년"이라는 라벨
		lb_MonthSpace = new JLabel("월 ");
		// lb_MonthSpace는 "월"이라는 라벨
		textField_Year = new JTextField(5);
		// textField_Year는 크기가 5인 JTextField이다
		textField_Month = new JTextField(3);
		// textField_Month는 크기가 5인 JTextField이다
		textPane = new JTextPane();
		// JTextPane: 이미지와 컴포넌트가 포함 된 스타일 문서에 사용
		// 텍스트, 이미지처리, 폰트크기, 폰트굵기 등을 처리가능(TestArea와 비교됨)
		panel_North = new JPanel(new GridLayout(2, 1));
		// panel_North : 2행 1열로 구성된 레이아웃.
		panel_NorthFloor = new JPanel();
		// panel_NorthFloor이라는 이름의 Panel생성
		bt_Input = new JButton("입력");
		// bt_Input 이라는 이름의 버튼 "입력" 생성

		try {// 예외가 발생할 수 있는 코드 부분
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			// 모든 플랫폼에서 동일하게 보이는 "Java L & F"("금속"이라고도 함), 코드에서 아무 것도 수행하지 않을 경우 사용되는 기본값
			// LookAndFeel : 프로그램 전체의 UI 모습을 변경할 수 있다.
			// 환경에 상관없이 동일하게 구현된 모습으로 인하여 시스템의 고유한 모습을 잃은 단점을 보완

			// UIManager :룩앤필 변경, 룩앤필 기본값, 다양한 기본값을 얻기위한 편리한 방법을 알려주는 현재 룩앤필, 사용 가능한 룩앤필 세트를
			// 관리
			// 예외처리가 필요함, 경로를 찾지 못해 ClassNotFoundException이 발생할 수 있기 때문
		} catch (Exception e) {
		} // Exception e : 처리할 예외 타입 e
			// try블록 안에서 예외가 발생했을 때 예외가 발생했을 때 예외를 처리하는 부분

		JFrame.setDefaultLookAndFeelDecorated(true);
		// 새롭게 작성된 JFrame이 현재의 Look & Feel에 의해 제공되는
		// 윈도우 수식(경계, 윈도우 클로스, 타이틀바 등)을 사용할 지 선택하는 함수,
		// true일 경우, swing형식으로 변경함.
		JFrame frame = new JFrame("==캘린더==");
		// JFrame생성, 프레임 이름 : Swing Calendar
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("pig.png");
		frame.setIconImage(img);

		frame.add(panel_North, "North");
		// 프레임을 panel_North의 North쪽에 배치
		panel_North.add(lb_title);
		// panel_North에 lb_title을 더한다
		panel_North.add(panel_NorthFloor);
		// panel_North에 panel_NorthFloor을 더한다
		panel_NorthFloor.add(textField_Year);
		// panel_NorthFloor에 textField_Year을 더한다
		panel_NorthFloor.add(lb_YearSpace);
		// panel_NorthFloor에 lb_YearSpace를 더한다
		panel_NorthFloor.add(textField_Month);
		// panel_NorthFloor에 textField_Month을 더한다
		panel_NorthFloor.add(lb_MonthSpace);
		// panel_NorthFloor에 lb_MonthSpace를 더한다
		panel_NorthFloor.add(bt_Input);
		// panel_NorthFloor에 bt_Input을 더한다

		frame.add(textPane, "Center");
		// frame에 textPane을 Center에 배치
		textPane.setEditable(false);
		// setEditable(): 콤보박스의 편집 가능 상태를 지정
		// 매개 변수가 false 인 경우 사용자는 필드에 입력 할 수 없다.
		textPane.setFont(new Font("font", Font.BOLD, 16));
		// 폰트의 이름, 스타일, 크기를 지정
		textPane.setForeground(Color.blue);
		// 폰트의 색깔을 파랑으로 지정

		bt_Input.addActionListener(this);
		// bt_Input에 이벤트 등록

		frame.setSize(260, 280);
		// frame크기 260 X 280
		frame.setLocation(300, 300);
		// 이 컴퍼넌트를 새로운 위치로 이동
		frame.setVisible(true);
		// frame를 보이게 함
		frame.setResizable(false);
		// frame의 크기 변경 여부 (불가)
		frame.setLocationRelativeTo(null);// 가운데 정렬
		// frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// getSource() : 이벤트를 발생시킨 객체의 위치값을 가져온다.
		if (obj == bt_Input) {
			year = textField_Year.getText();
			// textField_Year의 텍스트를 year에 대입
			month = textField_Month.getText();
			// textField_MONTH의 텍스트를 month에 대입
			try {
				int_Year = Integer.parseInt(year);
				// year을 int타입으로 변환하여 Year에 대입
				int_Month = Integer.parseInt(month);

				if (year.length() != 4 || month.length() != 2)
				// year의 크기가 4와 같지않거나 month의 크기가 2와 같지 않으면
				{
					textPane.setText("년도 및 월 입력이 잘못되었습니다.");
					// textPane의 텍스트를 ()안 텍스트로 지정함
					return;
				} else if ((int_Month == 0) || (12 < int_Month))
				// int_Month가 0과 같거나 int_Month가 12보다 크면
				{
					textPane.setText("년도 및 월 입력이 잘못되었습니다.");
					// textPane의 텍스트를 ()안 텍스트로 지정함
					return;
				} else
					setCalendar();
			} catch (NumberFormatException ne)
			// 괄호 안 에러메세지가 뜨는 것을 예외 처리
			{
				textPane.setText("입력된 년도와 월이 숫자형태가 아닙니다.");
				// textPane의 텍스트를 ()안 텍스트로 지정함
				return;
			} // end of catch
		} // end of outer if
	}// end of actionPerformed

	public static void main(String[] args) {
		new SwingCalender();
	}
}// end of class


