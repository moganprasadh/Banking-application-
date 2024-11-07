import java.sql.*;
import java.util.Scanner;


public class Main {
		public static void main(String[] args) throws SQLException {
			String url="jdbc:mysql://localhost:3306/sample";
			String username="root";
			String password="root";
			
			Connection con = DriverManager.getConnection(url,username,password);
			if(con!=null) {
				System.out.println("Connection Established");
			}
			else {
				System.out.println("Connection is Not Established");
			}
			
			Scanner sc =new Scanner(System.in);
//			Insert values to DB
			System.out.println("Enter User Detail");
			String name = sc.nextLine();
			String pass = sc.nextLine();
			int id = sc.nextInt();
			
			String sql = "Insert into users(username,password,user_id)"+"values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ps.setInt(3, id);
			
			int res = ps.executeUpdate();
			
			if(res>0) {
				System.out.println("A new user created");
			}
			
			//read values at Db
			String query="select * from users";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				System.out.println("Username: "+rs.getString("username"));
				System.out.println("Password: "+rs.getString("password"));
				System.out.println("User Id: "+rs.getString("user_id"));
			}
			
			sc.close();
		}
}
