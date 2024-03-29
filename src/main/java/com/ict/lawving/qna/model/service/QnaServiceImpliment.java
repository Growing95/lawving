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
	
//	QNA 목록 상세보기 전 조회수 업데이트
	@Override
	public int updateQuestionHit(String qna_idx) {
		return qnaDao.updateQuestionHit(qna_idx);
	}
	
//	QNA 목록 상세보기
	@Override
	public QnaVo selectQuestionOnelist(String qna_idx) {
		return qnaDao.selectQuestionOnelist(qna_idx);
	}
	
//	QNA 이전 글 보기
	@Override
	public QnaVo selectQuestionBefore(String qna_idx) {
		return qnaDao.selectQuestionBefore(qna_idx);
	}
	
//	QNA 다음 글 보기
	@Override
	public QnaVo selectQuestionAfter(String qna_idx) {
		return qnaDao.selectQuestionAfter(qna_idx);
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

//	체크된 QNA 문의 삭제하기
	@Override
	public void chkdeleteQuestion(String chkdel) {
		qnaDao.chkdeleteQuestion(chkdel);
	}

//	QNA 문의글 답변 작성/수정하기
	@Override
	public int updateAnswer(QnaVo qna) {
		return qnaDao.updateAnswer(qna);
	}

//	QNA 문의글 답변 삭제하기
	@Override
	public int deleteAnswer(String qna_idx) {
		return qnaDao.deleteAnswer(qna_idx);
	}

	@Override
	public int selectQnaIdx(String qna_idx) {
		return qnaDao.selectQnaIdx(qna_idx);
	}

	@Override
	public int deleteAll(String members_idx) {
		return qnaDao.deleteAll(members_idx);
	}

}
