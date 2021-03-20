
//데이터 접근, DB처리 클래스
import java.sql.Connection;//mysql연결을 위한 Connection객체 생성
import java.sql.DriverManager;//JDBC 드라이버를 통하여 Connection을 만드는 역할, Class.forName()메서드를 통해서 생성
import java.sql.PreparedStatement;//statement를 상속받는 인터페이스로 SQL구문을 실행시키는 기능을 갖는 객체
import java.sql.ResultSet;//질의 결과가 있다면, ResultSet객체를 생성하여 결과 저장
import java.sql.SQLException;
import java.sql.Statement;//Statement객체를 생성하여 질의 수행
import javax.swing.table.DefaultTableModel;
//DefaultTableModel을 사용하지 않고 테이블을 만들게되면 한 번만든 테이블의 데이터는 변경을 할 수 없다.
//JTable table = new JTable(b,a); 이렇게 하면 테이블에 데이터를 직접 연결한 상태가 된다.
//배열은 추가나 삭제를 근본적으로 하지 못한다.

//execute -> 테이블 생성, 수정, 삭제 등 데이터베이스 관리 명령어 사용 (create...)
//excuteUpdate -> 레코드 삽입, 수정, 삭제 등 테이블 조작 명령어 사용 (insert, update, delete...)
//excuteQuery -> 레코드 조회, 테이블 조회 등 조회 명령어 사용 (select...)

//"TRUNCATE table accountbook" - 테이블의 전체 내용 삭제

public class getDbConnection {
	private String driver = "com.mysql.cj.jdbc.Driver";//(외부 구성 파일을 사용하여 데이터베이스에 연결할 때) 사용할 드라이버 클래스 이름 및 드라이버 매개 변수를 제공 할 수 있다.
	private String url = "jdbc:mysql://localhost:3306/accountbook?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"; // 연결문자열, localhost - 127.0.0.1 
	private String user = "root"; // 데이터베이스 ID 
	private String pw = "Yuminc03"; // 데이터베이스 PW 
	
	Connection con = null;//네트워크상의 연결 자체를 의미
	Statement st = null;//Connection으로 길을 만들었으면 DB쪽으로 SQL문을 보내고 결과값을 받는 객체(데이터를 전송하는 객체)
	PreparedStatement ps = null;//statement와 비슷하지만 다르다. 바로 connection 객체로 생성할 때 sql을 보낸다. 그리고 값들을 세팅한다. (따옴표 실수 안할 수 있다)
	ResultSet rs = null;//명령에 대한 반환값을 반환해줌 (execteQuery)

	protected int LeftMoney;
	protected String money, leftmoney;
	
	public getDbConnection() {
		DbConnection();
	}
	public void DbConnection() {//로드 연결을 위한 생성자
		try { 
			//JDBC 드라이버 로딩
			Class.forName(driver);//DriverManager를 연결하려는 JDBC 드라이버를 지정
			//mysql에서 제공하는 Driver 클래스를 JVM method area에 로딩하여
			//여러 Driver 객체를 다루는 DriverManager의 static메서드를 사용할 수 있게 된다.
			//Connection 생성 
			con = DriverManager.getConnection(url, user, pw);//실제 자바프로그램과 데이터베이스를 네트워크상에서 연결을 해주는 메소드
			//연결에 성곡ㅇ하면 DB와 연결상태를 connection 객체로 표현하여 반환
			//DriverManager 클래스의 static 메서드인 getConnection() 메서드를 호출해서, 
			//mysql에 연결하기 위한 커넥션 정보(url, user, password)를 입력한다
			//getConnection() 메서드 수행 결과로 Connection 객체를 반환하는데, 이 객체를 통해 쿼리를 날리는 statement를 작성할 수 있다
			//데이터베이스 연결 
			System.out.println("Database에 연결 성공!!"); 
			} catch (SQLException e) { 
				System.out.println("[SQL Error : " + e.getMessage() +"]"); 
			} catch (ClassNotFoundException e1) { 
				System.out.println("[JDBC Connector Driver Error : " + e1.getMessage() + "]"); 
			}
		//return con; 
	}
	public void dbClose() {//db닫기 기능 메서드
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(ps != null) ps.close();
		}catch(Exception e) {
			System.out.println(e + "dbClose fail");
		}
	}
	public boolean insert(AccountTest acc) throws Exception{//mysql에 데이터를 전송하는 메서드
		boolean flag = false;
		ps = null;//데이터를 전송하는 객체
		
		String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, acc.getDate());
			ps.setString(2, acc.getEconomic());
			ps.setString(3, acc.getPrice());
			ps.setString(4, acc.getContents());
			ps.setString(5, acc.getLeftmoney());
			ps.executeUpdate();
			
			flag = true;
			//데이터베이스 연결 

			}catch(Exception e) {
				System.out.println(e);
				flag = false;
			}
			finally {
					try {
						dbClose();
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
			}//end of finally
		return flag;
	}
	
	public void getSelectAll(DefaultTableModel model) {//book(table)의 모든 레코드 조회
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM book");
			
			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				Object data[] = {rs.getString(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5)};
				model.addRow(data); //DefaultTableModel에 레코드 추가
				}
		
			}catch(SQLException e) {
				System.out.println(e + "getSelectAll fail");
			}finally {
				dbClose();
			}
	}
	
	public void getLastData(AccountTest acc) {//마지막 데이터 가져옴
		try {
			String sql = "SELECT * FROM book ORDER BY leftmoney LIMIT 1";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			if(!rs.isBeforeFirst()){//rs의 결과가 없을 때 (데이터가 없을 때), 커서가 첫 번째 레코드 앞에 있지 않거나 ResultSet에 행이없는 경우 false를 리턴

				Money.LeftMoney = (Money.sumG - Money.outG);
				LeftMoney = Money.LeftMoney;
				Money.leftmoney = Integer.toString(LeftMoney);
				acc.setLeftmoney(Money.leftmoney);
			}
			else if(rs.next()){
				money = rs.getString(5);
				System.out.println("남아 있던 돈 = " + money);
					
				Money.LeftMoney = (Money.sumG - Money.outG);//사용자가 입력한 돈
				//System.out.println(Money.LeftMoney);
				
				LeftMoney = Integer.parseInt(money);
				//System.out.println(LeftMoney);
				
				LeftMoney += Money.LeftMoney;//money의 LeftMoney(int)에 누적합한다. 
				//System.out.println("모두 계산해서 = " + Money.LeftMoney);
				System.out.println("모두 계산해서 = " + LeftMoney);
				//Money.leftmoney = Integer.toString(Money.LeftMoney);
				Money.leftmoney = Integer.toString(LeftMoney);
				acc.setLeftmoney(Money.leftmoney);
				System.out.println("남은 돈 = " + Money.leftmoney);
			}
			dbClose();
		}catch(Exception e) {
			System.out.println("Bot an exception ! ");
			System.out.println(e.getMessage());
		}
	}
	
}
