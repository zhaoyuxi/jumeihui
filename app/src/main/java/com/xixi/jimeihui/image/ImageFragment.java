package com.xixi.jimeihui.image;


import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.huewu.pla.lib.MultiColumnListView;
import com.xixi.jimeihui.R;
import com.xixi.jimeihui.image.loader.ImageLoader;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment {

    private PLAAdapter mAdapter;
    private ArrayList<String> imageUrls;
    public ImageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        MultiColumnListView listView = (MultiColumnListView) view.findViewById(R.id.image_list);
        imageUrls = queryMediaImages();
        mAdapter = new PLAAdapter(getActivity(), imageUrls);
        mAdapter.addAll(imageUrls);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }


    public ArrayList<String> queryMediaImages() {
        ArrayList<String> imageUrls = new ArrayList<String>();
        Cursor c = getActivity().getContentResolver().query( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null );
        if ( c != null ) {
            if (c.moveToFirst()) {
                do {
                    long id = c.getLong( c.getColumnIndex( MediaStore.Images.Media._ID ) );
                    Uri imageUri = Uri.parse( MediaStore.Images.Media.EXTERNAL_CONTENT_URI + "/" + id );
                    imageUrls.add(imageUri.toString());
                    //imageUrls.add(getRealFilePath(MainActivity.this, imageUri));
                } while (c.moveToNext());
            }
        }
        c.close();
        c = null;
        return imageUrls;
    }
    private static class PLAAdapter extends ArrayAdapter<String> {
        private LayoutInflater mLayoutInflater;
        private ImageLoader mLoader;
        private ArrayList<String> mImageList;
        public PLAAdapter(Context context, ArrayList<String> list) {
            super(context, R.layout.image_item_fragment, android.R.id.text1);
            mLayoutInflater = LayoutInflater.from(context);
            mImageList = list;
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            int width = metrics.widthPixels;
            mLoader = new ImageLoader(context);
            mLoader.setRequiredSize(width / 3);
            mLoader.setIsUseMediaStoreThumbnails(false);
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.image_item_fragment,
                        null);
                holder = new ViewHolder();
                holder.imageView = (ScaleImageView) convertView .findViewById(R.id.image_GridView);
                convertView.setTag(holder);
            }
            holder = (ViewHolder) convertView.getTag();

            mLoader.DisplayImage(mImageList.get(position), holder.imageView);
            return convertView;
        }
    }
    static class ViewHolder {
        ScaleImageView imageView;
    }
}
