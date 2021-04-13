package com.ict.lawving.members.controller;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.common.Paging;
import com.ict.lawving.limit.model.service.LimitService;
import com.ict.lawving.members.model.service.MembersService;
import com.ict.lawving.members.model.vo.MembersVo;
import org.json.simple.JSONObject;


@SessionAttributes({"loginUser","loginAdmin"})
@Controller
public class MembersController {

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Autowired
	private MembersService membersService;
	@Autowired
	private LimitService limitService;
	@Autowired
	private Paging paging;
	
	private Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	@RequestMapping("go_login.do")
	public String gologinMethod() {
		if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
		    logger.debug("로그인페이지");
		
		return "member/logIn";

	}
	
	@RequestMapping("go_signup.do")
	public String gosignupMethod() {
		if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
		    logger.debug("회원가입페이지");
		
		return "member/memberInsertForm";

	}
	
	
	// ID찾기 페이지 이동
	@RequestMapping("go_findid.do")
	public String findidMethod() {
		if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
			logger.debug("아이디찾기페이지");
		
		return "member/findid";
		
	}
	
	// ID찾기
	@RequestMapping(value = "findid.do", method = RequestMethod.GET)
	public void findIdGET() throws Exception{
	}

	@RequestMapping(value = "findid.do", method = RequestMethod.POST)
	public void findIdPOST(@ModelAttribute MembersVo members, HttpServletResponse response) throws Exception{
		membersService.findId(response, members);
	}
	
	
	
	/* 비밀번호 찾기 페이지이동 */
	@RequestMapping("go_findpw.do")
	public String findpwMethod() {
		if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
		    logger.debug("비번찾기페이지");
		
		return "member/findpw";

	}
	
	/* 비밀번호 찾기 */
	@RequestMapping(value = "findpw.do", method = RequestMethod.GET)
	public void findPwGET() throws Exception{
		
	}

	@RequestMapping(value = "findpw.do", method = RequestMethod.POST)
	public void findPwPOST(@ModelAttribute MembersVo member, HttpServletResponse response) throws Exception{
		membersService.findPw(response, member);
	}
	//이메일 인증
	@RequestMapping(value = "email.do",method=RequestMethod.POST)
	public void emailPost(@ModelAttribute MembersVo members_email,HttpServletResponse response) throws Exception{
		membersService.emailpost(response,members_email);
		
	}
	
	//비밀번호변경
	@RequestMapping("update_pw.do")
	  public String updatePwMethod(MembersVo member,Model model,HttpSession session) {
		System.out.println("변경할비번:"+member.getMembers_pw());
		member.setMembers_pw(bcryptPasswordEncoder.encode(member.getMembers_pw()));
		System.out.println("암호화된 비번:"+member.getMembers_pw());
		String id = member.getMembers_id();
		int result = membersService.updatepw(member);
		  if(result>0) { 
			  MembersVo loginmember = membersService.selectloginCheck(id);
			  session.setAttribute("loginMember", loginmember);
			  model.addAttribute("msg","패스워드가 변경되었습니다.");
			  model.addAttribute("url","list_mypage.do?members_idx="+loginmember.getMembers_idx());
			return "common/alert";
		  }else {
			  model.addAttribute("message","패스워드 변경에 실패하였습니다.");
			  return "common/errorPage";
		  }
		  
		  }
	
/*	@RequestMapping(value="insert_member.do",method=RequestMethod.POST)
	public String insertMember(@ModelAttribute MembersVo members, Model model) {
		
		// 회원가입전에 회원정보를 출력
		System.out.println("Member 정보 : " + members.getMembers_id());
		System.out.println("Member 패스워드 : " + members.getMembers_pw());
		
		
		
		 * 비밀번호 -> 평문으로 되어있다. 1234 
		 * DB에 저장을 할때 평문으로 저장하면 안되기 때문에 "암호화" 처리를 한다.
		 * 
		 * 스프링 시큐리티라는 모듈에서 제공하는 bcrypt라는 암호화 방식으로 암호화 처리를 할꺼다.
		 * 
		 * * bcrypt란?
		 *   DB에 비밀번호를 저장할 목적으로 설계되었다.
		 *   
		 *   jsp/servlet 에서 했던 SHA-512암호화(단방향해쉬알고리즘)
		 *   
		 *   단점 : 111 평문 동일한 암호화 코드를 반화한다. 
		 *   
		 *   해결점 : 솔팅(salting) -> 원문에 아주작은랜덤문자열 추가해서 암호화 코드를 발생시킨다.
		 
		
		// 기존의 평문을 암호문으로 바꾸서 m객체에 다시 담자.
		String encPwd = bcryptPasswordEncoder.encode(members.getMembers_pw());
		System.out.println("암호화 처리 후 값 : " +encPwd);
		// setter를 통해서 Member객체의 pwd를 변경
		members.setMembers_pw(encPwd);
		
		System.out.println("수정된 Member객체 : " + members);
		
		// 회원가입 서비스를 호출
		int result = membersService.insertMember(members);
		
		if(result > 0) {
			return "redirect:home.do";
		}else {
			
			return "common/errorPage";
		}*/
		//회원가입시 작성 아이디 중복 체크 확인용
		@RequestMapping(value = "idCheck.do",method = RequestMethod.POST)
		public void idDuplicationCheck(@RequestParam("members_id")String id,HttpServletResponse response) throws IOException {
			logger.info("idCheck.do :"+ id);
			int idCount = membersService.selectCheckid(id);
			String returnValue = null;
			System.out.println("idCount:"+idCount);
			if (idCount==0) {
				returnValue = "ok";
			}else {
				returnValue="dup";
			}
			//ajax 통신은 입출력 스트림을 이용함
			//response 를이용해서 출력 스트림을 만들고 값 전송하기
			response.setContentType("text/html; charset=utf-8");//데이터타입 정해주기
			PrintWriter out = response.getWriter();
			out.append(returnValue);
			out.flush();
			out.close();
		}
		
		//회원가입 처리(패스워드 암호화 처리)
		@RequestMapping(value = "anroll.do",method = RequestMethod.POST)
		public String enrollMemberMethod(MembersVo members, Model model) {
			logger.info("anroll.do :"+members);//넘어오는 파라미터값 확인
			logger.info("아이디값확인"+members.getMembers_id());
			//패스워드 암호화 처리
			members.setMembers_pw(bcryptPasswordEncoder.encode(members.getMembers_pw()));
			logger.info("pw encode:"+members.getMembers_pw()+","+members.getMembers_pw().length());//암호화된 패스워드 값과 그 길이 확인 
			
			//서비스로 전송하고 결과를 받기
			int result = membersService.insertMember(members);
			
			//받은 결과로 성공/실패 페이지 내보내기
			if (result>0) {
				return "member/logIn";
			}else {
				model.addAttribute("message","회원가입 실패");
				return "common/error";
			}
		}
		
		//로그인 요청 처리(패스워드 암호화 처리)
		@RequestMapping(value = "login.do",method = RequestMethod.POST)
		public String loginCheakMethod(HttpSession session,Model model,MembersVo members) {
			logger.info("login.do :"+members.getMembers_id()+","+members.getMembers_pw());
			String id = members.getMembers_id();
			String pw = members.getMembers_pw();
			System.out.println("id:"+id);
			System.out.println("pw"+pw);
			//패스워드 암호화 처리후 Member객체에 기록 저장
			MembersVo member = new MembersVo();
			member.setMembers_id(id);
			member.setMembers_pw(bcryptPasswordEncoder.encode(pw));
			logger.info("암호화pw:"+member.getMembers_pw());
			MembersVo loginmember = membersService.selectloginCheck(id);
			//System.out.println("조회해온회원비밀번호:"+loginmember.getMembers_pw());
			//전송은 패스워드 (일반글자)와 조회해온 패스워드(암호화글자) 비교시
			//matchs()사용한다
			//logger.info("pw비교:"+bcryptPasswordEncoder.matches(pw, loginmember.getMembers_pw()));
			//logger.info("레벨확인:"+loginmember.getMembers_lev());
			 if (loginmember != null) {
				 if (bcryptPasswordEncoder.matches(pw, loginmember.getMembers_pw())) {

					 int level=Integer.parseInt(loginmember.getMembers_lev());
					 if (level==2) {
						 session.setAttribute("loginMember", loginmember);
						return"admin/adminIndex";
					}else {
						 session.setAttribute("loginMember", loginmember);
						 return "home";
					}
				}else {
					model.addAttribute("msg","패스워드가 일치하지 않습니다.");
					model.addAttribute("url","go_login.do");
					return "common/alert";
					
				}
			}else {
				model.addAttribute("msg","일치하는 아이디가 없습니다.");
				model.addAttribute("url","go_login.do");
				return "common/alert";
			}
		}
		//kakao 로그인
		@RequestMapping("kakao_login.do")
		public ModelAndView loginCommand(HttpServletRequest request,HttpSession session) {
			// 1. 인증코드 받기
			String code = request.getParameter("code");
			
			//2. 토큰받기
			String access_Token="";
			String refresh_Token="";
			String reqURL="https://kauth.kakao.com/oauth/token";
			
			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				//POST방식으로 요청을위해
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				
				//POST요청에 필요로 요구하는 파라미터들을 스트림 통해 전송
				BufferedWriter bw = new BufferedWriter(
							new OutputStreamWriter(conn.getOutputStream())
						);
				//요구하는 파라미터들
				StringBuffer sb = new StringBuffer();
				sb.append("grant_type=authorization_code");
				sb.append("&client_id=e2179d87d4b2efece0708e2c85212139");
				sb.append("&redirect_uri=http://localhost:8090/kakao_login.do");
				sb.append("&code="+code);
				bw.write(sb.toString());
				bw.flush();
				
				//응답코드:200성공 ,4XX 클라이언트오류 5XX서버오류
				
				  int responeseCode = conn.getResponseCode();
				 System.out.println("responeseCode="+responeseCode);
				 
				//요청을 통해 얻은 json 타입의 response메세지 읽어보기
				BufferedReader br = 
						new BufferedReader(
								new InputStreamReader(conn.getInputStream()));
				String line="";
				String result = "";
				while ((line=br.readLine())!=null) {
					result += line;
					
				}
				br.close();
				bw.close();
				System.out.println(result);
				
				//받은 정보가 Json
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(result);
				JSONObject j_obj=(JSONObject)obj;
				
				//엑세스 토큰 을 세션에 저장 해서 MyMember.java에서 호출한다.
				String access_token=(String)j_obj.get("access_token");
				request.getSession().setAttribute("access_token", access_token);
				
			} catch (Exception e) {
				System.out.println("카카오로그인쪽 익셉션에러:"+e);
			}
			
			
			
			//세션에 저장후 home.jsp간다 이후에 ajax를 이용 해서 정보 가져오기
			session.setAttribute("kakaoMember", "kakao");
			return new ModelAndView("home");
		}
		
		//로그아웃 처리
		@RequestMapping("logout.do")
		public String logoutMethod(HttpSession session) {
			session.invalidate();
			return "redirect:home.do";
		}
		
		
		// 관리자페이지 이동
		@RequestMapping("go_adminpage.do")
		public String goadminpageMethod() {
			if(logger.isDebugEnabled()) // 프로젝트 배포시에 성능저하를 막기위해 logger의 레벨이 DEBUG인지 여부를 확인
			    logger.debug("관리자페이지");
			
			return "admin/adminPage";

		}
		//어드민:회원관리
		@RequestMapping("memberslist.do")
		public ModelAndView memberListMethod(HttpServletRequest request) {
			ModelAndView mv= new ModelAndView("admin/adminPage");
			try {
				// 1. 전체 게시물의 수 
				int count= membersService.getTotalCount();
				paging.setTotalRecord(count);
				// 2. 전체 페이지의 수
				if (paging.getTotalRecord() <= paging.getNumPerPage()) {
					paging.setTotalPage(1);
				}else {
					paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerPage());
					if (paging.getTotalRecord()%paging.getNumPerPage() != 0) {
						paging.setTotalPage(paging.getTotalPage()+1);
					}
				}
				
				// 3. 현재 페이지
				String cPage= request.getParameter("cPage");
				if (cPage == null) {
					paging.setNowPage(1);
				}else {
					paging.setNowPage(Integer.parseInt(cPage));
				}
				
				// 4. 시작번호, 끝번호
				paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage()+1);
				paging.setEnd( (paging.getBegin()-1)+paging.getNumPerPage() );
				// 5. 시작블록, 끝블록
				paging.setBeginBlock( (int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock() +1);
				paging.setEndBlock((paging.getBeginBlock()+paging.getPagePerBlock()-1));
				// 6. 주의사항(endBlock이 totalPage보다 클 수가 있다.)
				if (paging.getEndBlock() > paging.getTotalPage()) {
					paging.setEndBlock(paging.getTotalPage());
				}
				List<MembersVo> memberslist=membersService.getList(paging.getBegin(),paging.getEnd());
				mv.addObject("memberslist", memberslist);
				mv.addObject("paging", paging);
			} catch (Exception e) {
				System.out.println(e);
			}
			return mv;
		}
		@RequestMapping("membersonelist.do")
		public ModelAndView selectMembersOnelistMethod(@RequestParam("members_idx") int members_idx, Model model) {
			ModelAndView mv = new ModelAndView("admin/membersOneList");
			MembersVo mvo=membersService.selectOneList(members_idx);
			mv.addObject("mvo",mvo);
			return mv;
		}
		
		
		
		
		
		
		

		//회원정보수정
		@RequestMapping(value = "update_members.do", method =RequestMethod.POST )
		  public String updateMemberMethod(MembersVo m,Model model,HttpSession session) {
		  int result = membersService.updateMember(m);
		  String id = m.getMembers_id();
		  if(result>0) { 
			  MembersVo loginmember = membersService.selectloginCheck(id);
			  session.setAttribute("loginMember", loginmember);
			  model.addAttribute("msg","회원정보가 수정되었습니다.");
			  model.addAttribute("url","list_mypage.do?members_idx="+loginmember.getMembers_idx());
			return "common/alert";
		  }else {
			  model.addAttribute("message","회원정보수정에 실패하였습니다.");
			  return "common/errorPage";
		  }
		  
		  }
		//회원탈퇴
		@RequestMapping("delete_members.do")
		public String deleteMembersMethod(@RequestParam("members_idx")String id,Model model,HttpSession session) {
			int result = membersService.deleteMembers(id);
			if (result>0) {
				session.invalidate();
				model.addAttribute("msg","회원탈퇴 처리되었습니다.");
				  model.addAttribute("url","home.do");
				return "common/alert";
			}else {
				model.addAttribute("msg","[오류]회원탈퇴 실패.");
				  model.addAttribute("url","home.do");
				return "common/alert";
			}
		}
		//정보조회
		@RequestMapping(value = "list_lawdata.do",method = RequestMethod.GET)
		public String selectLawMethod(@RequestParam("law")String law,Model model) {
			model.addAttribute("law", law);
			System.out.println("넘어온 로우값:"+law);
			return "lawdata/lawdata";
		}
		//상세조회
		@RequestMapping(value = "search_lawdata.do",method = RequestMethod.POST)
		public String searchLawMethod(@RequestParam("search")String search,Model model) {
			System.out.println(search);
			String law = search;
			model.addAttribute("law", law);
			return "lawdata/lawdata";
		}
		
		@RequestMapping("chkblackdelete.do")
		public String chkDeleteMethod(HttpServletRequest request) {
			String [] chkMsg=request.getParameterValues("chkArr");
			int size = chkMsg.length;
			for (int i = 0; i < size; i++) {
				limitService.chkblackdelete(chkMsg[i]);
			}
			return "redirect: memberslist.do";
		}
		
		
		
		
		
		
	}
