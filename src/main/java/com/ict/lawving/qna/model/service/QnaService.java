package com.ict.lawving.qna.model.service;

import java.util.ArrayList;

import com.ict.lawving.qna.model.vo.QnaVo;

public interface QnaService {
	public abstract ArrayList<QnaVo> selectQuestionList();
	public abstract ArrayList<QnaVo> searchQuestion(String keyword, String category);
	public abstract QnaVo selectQuestionOnelist(String qna_idx);
	public abstract int insertQuestion(QnaVo qna);
	public abstract int deleteQuestion(String qna_idx);
	public abstract int chkdeleteQuestion(String[] qna_idx);
	public abstract QnaVo updateAnswer(QnaVo qna);
	public abstract int deleteAnswer(String qna_idx);
}
