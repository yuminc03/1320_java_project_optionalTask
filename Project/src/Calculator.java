
import java.awt.BorderLayout;
//컨테이너를 North, South, East, West, Center 모두 5개의 영역으로 나누고, 
//각 영역에 하나의 컴포넌트만을 배치할 수 있도록 한다. 
//그래서 한 영역에 여러 개의 컴포넌트를 배치하면, 마지막에 추가한 컴포넌트만 보이게 된다.
import java.awt.Color;
//Color는 색을 표현하기 위해 사용되는 클래스로써, 
//원하는 색의 RGB값만 알고 있으면 그 색을 표현할 수 있는 객체를 생성하여 사용할 수 있다. 
import java.awt.Font;//java에서 입력한 글자에 효과를 줄 때 활용한다
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
//원하는 행과 열로 구성된 레이아웃을 
//작성할 때 사용한다. 계산기의 숫자판과 같은 레이아웃을 구성할 때 쉽게 구성이 가능하다
import java.awt.event.ActionEvent;//AWTEvent를 확장
import java.awt.event.ActionListener;//액션 이벤트를 받기위한 리스너 인터페이스
import java.awt.event.InputEvent;
//ComponentEvent를 확장, 모든 컴포넌트 레벨 입력 이벤트의 루트 이벤트 클래스
import javax.swing.JButton;
//JButton 클래스는 플랫폼 독립적인 구현이있는 라벨이 지정된 단추를 작성하는 데 사용
import javax.swing.JFrame;
//프레임을 생성하기 위해 필요함
import javax.swing.JLabel;
//java Swing 클래스, JLabel은 짧은 문자열 또는 이미지 아이콘을 표시하는 데 사용
import javax.swing.JMenu;
//내가 가진 JFrame이 JMenuBar그것과 JMenus을 프레임을
//실행할 때에 포커스를 설정하고 싶기 JMenu때문에 space버튼을 누르면 MenuItems가 표시
import javax.swing.JMenuBar;
//하나 이상의 JMenu 객체를 포함하며, JMenu 객체가 선택되면 하나 이상의 JMenuItems를 표시
import javax.swing.JMenuItem;
//사용자가 "버튼"을 선택하면 메뉴 항목과 관련된 작업이 수행
import javax.swing.JPanel;
//구성 요소 그룹을 저장할 수있는 컨테이너, JPanel의 주요 임무는 컴포넌트를 구성하는 것
import javax.swing.KeyStroke;
//키보드 또는 동등한 입력 장치에서의 키 동작을 나타냅니다

@SuppressWarnings("serial") // Warnning 무시
public class Calculator extends JFrame implements ActionListener {
	// JFrame을 상속하고, ActionListener도 상속 받는다
	// Listener 인터페이스 - 하나 또는 2개 이상의 이벤트 처리를 위한 메소드를 소유한 인터페이스
	// event = GUI 컴포넌트에서 발생되는 모든 행위
	// extends는 클래스를 확장하는 것, implements는 인터페이스를 구현하는 것
	// java에서는 다중상속을 지원하지 않아서 인터페이스를 제공함
	JPanel jpButton, jpResult;
	// 패널 초기화 panel = 프레임 안에서 또 다른 프레임을 넣을 수 있게 하는 것
	// JPanel = 액자
	JMenuBar jmb; // 메뉴바 초기화, JMenuBar = (Menu를 올릴 틀)
	// menuBar : 윈도우의 메뉴를 제공하는 역할로 생략이 가능한 선택항목
	// - JMenu, JmenuItem 등을 이용해서 메뉴를 구성하여
	// setJMenuBar()메소드를 이용해서 등록 할 수 있다.
	JMenu jmEdit, jmHelp; // 메뉴 초기화, JMenu = 눌렀을 때 서브가 존재하는 경우
	JMenuItem mCopy, mPaste, mHelp, mInfo; // 메뉴 아이템 초기화
	// JMenuItem = 바로 실행으로 연결되는 경우
	JLabel jlbResult1, jlbResult2; // 라벨 초기화 label = 글자를 표시하는 역할을 함
	JButton[] jbButton = null; // 버튼배열 초기화
	String result = "";// String타입 result선언
	String result1 = "";// String타입 result1선언
	// 예) 3 + 5 = 8
	// result1 = 3 + , result = 3
	String result2 = "";// String타입 result2선언,
	String number[] = { " ", " ", " " };// 배열 number에 " "을 3개 저장
	String[] numStr = { "←", "CE", "C", "/", "7", "8", "9", "*", "4", "5", "6", "-", "1", "2", "3", "+", "0", "00", ".",
			"=" }; // 버튼에 들어갈 값

	public Calculator() {// 메서드 Calculator
		super("==계산기=="); // 타이틀 바 (프레임 상단 이름)
		getContentPane().setLayout(new BorderLayout()); // 전체 레이아웃을 BorderLayout
		setLocationRelativeTo(null);// 가운데 정렬
		// 기본적으로 컴포넌트들이 틀 형태로 존재, BorderLayout에 컴포넌트를 추가할 때는 .add(컴포넌트, 위치)
		// 위치지정을 안하면 기본적으로 center에 위치함
		// getContentPane() = 프레임의 contentPane 오브젝트를 돌려줌.
		// contentPane : 일반적인 컴포넌트들을 가질 수 있는 패널
		// - 프레임 객체의 getContentPane()메소드를 이용해서 얻을 수 있다.

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("pig.png");
		setIconImage(img);

		/** 메뉴바 시작 */
		// 로 시작하는 주석은 /*일반적인 코드 주석입니다. 이들은 일반적으로 논리를 설명하기 위해 코드 라인 위에 사용
		// /**로 시작하는 주석은 javadoc에 사용됨. 이들은 메소드와 클래스 위에 사용
		// javadoc = Java 소스에 문서화를 하는 방법
		// 으로 클래스나 메소드에 주석으로 기술한 내용을 javadoc 명령어나
		// 또는 이를 이용한 빌드 툴(maven 의 site phrase등)을 사용하여 문서화할 수 있다.
		// Java 프로그래밍 언어의 경우 두 언어 간에 차이가 없음, 그들은 공백으로 처리됨

		jmb = new JMenuBar();// jmb = 메뉴바

		// jmEdit = new JMenu("편집(E)");
		// jmHelp = new JMenu("도움말(H)");

		// mCopy = new JMenuItem("복사");
		// mPaste = new JMenuItem("붙여넣기");
		// mHelp = new JMenuItem("도움말 보기");
		// mInfo = new JMenuItem("계산기 정보");

		// jmEdit.add(mCopy); // jmEdit라는 메뉴에 mCopy라는 메뉴아이템 추가
		// jmEdit.add(mPaste); // jmEdit라는 메뉴에 mPaste라는 메뉴아이템 추가
		// jmHelp.add(mHelp); // jmHelp라는 메뉴에 mHelp라는 메뉴아이템 추가
		// jmHelp.add(mInfo); // jmHelp라는 메뉴에 mInfo라는 메뉴아이템 추가

		// jmb.add(jmEdit); // jmb라는 메뉴바에 jmEdit라는 메뉴 추가
		// jmb.add(jmHelp); // jmb라는 메뉴바에 jmHelp라는 메뉴 추가

		// setJMenuBar(jmb); // jmb메뉴바 추가
		/** 메뉴바 끝 */

		/** 레이블 시작 */
		jpResult = new JPanel(new GridLayout(2, 1));
		// jpResult라는 패널에 GrideLayout을 적용
		// GrideLayout = 테이블 형태의 레이아웃
		// 인수를 주지 않으면 행은 1행으로 고정되고 열이 계속 추가된다.
		// 예를 들어, (2,0)이면 행은 2행으로 고정하고 열은 무한대로 늘어날 수 있다
		jpResult.setBackground(Color.WHITE); // jpResult라는 패널에 흰색 배경적용
		jlbResult1 = new JLabel("", JLabel.RIGHT); // jlbResult1라는 값 없는 레이블, 우측정렬
		jlbResult2 = new JLabel("0", JLabel.RIGHT); // jlbResult2라는 기본값 0인 레이블, 우측정렬
		jlbResult2.setFont(new Font("굴림", Font.BOLD, 20)); // jlbResult2 레이블에 폰트는 굴림, 진하게, 크게20 적용

		jpResult.add(jlbResult1); // jpResult 패널에 jlbResult1 레이블 추가
		jpResult.add(jlbResult2); // jpResult 패널에 jlbResult2 레이블 추가
		/** 레이블 끝 */

		/** 버튼 시작 */
		jpButton = new JPanel(new GridLayout(5, 4, 5, 5)); // jpButton라는 패널,
		// GridLayout적용, 5 x 4, 간격은 가로세로 5씩, 5행 4열(계산기 버튼)
		jpButton.setBackground(Color.WHITE); // jpButton 패널 배경은 흰색
		jbButton = new JButton[numStr.length]; // jbButton 버튼 배열 초기화
		// numStr배열 크기만큼 초기화

		// jbButton에 표기할 값들 적용
		for (int i = 0; i < numStr.length; i++) {// i는 0부터 numStr.length까지 더한다
			jbButton[i] = new JButton(numStr[i]);
			// 버튼 배열 jbButton[i]을 numStr의 i번째 문자열에 대입
			jbButton[i].setFont(new Font("굴림", Font.BOLD, 20));
			// jbButton[i]에 폰트 설정(굴림, 진하게, 크기:20)
			jpButton.add(jbButton[i]);
			// JPanel jpButton에 jbButton[i]를 추가한다
			jbButton[i].addActionListener(this);
			// jbButton[i]에 객체 자신의 ActionListener를 추가
		} // end of for

		/** 버튼 색 시작 */
		for (int j = 0; j < numStr.length; j++) {
			if (j < 3) {// j가 3보다 작을 때
				jbButton[j].setForeground(Color.RED);// 1,2,3번째 버튼 = 빨강색
			} else if (j == 3 || j == 7 || j == 11 || j == 15 || j == 19) {
				jbButton[j].setForeground(Color.BLUE);// 4,8,12,16,20번째 버튼 = 파랑색
			}
			jbButton[j].setBackground(Color.LIGHT_GRAY);// 버튼 jbButton의 배경색깔
		} // end of for
		jbButton[0].setForeground(Color.RED);// 전경색을 빨강으로 함
		// setForeground = 전경색 설정
		/** 버튼 색 끝 */

		/** 버튼 단축키 시작 */
		// jmEdit.setMnemonic('E');
		// setMnemonic=단축키를 설정
		// jmHelp.setMnemonic('H');

		// mCopy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		// 단축키 설정함수 사용, 단축키를 설정한다. //CTRL + C
		// mPaste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		// CTRL + V
		// mHelp.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_MASK));
		// CTRL + H
		// mInfo.setAccelerator(KeyStroke.getKeyStroke('I', InputEvent.CTRL_MASK));
		// CTRL + I
		/** 버튼 단축키 끝 */

		// mCopy.addActionListener(this);
		// addActionListener=콤보박스에서 아이템을 선택했을 때 발생하는 이벤트를 받기위해 지정된 리스너 추가
		// mPaste.addActionListener(this);
		// mHelp.addActionListener(this);
		// mInfo.addActionListener(this);
		// /** 버튼 끝 */

		getContentPane().add("North", jpResult);
		// panel jpResult를 위쪽에 부착
		// contentPane = 일반적인 컴포넌트들을 가질 수 있는 패널
		// 기본적으로 BorderLayout 배치관리자를 갖고 있음
		// - 프레임 객체의 getContentPane()메소드를 이용해서 얻을 수 있다.
		// getContentPane() = 프레임의 contentPane 오브젝트를 돌려줍니다.
		// (계산기의 숫자가 나타나는 부분으로 예상)
		getContentPane().add("Center", jpButton);
		// panel jpButton을 가운데 부착
		setSize(350, 450);// 크기 300 * 400
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);// 프레임 크기 변경 불가
		setVisible(true);// 프레임을 보이게 함
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임을 닫으면 프로그램 종료
	}// end of Calculator()메서드

	/** 버튼 이벤트 시작 */
	// 버튼을 누르면 시작됨
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String txtButton = e.getActionCommand();
		// getActionCommand = action 명령을 나타내는 String을 제공합니다.

		// *여기서 txtButton은 계산기 버튼*
		if (txtButton.equals("1") || txtButton.equals("2") || txtButton.equals("3") || txtButton.equals("4")
				|| txtButton.equals("5") || txtButton.equals("6") || txtButton.equals("7") || txtButton.equals("8")
				|| txtButton.equals("9")) {// txtButton이 1이거나 2이거나 ... 9이면
			if (result2.equals("0")) {// result2가 0이면, result2에 아무것도 대입 안함
				result2 = "";
			} // end of inner if
			result2 += txtButton;// result2에 txtButton누적합
			jlbResult2.setText(result2);// jlbResult2에 표시되는 텍스트를 result2로 한다
			// setText = 텍스트박스 내에 문자열을 미리설정, text로 라벨 설정
			// 표시되는 텍스트를 지정된 텍스트로 설정
			// setText(String t)
		} else if (txtButton.equals("←")) {// txtButton이 ←이면
			int len = jlbResult2.getText().length();
			// getText() = text를 리턴함
			// len에 jlbResult2의 길이만큼 리턴하고 대입
			if (len == 1) {// len이 1과 같으면(문자열 길이)
				result2 = "";// result2에 대입 안함
				jlbResult2.setText("0");// jlbResult2에 표시되는 텍스트를 0으로 함
			} else {
				if (!"".equals(result2)) {// result2가 공백이 아니면
					result2 = result2.substring(0, len - 1);
					// substring = 문자열에서 원하는 부분을 추출함
					// substring(시작위치,끝위치)
					// result의 0번째부터 len-1까지 자르고 그 사이 문자열을 추출
					jlbResult2.setText(result2);
					// jlbResult2에 표시되는 텍스트를 result2로 한다
				} // end of if
			} // end of else
		} else if (txtButton.equals("CE")) {// txtButton이 CE면
			result2 = "";// result2를 공백으로
			result = "";// result를 공백으로
			jlbResult2.setText("0");// jlbResult2에 0이 표시된다
			number[0] = "0";// number[0]에 0대입
		} else if (txtButton.equals("C")) {// txtButton이 C이면
			result = "";// result를 공백으로
			result1 = "";// result1을 공백으로
			result2 = "";// result2를 공백으로
			jlbResult1.setText("");// jlbResult1에 공백표시
			jlbResult2.setText("0");// jlbResult2에 0표시
			number[0] = " ";// number[0]에 공백대입
			number[1] = " ";// number[1]에 공백대입
			number[2] = " ";// number[2]에 공백대입
		} else if (txtButton.equals("0") || txtButton.equals("00")) {
			// txtButton이 0이거나 00이면
			if (!("".equals(result2))) {// result2가 공백이 아닐경우
				if (!"0".equals(result2)) {// result2가 0이 아닐경우
					result2 += txtButton;// result2에 txtButton를 누적합
					jlbResult2.setText(result2);
					// jlbResult2에 표시되는 텍스트를 result2로 한다
				} // end of inner if
			} else { // end of outer if
				result2 = "0";
			}
		} else if (txtButton.equals(".")) {// txtButton이 .이면
			if ("".equals(result2)) {// result2가 공백이면
				result2 = "0" + txtButton;
				// result2에 0과 txtButton을 연결하여 대입
			} else {
				if (result2.indexOf(".") == -1) {
					// indexOf = 해당 문자가있는 위치를 알려줍니다.(없을경우 -1반환)
					// result2의 . 위치를 알려줌, -1과 같을경우(.이 없을경우)
					result2 += txtButton;// result2에 txtButton을 누적합
				}
			} // end of else
			jlbResult2.setText(result2);
			// jlbResult2에 표시되는 텍스트를 result2로 한다, 계산 화면에 찍는다
		} else if (txtButton.equals("=")) {// txtButton이 =일 때 //end of else if(.)
			if (!"".equals(result2)) {
				number[2] = result2;// number[2]에 result2를 대입
			}
			if ("".equals(result1)) {// result1에 아무것도 없으면
				if (!number[1].equals(" ")) {
					result = Cal();// result에 계산메소드 Cal()대입
					if ("0으로 나눌 수 없습니다.".equals(result)) {
						// result가 0으로 나눌 수 없습니다.이면
						result = "";
						result1 = "";
						result2 = "";
						number[0] = " ";
						number[1] = " ";
						number[2] = " ";
					} else {
						number[0] = result;// number[0]에 result를 대입
						jlbResult2.setText(result);
						// jlbResult2에 표시되는 텍스트를 result로 한다
					}
				} // end of outer if
				jlbResult1.setText(result1);
				// jlbResult1에 표시되는 텍스트를 result1로 한다
			} else {
				if (" ".equals(number[2])) {// number[2]가 " "이면
					number[2] = result2;// number[2]에 result2를 대입
				}
				result = number[0];// result에 number[0]를 대입

				if ("0으로 나눌 수 없습니다.".equals(result)) {
					jlbResult2.setText(result);
					// jlbResult2에 표시되는 텍스트를 result로 한다
					result = "";
					result1 = "";
					result2 = "";
					number[0] = " ";
					number[1] = " ";
					number[2] = " ";
				} else {
					result = Cal();

					if (!"0으로 나눌 수 없습니다.".equals(result)) {
						result1 = "";
						number[0] = result;
						jlbResult2.setText(result);
						jlbResult1.setText(result1);
						result2 = "";
					} else {
						jlbResult2.setText("0으로 나눌 수 없습니다.");
						jlbResult1.setText("");
						result = "";
						result1 = "";
						result2 = "";
						number[0] = " ";
						number[1] = " ";
						number[2] = " ";
					}
					System.out.println("?");
				} // end of inner else
			} // end of outer else
		} else if (txtButton.equals("/") || txtButton.equals("*") || txtButton.equals("-") || txtButton.equals("+")) {
			// txtButton이 /이거나 *이거나 -이거나 +일 때
			if ("".equals(result1)) {// result1이 공백일 때
				if ("".equals(result2)) {// result2가 공백일 때
					if ("".equals(result)) {// result가 공백일 때
						result1 = "0" + txtButton;
						// result1에 0과 txtButton을 연결하여 대입
						number[0] = "0";
						// number[0]에 0을 대입
					} else {
						result1 = result + txtButton;
						// result1에 result와 txtButton을 연결하여 대입
					}
				} else {
					if (!"".equals(result)) {// result가 공백이 아니면
						result1 = result + txtButton;
						// result1에 result와 txtButtton을 연결하여 대입
					}
					result1 = result2 + txtButton;
					number[0] = result2;
				}
				number[1] = txtButton;
			} else { // end of outer if
				if ("".equals(result2)) {
					result1 = result1.substring(0, result1.length() - 1) + txtButton;
					// result1에 result1을 0번째 부터 result1의 길이-1까지 잘라서 추출, txtButton을 연결하여 대입
					number[1] = txtButton;
				} else {
					result1 += result2 + txtButton;
					number[2] = result2;
					result = Cal();
					number[1] = txtButton;
					jlbResult2.setText(result);
					number[0] = result;
				}
			} // end of outer else
			if (number[1].equals("/") && (number[2].equals(" 0") || number[2].equals("0"))) {
				// number[1]이 "/"이고 number[2]가 " 0"거나, number[2]가 0일 때
			} else {
				result2 = "";
				jlbResult1.setText(result1);
			}

		} // end of else if
	}// end of actionPerformed

	/** 버튼 이벤트 끝 */

	/** 계산 메소드 시작 */
	public String Cal() {
		String num = "";
		String testNum1 = "";
		String testNum2 = "";

		if (number[0].indexOf(".") != -1) {// == -1 => 소수점이 없으면
			// indexOf = 해당 문자가있는 위치를 알려줍니다.(없을경우 -1반환)
			// 입력한 수에 소수점이 있으면
			int index1 = number[0].indexOf(".");
			// index1에 number[0]의 .위치를 대입한다
			testNum1 = number[0].substring(index1, number[0].length());
			// substring = 문자열에서 원하는 부분을 추출함
			// substring(시작위치,끝위치)
			// testNum1에 number[0]의 index1(number[0]의 .위치)부터 number[0]길이 까지 추출하여 대입
			// testNum1 = 소수점 뒷부분을 가지고 있음
			if (testNum1.replaceAll("0", "").equals(".")) {
				// replaceAll = 특정 문자열을 원하는 문자열로 치환하는 메소드
				// 왼쪽 인자값이 찾을문자열 , 오른쪽이 변환될 문자열
				// testNum1의 0을 공백으로 바꾸고 .이 있는 지 확인(0을 지워줌)
				number[0] = number[0].substring(0, index1);
				// number[0]에 number[0]의 0부터 index1의 길이 까지 추출하여 대입
			}
		} // end of outer if

		if (number[2].indexOf(".") != -1) {
			int index2 = number[2].indexOf(".");
			testNum2 = number[2].substring(index2, number[2].length());

			if (testNum2.replaceAll("0", "").equals(".")) {
				// replaceAll메소드는 정규식과 대체 문자열과 일치하는 모든 문자 시퀀스를 대체 문자열을 반환한다.
				// replaceAll(문자열 정규식, 문자열 대체)
				number[2] = number[2].substring(0, index2);
			}
		}
		if (number[1].equals("*")) {// number[1]이 *와 같으면
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				// number[0]이나 number[2]에 소수점이 있으면(number[0]이나 number[2]가 소수이면 double타입으로 변환)
				num = (Double.parseDouble(number[0]) * Double.parseDouble(number[2])) + "";
				// parseDouble = 숫자로 된 문자열을 숫자로 변환함. (double 타입으로 변환)
			} else {
				if ("".equals(number[2])) {
					num = result2;
				} else {
					if (!"".equals(result)) {
						number[0] = result;
					}
					num = (Long.parseLong(number[0]) * Long.parseLong(number[2])) + "";
					// Long.parseLong(String) = 문자열을 long값으로 변환한다.
					// num에 long값으로 변환한 number[0]과 long값으로 변환한 number[2]를 곱하여, ""을 붙이고 대입
					// number[0]이나 number[2]가 정수이면 long값으로 변환
					result = num;
					number[0] = result;
				} // end of inner else
			} // end of outer else
		} else if (number[1].equals("+")) {
			// number[1]과 +가 같으면
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) + Double.parseDouble(number[2])) + "";
			} else {
				if ("".equals(number[2])) {
					num = result2;
				} else {
					if (!"".equals(result)) {
						number[0] = result;
					}
					num = (Long.parseLong(number[0]) + Long.parseLong(number[2])) + "";
					result = num;
					number[0] = result;
				}
			}
		} else if (number[1].equals("-")) {
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) - Double.parseDouble(number[2])) + "";
			} else {
				if ("".equals(number[2])) {
					num = result2;
				} else {
					if (!"".equals(result)) {
						number[0] = result;
					}
					num = (Long.parseLong(number[0]) - Long.parseLong(number[2])) + "";
					result = num;
					number[0] = result;
				}
			}
		} else if (number[1].equals("/")) {
			number[2].trim();
			// trim() = 공백을 제거해 준다
			// number[2]의 공백을 제거해 준다 (양쪽 공백만) 예) " 공 백 " -> "공 백"
			if (number[0].indexOf(".") != -1 || number[2].indexOf(".") != -1) {
				num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";
			} else {
				if (number[2].equals("0")) {
					num = "0으로 나눌 수 없습니다.";
					result2 = "";
				} else {
					if ("".equals(number[2])) {
						num = result2;
					} else {
						if (!"".equals(result)) {
							number[0] = result;
						}
						num = (Double.parseDouble(number[0]) / Double.parseDouble(number[2])) + "";

						if (num.indexOf(".") != -1) {
							// num에 소수점이 있으면
							int index3 = num.indexOf(".");
							// index3에 num에서 .의 위치를 대입
							String testNum3 = num.substring(index3, num.length());
							// testNum3의 index3에서 num의 길이까지 추출하여 대입한다
							if (testNum3.replaceAll("0", "").equals(".")) {
								num = num.substring(0, index3);
								// num의 0번째 자리에서 index3번재 자리까지 추출하여 num에 대입한다
							}
						}
						result = num;
						number[0] = result;
					} // end of inner else
				} // end of second outer else
			} // end of outer else
		} // end of outer else if
		return num;
	}// end of cal()

	/** 계산 메소드 끝 */

	public static void main(String[] args) {
		new Calculator();
	}
}// end of Calculator class

