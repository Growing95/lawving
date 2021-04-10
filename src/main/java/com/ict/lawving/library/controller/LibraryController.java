package com.ict.lawving.library.controller;

import java.io.File;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.common.Paging;
import com.ict.lawving.library.model.service.LibraryService;
import com.ict.lawving.library.model.vo.LibrarySearch;
import com.ict.lawving.library.model.vo.LibraryVo;
import com.ict.lawving.notice.model.vo.NoticeVo;

@Controller
public class LibraryController {
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private Paging paging;
	
	private Logger logger= LoggerFactory.getLogger(LibraryController.class);
	
	@RequestMapping("llist.do")
	public ModelAndView memberListMethod(HttpServletRequest request) {
		logger.info("llist.do");
		ModelAndView mv= new ModelAndView("library/libraryListView");
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
	
	@RequestMapping(value="lsearch.do",method=RequestMethod.POST)
	public String searchLibraryListMethod(@RequestParam("category") String category, @RequestParam("order") String order,
			@RequestParam("keyword") String keyword, Model model, HttpServletRequest request) {
		LibrarySearch searchObject = new LibrarySearch(category, order, keyword);
		// 1. 전체 게시물의 수
		int count = libraryService.getTotalCount(searchObject);
		paging.setTotalRecord(count);

		// 2. 전체 페이지의 수
		if (paging.getTotalRecord() <= paging.getNumPerPage()) {
			paging.setTotalPage(1);
		} else {
			paging.setTotalPage(paging.getTotalRecord() / paging.getNumPerPage());
			if (paging.getTotalRecord() % paging.getNumPerPage() != 0) {
				paging.setTotalPage(paging.getTotalPage() + 1);
			}
		}

		// 3. 현재 페이지
		String cPage = request.getParameter("cPage");
		if (cPage == null) {
			paging.setNowPage(1);
		} else {
			paging.setNowPage(Integer.parseInt(cPage));
		}

		// 4. 시작번호, 끝번호
		paging.setBegin((paging.getNowPage() - 1) * paging.getNumPerPage() + 1);
		paging.setEnd((paging.getBegin() - 1) + paging.getNumPerPage());
		// 5. 시작블록, 끝블록
		paging.setBeginBlock(
				(int) ((paging.getNowPage() - 1) / paging.getPagePerBlock()) * paging.getPagePerBlock() + 1);
		paging.setEndBlock((paging.getBeginBlock() + paging.getPagePerBlock() - 1));
		// 6. 주의사항(endBlock이 totalPage보다 클 수가 있다.)
		if (paging.getEndBlock() > paging.getTotalPage()) {
			paging.setEndBlock(paging.getTotalPage());
		}
		ArrayList<LibraryVo> list = null;
		switch (searchObject.getCategory()) {
		case "library_title":
			switch (searchObject.getOrder()) {
			case "desc":
				list = libraryService.selectSearchTitleDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = libraryService.selectSearchTitleAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;

		case "library_content":
			switch (searchObject.getOrder()) {
			case "desc":
				list = libraryService.selectSearchContentDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = libraryService.selectSearchContentAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;
		}

		if (list.size() > 0) {
			model.addAttribute("librarylist", list);
			model.addAttribute("paging", paging);
			return "library/libraryListView";
		} else {
			model.addAttribute("msg", keyword + "로 검색된 자료 정보가 없습니다.");
			return "common/errorPage";
		}
	}
	// 상세보기

		@RequestMapping(value = "onelist_library.do", method = RequestMethod.GET)
		public String selectlibraryOnelistMethod(@RequestParam("library_idx") int library_idx, Model model,
				HttpSession session) {
			LibraryVo lvo = libraryService.selectOneList(library_idx);
			
			session.setAttribute("lvo", lvo);
			return "library/libraryOneList";

		}

		// 자료실 글쓰기 페이지 이동(관리자)
		@RequestMapping("library_write.do")
		public String insertLibraryMethod() {
			return "library/libraryWriteForm";

		}
		// 자료실 글쓰기(관리자)
		@RequestMapping(value = "insertlibrary.do", method = RequestMethod.POST)
		public String libraryInsertMethod(LibraryVo library, HttpServletRequest request, Model model,
				@RequestParam(name = "file", required = false) MultipartFile lfile) {
			// 업로드된 파일 저장 폴더 지정하기
			String savePath = request.getSession().getServletContext().getRealPath("resources/library_files");
			
			// 첨부파일이 있을때만 업로드된 파일을 지정 폴더로 옮기기
			if (lfile.isEmpty()) {
				library.setLibrary_file_name("");
				library.setLibrary_refile_name("");
			}else{
				String fileName = lfile.getOriginalFilename();
				if (fileName != null && fileName.length() > 0) {
					library.setLibrary_file_name(fileName); // 원래 파일명 vo 에 저장함

					// 첨부된 파일의 파일명 바꾸기
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
					renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);
					
					try {
						lfile.transferTo(new File(savePath + "\\" + renameFileName));
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("msg", "전송파일 저장 실패.");
						return "common/errorPage";
					}
					library.setLibrary_file_name(lfile.getOriginalFilename());
					library.setLibrary_refile_name(renameFileName); 
					logger.info("insertlibrary.do : " + library);
				}
			}			
			if (libraryService.insertLibrary(library) > 0) {
				return "redirect:llist.do";
			} else {
				model.addAttribute("msg", "공지글 등록 실패.");
				return "common/errorPage";
			}
		}
		
		
		@RequestMapping("library_update.do")
		public String libraryUpdateMethod() {
			return "library/libraryUpdateForm";
		}

		// 게시글 수정 요청 처리용
		@RequestMapping(value = "updatelibrary.do", method = RequestMethod.POST)
		public String libraryUpdateMethod(LibraryVo library, @RequestParam("cPage") String cPage,@RequestParam("library_file_name")String library_file_name,
				@RequestParam("library_idx") int library_idx,@RequestParam("library_writer") String library_writer,
				@RequestParam(name = "delFlag", required = false) String delFlag, HttpServletRequest request, Model model,
				@RequestParam(name = "file", required = false) MultipartFile lfile) {

			// 첨부된 파일 저장 폴더 지정하기
			String savePath = request.getSession().getServletContext().getRealPath("resources/library_files");

			// 원래 첨부파일이 있는데, 삭제를 선택한 경우
			System.out.println("*****************************"+delFlag);
			if (library.getLibrary_file_name()!= null && delFlag != null && delFlag.equals("yes")) {
				// 저장 폴더에서 파일 삭제함
				new File(savePath + "\\" + library.getLibrary_file_name()).delete();
				library.setLibrary_file_name("");
				library.setLibrary_refile_name("");
			}
			// 새로운 첨부파일이 있다면
			
			if (lfile != null) {
				String fileName = lfile.getOriginalFilename();
				String renameFileName = null;
				if (fileName != null) {
					// 첨부된 파일의 파일명 바꾸기
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
					renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

					try {
						lfile.transferTo(new File(savePath + "\\" + renameFileName));
						library.setLibrary_file_name(lfile.getOriginalFilename());
						library.setLibrary_refile_name(renameFileName); 
					} catch (Exception e) {
						e.printStackTrace();
						model.addAttribute("msg", "전송파일 저장 실패.");
						return "common/errorPage";
					}
				} // 첨부된 파일의 파일명 변경에서 폴더에 저장 처리

				// 원래 첨부파일이 있는데 바뀐 경우
				if (library.getLibrary_file_name() != null) {
					// 원래 파일을 폴더에서 삭제 처리
					
					new File(savePath + "\\" + library.getLibrary_refile_name());
				}
				library.setLibrary_file_name(library.getLibrary_file_name());
				library.setLibrary_refile_name(library.getLibrary_refile_name());
				
			} // lfile != null

			if (libraryService.updatelibrary(library) > 0) {
				return "redirect:onelist_library.do?library_idx="+library_idx;
			} else {
				model.addAttribute("msg", library.getLibrary_idx() + "번 자료글 수정 실패.");
				return "common/errorPage";
			}
		}
		// 다운로드
		@RequestMapping("download_library.do")
		public ModelAndView fileDownMethod(@RequestParam("ofile") String library_file_name,
				@RequestParam("rfile") String library_refile_name, HttpServletRequest request) {
			String savePath = request.getSession().getServletContext().getRealPath("resources/library_files");
			File renameFile = new File(savePath + "\\" +library_refile_name );

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("renameFile", renameFile);
			model.put("library_file_name", library_file_name);
			return new ModelAndView("filedown2", "downFile", model);
		}

		
		@RequestMapping("chkdelete.do")
		public String chkDeleteMethod(HttpServletRequest request) {
			String [] chkMsg=request.getParameterValues("chkArr");
			int size = chkMsg.length;
			for (int i = 0; i < size; i++) {
				libraryService.chkdelete(chkMsg[i]);
			}
			return "redirect: llist.do";
		}
		
		
		
}
	
	
	

