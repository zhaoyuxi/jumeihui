package com.xixi.jimeihui.image;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xixi.jimeihui.R;
import com.xixi.jimeihui.bean.Follow;
import com.xixi.jimeihui.definition.Consts;
import com.xixi.jimeihui.image.framework.RecommendElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class WaterFallAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<PictureCard> mData; //定义数据源
    private int screenWidth;
    private int gridNumber;
    //定义构造方法，默认传入上下文和数据源
    public WaterFallAdapter(int gridNumber, Context context, List<PictureCard> data) {
        this.gridNumber = gridNumber;
        mContext = context;
        mData = data;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();;
        screenWidth = dm.widthPixels;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == Consts.followColumnIndex) {
            return RecommendElement.Follows.getType();
        } else if (position == Consts.recommendColumnIndex) {
            return RecommendElement.Column.getType();
        } else {
            return RecommendElement.PictureWaterFall.getType();
        }
    }

    @Override  //将ItemView渲染进来，创建ViewHolder
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.image_fragment_page_item, null);
            return new ImageViewHolder(view);
       }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
              ImageViewHolder imageHolder = (ImageViewHolder) holder;
            PictureCard personCard = mData.get(position);
            Uri uri = Uri.parse(personCard.avatarUrl);
            imageHolder.userAvatar.setImageURI(uri);
            //ViewGroup.LayoutParams layout = holder2.userAvatar.getLayoutParams();
            //layout.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            //layout.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            //layout.height = personCard.imgHeight;
            //holder2.userAvatar.setMaxWidth(screenWidth);
            //holder2.userAvatar.setMaxHeight(screenWidth*5);
            imageHolder.userName.setText(personCard.name);
    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size() ;
        }
        return 0;
    }

    //定义自己的ViewHolder，将View的控件引用在成员变量上
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public ScaleImageView userAvatar;
        public TextView userName;
        public int imageViewWidth;

        public ImageViewHolder(View itemView) {
            super(itemView);
            userAvatar = (ScaleImageView) itemView.findViewById(R.id.user_avatar);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            imageViewWidth = userAvatar.getWidth();
        }
    }
    public class FollowViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView mRecyclerView;
        private FollowAdapter mAdapter;
        public FollowViewHolder(View itemView, ViewGroup parent, List<PictureCard> cards) {
            super(itemView);
            //LayoutInflater localInflater =(LayoutInflater)itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mRecyclerView = (RecyclerView)itemView;
        }
    }
}
