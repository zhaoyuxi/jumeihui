package com.xixi.jimeihui.image;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xixi.jimeihui.R;
import com.xixi.jimeihui.bean.Follow;
import com.xixi.jimeihui.definition.Consts;

import java.util.List;

public class FollowAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Follow> mData; //定义数据源
    //定义构造方法，默认传入上下文和数据源
    public FollowAdapter(Context context, List<Follow> data) {
        mContext = context;
        mData = data;
    }

    @Override  //将ItemView渲染进来，创建ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.follow_list_item, null);
        return new FollowViewHolder(view);
    }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FollowViewHolder hld = (FollowViewHolder) holder;
        Follow item = mData.get(position);
        hld.head.setImageURI(item.getHeadImage());
        hld.userName.setText(item.getDisplayName());
    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    //定义自己的ViewHolder，将View的控件引用在成员变量上
    public class FollowViewHolder extends RecyclerView.ViewHolder {
        public ScaleImageView head;
        public TextView userName;
        public FollowViewHolder(View itemView) {
            super(itemView);
            head = (ScaleImageView)itemView.findViewById(R.id.follow_head_image);
            userName = (TextView)itemView.findViewById(R.id.follow_name);
        }
    }
}
