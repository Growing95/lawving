package com.ict.lawving.repot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.common.Paging;
import com.ict.lawving.limit.controller.LimitController;
import com.ict.lawving.limit.model.vo.LimitVo;
import com.ict.lawving.repot.model.service.RepotService;
import com.ict.lawving.repot.model.vo.RepotVo;

@Controller
public class RepotController {
	@Autowired
	private RepotService repotService;
	@Autowired
	private Paging paging;
	
	private Logger logger= LoggerFactory.getLogger(LimitController.class);
	
	
	@RequestMapping("repotlist.do")
	public ModelAndView memberListMethod(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("admin/repotlist");
		try {
			// 1. 전체 게시물의 수 
			int count= repotService.getTotalCount();
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
			List<RepotVo> repotlist=repotService.getList(paging.getBegin(),paging.getEnd());
			mv.addObject("repotlist", repotlist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
		}
		return mv;
	}
	
	@RequestMapping("go_repot.do")
	public String gorepotMethod() {
		return "qna/repotForm";
	}
	@RequestMapping(value="insert_repot.do", method=RequestMethod.POST )
	public String insertrepotMethod(@RequestParam("members_idx")String m_idx,@RequestParam("qna_writer")String q_writer) {
		int result = repotService.insertrepot(m_idx,q_writer);
		if (result>0) {
			return "redirect:list_qna.do" ;
		}else {
			return "common/errorPage";
		}
		
	}
	
}
