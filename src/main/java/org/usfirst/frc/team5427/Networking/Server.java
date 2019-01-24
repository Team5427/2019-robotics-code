package org.usfirst.frc.team5427.Networking;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {

	private static Socket connection = null;
	private static ServerSocket serverSocket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;
	private static final int PORT = 25565;

	public static boolean send(byte[] buff) {
		if (hasConnection()) {
			try {
				out.write(buff);
				out.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public static boolean send(String s) {
		if (hasConnection()) {
			try {
				out.writeObject(s);
				out.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * Call whenever sending something to the client in order to check whether
	 * or not it is connected to the server.
	 * 
	 * @return whether the client is connected.
	 */
	public static boolean hasConnection() {
		return (connection != null && !connection.isClosed());
	}

	public static synchronized void reset() {
		try {
			connection.close();
			// serverSocket.close();
			in.close();
			out.close();
			connection = null;
			// serverSocket = null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void start() {

		try {
			serverSocket = new ServerSocket(PORT);
			serverSocket.setSoTimeout(800);

		} catch (Exception e) {
			e.printStackTrace();
		}

		listener.start();
	}

	public static synchronized void stop() {
		listener.interrupt();

		try {
			connection.close();
			serverSocket.close();
			in.close();
			out.close();
			connection = null;
			serverSocket = null;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Thread listener = new Thread(new Runnable() {

		@Override
		public void run() {

			while (!listener.isInterrupted()) {
				try {

					if (connection == null || connection.isClosed()) {
						//System.out.println("Searching for a connection...");
						try {

							connection = serverSocket.accept();
							out = new ObjectOutputStream(connection.getOutputStream());
							in = new ObjectInputStream(connection.getInputStream());

							if (connection != null && !connection.isClosed())
								System.out.println("Connected!");
						} catch (Exception e) {
						}
					} else {
						String s = in.readUTF();

						// TODO make sure that these are all working

						if (s.contains(StringDictionary.TASK)) {

							s = s.substring(StringDictionary.TASK.length(), s.length() - 1);

							if (s.contains(StringDictionary.GOAL_ATTACHED)) {

							} else if (s.contains(StringDictionary.LOG)) {

								send(StringDictionary.TASK + StringDictionary.LOG
										+ "roborio told the driverstation to log something, it should be the other way around.");

							} else if (s.contains(StringDictionary.MESSAGE)) {

								System.out.println("ROBORIO replied with message: " + s);

							} else if (s.contains(StringDictionary.TELEOP_START)) {


							} else if (s.contains(StringDictionary.AUTO_START)) {


							} else {
								System.out.println("Valid task was recieved, but with unrecognized contents.");
							}

						} else {
							System.out.println("unrecognized task");
						}

					}

				} catch (SocketException e) {
					System.out.println(
							"\n\tConnection to the client has been lost. Attempting to re-establish connection");
					reset();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	);
}
