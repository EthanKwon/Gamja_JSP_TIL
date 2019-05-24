package json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonBook {
	public static void main(String[] args) {
		System.out.println("-----------------------------------");
		List<BookDTO>bList = new ArrayList<BookDTO>();
		bList.add(new BookDTO("전쟁과 평화", "톨스토이",20000,"소설","톨스토이 출판사"));
		bList.add(new BookDTO("홍길동전", "허균",30000,"소설","허균 출판사"));
		bList.add(new BookDTO("레미제라블","빅토르 위고",10000,"소설","위고 출판사"));
		
		System.out.println("JSON String 생성");
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		for(BookDTO bDto: bList) {
			JSONObject book = new JSONObject();
			book.put("name", bDto.getName());
			book.put("writer",bDto.getWriter());
			book.put("price", bDto.getPrice());
			book.put("genre",bDto.getGenre());
			book.put("publisher", bDto.getPublisher());
			jArray.add(book);
			System.out.println(bDto.toString());
		}
		jObj.put("BookList", jArray);
		System.out.println(jObj.toString());
		
		System.out.println("------------------------------------");
		System.out.println("JSON String 파싱");
		
		try {
			JSONArray bookList = (JSONArray) jObj.get("BookList");
			System.out.println("Size =" + bookList.size());
			for(int i = 0; i<bookList.size();i++) {
				JSONObject book = (JSONObject) bookList.get(i);
				BookDTO newBook = new BookDTO();
				newBook.setName((String)book.get("name"));
				newBook.setWriter((String)book.get("writer"));
				newBook.setPrice((Integer)book.get("price"));
				newBook.setGenre((String)book.get("genre"));
				newBook.setPublisher((String)book.get("publisher"));
				
				System.out.println(newBook.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------------------------------");
		
		
	}
}
