package connect;

import main.MainController;

/**
 * Gère le dialogue entre l'IHM et le connecteur au SGBD.
 */
public class ConnectionController 
{
	//Attributes
	/**
	 * IHM pour se connecter à un SGBD.
	 */
	private ConnectionView ihm;
	
	/**
	 * Objet pour se connecter à un SGBD.
	 */
	private ConnectionManager connector;
	
	
	//Constructeurs
	/**
	 * Constructeur commun.
	 */
	public ConnectionController ()
	{
		this.ihm = new ConnectionView(this);
		this.connector = new ConnectionManager();
	}
	
	
	//Methods
	/**
	 * Etablit une connexion vers le SGBD $url, pour l'utilisateur $user,
	 * avec son mot de passe $pswd.
	 * Retourne un objet qui décrit grossièrement la tentative de connexion.
	 * 
	 * @param driver : le type de drivers
	 * @param url : url du SGBD.
	 * @param user : nom d'utilisateur souhaitant se connecter.
	 * @param pswd : mot de passe de l'utilisateur.
	 * @param baseName : ne nom de la base de données 
	 * @param port : le port du serveur 
	 * @return ConnectionResponse
	 */
	public void connect(String driver, String url, String user, String pswd, String baseName, int port)
	{
		ConnectionResponse response;
		this.talk("Tentative de connexion...");
		response = this.connector.connect(driver, url, user, pswd, baseName, port);
		this.talk(response.toString());
		if (response.success()) {
			this.ihm.dispose();
			MainController mc = new MainController(this.connector);
		}
	}
	 
	
	/**
	 * Communique avec l'utilisateur en lui affichant $msg.
	 * 
	 * @param msg : un message à transmettre à l'utilisateur.
	 */
	public void talk(String msg)
	{
		this.ihm.talk(msg);
	}
}
