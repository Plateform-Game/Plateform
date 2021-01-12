package fr.julman.platform.log;

import java.io.PrintStream;

public class Logger {

	private final PrintStream stream;

	public Logger(PrintStream stream) {
		this.stream = stream;
	}

	public void info(String service, Object toLog) {
		stream.println("[" + service + "/INFO] " + toLog);
	}

	public void warn(String service, Object toLog) {
		stream.println("[" + service + "/WARN] " + toLog);
	}

	public void error(String service, Object toLog) {
		stream.println("[" + service + "/ERROR] " + toLog);
	}

	public void fatal(String service, Object toLog) {
		stream.println("[" + service + "/FATAL] " + toLog);
	}
}
