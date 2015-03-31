package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

public class BAMAgentClassLoader extends URLClassLoader{	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public void extractCode(){
		
	}
	
	public void integrateCode(){
		
	}
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}
	@Override
	protected void addURL(URL url) {
		// TODO Auto-generated method stub
		super.addURL(url);
	}

	public BAMAgentClassLoader(URL[] urls, ClassLoader classLoader) {
		super(urls, classLoader);
		System.out.println("lulu");
		// TODO Auto-generated constructor stub
	}

}
