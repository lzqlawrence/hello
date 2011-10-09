package com.lsgw.ui;

import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lsgw.R;
import com.lsgw.bean.Book;
import com.lsgw.logic.IActivity;

public class ShoppingCartActivity extends Activity implements IActivity {

	private static final String TAG = "ShoppingCartActivity";
	private TextView tv;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(Object... param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.shoppingcart);
		tv = (TextView) findViewById(R.id.shoppingcart_tv);
//		tv.setText("hello shoppingcart");
		String urlStr = "http://192.168.1.61:8888/book1/bookext.do?method=getBookList";
		HttpClient client = new DefaultHttpClient();
		try {
			HttpPost request = new HttpPost(new URI(urlStr));
			HttpResponse response = client.execute(request);
			if (200 == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				String out = EntityUtils.toString(entity, "UTF-8");
				JSONArray jsonArr = new JSONObject(out).getJSONArray("Book");
				int length = jsonArr.length();
				ArrayList<Book> bookList = new ArrayList<Book>();
				for (int i = 0; i < length; i++) {
					JSONObject jsonObj = (JSONObject) jsonArr.get(i);
					Book book = new Book();
					book.setId(jsonObj.getInt("id"));
					book.setBookName(jsonObj.getString("bookName"));
					book.setAuthor(jsonObj.getString("author"));
					book.setTypeName(jsonObj.getString("typeName"));
					book.setBookTypeId(jsonObj.getInt("bookTypeId"));
					book.setBrief(jsonObj.getString("brief"));
					book.setPrice(Float.parseFloat(jsonObj.getString("price")));
					bookList.add(book);
				}
				Log.e(TAG, length + "-->");
				tv.setText(bookList.get(0).getBookName());
			} else {
				Log.e(TAG, "net work error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
