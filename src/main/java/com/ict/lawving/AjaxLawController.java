package com.ict.lawving;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxLawController {
	@RequestMapping( value = "update_lawdata.do",produces = "text/xml; charset=utf-8")
	@ResponseBody//브라우저
	public String xml04Command(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		try {
			 StringBuilder urlBuilder = new StringBuilder("https://talk.lawnorder.go.kr/openapi/service/lawqna.do"); /*URL*/
				urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8")+"=UJGfA3kmYlBRRWy70VY23GQJJckaErowDzg%2BZDO%2FCzQi%2B0QcbojTyLTyzkYNYpzbBC8Q%2BjOqOfPDrHvLC2iBLg%3D%3D"); /*Service Key*/
			        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
			        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
			        urlBuilder.append("&" + URLEncoder.encode("SG_APIM","UTF-8") + "=" + URLEncoder.encode("2ug8Dm9qNBfD32JLZGPN64f3EoTlkpD8kSOHWfXpyrY", "UTF-8"));
			        
				URL url = new URL(urlBuilder.toString());
				URLConnection conn = url.openConnection();
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
				//xml파일로저장시키기
				String savepath = request.getSession().getServletContext().getRealPath("/resources/lawdata/law.xml");//저장될경로설정
				FileWriter fwriter = new FileWriter(new File(savepath));//파일을생성시켜준다.
				String msg="";
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				while ((msg=br.readLine())!=null) {
					sb.append(msg);//sb읽어온데이터를 밀어넣어준다.
				}
				sb.replace(sb.lastIndexOf("</item>")+7, sb.length(),"</items></body></response>");
				System.out.println("내용확인:"+sb.toString());
				System.out.println("savepath확인:"+savepath);
				System.out.println("fwriter확인:"+fwriter);
				
				fwriter.write(sb.toString());
				fwriter.flush();
				fwriter.close();
				
			StringBuilder dbString = new StringBuilder();
			dbString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			dbString.append(sb.substring(sb.indexOf("<items>"),sb.lastIndexOf("</items>")+8));
			
			String savepath2 = request.getSession().getServletContext().getRealPath("/resources/lawdata/dblaw.xml");//저장될경로설정
			FileWriter fwriter2 = new FileWriter(new File(savepath2));//파일을생성시켜준다.
				fwriter2.write(dbString.toString());
				fwriter2.flush();
				fwriter2.close();
			
			System.out.println(dbString);
			Properties prop = new Properties();
			prop.load(new CharArrayReader(dbString.toString().toCharArray()));
			/*
			 * for (int i = 0; i < prop.size(); i++) {
			 * System.out.println(prop.getProperty("item")); }
			 */
			System.out.println(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
