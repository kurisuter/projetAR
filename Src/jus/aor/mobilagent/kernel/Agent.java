package jus.aor.mobilagent.kernel;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;


public class Agent implements _Agent {
	Route route ;//added
	AgentServer agentServer;
	String servername;
	BAMAgentClassLoader loader;
	@Override
	public void run() {
		// TODO Auto-generated method stub

		if(route.hasNext()){
			Etape prochaineetape= route.next();
			prochaineetape.action.execute();
			try{
				System.out.println("route ="+route.toString());
				System.out.println("port que l'agent utilise = "+prochaineetape.server.getPort());
				Socket soc = new Socket(prochaineetape.server.getHost(),prochaineetape.server.getPort());
				OutputStream is = soc.getOutputStream();
				ObjectOutputStream dis = new ObjectOutputStream(is);
				dis.writeObject(this);
				is.close();
				dis.close();
				soc.close();
			}catch (Exception e){
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		// TODO Auto-generated method stub
		this.agentServer=agentServer;
		this.servername=serverName;
		String uri = new String("mobileagent://S0:").concat(agentServer.site()).concat("/");
		try {
			route = new Route(new Etape(new URI(uri), new _Action(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void execute() {
					// TODO Auto-generated method stub
					System.out.println("action vide");
				}
			}));
			System.out.println("route = "+route.toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@Override
	public void init(BAMAgentClassLoader loader, AgentServer server,
			String serverName) {
		// TODO Auto-generated method stub
				this.agentServer=server;
				String uri = new String("mobileagent://...:").concat(agentServer.site()).concat("/");
				this.servername=serverName;
				try {
					route = new Route(new Etape(new URI(uri), new _Action(){
						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void execute() {
							// TODO Auto-generated method stub
							System.out.println("action vide");
						}
					}));
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					System.out.println(e);
					e.printStackTrace();
				}
				this.loader = loader;
	}

	@Override
	public void addEtape(Etape etape) {
		// TODO Auto-generated method stub
		route.add(etape);//added
	}


	protected _Action retour() {
		// TODO Auto-generated method stub
		return null;
	}

}
