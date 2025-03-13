import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class FirebaseAuth {

    private static final String FIREBASE_API_KEY = "AIzaSyCozlMcUAW_xd0XOpDQtFjp-SwORZLMRcI";

    // Method to register a new user
    public static JSONObject registerUser(String email, String password) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + FIREBASE_API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create the JSON payload
            JSONObject json = new JSONObject();
            json.put("email", email);
            json.put("password", password);
            json.put("returnSecureToken", true);

            // Send the request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check the response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Parse the response
                String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                JSONObject responseJson = new JSONObject(response);
                return responseJson; // Return the response JSON
            } else {
                // Parse the error response
                String errorResponse = new String(conn.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
                JSONObject errorJson = new JSONObject(errorResponse);
                String errorMessage = errorJson.getJSONObject("error").getString("message");
                System.out.println("Error: " + errorMessage);
                return null; // Registration failed
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Registration failed due to an exception
        }
    }

    // Method to send a verification email
    public static boolean sendVerificationEmail(String idToken) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + FIREBASE_API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create the JSON payload
            JSONObject json = new JSONObject();
            json.put("idToken", idToken);
            json.put("requestType", "VERIFY_EMAIL");

            // Send the request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check the response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return true; // Verification email sent successfully
            } else {
                // Parse the error response
                String errorResponse = new String(conn.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
                JSONObject errorJson = new JSONObject(errorResponse);
                String errorMessage = errorJson.getJSONObject("error").getString("message");
                System.out.println("Error: " + errorMessage);
                return false; // Failed to send verification email
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Failed due to an exception
        }
    }
}