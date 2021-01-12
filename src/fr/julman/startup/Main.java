package fr.julman.startup;

public abstract class Main {
	public static final void main(String[] args) throws java.lang.Exception {
		java.lang.System.out.println("[startup/INFO] JulMan Startup - v1.0 - For \"Plateform\"");
		java.lang.System.out.println("[startup/INFO] Using \"org.json.simple\"");
		java.lang.System.out.println("[startup/INFO] Using \"fr.julman.startup\"");
		java.lang.System.out.println("[startup/INFO] Jar version : " + fr.julman.platform.Launcher.VERSION);
		java.lang.System.out.println("[startup/INFO] ");
		java.lang.System.out.println("[startup/INFO] Starting jar...");

		// Start the JAR
		java.awt.EventQueue.invokeLater(() -> {
			fr.julman.platform.Launcher.main(args);
		});
	}
}
