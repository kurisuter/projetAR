package jus.aor.mobilagent.kernel;

import java.net.URL;
import java.net.URLClassLoader;

public class BAMServerClassLoader extends URLClassLoader{
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.findClass(name);
	}
	@Override
	protected Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return super.loadClass(name, resolve);
	}

	public BAMServerClassLoader(URL[] urls, ClassLoader classLoader) {
		// TODO Auto-generated constructor stub
		
		super(urls,classLoader);
	}
	
	@Override
	protected void addURL(URL url) {
		// TODO Auto-generated method stub
		super.addURL(url);
	}
	
	

}
