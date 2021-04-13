package com.ict.lawving.limit.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.limit.model.dao.LimitDao;
import com.ict.lawving.limit.model.vo.LimitVo;

@Service("limitService")
public class LimitServiceImpliment implements LimitService{
	@Autowired
	private LimitDao limitDao;
	@Autowired
	private Paging paging;
	
	
	@Override
	public int insertLimitMember(int members_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int chkinsertLimitMember(int[] members_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLimitCountPlus(int members_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int chkUpdateLimitCountPlus(int[] members_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateLimitCountMinus(int members_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int chkUpdateLimitCountMinus(int members_idx) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCount() {
		return limitDao.getCount();
	}

	@Override
	public List<LimitVo> getList(int begin, int end) {
		return limitDao.getList(paging.getBegin(),paging.getEnd());
	}

	@Override
	public List<LimitVo> getBlackList(int begin, int end) {
		return limitDao.getBlackList(paging.getBegin(),paging.getEnd());
	}

	@Override
	public int getTotalBlackCount() {
		return limitDao.getBlackCount();
	}

	@Override
	public int searchMember(LimitVo lvo) {
		return limitDao.getsearch(lvo);
	}

	@Override
	public int updateinfo(LimitVo lvo) {
		return limitDao.getupdate(lvo);
	}

	@Override
	public int insertinfo(LimitVo lvo) {
		return limitDao.getinsert(lvo);
	}

	@Override
	public int chkcount(LimitVo lvo) {
		return limitDao.getchkcount(lvo);
	}

	


}
