package com.example.getcsdnlistview;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public BlogListAdapter mAdapter;
	private ListView mListView;
	// ������Ϣ�б�
	public static List<BlogListInfo> blogList;
	final String CSDNURL = "http://www.cnblogs.com/yc-755909659/";
	final String REGEX = "class=\"postTitle2\" href=\"(.*?)\">(.*?)</a>.*?ժҪ:(.*?)<a.*?posted @(.*?)J��ҶС�� �Ķ�(.*?) ����(.*?)<a";
	Handler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler = getHandler();
		ThreadStart();
	}

	/**
	 * �¿����̴߳�����������
	 * 
	 */
	private void ThreadStart() {
		new Thread() {
			public void run() {
				Message msg = new Message();
				try {
					blogList = DMethod.getBlogNetDate(CSDNURL, REGEX);
					msg.what = blogList.size();
				} catch (Exception e) {
					e.printStackTrace();
					msg.what = -1;
				}
				handler.sendMessage(msg);
			}
		}.start();
	}

	/**
	 * ���������������ʾ��listview
	 * 
	 */
	private Handler getHandler() {
		return new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what < 0) {
					Toast.makeText(MainActivity.this, "���ݻ�ȡʧ��",
							Toast.LENGTH_SHORT).show();
				} else {
					initListview();
				}
			}
		};
	}

	/**
	 * ��listview����ʾ����
	 */
	private void initListview() {
		// ��XML�е�ListView����ΪItem������
		mListView = (ListView) findViewById(R.id.list);
		mAdapter = new BlogListAdapter(MainActivity.this);
		mListView.setAdapter(mAdapter);
	}
}
