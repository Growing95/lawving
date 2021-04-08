package com.ict.lawving.bookmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.lawving.bookmark.model.service.BookmarkService;

@Controller
public class BookmarkController {

	@Autowired
	private BookmarkService bookmarkService;
	
	
	
}
