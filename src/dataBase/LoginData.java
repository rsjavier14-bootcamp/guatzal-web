package dataBase;

import java.sql.*;

import util.Conector;

public class LoginData {
	//inspirado en el video 52
	
	/**
	 * Realiza un insert en la tabla de Usuario
	 * @param co
	 * @param nick
	 * @param password
	 * @param salt
	 */
	public void insert(Conector co, String nick, String password, String salt) {
		
		try(Connection con = co.getConector()){
			
			if(nick!=null && password!=null && salt !=null) {
				try(PreparedStatement pstmt = con.prepareStatement("INSERT INTO Usuario(Nombre_user,Contraseņa_user,Salt_user) values (?,?,?)", new String[] {"Id_user"})){
					pstmt.setString(1, nick);
					pstmt.setString(2, password);
					pstmt.setString(3, salt);
					pstmt.executeUpdate();
					co.commit();
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		
	}
	
	/**
	 * Devuelve un array con La contraseņa y la Salt del usuario en cuestion
	 * @param co
	 * @param nick
	 * @return String[]
	 */
	public String[] selectUser(Conector co, String nick) {
		String[] password= {null,null};
		try(Connection con = co.getConector()){
			if(nick!=null) {
				try(PreparedStatement pstmt = con.prepareStatement("Select Contraseņa_user, Salt_user from Usuario where Nombre_user = ?")){
					pstmt.setString(1, nick);
					pstmt.executeQuery();
					try (ResultSet rs = pstmt.executeQuery()) {
						if (rs.next()) {
							password[0] = rs.getString(1);
							password[1] = rs.getString(2);
						}
					}
				}
			}
			
		}catch (SQLException e) {
			throw new Error("Error al iniciar el servidor");
		}
		return password;
	}
	
}
