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

}
