package servicios;

import org.mindrot.jbcrypt.BCrypt;

import dataBase.LoginData;
import ultil.Conector;

public class LoginService {

	private final LoginData _login = new LoginData();
	
	public void insertar(Conector co,String nombre, String contraseņa) {
		try {
			
			String salt = BCrypt.gensalt();
		
			String saltedPassword = BCrypt.hashpw(contraseņa,salt);
			_login.insert(co, nombre, saltedPassword, salt);
		
		}finally{
			
		};
	
		
	}
	
	public boolean verify(Conector co, String nombre, String contraseņa) {
		String[] pass = _login.selectUser(co, nombre);
		
		if(pass[0].equals(BCrypt.hashpw(contraseņa,pass[1]))) {
			return true;
		}
		
		return false;
	}
	
}
