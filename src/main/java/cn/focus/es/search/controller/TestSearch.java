package cn.focus.es.search.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.focus.es.search.service.BookSearchService;
import cn.focus.es.util.ResponseUtil;

@Controller
@RequestMapping("search")
public class TestSearch {

	@Autowired
	private BookSearchService bookSearchService;
	
	@RequestMapping("test1")
	public Map<String,Object> test1(){
		bookSearchService.search1();
		return ResponseUtil.ok();
	}
	
	@RequestMapping("test2")
	public Map<String,Object> test2(){
		bookSearchService.search2();
		return ResponseUtil.ok();
	}
	
	@RequestMapping("test3")
	public Map<String,Object> test3(){
		bookSearchService.search3();
		return ResponseUtil.ok();
	}
	
	@RequestMapping("test4")
	public Map<String,Object> test4(){
		bookSearchService.search4();
		return ResponseUtil.ok();
	}
	
	
	/**
	 * term（词条查询）
	 */
	@RequestMapping("term")
	public Map<String,Object> term(){
		String str = "front";
		bookSearchService.term(str);
		return ResponseUtil.ok();
	}
	
	/**
	 * match_all 查询
	 */
	@RequestMapping("matchall")
	public Map<String,Object> matchall(){
		bookSearchService.matchall();
		return ResponseUtil.ok();
	}
	
	/**
	 * common(常用词查询)
	 * 
	 */
	@RequestMapping("common")
	public Map<String,Object> common(){
		bookSearchService.common();
		return ResponseUtil.ok();
	}
	
	/**
	 * match
	 */
	@RequestMapping("match")
	public Map<String,Object> match(){
		bookSearchService.match();
		return ResponseUtil.ok();
	}
	
	/**
	 * match_phrase
	 */
	@RequestMapping("match_phrase")
	public Map<String,Object> matchPhrase(){
		bookSearchService.matchPhrase();
		return ResponseUtil.ok();
	}
	
	/**
	 * 
	  * @author weidongduan
	  * @Description:match_phrase_prefix (类似与match_phrase，不同在于，允许查询短语的最后一个词做前缀匹配)
	  * @Date 2016-2-19下午1:43:17
	 */
	@RequestMapping("match_phrase_prefix")
	public Map<String,Object> matchPhrasePrefix(){
		bookSearchService.matchPhrasePrefix();
		return ResponseUtil.ok();
	}
	
	/**
	 * multi_match (匹配多个字段)
	 */
	@RequestMapping("multi_match")
	public Map<String,Object> mutiMatch(){
		bookSearchService.mutiMatch();
		return ResponseUtil.ok();
	}
	
	
	/**
	 * 
	  * @author weidongduan
	  * @Description:query_string
	  * @Date 2016-2-23下午4:10:14
	 */
	@RequestMapping("query_string")
	public Map<String,Object> queryString(){
		bookSearchService.queryString();
		return ResponseUtil.ok();
	}
	
	
	/**
	 * 
	  * @author weidongduan
	  * @Description:simple_query_string
	  * @Date 2016-2-23下午4:39:04
	 */
	@RequestMapping("simple_query_string")
	public Map<String,Object> simpleQueryString(){
		bookSearchService.simpleQueryString();
		return ResponseUtil.ok();
	}
	
	/**
	 * 
	  * @author weidongduan
	  * @Description:ids
	  * @Date 2016-2-23下午6:07:17
	 */
	@RequestMapping("ids")
	public Map<String,Object> ids(){
		bookSearchService.simpleQueryString();
		return ResponseUtil.ok();
	}
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(){

			@Override
			public void run() {
				while(true){
					System.out.println("a");
				}
			}
			
		};
//		t.setDaemon(true);
		t.start();
		Thread.sleep(1000);
		System.out.println("main over");
	}
}
