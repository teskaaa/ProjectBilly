import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


/*
 * Classe permettant de construire les requettes UDP de recherche de fichier
 * Il faut specifier notre numero de port, le type de recherche (via empreinte ou nom),
 * 		l'empreinte ou le nom de notre recherche
 */


public class RequestUDP {

	private DatagramPacket myPacket;
	private DatagramSocket mySocket;
	private int myPort;
	
	private String initiator;
	private int requestType;
	private String fileName;
	private int filenameLength;
	private String filePrint;
	
	
	
	/*
	 * Constructeur de la classe
	 * recupere le numero de port (le notre) en paramettre
	 */
	public RequestUDP(int port) throws SocketException {
		
		this.myPacket = new DatagramPacket(new byte[0], 0);
		this.mySocket = new DatagramSocket();
		
		this.myPort = port;
		this.requestType = -1; //-1 correspond a une erreur en attendant que le type soit spécifié
		this.filenameLength = -1;
	}
	
	/*
	 * Construit la partie initiateur de la de requette (IPV4/6, @IP, nPort)
	 */
	public void setInitiator(){
		InetAddress myIA;
		String myIp;
		int myIPClass = -1; //(4 si ipv4, 6 si ipv6)
		
		try {
			myIA = InetAddress.getLocalHost();
			
			if (myIA instanceof Inet6Address) {
			    myIPClass = 6;
			    initiator = "6" + myIA.getHostAddress()+Integer.toString(myPort);
			} else if (myIA instanceof Inet4Address) {
				myIPClass = 4;
				initiator = "4" + myIA.getHostAddress()+Integer.toString(myPort);
			}
			
			
		} catch (UnknownHostException e) {
			System.out.println("Erreur : Impossible de recuperer l'@IP de la machine");
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * setter du type de requette
	 * (0 = via empreinte, 1 = via nom de fichier)
	 */
	public void setRequestType(int type) {
		this.requestType = type;
	}
	
	/*
	 * setter de l'empreinte demandée, doit faire 16 octets
	 */
	public void setFilePrint(String print) {
		this.filePrint = print;
	}
	
	/*
	 * setter de l'empreinte demandée, doit faire 16 octets
	 */
	public void setFileName(String name) {
		this.fileName = name;
		this.filenameLength = name.length();
	}
	
	/*
	 * Methode a utiliser lorsque tout est parametre et verifier
	 */
	public boolean sendRequestUDP() {
		boolean success = false; //sert a verifier que l'envoie est reussis, doit passer a true quand c'est le cas
	
		//TODO
	
		return success;
	}
	
}
