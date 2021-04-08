package com.ict.lawving.qna.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
//	QNA 검색
	public ArrayList<QnaVo> searchQuestion(String keyword, String category) {
//		qna_content에서 keyword를 포함하는 데이터를 카테고리 파라미터에 따라 다르게 검색
		List<QnaVo> qnaList = new ArrayList<QnaVo>();
		switch (category) {
//			searchDesc : 최신순으로 select 
			case "최신순": qnaList = sqlSession.selectList("qnaMapper.searchDesc", keyword); break;
//			searchAsc : 오래된순으로 select 
			case "오래된순": qnaList = sqlSession.selectList("qnaMapper.searchAsc", keyword); break;
//			searchCompleted : qna_status가 답변완료 상태인 데이터를 select
			case "답변완료": qnaList = sqlSession.selectList("qnaMapper.searchCompleted", keyword); break;
//			searchWaiting :  qna_status가 대기중 상태인 데이터를 select
			case "대기중": qnaList = sqlSession.selectList("qnaMapper.searchWaiting", keyword); break;
		}
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
