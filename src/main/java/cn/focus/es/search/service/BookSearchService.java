package cn.focus.es.search.service;

import java.util.List;

import org.elasticsearch.index.query.CommonTermsQueryBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.RangeFilterBuilder;
import org.elasticsearch.index.query.SimpleQueryStringBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import cn.focus.es.index.model.BookIndex;

@Service
public class BookSearchService {
	
	@Autowired
	private ElasticsearchTemplate esTemplate;
	
	/**
	 * 查询title字段含有front
	 */
	public void search1(){
		TermQueryBuilder tqb = QueryBuilders.termQuery("title", "front");
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withQuery(tqb);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		List<BookIndex> content = page.getContent();
		for(BookIndex book : content){
			System.out.println(book.getTitle());
		}
	}
	
	/**
	 * 分页和结果集大小
	 */
	public void search2(){
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		
		searchQuery.withPageable(new PageRequest(0, 2));
		
		QueryStringQueryBuilder qsqb = QueryBuilders.queryString("title:front");
		searchQuery.withQuery(qsqb);
		
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex temp: page.getContent()){
			System.out.println(temp.getTitle());
		}
		
		
	}
	
	/**
	 * 选择需要返回的字段
	 */
	public void search3(){
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withPageable(new PageRequest(0, 2));
		searchQuery.withFields("title","author");
		QueryStringQueryBuilder qsqb = QueryBuilders.queryString("title:front");
		searchQuery.withQuery(qsqb);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex temp:page.getContent()){
			System.out.println(temp.getTitle());
		}
		
	}
	
	/**
	 * 
	 *Description:测试filter查询
	 *time:2016-1-26下午2:57:54
	 */
	public void search4(){
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		QueryBuilder matchQuery = QueryBuilders.matchPhrasePrefixQuery("title", "front");
		
		RangeFilterBuilder filterBuilder = FilterBuilders.rangeFilter("year");
		filterBuilder.lt(2000);
		FilteredQueryBuilder fqb = QueryBuilders.filteredQuery(matchQuery, filterBuilder);
		searchQuery.withQuery(fqb);
		searchQuery.withFilter(filterBuilder);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}

	public void term(String value) {
		//单词条查询
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", value);
		termQueryBuilder.boost(2.0f);
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		searchQuery.withQuery(termQueryBuilder);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
		//多词条查询
		NativeSearchQueryBuilder searchQuery2 = new NativeSearchQueryBuilder();
		TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("title", "front","haia");
		termsQueryBuilder.boost(10.0f);
		termsQueryBuilder.minimumMatch(1);
		searchQuery2.withQuery(termsQueryBuilder);
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		page = esTemplate.queryForPage(searchQuery2.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
		
	}

	public void matchall() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		MatchAllQueryBuilder matchAll = QueryBuilders.matchAllQuery();
		matchAll.boost(10f);
		searchQuery.withQuery(matchAll);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}

	public void common() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		CommonTermsQueryBuilder common = QueryBuilders.commonTerms("title", "western front");
		common.cutoffFrequency(0.001f);
		searchQuery.withQuery(common);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
		
	}

	public void match() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		MatchQueryBuilder match = QueryBuilders.matchQuery("title", "western and front");
		searchQuery.withQuery(match);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}

	public void matchPhrase() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		//短语匹配查询，查找包含短语 the  front 并且中间最多可以略过一个词的短语
		MatchQueryBuilder matchPhrase = QueryBuilders.matchPhraseQuery("title", "the  front");
		//表示短语之间可以略过词的数量（当前为1）
		matchPhrase.slop(1);
		searchQuery.withQuery(matchPhrase);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}

	public void matchPhrasePrefix() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		MatchQueryBuilder matchQuery = QueryBuilders.matchPhrasePrefixQuery("title", "the fron");
		matchQuery.slop(1);
		searchQuery.withQuery(matchQuery);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}

	public void mutiMatch() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		MultiMatchQueryBuilder mq = QueryBuilders.multiMatchQuery("front","title","tag");
		searchQuery.withQuery(mq);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
		
	}

	/**
	 * 
	  * @author weidongduan
	  * @Description:query_string
	  * @Date 2016-2-23下午4:19:02
	 */
	public void queryString() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		QueryStringQueryBuilder queryString = QueryBuilders.queryString("western front");
		searchQuery.withQuery(queryString);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}

	public void simpleQueryString() {
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		SimpleQueryStringBuilder sqsb = QueryBuilders.simpleQueryString("front");
		searchQuery.withQuery(sqsb);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}
	
	public void ids(){
		IdsQueryBuilder ids = QueryBuilders.idsQuery("book");
		ids.addIds("1");
		NativeSearchQueryBuilder searchQuery = new NativeSearchQueryBuilder();
		searchQuery.withIndices("library");
		searchQuery.withTypes("book");
		searchQuery.withQuery(ids);
		Page<BookIndex> page = esTemplate.queryForPage(searchQuery.build(), BookIndex.class);
		for(BookIndex book : page.getContent()){
			System.out.println(book.getTitle());
		}
	}
	
	
}
