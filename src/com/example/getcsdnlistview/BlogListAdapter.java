package com.example.getcsdnlistview;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BlogListAdapter extends BaseAdapter {

	private List<BlogListInfo> blogList;
	public Context context;

	public BlogListAdapter(Context context) {
		this.context = context;
		blogList = MainActivity.blogList;
	}

	@Override
	public int getCount() {
		return blogList.size();
	}

	@Override
	public Object getItem(int position) {
		return blogList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			// ������ͨ��LayoutInflater����ʵ����Ϊһ��view
			convertView = LayoutInflater.from(context).inflate(
					R.layout.list_view, null);

			holder.blogTitle = (TextView) convertView
					.findViewById(R.id.blog_Title);
			holder.blogSummary = (TextView) convertView
					.findViewById(R.id.blog_Summary);
			holder.blogRemark = (TextView) convertView
					.findViewById(R.id.blog_Remark);
			// ��holder�󶨵�convertView
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.blogTitle.setText(blogList.get(position).getBlogTitle());
		holder.blogSummary.setText(blogList.get(position).getBlogSummary());
		holder.blogRemark.setText("����ʱ�䣺"
				+ blogList.get(position).getBlogTime() + "  ���ۣ�"
				+ blogList.get(position).getBlogReply() + "  �����" + ""
				+ blogList.get(position).getBlogReadNum());
		return convertView;
	}

	/**
	 * ViewHolder�����Դ���item�пؼ�������
	 */
	final class ViewHolder {
		TextView blogTitle;
		TextView blogSummary;
		TextView blogRemark;
	}
}
