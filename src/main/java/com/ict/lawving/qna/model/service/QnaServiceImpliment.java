package com.ict.lawving.qna.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.qna.model.dao.QnaDao;
import com.ict.lawving.qna.model.vo.QnaSearch;
import com.ict.lawving.qna.model.vo.QnaVo;

@Service("qnaService")
public class QnaServiceImpliment implements QnaService {
	@Autowired
	private QnaDao qnaDao;

//	QNA 목록 조회하기 : 전체 게시물 수 구하기
	@Override
	public int getTotalCount() {
		return qnaDao.getCount();
	}
	
//	QNA 목록 조회하기
	@Override
	public ArrayList<QnaVo> selectQuestionList(
			int begin, int end) {
		return qnaDao.selectQuestionList(begin, end);
	}
	
//	QNA 목록 조회하기 : 전체 게시물 수 구하기
	@Override
	public int getTotalCount(QnaSearch searchObject) {
		return qnaDao.getCount(searchObject);
	}

//	QNA 검색
	@Override
	public ArrayList<QnaVo> searchAllQuestionDesc(
			QnaSearch searchObject, int begin, int end) {
		return qnaDao.searchAllQuestionDesc(searchObject, begin, end);
	}
	
	@Override
	public ArrayList<QnaVo> searchAllQuestionAsc(
			QnaSearch searchObject, int begin, int end) {
		return qnaDao.searchAllQuestionAsc(searchObject, begin, end);
	}
	
	@Override
	public ArrayList<QnaVo> searchCompletedQuestionDesc(
			QnaSearch searchObject, int begin, int end) {
		return qnaDao.searchCompletedQuestionDesc(searchObject, begin, end);
	}
	
	@Override
	public ArrayList<QnaVo> searchCompletedQuestionAsc(
			QnaSearch searchObject, int begin, int end) {
		return qnaDao.searchCompletedQuestionAsc(searchObject, begin, end);
	}
	
	@Override
	public ArrayList<QnaVo> searchWaitingQuestionDesc(
			QnaSearch searchObject, int begin, int end) {
		return qnaDao.searchWaitingQuestionDesc(searchObject, begin, end);
	}
	
	@Override
	public ArrayList<QnaVo> searchWaitingQuestionAsc(
			QnaSearch searchObject, int begin, int end) {
		return qnaDao.searchWaitingQuestionAsc(searchObject, begin, end);
	}
	
//	QNA 목록 상세보기
	@Override
	public QnaVo selectQuestionOnelist(String qna_idx) {
		return qnaDao.selectQuestionOnelist(qna_idx);
	}

//	QNA 문의 작성하기
	@Override
	public int insertQuestion(QnaVo qna) {
		return qnaDao.insertQuestion(qna);
	}

//	QNA 문의 삭제하기
	@Override
	public int deleteQuestion(String qna_idx) {
		return qnaDao.deleteQuestion(qna_idx);
	}

//	체크된 QNA 문의 삭제하기 (관리자 전용) -> 보류
	@Override
	public int chkdeleteQuestion(String[] qna_idx) {return 0;}

//	QNA 문의글 답변 작성/수정하기
	@Override
	public QnaVo updateAnswer(QnaVo qna) {
		return qnaDao.updateAnswer(qna);
	}

//	QNA 문의글 답변 삭제하기
	@Override
	public int deleteAnswer(String qna_idx) {
		return qnaDao.deleteAnswer(qna_idx);
	}
}
