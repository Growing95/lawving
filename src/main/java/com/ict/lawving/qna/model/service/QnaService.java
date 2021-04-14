package com.ict.lawving.qna.model.service;

import java.util.ArrayList;

import com.ict.lawving.qna.model.vo.QnaSearch;
import com.ict.lawving.qna.model.vo.QnaVo;

public interface QnaService {
	int getTotalCount();
	ArrayList<QnaVo> selectQuestionList(int begin, int end);
	int getTotalCount(QnaSearch searchObject);
	ArrayList<QnaVo> searchAllQuestionDesc(
			QnaSearch searchObject, int begin, int end);
	ArrayList<QnaVo> searchAllQuestionAsc(
			QnaSearch searchObject, int begin, int end);
	ArrayList<QnaVo> searchCompletedQuestionDesc(
			QnaSearch searchObject, int begin, int end);
	ArrayList<QnaVo> searchCompletedQuestionAsc(
			QnaSearch searchObject, int begin, int end);
	ArrayList<QnaVo> searchWaitingQuestionDesc(
			QnaSearch searchObject, int begin, int end);
	ArrayList<QnaVo> searchWaitingQuestionAsc(
			QnaSearch searchObject, int begin, int end);
	int updateQuestionHit(String qna_idx);
	QnaVo selectQuestionOnelist(String qna_idx);
	QnaVo selectQuestionBefore(String qna_idx);
	QnaVo selectQuestionAfter(String qna_idx);
	int insertQuestion(QnaVo qna);
	int deleteQuestion(String qna_idx);
	void chkdeleteQuestion(String chkdel);
	int updateAnswer(QnaVo qna);
	int deleteAnswer(String qna_idx);
	int selectQnaIdx(String qna_idx);
}
