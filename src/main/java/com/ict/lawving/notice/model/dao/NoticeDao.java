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
	public ArrayList<NoticeVo> selectSearch(NoticeSearch searchObject) {
		List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.search", searchObject);
		return (ArrayList<NoticeVo>) NoticeList;
	}

	// 상세보기
	public NoticeVo selectOneList(int notice_idx) {
		return sqlSession.selectOne("noticeMapper.selectNotice", notice_idx);

	}

	// 조회수
	public int getCount() {
		int result = 0;
		result = sqlSession.selectOne("count");
		return result;
	}

	// 시작, 끝 페이지
	public List<NoticeVo> getList(int begin, int end) {
		List<NoticeVo> noticelist = null;
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		noticelist = sqlSession.selectList("noticelist", map);
		return noticelist;
	}

	public int insertnotice(NoticeVo notice) {
		int result = 0;
		result = sqlSession.insert("insertnotice", notice);
		return result;
	}

}