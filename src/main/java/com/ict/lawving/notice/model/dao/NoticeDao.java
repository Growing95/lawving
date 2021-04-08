
package com.ict.lawving.notice.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.notice.model.vo.NoticeSearch;
import com.ict.lawving.notice.model.vo.NoticeVo;
import com.ict.lawving.qna.model.vo.QnaVo;

@Repository("noticeDao")
public class NoticeDao {
// 스프링-마이바티스 연동 객체 연결함 : root-context.xml 에 선언되어 있음
	@Autowired
	private SqlSessionTemplate sqlSession;

	// Notice 목록 조회하기
	public ArrayList<NoticeVo> selectNoticeList() {
		List<NoticeVo> List = sqlSession.selectList("noticeMapper.list");
		return (ArrayList<NoticeVo>) List;
	}

	// 검색하기
	public ArrayList<NoticeVo> selectSearchTitleDesc(NoticeSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.searchTitleDesc", map);
		return (ArrayList<NoticeVo>) NoticeList;
	}

	public ArrayList<NoticeVo> selectSearchTitleAsc(NoticeSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.searchTitleAsc", map);
		return (ArrayList<NoticeVo>) NoticeList;
	}

	public ArrayList<NoticeVo> selectSearchContentDesc(NoticeSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.searchContentDesc", map);
		return (ArrayList<NoticeVo>) NoticeList;
	}

	public ArrayList<NoticeVo> selectSearchContentAsc(NoticeSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.searchContentAsc", map);
		return (ArrayList<NoticeVo>) NoticeList;
	}

	// 상세보기
	public NoticeVo selectOneList(int notice_idx) {
		List<NoticeVo> OneList = sqlSession.selectList("noticeMapper.Onelist");
		return (NoticeVo) OneList;
	}

	// 조회수
	public int getCount() {
		int result = 0;
		result = sqlSession.selectOne("noticeMapper.count");
		return result;
	}

	public int getCount(NoticeSearch searchObject) {
		int result = 0;
		switch (searchObject.getCategory()) {
		case "notice_title":
			result = sqlSession.selectOne("noticeMapper.searchCountTitle", searchObject);
			break;
		case "notice_content":
			result = sqlSession.selectOne("noticeMapper.searchCountContent", searchObject);
			break;
		}
		return result;
	}

	// 시작, 끝 페이지
	public List<NoticeVo> getList(int begin, int end) {
		System.out.println("dao:" + begin + ";" + end);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		List<NoticeVo> noticelist = sqlSession.selectList("noticeMapper.noticelist", map);
		System.out.println("dao:" + noticelist);
		return noticelist;
	}

	public int insertnotice(NoticeVo notice) {
		int result = 0;
		result = sqlSession.insert("insertnotice", notice);
		return result;
	}

	public NoticeVo selectNoticeOnelist(String notice_idx) {
		NoticeVo onelist = sqlSession.selectOne("noticeMapper.onelist", notice_idx);
		return onelist;
	}

}
