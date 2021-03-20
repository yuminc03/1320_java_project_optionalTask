
import java.awt.Color;
import java.awt.FlowLayout;//FlowLayout을 불러옴.
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
//구성 요소 정의 조치가 발생했음을 나타내는 의미 론적 이벤트. 
import java.awt.event.ActionListener;
//버튼 또는 메뉴 항목을 클릭 할 때마다 Java ActionListener에 알린다. ActionEvent에 대해 통지
import javax.swing.JButton;
//JButton 클래스는 플랫폼 독립적인 구현이있는 레이블이 지정된 단추를 작성하는 데 사용됨
import javax.swing.JFrame;
//java.awt.Frame 클래스를 상속하는 컨테이너 유형. JFrame은 GUI를 만들기 위해 레이블, 버튼, 텍스트 필드와 같은 구성 요소가 추가되는 기본 창처럼 작동함
import javax.swing.JLabel;
//JLabel은 java Swing 클래스이다. JLabel은 짧은 문자열 또는 이미지 아이콘을 표시하는 데 사용함.
import javax.swing.JPanel;
//Java Swing 패키지의 일부인 JPanel은 구성 요소 그룹(라벨, 버튼 등.)을 저장할 수있는 컨테이너이다.
import javax.swing.JTextField;
//JTextField 클래스의 객체는 한 줄 텍스트를 편집 할 수있는 텍스트 구성 요소이다.

class MyGoal {
	// 목표

	protected static String Lmoney;
	public MyGoal() {
		
		JFrame frame1 = new JFrame();// 목표내용이 나타날 프레임 생성
		frame1.setLayout(new FlowLayout());
		// FlowLayout = 컴포넌트들이 왼쪽에서 오른쪽으로 추가됨
		// 기본적으로 가운데 정렬
//		JPanel goalP, buttonP;//금액 입력 패널, 버튼삽입패널
//		JTextField goalT;//금액 입력 텍스트필드
//		JLabel goalL;//금액 입력 라벨
//		JButton goalB;//목표 메시지를 띄울 버튼

		JPanel moneyP = new JPanel();// 현재 가지고 있는 돈을 알려주는 JLabel을 부착할 JPanel
		JPanel goalP = new JPanel();// 목표금액의 내용을 부착할 JPanel
		JPanel buttonP = new JPanel();// 버튼을 부착할 JPanel

		moneyP.setBackground(new Color(255, 204, 0));// moneyP의 색깔 지정
		goalP.setBackground(new Color(255, 204, 0));// goalP의 색깔 지정
		buttonP.setBackground(new Color(255, 204, 0));// buttonP의 색깔 지정
		Toolkit toolkit = Toolkit.getDefaultToolkit();// 아이콘 넣기
		Image img = toolkit.getImage("pig.png");
		frame1.setIconImage(img);

		JLabel moneyL = new JLabel("현재 가지고 있는 돈은 ");// JLabel moneyL에 "현재 가지고 있는 돈은" 멘트를 넣는다.
		
		Lmoney = Money.leftmoney;// 현재 가지고 있는 돈을 장부에서 불러와서 int타입으로 바꾼 뒤, LMoney에 대입
		JTextField moneyT = new JTextField();// JTextField moneyT를 생성함(현재 가지고 있는 금액을 넣을 TextField).
		moneyT.setText(Lmoney);// moneyT의 텍스트를 LMoney로 지정함
		JLabel moneyL2 = new JLabel("원 입니다.");// moneyL2에 "원 입니다." 멘트를 넣는다.

		JLabel goalL = new JLabel("모으고 싶은 금액을 입력해주세요 : ");// "모으고 싶은 금액을 입력해주세요 : "멘트를 goalL에 넣는다, JLabel생성
		JTextField goalT = new JTextField(12);// 크기 12의 텍스트필드 생성(모으고 싶은 금액을 입력할 창)
		JButton goalB = new JButton("확인");// 버튼 이름이 확인인 goalB 생성
		moneyP.add(moneyL);// moneyP에 moneyL을 삽입 (moneyP = 현재 가지고 있는 돈의 내용을 나타냄)
		moneyP.add(moneyT);// moneyP에 moneyT을 삽입
		moneyP.add(moneyL2);// moneyP에 moneyL2을 삽입
		goalP.add(goalL);// goalP에 goalL을 삽입 (goalP = 목표금액 내용을 나타냄)
		goalP.add(goalT);// goalP에 goalT를 삽입
		buttonP.add(goalB);// buttonP에 goalB를 삽입

		frame1.add(moneyP);// frame1에 moneyP를 삽입(부착)
		frame1.add(goalP);// frame1에 goalP를 삽입(부착)
		frame1.add(buttonP);// frame1에 buttonP삽입

		goalB.addActionListener(new ActionListener() {// goalB의 기능을 추가함
			@Override
			public void actionPerformed(ActionEvent arg0) {// 버튼을 클릭하면 실행함

				JFrame moneyf = new JFrame();// JFrame moneyf을 생성(모으고 싶은 금액 내용의 프레임)
				JPanel moneyp = new JPanel();// JPanel moneyp을 생성(목표 금액 내용의 JPanel)

				int moneyI = Integer.parseInt(Lmoney);// Lmoney(현재 가지고 있는 돈)을 int타입으로 바꾸고 moneyI에 저장함
				int getmoney = Integer.parseInt(goalT.getText());// goalT의 텍스트(모으고 싶은 금액)을
				// 가져와서 int타입으로 바꾼 뒤, getmoney에 저장. getmoney = 모으고 싶은 금액
				// gText를 int타입 숫자형태로 바꾼다
				int gather = getmoney - moneyI;// getmoney(모으고 싶은 금액)에서 moneyI(현재 가지고 있는 돈)
				// 을 빼서, gether에 저장. gather = 모아야 할 금액

				if (getmoney <= moneyI) {// 모아야 할 금액이 가지고 있는 돈보다 작거나 같다면

					JLabel result2 = new JLabel("목표를 더 높게 잡아보세요!");
					// JLabel result2생성, "목표를 더 높게 잡아보세요!" 멘트를 대입
					moneyp.add(result2);// result2를 moneyp에 삽입
					moneyf.add(moneyp);// moneyp를 moneyf에 삽입
					result2.setFont(new Font("굴림", Font.PLAIN, 30));// label의 폰트 지정( 글꼴, 크기)
					moneyp.setBackground(new Color(255, 204, 0));
					moneyf.setLocationRelativeTo(null);// 가운데 정렬
					moneyf.setSize(400, 100);// moneyf의 크기 :400 X 200
					moneyf.setVisible(true);// moneyf를 보이게 함
					moneyf.setResizable(false);// moneyf의 크기 변경 불가
					// moneyf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//X버튼을 누르면 프로그램 종료함
				} else {// 모아야 할 금액이 한 달용돈 보다 크면
					JLabel moneyl = new JLabel("앞으로 ");// JLabel moneyl에 "앞으로 "멘트를 대입
					String result = Integer.toString(gather);// gather(모아야 할 돈)을 String타입으로 바꾸고 result에 넣음
					JTextField result2 = new JTextField();// JTextField result2을 생성함.
					result2.setText(result);// result2의 텍스트는 Result로 한다
					result2.setEditable(false);// setText는 작동하지만, 사용자는 텍스트필드에 입력할 수 없다
					result2.setFont(new Font("굴림", Font.PLAIN, 30));// label의 폰트 지정( 글꼴, 크기)

					JLabel money2 = new JLabel("원 남았습니다! 화이팅!");// JLabel money2에 "원 남았습니다! 화이팅!"멘트를 대입.
					moneyp.add(moneyl);// moneyp(목표 금액 내용의 JPanel)에 moneyl를 삽입
					moneyp.add(result2);// moneyp(목표 금액 내용의 JPanel)에 result2를 삽입
					moneyp.add(money2);// moneyp(목표 금액 내용의 JPanel)에 money2를 삽입
					moneyf.add(moneyp);// moneyf(모으고 싶은 금액 내용의 프레임)에 moneyP를 삽입

					moneyf.setTitle("목표금액");// moneyf의 제목을 "목표금액"으로 함
					moneyf.setSize(400, 200);// moneyf 크기를 250 X 200으로 지정
					moneyf.setVisible(true);// moneyf를 보이게 지정
					moneyf.setResizable(false);// moneyf의 크기를 조절할 수 없게 지정.
					moneyf.setLocationRelativeTo(null);// 가운데 정렬
					moneyl.setFont(new Font("굴림", Font.PLAIN, 20));// 폰트 지정( 글꼴, 크기)
					money2.setFont(new Font("굴림", Font.PLAIN, 20));// 폰트 지정( 글꼴, 크기)
					moneyf.setLocationRelativeTo(null);// 가운데 정렬
					moneyp.setBackground(new Color(255, 204, 0));
					// moneyf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} // end of else

			}// end of actionPerformed

		});// end of ActionListener
		frame1.setTitle("목표");// frame1의 제목 : 목표
		frame1.setSize(385, 200);// frame1의 크기 : 385 X 500
		frame1.setVisible(true);// frame1을 보이게 함
		frame1.setResizable(false);// frame1의 크기를 조절할 수 없음.
		frame1.setLocationRelativeTo(null);// 가운데 정렬
		frame1.getContentPane().setBackground(new Color(255, 204, 0));// frame1의 배경색 지정함
		// frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// end of MyGoal

}// end of class

public class MyGoalTest {

	public static void main(String[] args) {
		new MyGoal();
	}
}

