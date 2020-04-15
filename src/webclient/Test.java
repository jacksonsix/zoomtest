package webclient;

public class Test {
	public static void main(String[] args) {
		
		RESTClient client = new RESTClient();
		String token =client.getOauthToken();
		
	}
}
