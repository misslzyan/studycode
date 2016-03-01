package cn.focus.es.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.focus.dc.crawler.cluster.utils.HttpParseUtils;
import cn.focus.dc.crawler.cluster.utils.ResponseUtil;
import cn.focus.es.analyzer.TestAnalyzer;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestAnalyzer testAnalyzer;
	
	private static Logger logger = Logger.getLogger(TestController.class);
	
	public TestController(){
		System.out.println("aaa");
	}
	
	/**
	 * 测试关键词提取
	 */
	@RequestMapping("word")
	public Map<String,Object> word(){
		String url = "http://home.focus.cn/news/2016-01-26/10673735_0.html";
		String title = "《我是歌手》郑有智拜布丁机器人为师";
		String content = "";
		content = HttpParseUtils.getHtml(url);
 		Document document = Jsoup.parse(content);
 		content = document.text();
		String result = testAnalyzer.analyze(title, content);
		logger.info(result);
		return ResponseUtil.ok();
	}
}
