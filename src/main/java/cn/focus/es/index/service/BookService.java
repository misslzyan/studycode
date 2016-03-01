package cn.focus.es.index.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;

import cn.focus.es.index.model.BookIndex;

@Service
public class BookService {
	
	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	public List<String> index(List<BookIndex> list){
		
		
		List<String> result = new ArrayList<String>();
		for(int i =0;i<list.size();i++){
			IndexQuery indexQuery = new IndexQuery();
			indexQuery.setId(list.get(i).getId());
			indexQuery.setObject(list.get(i));
			result.add(esTemplate.index(indexQuery));
		}
		return result;
		
	}
}
