package org.arquillian.example.common;

// import Java standard package(s) -- BEGIN

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.util.HashMap;

// import internal library package(s) -- BEGIN
// import internal library package(s) -- END

// import module package(s) -- BEGIN
// import module package(s) -- END

/**
 * Logging utility class.
 *
 */
public class Logger {

	Class<?> classObject;

	private Logger(Class<?> classObject) {
		this.classObject = classObject;
	}

	/**
	 * Get logger for the class.
	 *
	 * @param classObject The instance of input class (class where the log messages originate from).
	 * @return the logger instance
	 */
	public static Logger getLogger(Class<?> classObject) {
		Logger returnValue = new Logger(classObject);// this.classObject = classObject;
		return returnValue;
	}

	// public constant(s) -- BEGIN
	// public constant(s) -- END
	// public data member(s) -- BEGIN
	// public data member(s) -- END

	// protected constant(s) -- BEGIN
	// protected constant(s) -- END
	// protected method(s) -- BEGIN
	// protected method(s) -- END

	// public method(s) -- BEGIN

	// method for logging message
	/**
	 *	Log a message with FATAL level.
	 *
	 *	@param message the message to be logged.
	 */
	public void fatal(String message) {
		Log log = getLog();

		if (log.isFatalEnabled())
			log.fatal(getLogPattern(message));
	}

	/**
	 *	Log a message with FATAL level including the stack trace of second parameter.
	 *
	 *	@param message the message to be logged.
	 *	@param t the exception, including the stack trace, to be logged.
	 */
	public void fatal(String message, Throwable t) {
		Log log = getLog();

		if (log.isFatalEnabled())
			log.fatal(getLogPattern(message), t);
	}

	/**
	 *	Log a message with ERROR level.
	 *
	 *	@param message the message to be logged.
	 */
	public void error(String message) {
		Log log = getLog();

		if (log.isErrorEnabled())
			log.error(getLogPattern(message));
	}

	/**
	 *	Log a message with ERROR level including the stack trace of second parameter.
	 *
	 *	@param message the message to be logged.
	 *	@param t the exception, including the stack trace, to be logged.
	 */
	public void error(String message, Throwable t) {
		Log log = getLog();

		if (log.isErrorEnabled())
			log.error(getLogPattern(message), t);
	}

	/**
	 *	Log a message with WARN level.
	 *
	 *	@param message the message to be logged.
	 */
	public void warn(String message) {
		Log log = getLog();

		if (log.isWarnEnabled())
			log.warn(getLogPattern(message));
	}

	/**
	 *	Log a message with WARN level including the stack trace of second parameter.
	 *
	 *	@param message the message to be logged.
	 *	@param t the exception, including the stack trace, to be logged.
	 */
	public void warn(String message, Throwable t) {
		Log log = getLog();

		if (log.isWarnEnabled())
			log.warn(getLogPattern(message), t);
	}

	/**
	 *	Log a message with INFO level.
	 *
	 *	@param message the message to be logged.
	 */
	public void info(String message) {
		Log log = getLog();

		if (log.isInfoEnabled())
			log.info(getLogPattern(message));
	}

	/**
	 *	Log a message with INFO level including the stack trace of second parameter.
	 *
	 *	@param message the message to be logged.
	 *	@param t the exception, including the stack trace, to be logged.
	 */
	public void info(String message, Throwable t) {
		Log log = getLog();

		if (log.isInfoEnabled())
			log.info(getLogPattern(message), t);
	}

	/**
	 *	Log a message with DEBUG level.
	 *
	 *	@param message the message to be logged.
	 */
	public void debug(String message) {
		Log log = getLog();

		if (log.isDebugEnabled())
			log.debug(getLogPattern(message));
	}

	/**
	 *	Log a message with DEBUG level including the stack trace of second parameter.
	 *
	 *	@param message the message to be logged.
	 *	@param t the exception, including the stack trace, to be logged.
	 */
	public void debug(String message, Throwable t) {
		Log log = getLog();

		if (log.isDebugEnabled())
			log.debug(getLogPattern(message), t);
	}

	/**
	 *	Check whether message with FATAL level will be logged.
	 *
	 *	@return true if FATAL level message will be logged, false otherwise.
	 */
	public boolean isFatalEnabled() {
		return getLog().isFatalEnabled();
	}

	/**
	 *	Check whether message with ERROR level will be logged.
	 *
	 *	@return true if ERROR level message will be logged, false otherwise.
	 */
	public boolean isErrorEnabled() {
		return getLog().isErrorEnabled();
	}

	/**
	 *	Check whether message with WARN level will be logged.
	 *
	 *	@return true if WARN level message will be logged, false otherwise.
	 */
	public boolean isWarnEnabled() {
		return getLog().isWarnEnabled();
	}

	/**
	 *	Check whether message with INFO level will be logged.
	 *
	 *	@return true if INFO level message will be logged, false otherwise.
	 */
	public boolean isInfoEnabled() {
		return getLog().isInfoEnabled();
	}

	/**
	 *	Check whether message with DEBUG level will be logged.
	 *
	 *	@return true if DEBUG level message will be logged, false otherwise.
	 */
	public boolean isDebugEnabled() {
		return getLog().isDebugEnabled();
	}

	// method for logging error that would be captured by network manager
	/**
	 *	Log a message with FATAL level to the centralized log repository.
	 *
	 *	@param message the message to be logged.
	 */
	public void logNetworkFatal(String message) {
		try {
			Log log = getNetworkLog();

			if (log.isFatalEnabled())
				log.fatal(getLogPattern(message));
		} catch (Exception e) {
			error("Failed to log fatal message to network manager.", e);
		}
	}

	/**
	 *	Log a message with ERROR level to the centralized log repository.
	 *
	 *	@param message the message to be logged.
	 */
	public void logNetworkError(String message) {
		try {
			Log log = getNetworkLog();

			if (log.isErrorEnabled())
				log.error(getLogPattern(message));
		} catch (Exception e) {
			error("Failed to log error message to network manager.", e);
		}
	}

	/**
	 *	Check whether message with FATAL level will be logged to the
	 *	centralized log repository.
	 *
	 *	@return true if FATAL level message will be logged, false otherwise.
	 */
	public boolean isNetworkLogFatalEnabled() {
		try {
			return getNetworkLog().isFatalEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 *	Check whether message with ERROR level will be logged to the
	 *	centralized log repository.
	 *
	 *	@return true if ERROR level message will be logged, false otherwise.
	 */
	public boolean isNetworkLogErrorEnabled() {
		try {
			return getNetworkLog().isErrorEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	// public methods -- END

	// private methods -- BEGIN
	private String getLogPattern(String message) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.classObject.getSimpleName());
		sb.append("] at [");
		sb.append(m_IPAddress);
		sb.append("] ");
		sb.append(message);
		return  sb.toString();
	}

	private Log getLog() {
		//		String name = getClass().getName();
		String name = this.classObject.getName();
		try {
			Log log = m_logs.get(name);
			if (log == null) {
				synchronized (LOG_MUTEX) {
					log = m_logs.get(name);
					if (log == null) {
						log = LogFactory.getLog(this.classObject);
						//						log = LogFactory.getLog(getClass());
						m_logs.put(name, log);
					}
				}
			}

			return log;
		} catch (Exception e) {
			System.err.println("Error !! Failed to initial logger for class ["
					+ name + "]");
			e.printStackTrace(System.err);
			return null;
		}
	}

	private Log getNetworkLog() throws Exception {
		try {
			//			String name = getClass().getName();
			String name = this.classObject.getName();

			Log log = m_networkLogs.get(name);
			if (log == null) {
				synchronized (NETWORK_LOG_MUTEX) {
					log = m_networkLogs.get(name);
					if (log == null) {
						log = LogFactory.getLog("network." + name);
						m_networkLogs.put(name, log);
					}
				}
			}

			return log;
		} catch (Exception e) {
			error(
					"Failed to initial the logger for logging message monitored by network manager.",
					e);
			throw e;
		}
	}

	// private methods -- END

	// private data members -- BEGIN
	private static String m_IPAddress;

	private static HashMap<String, Log> m_logs = new HashMap<String, Log>();

	private static HashMap<String, Log> m_networkLogs = new HashMap<String, Log>();

	private static final String LOG_MUTEX = new String("LOG_MUTEX");

	private static final String NETWORK_LOG_MUTEX = new String(
			"NETWORK_LOG_MUTEX");
	// private data members -- END

	// private constants -- BEGIN
	// private constants -- END

	static {
		try {
			m_IPAddress = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			m_IPAddress = "unknown host";
		}
	}
}
