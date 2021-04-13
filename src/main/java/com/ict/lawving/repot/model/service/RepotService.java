package com.ict.lawving.repot.model.service;

import java.util.List;

import com.ict.lawving.repot.model.vo.RepotVo;

public interface RepotService {
	int getTotalCount();
	List<RepotVo> getList(int begin, int end);
	int insertrepot(RepotVo rvo);
	int getdelete(String qna_idx);
}
