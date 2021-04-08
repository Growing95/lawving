package com.ict.lawving;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxLawController {
	@RequestMapping( value = "lawdata.do",produces = "text/xml; charset=utf-8")
	@ResponseBody//브라우저
	public String xml04Command() {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			 StringBuilder urlBuilder = new StringBuilder("https://talk.lawnorder.go.kr/openapi/service/lawqna.do"); /*URL*/
				urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8")+"=UJGfA3kmYlBRRWy70VY23GQJJckaErowDzg%2BZDO%2FCzQi%2B0QcbojTyLTyzkYNYpzbBC8Q%2BjOqOfPDrHvLC2iBLg%3D%3D"); /*Service Key*/
			        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
			        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*한 페이지 결과 수*/
			        urlBuilder.append("&" + URLEncoder.encode("SG_APIM","UTF-8") + "=" + URLEncoder.encode("2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY", "UTF-8"));
			        
				URL url = new URL(urlBuilder.toString());
				URLConnection conn = url.openConnection();
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				String msg="";
				while ((msg=br.readLine())!=null) {
					sb.append(msg);
				}
		} catch (Exception e) {
		}
		
		return sb.toString();
	}
}
