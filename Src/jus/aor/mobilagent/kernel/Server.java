/**
 * J<i>ava</i> U<i>tilities</i> for S<i>tudents</i>
 */
package jus.aor.mobilagent.kernel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.InstanceNotFoundException;

import com.sun.org.apache.xml.internal.serialize.Serializer;

/**
 * Le serveur principal permettant le lancement d'un serveur d'agents mobiles et les fonctions permettant de déployer des services et des agents.
 * @author     Morat
 */
public final class Server {
	/** le nom logique du serveur */
	protected String name;
	/** le port où sera ataché le service du bus à agents mobiles. Pafr défaut on prendra le port 10140 */
	protected int port=10140;
	/** le server d'agent démarré sur ce noeud */
	protected AgentServer agentServer;
	/** le nom du logger */
	protected String loggerName;
	/** le logger de ce serveur */
	protected Logger logger=null;
	/**
	 * Démarre un serveur de type mobilagent 
	 * @param port le port d'écuote du serveur d'agent 
	 * @param name le nom du serveur
	 */
	public Server(final int port, final String name){
		this.name=name;
		try {
			this.port=port;
			/* mise en place du logger pour tracer l'application */
			loggerName = "jus/aor/mobilagent/"+InetAddress.getLocalHost().getHostName()+"/"+this.name;
			logger=Logger.getLogger(loggerName);
			/* démarrage du server d'agents mobiles attaché à cette machine */
			//A COMPLETER
			logger.log(Level.INFO, "démarrage du server d'agents mobiles attaché à cette machine");
			agentServer = new AgentServer(port,this.name);
			//----
			/* temporisation de mise en place du server d'agents */
			Thread.sleep(1000);
		}catch(Exception ex){
			System.out.println("server l56");
			System.out.println(ex);
			logger.log(Level.FINE," erreur durant le lancement du serveur"+this,ex);
			return;
		}
	}
	/**
	 * Ajoute le service caractérisé par les arguments
	 * @param name nom du service
	 * @param classeName classe du service
	 * @param codeBase codebase du service
	 * @param args arguments de construction du service
	 */
	public final void addService(String name, String classeName, String codeBase, Object... args) {
		try {
			System.out.println("Ajout de service");
			agentServer.addService(name, classeName, codeBase, args);
		}catch(Exception ex){
			System.out.println("server l75");
			System.out.println(ex);
			logger.log(Level.FINE," erreur durant le lancement du serveur"+this,ex);
			return;
		}
	}
	/**
	 * deploie l'agent caractérisé par les arguments sur le serveur
	 * @param classeName classe du service
	 * @param args arguments de construction de l'agent
	 * @param codeBase codebase du service
	 * @param etapeAddress la liste des adresse des étapes
	 * @param etapeAction la liste des actions des étapes
	 */
	public final void deployAgent(String classeName, Object[] args, String codeBase, List<String> etapeAddress, List<String> etapeAction) {
		try {
			System.out.println("Deploiment d'un agent");
			String path = "file:///"+System.getProperty("user.dir")+codeBase;
			System.out.println("path : " + path);
			BAMAgentClassLoader loader = new BAMAgentClassLoader(new URL[]{new URL(path)},this.getClass().getClassLoader());
			System.out.println("lulu");
			Class<?> c = Class.forName(classeName,true,(ClassLoader)loader);
			/*
			Method m =c.getMethod(c.getName(), Class.forName("Object[]"));
			System.out.println(m.toString()+" here \n");
			Agent a1=(Agent) m.invoke((Object) null,(Object) null);*/
			//Object[] obj = (Object[]) new Object() ;
//				Agent a1 = (Agent) c.newInstance();
			System.out.println("Création d'un agent");
			Agent a1 = (Agent) c.getConstructor(Object[].class).newInstance(new Object[]{args});
			System.out.println("Agent crée");
			a1.init(agentServer, name);//added
			Iterator<String> iterAd = etapeAddress.iterator();
			Iterator<String> iterAc = etapeAction.iterator();
			System.out.println("on l'a initialisé ");
			while(iterAd.hasNext()){
				String s = iterAc.next();
				String s1 = iterAd.next();
				URI e1 = new URI(s1);
				String e2 =c.getField(s).getName();
				_Action ac = (_Action) c.getField(s).get(a1);
				a1.addEtape(new Etape(e1, ac));
				new Thread(a1).start();
			}
		}
		catch(Exception i){
			System.out.println("server l123");
			System.out.println(i);
			logger.log(Level.FINE," erreur durant le lancement de l'agent",i);
			return;
		}
	}
}
