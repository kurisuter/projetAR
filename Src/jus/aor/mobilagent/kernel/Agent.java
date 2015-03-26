package jus.aor.mobilagent.kernel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;


public class Agent implements _Agent {
	Route route ;//added
	AgentServer agentServer;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Je tourne\n");

	}

	@Override
	public void init(AgentServer agentServer, String serverName) {
		// TODO Auto-generated method stub
		this.agentServer=agentServer;
		String uri = new String("mobileagent://...:").concat(agentServer.site()).concat("/");
		try {
			route = new Route(new Etape(new URI(uri), new _Action(){
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					System.out.println("action vide");
				}
			}));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(BAMAgentClassLoader loader, AgentServer server,
			String serverName) {
		// TODO Auto-generated method stub

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
