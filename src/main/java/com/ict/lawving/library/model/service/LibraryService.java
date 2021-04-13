package com.ict.lawving.library.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ict.lawving.library.model.vo.LibrarySearch;
import com.ict.lawving.library.model.vo.LibraryVo;


public interface LibraryService {
	int getTotalCount();
	int getTotalCount(LibrarySearch searchObject);
	List<LibraryVo> getList(int begin, int end);
	LibraryVo selectOneList(int library_idx);
	int insertLibrary(LibraryVo library);
	ArrayList<LibraryVo> selectSearchTitleDesc(LibrarySearch searchObject, int begin, int end);
	ArrayList<LibraryVo> selectSearchTitleAsc(LibrarySearch searchObject, int begin, int end);
	ArrayList<LibraryVo> selectSearchContentDesc(LibrarySearch searchObject, int begin, int end);
	ArrayList<LibraryVo> selectSearchContentAsc(LibrarySearch searchObject, int begin, int end);
	int updatelibrary(LibraryVo library);
	void chkdelete(String chkdel);
	int deletelibrary(int library_idx);
	int selectlibraryBefore(String library_idx);
	int selectlibraryAfter(String library_idx);

}
