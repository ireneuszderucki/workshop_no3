package pl.coderslab.services;

public class MultiHelper {
	
	static public boolean atLeastOneChar(String string) {
		if (string.length()>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static public boolean checkEmail(String string) {
		if (string.length()>4 && string.contains("@")) {
			return true;
		}
		else {
			return false;
		}
	}

}
