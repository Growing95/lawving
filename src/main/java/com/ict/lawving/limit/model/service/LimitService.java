package com.ict.lawving.limit.model.service;

import java.util.List;

import com.ict.lawving.limit.model.vo.LimitVo;

public interface LimitService {
	int insertLimitMember(int members_idx);
	int chkinsertLimitMember(int[]members_idx);
	int updateLimitCountPlus(int members_idx);
	int chkUpdateLimitCountPlus(int[]members_idx);
	int updateLimitCountMinus(int members_idx);
	int chkUpdateLimitCountMinus(int members_idx);
	int getTotalCount();
	int getTotalBlackCount();
	List<LimitVo> getList(int begin, int end);
	List<LimitVo> getBlackList(int begin, int end);
	int searchMember(LimitVo lvo);
	int updateinfo(LimitVo lvo);
	int insertinfo(LimitVo lvo);
	int chkcount(LimitVo lvo);
	void chkblackdelete(String string);
	int deleteOneLimit(String id);

	
	
}
