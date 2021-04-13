package com.ict.lawving.delpost.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.delpost.model.dao.DelpostDao;
import com.ict.lawving.delpost.model.vo.DelpostVo;

@Service("delpostService")
public class DelpostServiceImpliment implements DelpostService{
	@Autowired
	private DelpostDao delpostDao;

	@Override
	public int getinsert(DelpostVo dvo) {
		return delpostDao.insertQuestion(dvo);
	}
	
	
}
