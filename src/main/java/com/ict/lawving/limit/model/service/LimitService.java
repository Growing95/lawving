package com.ict.lawving.limit.model.service;

import java.util.List;

import com.ict.lawving.limit.model.vo.LimitVo;
import com.ict.lawving.members.model.vo.MembersVo;

public interface LimitService {
	int insertLimitMember(int members_idx);
	int chkinsertLimitMember(int[]members_idx);
	int updateLimitCountPlus(int members_idx);
	int chkUpdateLimitCountPlus(int[]members_idx);
	int updateLimitCountMinus(int members_idx);
	int chkUpdateLimitCountMinus(int members_idx);
	int getTotalCount();
	List<LimitVo> getList(int begin, int end);
	
	
}
