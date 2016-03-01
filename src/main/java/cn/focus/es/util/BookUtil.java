package cn.focus.es.util;

import java.util.ArrayList;
import java.util.List;

import cn.focus.es.index.model.BookIndex;

public class BookUtil {

	public static List<BookIndex> createList() {
		List<BookIndex> list = new ArrayList<BookIndex>();
		List<String> characters = new ArrayList<String>();
		BookIndex book = new BookIndex();
		book.setId(1+"");
		book.setTitle("All Quiet on the Western Front");
		book.setOtitle("Im Westen nichts Neues");
		book.setAuthor("Erich Maria Remarque");
		book.setYear(1929L);
		characters.add("Paul Baumer");
		characters.add("Albert Kropp");
		characters.add("Haie Westhus");
		characters.add("Frerich Muller");
		characters.add("Stanislaus Katczinsky");
		characters.add("Tjaden");
		book.setCharacters(characters);
		List<String> tags = new ArrayList<String>();
		tags.add("novel");
		book.setTags(tags);
		book.setCopies(1L);
		book.setAvailable(true);
		list.add(book);
		return list;
	}
}
