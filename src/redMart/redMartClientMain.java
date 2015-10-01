package redMart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class redMartClientMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JSONObject js = new JSONObject();
		JSONObject js1 = new JSONObject();
		JSONObject js2 = new JSONObject();
		JSONArray jArr = new JSONArray();
		try {
			js.put("ID", "1");
			js.put("hight", "10");
			js.put("width", "5");
			js.put("breadth", "9");
			jArr.put(js);
			js1.put("ID", "2");
			js1.put("hight", "10");
			js1.put("width", "4");
			js1.put("breadth", "9");
			jArr.put(js1);
			js2.put("ID", "3");
			js2.put("hight", "8");
			js2.put("width", "5");
			js2.put("breadth", "9");
			jArr.put(js2);
			JSONObject jMain = new JSONObject();
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://localhost:8082/RestDemo/rest/redmart/getSlot");

			StringEntity input = new StringEntity(jArr.toString());//new StringEntity("{\"qty\":100,\"name\":\"iPad 4\"}");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();
			
			} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
}
