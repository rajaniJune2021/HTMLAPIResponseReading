import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class APITest {

    @Test
    public void getMessages() throws Exception {
        String responseBody = null;
        RestAssured.baseURI = "https://smsreceivefree.com";
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent","Mozilla/5.0");
        headers.put("Accept","text/html");
        headers.put("Host","smsreceivefree.com");
        headers.put("sec-ch-ua-mobile","?0");
        headers.put("sec-ch-ua-platform","Windows");
        headers.put("Sec-Fetch-Dest","document");
        headers.put("Sec-Fetch-Mode","navigate");
        headers.put("Sec-Fetch-Site","same-origin");
        headers.put("Sec-Fetch-User","?1");
        Response response = RestAssured.given().relaxedHTTPSValidation().headers(headers).get("/info/14343376042/").then().extract().response();
        if (response.getStatusCode() == 200) {
            responseBody = response.getBody().asString();
            //System.out.println("get message resonse body : " + responseBody);
        }
        PrintWriter printWriter = new PrintWriter("C:\\Users\\e678332\\Desktop\\getmessageresponse.txt");
        printWriter.println(responseBody);
        FileReader fileReader = new FileReader("C:\\Users\\e678332\\Desktop\\getmessageresponse.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        String verificationCode = "";
        while((line = bufferedReader.readLine())!=null ){
            if((line=bufferedReader.readLine()).contains("Your PatientFirstPortal")) {
                verificationCode = line;
                break;
            }
        }
        System.out.println("verification code is:"+verificationCode);
    }
}
