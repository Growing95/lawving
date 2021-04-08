
package com.ict.lawving.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

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
			List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.list");
			return (ArrayList<NoticeVo>) NoticeList;
		}


		public ArrayList<NoticeVo> selectSearch(NoticeSearch searchObject) {
			List<NoticeVo> NoticeList = sqlSession.selectList("noticeMapper.search",searchObject);
			return (ArrayList<NoticeVo>) NoticeList;
		}

	}