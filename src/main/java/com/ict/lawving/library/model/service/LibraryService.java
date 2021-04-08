package com.ict.lawving.library.model.service;

import java.util.List;

import com.ict.lawving.library.model.vo.LibraryVo;


public interface LibraryService {
	int getTotalCount();
	List<LibraryVo> getList(int begin, int end);
}
