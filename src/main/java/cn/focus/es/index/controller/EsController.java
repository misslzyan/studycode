package cn.focus.es.index.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.focus.es.index.model.BookIndex;
import cn.focus.es.index.service.BookService;
import cn.focus.es.util.BookUtil;
import cn.focus.es.util.ResponseUtil;

@Controller
@RequestMapping("es")
public class EsController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping("test")
	public Map<String,Object> test(){
		List<BookIndex> list = BookUtil.createList();
		bookService.index(list);
		return ResponseUtil.ok();
	}
}
