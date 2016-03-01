package cn.focus.es.index.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;


@Document(indexName="library", type="book", shards=9, replicas=1,refreshInterval="-1")
@Mapping(mappingPath="mapping/book.json")
public class BookIndex {
	
	private String id;
	
	private String author;
	
	private List<String> characters;
	
	private Long copies;
	
	private String otitle;
	
	private List<String> tags;
	
	private String title;
	
	private Long year;
	
	private Boolean available;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	

	public List<String> getCharacters() {
		return characters;
	}

	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}

	public Long getCopies() {
		return copies;
	}

	public void setCopies(Long copies) {
		this.copies = copies;
	}

	public String getOtitle() {
		return otitle;
	}

	public void setOtitle(String otitle) {
		this.otitle = otitle;
	}



	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
