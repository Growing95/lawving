package com.ict.lawving.library.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.lawving.common.Paging;
import com.ict.lawving.library.model.dao.LibraryDao;
import com.ict.lawving.library.model.vo.LibrarySearch;
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

	@Override
	public int getTotalCount(LibrarySearch searchObject) {
		return libraryDao.getCount(searchObject);
	}

	@Override
	public ArrayList<LibraryVo> selectSearchTitleDesc(LibrarySearch searchObject, int begin, int end) {
		return libraryDao.selectSearchTitleDesc(searchObject, begin, end);
	}

	@Override
	public ArrayList<LibraryVo> selectSearchTitleAsc(LibrarySearch searchObject, int begin, int end) {
		return libraryDao.selectSearchTitleAsc(searchObject, begin, end);
	}

	@Override
	public ArrayList<LibraryVo> selectSearchContentDesc(LibrarySearch searchObject, int begin, int end) {
		return libraryDao.selectSearchContentDesc(searchObject, begin, end);
	}
	@Override
	public ArrayList<LibraryVo> selectSearchContentAsc(LibrarySearch searchObject, int begin, int end) {
		return libraryDao.selectSearchContentAsc(searchObject, begin, end);
	}

	@Override
	public LibraryVo selectOneList(int library_idx) {
		return libraryDao.selectOneList(library_idx);
	}

	@Override
	public int insertLibrary(LibraryVo library) {
		int result = libraryDao.insertlibrary(library);

		return result;
	}

	@Override
	public int updatelibrary(LibraryVo library) {
		int result = libraryDao.updatelibrary(library);

		return result;
	}

	@Override
	public void chkdelete(String chkdel) {
		libraryDao.chklistdelete(chkdel);
	}

	@Override
	public int deletelibrary(int library_idx) {
		int result = libraryDao.deletelibrary(library_idx);
		return result;
	}
	
	@Override
	public LibraryVo selectlibraryAfter(int library_idx) {
		return libraryDao.selectlibraryAfter(library_idx);
	}

	@Override
	public LibraryVo selectlibraryBefore(int library_idx) {
		return libraryDao.selectlibraryBefore(library_idx);
	}
	
}
