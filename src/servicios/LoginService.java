package servicios;

import org.mindrot.jbcrypt.BCrypt;

import dataBase.LoginData;
import util.Conector;

public class LoginService {

	private final LoginData _login = new LoginData();
	
	/**
	 * Encripta la contraseņa recibida y ejecuta el comando para agregar el nuevo usuario a la base de datos
	 * @param co
	 * @param nombre
	 * @param contraseņa
	 */
	public void insertar(Conector co,String nombre, String contraseņa) {
		try {
			
			String salt = BCrypt.gensalt();
		
			String saltedPassword = BCrypt.hashpw(contraseņa,salt);
			_login.insert(co, nombre, saltedPassword, salt);
		
		}finally{
			
		};
	
		
	}
	
	/**
	 * Verifica que el usuario y la contraseņa sean los correctos
	 * @param co
	 * @param nombre
	 * @param contraseņa
	 * @return
	 */
	public boolean verify(Conector co, String nombre, String contraseņa) {
		String[] pass = _login.selectUser(co, nombre);
		
		if(pass[0].equals(BCrypt.hashpw(contraseņa,pass[1]))) {
			return true;
		}
		
		return false;
	}
	
}
