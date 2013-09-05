import java.security.MessageDigest;

public class Validation {
	public static boolean isValidUser(String user) {
		// TODO make this actually check the server
		return true;
	}

	public static boolean createAccount(String user, String password, String email) {
		// TODO make this actually create an account through the server
		return true;
	}

	public static boolean isWorkValid(String user, Object work) {
		// TODO make this actually check the server
		return true;
	}

	private static String md5(String source) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(source.getBytes("UTF-8"));
			return getString(bytes);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String getString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			String hex = Integer.toHexString((int) 0x00FF & b);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}