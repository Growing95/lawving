package com.ict.lawving.limit.model.service;

import java.util.ArrayList;

import com.ict.lawving.limit.model.vo.LimitVo;

public interface LimitService {
	ArrayList<LimitVo> selectLimitList();
	ArrayList<LimitVo> selectBlackList();
	int insertLimitMember(int members_idx);
	int chkinsertLimitMember(int[]members_idx);
	int updateLimitCountPlus(int members_idx);
	int chkUpdateLimitCountPlus(int[]members_idx);
	int updateLimitCountMinus(int members_idx);
	int chkUpdateLimitCountMinus(int members_idx);
	
	
}
