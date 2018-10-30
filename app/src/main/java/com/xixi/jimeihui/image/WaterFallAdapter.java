package com.xixi.jimeihui.image;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
    private RecyclerView mView;
    private Context mContext;
    private List<PictureCard> mData; //定义数据源
    private int screenWidth;
    private int gridNumber;

    private RecyclerView mFollowRecyclerView;
    private RecyclerView.LayoutManager mFollowLayoutManager;
    private FollowAdapter mFollowAdapter;
    private List<Follow> mFollowsData;
    private ViewGroup mContainer;
    //定义构造方法，默认传入上下文和数据源
    public WaterFallAdapter(int gridNumber, RecyclerView view, ViewGroup container, Context context, List<PictureCard> data) {
        this.gridNumber = gridNumber;
        mContainer = container;
        mView = view;
        mContext = context;
        mData = data;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();;
        screenWidth = dm.widthPixels;
        initFollowView(mView);
    }

    private void initFollowView(RecyclerView view) {
        View followView = LayoutInflater.from(mContext).inflate(R.layout.follow_list, view, false);
        //View followView = view.inflate(view.getContext(), R.layout.follow_list, null);
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) followView.getLayoutParams();
        params.setFullSpan(true);
        followView.setLayoutParams(params);

        mFollowRecyclerView = (RecyclerView) followView.findViewById(R.id.followers);
        mFollowLayoutManager = new LinearLayoutManager(view.getContext());
        ((LinearLayoutManager) mFollowLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        mFollowsData = new LinkedList<Follow>();
        for (PictureCard card :  mData) {
            Follow follow = new Follow();
            follow.setHeadImage(card.getAvatarUrl());
            follow.setName(card.getName());
            mFollowsData.add(follow);
            if (mFollowsData.size() > 10) {
                break;
            }
        }
        mFollowAdapter = new FollowAdapter(view.getContext(), mFollowsData);
        mFollowRecyclerView.setLayoutManager(mFollowLayoutManager);
        mFollowRecyclerView.setAdapter(mFollowAdapter);
        mFollowRecyclerView.setNestedScrollingEnabled(false);
        mFollowAdapter.notifyDataSetChanged();
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
        if (viewType == RecommendElement.Follows.getType()) {
            //StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) mFollowRecyclerView.getLayoutParams();
            //params.setFullSpan(true);
            //mFollowRecyclerView.setLayoutParams(params);
            return new FollowViewHolder(mFollowRecyclerView);
        } else {
            //root应该填空，不然每个网格都是固定高度
            //View view = LayoutInflater.from(mContext).inflate(R.layout.image_fragment_page_item, parent, false);
            View view = LayoutInflater.from(mContext).inflate(R.layout.image_fragment_page_item, null);
            return new ImageViewHolder(view);
        }
    }

    @Override  //将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Consts.followColumnIndex:
                break;
            default:
                ImageViewHolder imageHolder = (ImageViewHolder) holder;
                PictureCard personCard = mData.get(position);
                Uri uri = Uri.parse(personCard.getAvatarUrl());
                imageHolder.userAvatar.setImageURI(uri);
                //ViewGroup.LayoutParams layout = holder2.userAvatar.getLayoutParams();
                //layout.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                //layout.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                //layout.height = personCard.imgHeight;
                //holder2.userAvatar.setMaxWidth(screenWidth);
                //holder2.userAvatar.setMaxHeight(screenWidth*5);
                imageHolder.userName.setText(personCard.getName());
                break;
        }
    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size() + 1;
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
        public FollowViewHolder(View itemView) {
            super(itemView);
            //LayoutInflater localInflater =(LayoutInflater)itemView.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mRecyclerView = (RecyclerView)itemView;
        }
    }
}
