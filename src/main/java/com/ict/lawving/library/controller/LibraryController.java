package com.ict.lawving.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.common.Paging;
import com.ict.lawving.library.model.service.LibraryService;
import com.ict.lawving.library.model.vo.LibraryVo;
import com.ict.lawving.members.model.vo.MembersVo;

@Controller
public class LibraryController {
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private Paging paging;
	
	private Logger logger= LoggerFactory.getLogger(LibraryController.class);
	
	@RequestMapping("go_library.do")
	public ModelAndView memberListMethod(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("library/librarymain");
		try {
			// 1. 전체 게시물의 수 
			int count= libraryService.getTotalCount();
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
			List<LibraryVo> librarylist=libraryService.getList(paging.getBegin(),paging.getEnd());
			mv.addObject("librarylist", librarylist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mv;
	}
	
	@RequestMapping("searchLibraryList.do")
	public ModelAndView searchLibraryListMethod(HttpServletRequest request) {
		ModelAndView mv= new ModelAndView("library/librarymain");
		
		return mv;
		
	}
	
	
	
	
}
