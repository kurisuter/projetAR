package jus.aor.mobilagent.hello;

import java.net.URI;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jus.aor.mobilagent.kernel._Action;
import jus.aor.mobilagent.kernel.Agent;

/**
 * Classe de test élémentaire pour le bus à agents mobiles
 * @author  Morat
 */
public class Hello extends Agent{

	 /**
	  * construction d'un agent de type hello.
	  * @param args aucun argument n'est requis
	  */
	 public Hello(Object... args) {
		 super();
		 System.out.println("on construit");
		 
		 // ....
	 }
	 public Hello(){//a supprimer

		 super();
		 System.out.println("hey you !");
	 }
	 /**
	 * l'action à entreprendre sur les serveurs visités  
	 */
	public _Action doIt = new _Action(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			System.out.println("Hello ! I am here :) ");
		}
		// ...
	};
	/* (non-Javadoc)
	 * @see jus.aor.mobilagent.kernel.Agent#retour()
	 */
	@Override
	protected _Action retour(){
		return doIt;// faux ,à changer
		// return ...;
	}
	// ...
}
