package jus.aor.mobilagent.kernel;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class AgentServer extends Thread{
	protected List<Service<?>> services;
	private int port;
	private Agent agent = null;
	
	public void run(){
		try{
			Socket soc=null;
			ServerSocket serverTCPSoc = new ServerSocket(port);
			while(true) {
				
					//---------------------------------------------------------------------- A COMPLETER
				soc = serverTCPSoc.accept();
				
				if(soc==null){break;}
			}
			serverTCPSoc.close();
		}catch (Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	public AgentServer(){
		
	}
	public AgentServer(int port) {
		// TODO Auto-generated constructor stub
		port = port;
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
	public void startAgent(){
		agent.run();
	}
}
