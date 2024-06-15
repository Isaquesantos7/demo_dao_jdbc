package application;

import DB.DbConnect;

public class Program {
	public static void main(String [] args) {
		DbConnect.getConnection();
		DbConnect.closeConnection();
	}
}
