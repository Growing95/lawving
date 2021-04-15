package com.ict.lawving.qna.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.lawving.notice.model.vo.NoticeVo;
import com.ict.lawving.qna.model.vo.QnaSearch;
import com.ict.lawving.qna.model.vo.QnaVo;

@Repository("qnaDao")
public class QnaDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
//	QNA 목록 조회하기 : 전체 게시물 수 구하기
	public int getCount() {
		int result = sqlSession.selectOne("qnaMapper.count");
		return result;
	}
	
//	QNA 목록 조회하기
	public ArrayList<QnaVo> selectQuestionList(int begin, int end) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		List<QnaVo> qnaList = new ArrayList<QnaVo>();
		qnaList = sqlSession.selectList("qnaMapper.list", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
//	QNA 검색 : 검색된 게시물 수 구하기
	public int getCount(QnaSearch searchObject) {
		int result = 0;
		switch (searchObject.getStatus()) {
		case "all":
			result = sqlSession.selectOne(
					"qnaMapper.searchCountAll", searchObject);
			break;
		case "completed":
			result = sqlSession.selectOne(
					"qnaMapper.searchCountCompleted", searchObject);
			break;
		case "waiting":
			result = sqlSession.selectOne(
					"qnaMapper.searchCountWaiting", searchObject);
			break;
		}
		return result;
	}
	
//	QNA 검색
	public ArrayList<QnaVo> searchAllQuestionDesc(
			QnaSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<QnaVo> qnaList = sqlSession.selectList(
				"qnaMapper.searchAllQuestionDesc", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
	public ArrayList<QnaVo> searchAllQuestionAsc(
			QnaSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<QnaVo> qnaList = sqlSession.selectList(
				"qnaMapper.searchAllQuestionAsc", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
	public ArrayList<QnaVo> searchCompletedQuestionDesc(
			QnaSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<QnaVo> qnaList = sqlSession.selectList(
				"qnaMapper.searchCompletedQuestionDesc", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
	public ArrayList<QnaVo> searchCompletedQuestionAsc(
			QnaSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<QnaVo> qnaList = sqlSession.selectList(
				"qnaMapper.searchCompletedQuestionAsc", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
	public ArrayList<QnaVo> searchWaitingQuestionDesc(
			QnaSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<QnaVo> qnaList = sqlSession.selectList(
				"qnaMapper.searchWaitingQuestionDesc", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
	public ArrayList<QnaVo> searchWaitingQuestionAsc(
			QnaSearch searchObject, int begin, int end) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyword", searchObject.getKeyword());
		map.put("begin", String.valueOf(begin));
		map.put("end", String.valueOf(end));
		List<QnaVo> qnaList = sqlSession.selectList(
				"qnaMapper.searchWaitingQuestionAsc", map);
		return (ArrayList<QnaVo>)qnaList;
	}
	
//	QNA 목록 상세보기 전 조회수 업데이트
	public int updateQuestionHit(String qna_idx) {
		int result = sqlSession.update("qnaMapper.updateQuestionHit", qna_idx);
		return result;
	}
	
//	QNA 목록 상세보기
	public QnaVo selectQuestionOnelist(String qna_idx) {
		QnaVo onelist = sqlSession.selectOne("qnaMapper.onelist", qna_idx);
		return onelist;
	}
	
//	QNA 이전 글 보기
	public QnaVo selectQuestionBefore(String qna_idx) {
		QnaVo onelist = sqlSession.selectOne("qnaMapper.onelist_before", qna_idx);
		return onelist;
	}
	
//	QNA 다음 글 보기
	public QnaVo selectQuestionAfter(String qna_idx) {
		QnaVo onelist = sqlSession.selectOne("qnaMapper.onelist_after", qna_idx);
		return onelist;
	}
	
//	QNA 문의 작성하기
	public int insertQuestion(QnaVo qna) {
		int result = sqlSession.insert("qnaMapper.insertQuestion", qna);
		return result;
	}
	
//	QNA 문의 삭제하기
	public int deleteQuestion(String qna_idx) {
		int result = sqlSession.delete("qnaMapper.deleteQuestion", qna_idx);
		return result;
	}
	
//	체크된 QNA 문의 삭제하기
	public void chkdeleteQuestion(String chkdel) {
		sqlSession.delete("qnaMapper.chkdeleteQuestion", chkdel);
	}
	
//	QNA 문의글 답변 작성/수정하기
	public int updateAnswer(QnaVo qna) {
//		updateAnswer : qna_idx를 사용해서 qna_comment를 update
		int result = sqlSession.update("qnaMapper.updateAnswer", qna);
		return result;
	}
	
//	QNA 문의글 답변 삭제하기
	public int deleteAnswer(String qna_idx) {
//		deleteAnswer : qna_idx를 사용해서 qna_comment를 null로 update 
		int result = sqlSession.update("qnaMapper.deleteAnswer", qna_idx);
		return result;
	}
	public int selectQnaIdx(String qna_idx) {
		int result = sqlSession.selectOne("qnaMapper.selectQnaIdx", qna_idx);
		return result;
	}

	public int deleteAll(String members_idx) {
		return sqlSession.delete("qnaMapper.repotdeleteAll",members_idx);
	}
}
