package com.ict.lawving.repot.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.repot.model.dao.RepotDao;
import com.ict.lawving.repot.model.vo.RepotVo;

@Service
public class RepotServiceImpliment implements RepotService{
	
	@Autowired
	private RepotDao repotDao;
	@Autowired
	private Paging paging;
	
	
	
	@Override
	public int getTotalCount() {
		return repotDao.getCount();
	}

	@Override
	public List<RepotVo> getList(int begin, int end) {
		return repotDao.getList(paging.getBegin(),paging.getEnd());
	}
	/*
	 * @Override public int insertrepot(String qna_idx, String members_idx_2, String
	 * members_idx) { return repotDao.getinsert(qna_idx, members_idx_2,
	 * members_idx); }
	 */

	@Override
	public int insertrepot(RepotVo rvo) {
		return repotDao.getinsert(rvo);
	}

	@Override
	public int getdelete(String qna_idx) {
		return repotDao.getdelete(qna_idx);
	}

	@Override
	public int chkRepot(RepotVo repotVo) {
		return repotDao.chkRepot(repotVo);
	}

	@Override
	public int deleteAll(String members_idx) {
		return repotDao.delteAll(members_idx);
	}
}
