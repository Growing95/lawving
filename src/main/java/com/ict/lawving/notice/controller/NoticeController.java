package com.ict.lawving.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.lawving.common.Paging;
import com.ict.lawving.notice.model.service.NoticeService;
import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;

@Controller
public class NoticeController {
	private Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private Paging paging;

	// 리스트 목록
	@RequestMapping("nlist.do")
	public ModelAndView selectNoticeListMethod(HttpServletRequest request) {
		logger.info("nlist.do");
		ModelAndView mv = new ModelAndView("notice/noticeListView");
		try {
			// 1. 전체 게시물의 수
			int count = noticeService.getTotalCount();
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
			List<NoticeVo> noticelist = noticeService.getList(paging.getBegin(), paging.getEnd());
			logger.info("list:" + noticelist);
			mv.addObject("noticelist", noticelist);
			mv.addObject("paging", paging);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	// 검색하기
	@RequestMapping(value = "nsearch.do", method = RequestMethod.POST)
	public String searchNoticeMethod(@RequestParam("category") String category, @RequestParam("order") String order,
			@RequestParam("keyword") String keyword, Model model, HttpServletRequest request) {
		NoticeSearch searchObject = new NoticeSearch(category, order, keyword);
		// 1. 전체 게시물의 수
		int count = noticeService.getTotalCount(searchObject);
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
		ArrayList<NoticeVo> list = null;
		switch (searchObject.getCategory()) {
		case "notice_title":
			switch (searchObject.getOrder()) {
			case "desc":
				list = noticeService.selectSearchTitleDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = noticeService.selectSearchTitleAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;

		case "notice_content":
			switch (searchObject.getOrder()) {
			case "desc":
				list = noticeService.selectSearchContentDesc(searchObject, paging.getBegin(), paging.getEnd());
				break;

			case "asc":
				list = noticeService.selectSearchContentAsc(searchObject, paging.getBegin(), paging.getEnd());
				break;
			}
			break;
		}

		if (list.size() > 0) {
			model.addAttribute("noticelist", list);
			model.addAttribute("paging", paging);
			return "notice/noticeListView";
		} else {
			model.addAttribute("msg", keyword + "로 검색된 공지사항 정보가 없습니다.");
			return "common/errorPage";
		}
	}
	// 상세보기

	@RequestMapping(value = "onelist_notice.do", method = RequestMethod.GET)
	public String selectNoticeOnelistMethod(@RequestParam("notice_idx") int notice_idx, Model model,
			HttpSession session, HttpServletRequest request) {
		NoticeVo nvo = noticeService.selectOneList(notice_idx);
		session.setAttribute("nvo", nvo);
		return "notice/noticeOneList";

	}

	// 공지사항 글쓰기 페이지 이동(관리자)
	@RequestMapping("notice_insert.do")
	public String insertNoticeMethod() {
		return "notice/noticeWriteForm";

	}

	// 공지사항 글쓰기(관리자)
	@RequestMapping(value = "insert_notice.do", method = RequestMethod.POST)
	public String noticeInsertMethod(NoticeVo notice, HttpServletRequest request, Model model,
			@RequestParam(name = "file", required = false) MultipartFile nfile) {
		// 업로드된 파일 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_files");

		// 첨부파일이 있을때만 업로드된 파일을 지정 폴더로 옮기기
		if (nfile.isEmpty()) {
			notice.setNotice_file_name("");
			notice.setNotice_refile_name("");
		} else {
			String fileName = nfile.getOriginalFilename();
			if (fileName != null && fileName.length() > 0) {
				notice.setNotice_file_name(fileName); // 원래 파일명 vo 에 저장함

				// 첨부된 파일의 파일명 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

				try {
					nfile.transferTo(new File(savePath + "\\" + renameFileName));
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("msg", "전송파일 저장 실패.");
					return "common/errorPage";
				}
				notice.setNotice_file_name(nfile.getOriginalFilename());
				notice.setNotice_refile_name(renameFileName);
				logger.info("insert_notice.do : " + notice);
			}
		}
		if (noticeService.insertNotice(notice) > 0) {
			return "redirect:nlist.do";
		} else {
			model.addAttribute("msg", "공지글 등록 실패.");
			return "common/errorPage";
		}
	}

	@RequestMapping("notice_update.do")
	public String noticeUpdateMethod() {
		return "notice/noticeUpdateForm";
	}

	// 게시글 수정 요청 처리용
	@RequestMapping(value = "updatenotice.do", method = RequestMethod.POST)
	public String noticeUpdateMethod(NoticeVo notice, @RequestParam("cPage") String cPage,
			@RequestParam("notice_file_name") String notice_file_name, @RequestParam("notice_idx") int notice_idx,
			@RequestParam("notice_writer") String notice_writer,
			@RequestParam(name = "delFlag", required = false) String delFlag, HttpServletRequest request, Model model,
			@RequestParam(name = "file", required = false) MultipartFile nfile) {

		// 첨부된 파일 저장 폴더 지정하기
		String savePath = request.getSession().getServletContext().getRealPath("resources/notice_files");

		// 원래 첨부파일이 있는데, 삭제를 선택한 경우
		System.out.println("*****************************" + delFlag);
		if (notice.getNotice_file_name() != null && delFlag != null && delFlag.equals("yes")) {
			// 저장 폴더에서 파일 삭제함
			new File(savePath + "\\" + notice.getNotice_file_name()).delete();
			notice.setNotice_file_name("");
			notice.setNotice_refile_name("");
		}
		// 새로운 첨부파일이 있다면

		if (nfile != null) {
			String fileName = nfile.getOriginalFilename();
			String renameFileName = null;
			if (fileName != null) {
				// 첨부된 파일의 파일명 바꾸기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				renameFileName = sdf.format(new java.sql.Date(System.currentTimeMillis()));
				renameFileName += "." + fileName.substring(fileName.lastIndexOf(".") + 1);

				try {
					nfile.transferTo(new File(savePath + "\\" + renameFileName));
					notice.setNotice_file_name(nfile.getOriginalFilename());
					notice.setNotice_refile_name(renameFileName);
				} catch (Exception e) {
					e.printStackTrace();
					model.addAttribute("msg", "전송파일 저장 실패.");
					return "common/errorPage";
				}
			} // 첨부된 파일의 파일명 변경에서 폴더에 저장 처리

			// 원래 첨부파일이 있는데 바뀐 경우
			if (notice.getNotice_file_name() != null) {
				// 원래 파일을 폴더에서 삭제 처리

				new File(savePath + "\\" + notice.getNotice_refile_name());
			}
			notice.setNotice_file_name(notice.getNotice_file_name());
			notice.setNotice_refile_name(notice.getNotice_refile_name());

		} // nfile != null

		if (noticeService.updateNotice(notice) > 0) {
			return "redirect:onelist_notice.do?notice_idx=" + notice_idx;
		} else {
			model.addAttribute("msg", notice.getNotice_idx() + "번 자료글 수정 실패.");
			return "common/errorPage";
		}
	}

	/*
	 * // 다운로드
	 * 
	 * @RequestMapping("download_library.do") public ModelAndView
	 * fileDownMethod(@RequestParam("ofile") String library_file_name,
	 * 
	 * @RequestParam("rfile") String library_refile_name, HttpServletRequest
	 * request) { String savePath =
	 * request.getSession().getServletContext().getRealPath(
	 * "resources/library_files"); File renameFile = new File(savePath + "\\" +
	 * library_refile_name);
	 * 
	 * Map<String, Object> model = new HashMap<String, Object>();
	 * model.put("renameFile", renameFile); model.put("library_file_name",
	 * library_file_name); return new ModelAndView("filedown2", "downFile", model);
	 * }
	 */

	/*
	 * @RequestMapping("chkdelete.do") public String
	 * chkDeleteMethod(HttpServletRequest request) { String[] chkMsg =
	 * request.getParameterValues("chkArr"); int size = chkMsg.length; for (int i =
	 * 0; i < size; i++) { noticeService.chkdelete(chkMsg[i]); } return
	 * "redirect: nlist.do"; }
	 */

	@RequestMapping("notice_delete.do")
	public String deletenoticeMethod(@RequestParam("notice_idx") int notice_idx) {
		int result = noticeService.deleteNotice(notice_idx);
		return "redirect: nlist.do";
	}

	// Notice 이전 글 보기

	@RequestMapping("before_notice.do")
	public String selectNoticeBeforeMethod(@RequestParam("notice_idx") String notice_idx,
			@ModelAttribute("cPage") String cPage, HttpSession session, HttpServletRequest request) {
		System.out.println("notice_idx : " + notice_idx);
		System.out.println("cPage : " + cPage);
		NoticeVo nvo = noticeService.selectNoticeBefore(notice_idx);
		session.setAttribute("nvo", nvo);
		return "notice/noticeOneList";
		}

	// Notice 다음 글 보기

	@RequestMapping("after_notice.do")
	public String selectNoticeAfterMethod(@RequestParam("notice_idx") String notice_idx,
			@ModelAttribute("cPage") String cPage, HttpSession session, HttpServletRequest request) {
		System.out.println("notice_idx : " + notice_idx);
		System.out.println("cPage : " + cPage);
		NoticeVo nvo = noticeService.selectNoticeAfter(notice_idx);
		session.setAttribute("nvo", nvo);
		return "notice/noticeOneList";
	}

}
