package com.xixi.jimeihui.image;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
    private RecyclerView mImageRecyclerView;
    private RecyclerView.LayoutManager mImageLayoutManager;
    private WaterFallAdapter mImageAdapter;
    private List<PictureCard> imageUrls;
    private final int imageGridNumber = 2;

    private RecyclerView mFollowRecyclerView;
    private RecyclerView.LayoutManager mFollowLayoutManager;
    private FollowAdapter mFollowAdapter;
    private List<Follow> mFollowsData;

    public static ImagePageFragmentPage newInstance() {
        ImagePageFragmentPage fragment = new ImagePageFragmentPage();
        return fragment;
    }


    private void initImageView(View view) {
        mImageRecyclerView = (RecyclerView) view.findViewById(R.id.pic_rv);
        //设置布局管理器为2列，纵向
        mImageLayoutManager = new StaggeredGridLayoutManager(imageGridNumber, StaggeredGridLayoutManager.VERTICAL);
        mImageAdapter = new WaterFallAdapter(imageGridNumber, getContext(), imageUrls);
        mImageRecyclerView.setLayoutManager(mImageLayoutManager);
        mImageRecyclerView.setAdapter(mImageAdapter);
        mImageAdapter.notifyDataSetChanged();
    }
    private void initFollowView(View view) {
        mFollowRecyclerView = (RecyclerView) view.findViewById(R.id.follow_rv);
        mFollowLayoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) mFollowLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        mFollowsData = new LinkedList<Follow>();
        for (PictureCard card :  imageUrls) {
            Follow follow = new Follow();
            follow.setHeadImage(card.avatarUrl);
            follow.setName(card.name);
            mFollowsData.add(follow);
            if (mFollowsData.size() > 10) {
                break;
            }
        }
        mFollowAdapter = new FollowAdapter(getContext(), mFollowsData);
        mFollowRecyclerView.setLayoutManager(mFollowLayoutManager);
        mFollowRecyclerView.setAdapter(mFollowAdapter);
        mFollowAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.image_fragment_page, container, false);
        imageUrls = queryMediaImages();
        initFollowView(view);
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
                    card.avatarUrl = imageUri.toString();
                    card.name = "冰冰";
                    card.imgHeight = 200;
                    card.imgHeight = (index++ % 2)*100 + 400; //偶数和奇数的图片设置不同的高度，以到达错开的目的
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
