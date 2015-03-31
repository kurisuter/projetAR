package jus.aor.mobilagent.kernel;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class AgentServer extends Thread{
	protected List<Service<?>> services;
	private int port;
	private String servername;
	public void run(){
		try{
			Socket soc=null;
			ServerSocket serverTCPSoc = new ServerSocket(port);
			while(true) {
				
					//---------------------------------------------------------------------- A COMPLETER
				if(soc==null){break;}
				soc = serverTCPSoc.accept();
				InputStream is = soc.getInputStream();
				ObjectInputStream dis = new ObjectInputStream(is);
				
				BAMAgentClassLoader loader = new BAMAgentClassLoader(null, this.getClass().getClassLoader());
				Agent a = (Agent)dis.readObject();
				a.init(loader, this, servername);
				startAgent(a);
				System.out.println(a.toString());
				is.close();
				dis.close();
			}
			serverTCPSoc.close();
		}catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	public AgentServer(){
		
	}
	public AgentServer(int port,String name) {
		// TODO Auto-generated constructor stub
		port = port;
		this.servername =name;
	}
	/**
	 * Ajoute le service caractérisé par les arguments
	 * @param name nom du service
	 * @param classeName classe du service
	 * @param codeBase codebase du service
	 * @param args arguments de construction du service
	 */
	public void addService(String name, String classeName, String codeBase, Object[] args){
		
	}
	public void getService(){
		
	}
	public String site(){
		return Integer.toString(port);
	}
	public String toString(){
		return null;
	}
	public void startAgent(Agent agent){
		new Thread(agent).start();
	}
}
