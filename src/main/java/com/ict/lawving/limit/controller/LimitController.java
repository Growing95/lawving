package com.ict.lawving.limit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ict.lawving.common.Paging;
import com.ict.lawving.limit.model.service.LimitService;
import com.ict.lawving.limit.model.service.LimitServiceImpliment;
import com.ict.lawving.limit.model.vo.LimitVo;
import com.ict.lawving.members.model.service.MembersService;
import com.ict.lawving.members.model.vo.MembersVo;

@Controller
public class LimitController {
	@Autowired
	private LimitService limitService;
	@Autowired
	private MembersService membersService;
	@Autowired
	private Paging paging;
	
	private Logger logger= LoggerFactory.getLogger(LimitController.class);
	
	@RequestMapping("limitlist.do")
	public ModelAndView limitListMethod(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("admin/limitlist");
		try {
			// 1. 전체 게시물의 수 
			int count= limitService.getTotalCount();
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
			List<LimitVo> limitlist=limitService.getList(paging.getBegin(),paging.getEnd());
			mv.addObject("limitlist", limitlist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
		}
		return mv;
	}
	@RequestMapping("blacklist.do")
	public ModelAndView blackListMethod(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("admin/blacklist");
		try {
			int count= limitService.getTotalBlackCount();
			System.out.println(count);
			paging.setTotalRecord(count);
			if (paging.getTotalRecord() <= paging.getNumPerPage()) {
				paging.setTotalPage(1);
			}else {
				paging.setTotalPage(paging.getTotalRecord()/paging.getNumPerPage());
				if (paging.getTotalRecord()%paging.getNumPerPage() != 0) {
					paging.setTotalPage(paging.getTotalPage()+1);
				}
			}
			String cPage= request.getParameter("cPage");
			if (cPage == null) {
				paging.setNowPage(1);
			}else {
				paging.setNowPage(Integer.parseInt(cPage));
			}
			paging.setBegin((paging.getNowPage()-1)*paging.getNumPerPage()+1);
			paging.setEnd( (paging.getBegin()-1)+paging.getNumPerPage() );
			paging.setBeginBlock( (int)((paging.getNowPage()-1)/paging.getPagePerBlock())*paging.getPagePerBlock() +1);
			paging.setEndBlock((paging.getBeginBlock()+paging.getPagePerBlock()-1));
			if (paging.getEndBlock() > paging.getTotalPage()) {
				paging.setEndBlock(paging.getTotalPage());
			}
			List<LimitVo> blacklist=limitService.getBlackList(paging.getBegin(),paging.getEnd());
			mv.addObject("blacklist", blacklist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
		}
		return mv;
	}
	@RequestMapping(value="register_limit.do",method={RequestMethod.GET,RequestMethod.POST})
	public String registerLimitMethod(LimitVo lvo,MembersVo mvo,RedirectAttributes redirectAttributes,
			@RequestParam("members_idx")String members_idx,@RequestParam("qna_idx")String qna_idx,Model model
			) {
		String members_id= membersService.searchid(members_idx);
		String members_lev=membersService.searchlev(members_idx);
		String members_reg=membersService.searchreg(members_idx);
		lvo.setReg(members_reg.substring(0,10));
		lvo.setLimit_id(members_id);
		System.out.println("************리미트 아이디 :"+lvo.getLimit_id());
		int count = limitService.searchMember(lvo);
		System.out.println("***************카운팅 완료");
		if (count>0) {
			int result=limitService.updateinfo(lvo);
			System.out.println("***************리미트업데이트성공");
			int lcount = limitService.chkcount(lvo);
			System.out.println("************카운트횟수"+lcount);
			if (lcount == 5) {
				try {
					System.out.println("***************멤버 레벨 업데이트 시작***************");
					int result2=membersService.getupdatelev(members_idx);
					
					model.addAttribute("result2",result2);
					System.out.println("***************멤버 레벨 업데이트 완료***************");
					return "redirect:delete_repot.do?qna_idx="+qna_idx+"&members_idx="+members_idx;
				} catch (Exception e) {
					model.addAttribute("msg","처리완료"); model.addAttribute("url","home.do"); 
					return "common/alert";
				}
				}else {
				return "redirect:delete_repot.do?qna_idx="+qna_idx+"&members_idx="+members_idx;
			}
		}else {
			int result=limitService.insertinfo(lvo);
			return "redirect:delete_repot.do?qna_idx="+qna_idx+"&members_idx="+members_idx;
		}
	}
	
	
	
	
	
}
