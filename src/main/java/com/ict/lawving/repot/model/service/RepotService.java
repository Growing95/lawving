package com.ict.lawving.repot.model.service;

import java.util.List;

import com.ict.lawving.repot.model.vo.RepotVo;

public interface RepotService {
	int getTotalCount();
	List<RepotVo> getList(int begin, int end);
	int insertrepot(String m_idx, String q_writer);
}
