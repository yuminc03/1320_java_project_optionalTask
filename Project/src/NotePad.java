//메모장
import java.awt.Event;
//AWT 구성 요소에 의해 발생 된 다양한 유형의 이벤트를 처리하기위한 인터페이스 및 클래스를 제공
import java.awt.Image;
//이미지를 만들고 수정하기위한 클래스를 제공함.
import java.awt.Toolkit;
//이 클래스는 Abstract Window Toolkit의 모든 실제 구현의 추상 수퍼 클래스이다. 클래스의 서브 Toolkit클래스는 다양한 컴포넌트를 특정 고유 툴킷 구현에 바인딩하는 데 사용함.
import java.awt.event.*;
import java.io.*;
//java.io 패키지에 정의 된 모든 클래스를 파일로 가져온다. 이를 통해 Java 프로그램은 해당 클래스와 해당 메소드를 사용하여 일부 태스크를 수행 할 수 있다.
import javax.swing.*;
//가능한 한 모든 플랫폼에서 동일하게 작동하는 "경량"(모든 Java 언어) 구성 요소 세트를 제공

public class NotePad extends JFrame implements ActionListener {
	// 멤버 변수 정의
	JTextArea ta; // 편집할 수 있는 여러 줄 입력란
	JMenuBar menubar; // 메뉴바
	// menuBar : 윈도우의 메뉴를 제공하는 역할로 생략이 가능한 선택항목
	// JMenu, JmenuItem 등을 이용해서 메뉴를 구성하여 setJMenuBar()메소드를 이용해서 등록 할 수 있다.
	JMenu fileMenu, editMenu; // 파일 메뉴, 편집 메뉴
	JMenuItem newItem, openItem, saveItem, exitItem;// 메뉴아이템
	JMenuItem copyItem, cutItem, pasteItem, allItem, dateItem;

	// 생성자 정의
	public NotePad() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// X 클릭 => 종료
		setTitle("==메모장==");// 제목
		ta = new JTextArea();// ta 생성
		JScrollPane scroll = new JScrollPane(ta);// 스크롤에 ta 를 넣는다.
		add(scroll);// 스크롤을 프레임의 중앙에 붙인다.
		// getContentPane().add(scroll); // JDK 1.4일 경우의 코드
		menubar = new JMenuBar();// 메뉴바 생성
		setJMenuBar(menubar);// 메뉴바를 붙인다.
		fileMenu = new JMenu("파일(F)");

		Toolkit toolkit = Toolkit.getDefaultToolkit();// 아이콘 넣기
		Image img = toolkit.getImage("pig.png");
		setIconImage(img);

		fileMenu.setMnemonic('F');// Alt + F
		menubar.add(fileMenu);// 메뉴바에 파일 메뉴 붙이기
		newItem = new JMenuItem("새로 만들기");// newItem은 새로만들기 기능
		openItem = new JMenuItem("열기");// openItem은 열기 기능
		saveItem = new JMenuItem("저장");// saveItem은 저장 기능
		exitItem = new JMenuItem("끝내기");// exitItem은 끝내기 기능

		newItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));// Ctrl + N 단축키 설정함

		openItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));// Ctrl + O

		saveItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));// Ctrl + S

		fileMenu.add(newItem);// filMenu에 newItem을 부착(JMenu에 JMenuItem넣음)
		fileMenu.add(openItem);// filMenu에 openItem을 부착
		fileMenu.add(saveItem);// filMenu에 saveItem 부착
		fileMenu.add(exitItem);// filMenu에 exitItem을 부착
		newItem.addActionListener(this); // 감지기 붙이기, newItem을 ActionListener(버튼기능 추가)사용하기
		// 익명(=무명)클래스(Anonymous Class)
		// 따로 클래스를 생성하지 않고 하나의 클래스내에서 인터페이스를 구현해준 다음에
		// 그 인터페이스에 맞는 메소드를 오버라이딩 해주는 방법
		openItem.addActionListener(this);// openItem을 ActionListener(버튼기능 추가)사용하기
		saveItem.addActionListener(this);// saveItem을 ActionListener(버튼기능 추가)사용하기
		exitItem.addActionListener(this);// exitItem을 ActionListener(버튼기능 추가)사용하기
		editMenu = new JMenu("편집(E)");// 편집 메뉴 생성

		editMenu.setMnemonic('E');// 메뉴에 바로 가기 키를 추가함
		menubar.add(editMenu);// 메뉴바에 편집 메뉴 붙이기
		copyItem = new JMenuItem("복사");// copyItem은 복사 기능
		cutItem = new JMenuItem("잘라내기");// cutItem은 잘라내기 기능
		pasteItem = new JMenuItem("붙여넣기");// pasteItem은 붙여넣기 기능
		allItem = new JMenuItem("모두 선택");// allItem은 모두 선택 기능
		dateItem = new JMenuItem("날짜/시간");// dateItem은 날짜/시간 나타내기 기능

		copyItem.setAccelerator(KeyStroke.getKeyStroke('C', Event.CTRL_MASK));// Ctrl + C, 단축키 설정함
		pasteItem.setAccelerator(KeyStroke.getKeyStroke('V', Event.CTRL_MASK));// Ctrl + V
		allItem.setAccelerator(KeyStroke.getKeyStroke('A', Event.CTRL_MASK));// Ctrl + A
		dateItem.setAccelerator(KeyStroke.getKeyStroke("F5"));

		editMenu.add(copyItem);// editMenu에 copyItem을 부착
		editMenu.add(pasteItem);// editMenu에 pasteItem을 부착
		editMenu.add(allItem);// editMenu에 allItem을 부착
		editMenu.add(dateItem);// editMenu에 dateItem을 부착
		copyItem.addActionListener(this); // 감지기 붙이기

		pasteItem.addActionListener(this);// pasteItem을 ActionListener(버튼기능 추가)사용하기
		allItem.addActionListener(this);// allItem을 ActionListener(버튼기능 추가)사용하기
		dateItem.addActionListener(this);// dateItem을 ActionListener(버튼기능 추가)사용하기
		setBounds(400, 400, 400, 300);// 크기 지정(x 좌표, y 좌표, 가로, 세로)
		setVisible(true);// 보이기
	}// 생성자

	// 이벤트 처리
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// getSource() = 메소드를 사용해서 이벤트리스너를 등록한 무언가를 실행할 시
		// 그 이벤트가 실행되는 특정 컨테이너의 모든 속성을 가져올 수 있다.
		if (obj == exitItem) {
			// System.exit(0); // 프로그램 종료
		}
		if (obj == openItem) {
			openFile(); // 파일 열기 메소드 호출
		} // if
		if (obj == saveItem) {
			saveFile(); // 파일 저장 메소드 호출
		} // if
		if (obj == copyItem) { // 복사하기 선택시
			ta.copy();
			copyItem.setEnabled(false);
			cutItem.setEnabled(false);
			// setEnabled : 컨트롤을 활성화 또는 비활성화한다, 이 기능은 Open Agent를 사용하는 경우에만 지원함
		} // if
		if (obj == cutItem) {
			ta.cut();
			copyItem.setEnabled(false);
			cutItem.setEnabled(false);
		} // if

		if (obj == allItem) {
			ta.selectAll(); // 모두 선택
		} // if
		if (obj == dateItem) {
			java.util.Date date = new java.util.Date();
			ta.append(date.toLocaleString());// 간단한 날짜/시간 추가
		} // if
	}// actionPerformed

	private void openFile() { // 파일 열기
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(this);// 열기 대화상자
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			// JFileChooser :윈도우의 파일열기, 저장등 과같은 형태의 Dialog 를 사용할수 있다.
			// "열기"버튼이나 "저장"버튼을 클릭하게되면 APPROVE_OPTION이 반환

			System.out.println(file.getName() + "선택됨");
			// 이 부분은 I/O 공부 후 진행^^
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
				// BufferedReader / BufferedWriter는 문자 입력 스트림으로부터 문자를 읽어 들이거나
				// 문자 출력 스트림으로 문자를 내보낼 때 버퍼링을 함으로써 문자, 문자 배열, 문자열 라인
				// 등을 보다 효율적으로 처리할 수 있도록 해준다.

				String text = "";
				while ((text = br.readLine()) != null) {
					ta.append(text + "\r\n");// 줄바꿈( Win : \r\n, Unix : \n )
					// append : 문자열을 결합
				} // while
				br.close();
			} catch (Exception e) {// 에러가 생기는 경우
				e.printStackTrace();// 예외 메시지 자세히 출력
			} // catch
		} // if
	}// openFile

	private void saveFile() { // 파일 저장
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(this);// 저장 대화상자
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// JFileChooser :윈도우의 파일열기, 저장등 과같은 형태의 Dialog 를 사용할수 있다.
			File file = chooser.getSelectedFile();
			System.out.println(file.getName() + "선택됨");// file의 이름을 "선택됨"으로 지정
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(file));
				bw.write(ta.getText());// ta 내용 읽어서 파일에 저장
				bw.flush();// 즉시 저장
				// bw.close();// 닫기
			} catch (Exception e) {
				e.printStackTrace();// 예외 메시지 자세히 출력
			} // catch
		} // if
	}// saveFile

	public static void main(String[] args) {
		new NotePad();
	}// main
}// end
