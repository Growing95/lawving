package com.ict.lawving.qna.model.service;

import java.util.ArrayList;

import com.ict.lawving.qna.model.vo.QnaSearch;
import com.ict.lawving.qna.model.vo.QnaVo;

public interface QnaService {
	public abstract int getTotalCount();
	public abstract ArrayList<QnaVo> selectQuestionList(int begin, int end);
	public abstract int getTotalCount(QnaSearch searchObject);
	public abstract ArrayList<QnaVo> searchAllQuestionDesc(
			QnaSearch searchObject, int begin, int end);
	public abstract ArrayList<QnaVo> searchAllQuestionAsc(
			QnaSearch searchObject, int begin, int end);
	public abstract ArrayList<QnaVo> searchCompletedQuestionDesc(
			QnaSearch searchObject, int begin, int end);
	public abstract ArrayList<QnaVo> searchCompletedQuestionAsc(
			QnaSearch searchObject, int begin, int end);
	public abstract ArrayList<QnaVo> searchWaitingQuestionDesc(
			QnaSearch searchObject, int begin, int end);
	public abstract ArrayList<QnaVo> searchWaitingQuestionAsc(
			QnaSearch searchObject, int begin, int end);
	public abstract int updateQuestionHit(String qna_idx);
	public abstract QnaVo selectQuestionOnelist(String qna_idx);
	public abstract QnaVo selectQuestionBefore(String qna_idx);
	public abstract QnaVo selectQuestionAfter(String qna_idx);
	public abstract int insertQuestion(QnaVo qna);
	public abstract int deleteQuestion(String qna_idx);
	public abstract int chkdeleteQuestion(String[] qna_idx);
	public abstract int updateAnswer(QnaVo qna);
	public abstract int deleteAnswer(String qna_idx);
	public abstract int selectQnaIdx(String qna_idx);
}
