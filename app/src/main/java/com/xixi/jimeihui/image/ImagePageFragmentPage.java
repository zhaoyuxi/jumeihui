package com.xixi.jimeihui.image;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xixi.jimeihui.R;
import com.xixi.jimeihui.bean.Follow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImagePageFragmentPage extends Fragment {
    private View view;
    private ViewGroup mContainer;
    private RecyclerView mImageRecyclerView;
    private RecyclerView.LayoutManager mImageLayoutManager;
    private WaterFallAdapter mImageAdapter;
    private List<PictureCard> imageUrls;
    private final int imageGridNumber = 2;

    private RecyclerView mFollowRecyclerView;
    private RecyclerView.LayoutManager mFollowLayoutManager;
    private FollowAdapter mFollowAdapter;
    private List<Follow> mFollowsData;

    public static ImagePageFragmentPage newInstance(ViewGroup container) {
        ImagePageFragmentPage fragment = new ImagePageFragmentPage();
        fragment.mContainer = container;
        return fragment;
    }


    private void initImageView(View view) {
        mImageRecyclerView = (RecyclerView) view.findViewById(R.id.pic_rv);
        //设置布局管理器为2列，纵向
        mImageLayoutManager = new StaggeredGridLayoutManager(imageGridNumber, StaggeredGridLayoutManager.VERTICAL);
        /*
        mImageLayoutManager = new GridLayoutManager(getContext(), imageGridNumber, GridLayoutManager.VERTICAL, false);
        ((GridLayoutManager) mImageLayoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return imageGridNumber;
                }
                return 1;
            }
        });*/
        mImageRecyclerView.setLayoutManager(mImageLayoutManager);
        //mImageRecyclerView.setNestedScrollingEnabled(false);
        mImageAdapter = new WaterFallAdapter(imageGridNumber, mImageRecyclerView, mContainer, getContext(), imageUrls);
        mImageRecyclerView.setAdapter(mImageAdapter);
        mImageAdapter.notifyDataSetChanged();
    }
    private void initFollowView(View view) {
        View followView = view.inflate(view.getContext(), R.layout.follow_list, null);
        mFollowRecyclerView = (RecyclerView) followView.findViewById(R.id.followers);
        mFollowLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) mFollowLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        mFollowsData = new LinkedList<Follow>();
        for (PictureCard card :  imageUrls) {
            Follow follow = new Follow();
            follow.setHeadImage(card.getAvatarUrl());
            follow.setName(card.getName());
            mFollowsData.add(follow);
            if (mFollowsData.size() > 10) {
                break;
            }
        }
        mFollowAdapter = new FollowAdapter(getContext(), mFollowsData);
        mFollowRecyclerView.setLayoutManager(mFollowLayoutManager);
        mFollowRecyclerView.setAdapter(mFollowAdapter);
        mFollowRecyclerView.setNestedScrollingEnabled(false);
        mFollowAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.image_fragment_page, container, false);
        imageUrls = queryMediaImages();
        //initFollowView(view);
        initImageView(view);
        return view;
    }
    public List<PictureCard> queryMediaImages() {
        ArrayList<PictureCard> imageUrls = new ArrayList<PictureCard>();
        Cursor c = getActivity().getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null );
        if ( c != null ) {
            int index = 0;
            if (c.moveToFirst()) {
                do {
                    long id = c.getLong( c.getColumnIndex( MediaStore.Images.Media._ID ) );
                    Uri imageUri = Uri.parse( MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + id );
                    PictureCard card = new PictureCard();
                    card.setAvatarUrl(imageUri.toString());
                    card.setName("还在追梦的老男人");
                    //card.imgHeight = 200;
                    imageUrls.add(card);
                    //imageUrls.add(getRealFilePath(MainActivity.this, imageUri));
                } while (c.moveToNext());
            }
        }
        c.close();
        c = null;
        return imageUrls;
    }
}
