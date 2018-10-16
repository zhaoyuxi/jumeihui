package com.xixi.jimeihui.image;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xixi.jimeihui.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImagePageFragmentPage extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WaterFallAdapter mAdapter;
    private List<PictureCard> imageUrls;
    private final int gridNumber = 2;

    public static ImagePageFragmentPage newInstance() {
        ImagePageFragmentPage fragment = new ImagePageFragmentPage();
        return fragment;
    }


    private void initView(View view) {
        imageUrls = queryMediaImages();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        //设置布局管理器为2列，纵向
        mLayoutManager = new StaggeredGridLayoutManager(gridNumber, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new WaterFallAdapter(gridNumber, getContext(), imageUrls);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.image_fragment_page, container, false);
        initView(view);
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
