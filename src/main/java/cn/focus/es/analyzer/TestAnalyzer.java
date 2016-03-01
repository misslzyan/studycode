package cn.focus.es.analyzer;

import java.util.List;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 测试分词
 * @author weidongduan
 *
 */
@Service
public class TestAnalyzer {

	@Autowired
	private  KeyWordComputer keyWordComputer;
	
	
	public  String analyze(String title,String content){
		List<Keyword> list = keyWordComputer.computeArticleTfidf(title, content);
		StringBuffer sb = new StringBuffer();
		for(Keyword keyword : list){
			sb.append(keyword.getName());
			sb.append(" ");
		}
		return sb.toString();
	}
}
