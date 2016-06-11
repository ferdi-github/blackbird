package net.studio24.blackbird;

import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * The launcher for embedded webserver.
 * 
 * @author Frederik Fischer
 */
public class Launcher {

	/**
	 * Application entry method.
	 * 
	 * @param args
	 *            the launcher arguments
	 * @throws Exception
	 *             is thrown in case of any failure
	 */
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);

		org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList
		        .setServerDefault(server);
		classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
		        "org.eclipse.jetty.plus.webapp.EnvConfiguration",
		        "org.eclipse.jetty.plus.webapp.PlusConfiguration");
		classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
		        "org.eclipse.jetty.annotations.AnnotationConfiguration");

		ProtectionDomain domain = Launcher.class.getProtectionDomain();
		URL location = domain.getCodeSource().getLocation();

		// Create the WebApp
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar(location.toExternalForm());
		server.setHandler(webapp);

		// Start server
		server.start();
		server.join();
	}

}
