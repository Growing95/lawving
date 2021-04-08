package com.ict.lawving.library.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.library.model.dao.LibraryDao;
import com.ict.lawving.library.model.vo.LibraryVo;

@Service("libraryService")
public class LibraryServiceImpliment implements LibraryService{
	@Autowired
	private LibraryDao libraryDao;
	@Autowired
	private Paging paging;
	@Override
	public int getTotalCount() {
		return libraryDao.getCount();
	}

	@Override
	public List<LibraryVo> getList(int begin, int end) {
		return libraryDao.getLibraryList(paging.getBegin(),paging.getEnd());
	}
	
}
