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
		switch (searchObject.getCategory()) {
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
		
		System.out.println("AllDesc-Dao-keyword : "+searchObject.getKeyword());
		System.out.println("AllDesc-Dao-begin : "+String.valueOf(begin));
		System.out.println("AllDesc-Dao-end : "+String.valueOf(end));
		
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
		
		System.out.println("AllAsc-Dao-keyword : "+searchObject.getKeyword());
		System.out.println("AllAsc-Dao-begin : "+String.valueOf(begin));
		System.out.println("AllAsc-Dao-end : "+String.valueOf(end));
		
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
	
//	QNA 목록 상세보기
	public QnaVo selectQuestionOnelist(String qna_idx) {
		QnaVo onelist = sqlSession.selectOne("qnaMapper.onelist", qna_idx);
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
	
//	체크된 QNA 문의 삭제하기 (관리자 전용) -> 보류
	public int chkdeleteQuestion(String[] qna_idx) {return 0;}
	
//	QNA 문의글 답변 작성/수정하기
	public QnaVo updateAnswer(QnaVo qna) {
//		updateAnswer : qna_idx를 사용해서 qna_comment를 update하고 
//		   			   qna_status를 답변완료 상태로 update한다.
		int result = sqlSession.update("qnaMapper.updateAnswer", qna);
//		업데이트에 성공할 시
		if (result>0) {
//			onelist로 새로 받은 QNA를 가지고 return한다.
			String idx = qna.getQna_idx();
			QnaVo onelist = sqlSession.selectOne("qnaMapper.onelist", idx);
			return onelist;
//		업데이트에 실패할 시
		} else {
//			입력된 QNA에 실패 메세지를 덧씌워 return한다.
			String msg = "답변을 입력하지 못했습니다.";
			qna.setQna_comment(msg);
			return qna;
		}
	}
	
//	QNA 문의글 답변 삭제하기
	public int deleteAnswer(String qna_idx) {
//		deleteAnswer : qna_idx를 사용해서 qna_comment를 null로 update하고 
//		   			   qna_status를 waiting으로 update한다.
		int result = sqlSession.update("qnaMapper.deleteAnswer", qna_idx);
		return result;
	}
}
