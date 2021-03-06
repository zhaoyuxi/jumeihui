package com.xixi.jimeihui.image;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xixi.jimeihui.R;

/**
 * This view will auto determine the width or height by determining if the
 * height or width is set and scale the other dimension depending on the images dimension
 * <p>
 * This view also contains an ImageChangeListener which calls changed(boolean isEmpty) once a
 * change has been made to the ImageView
 *
 * @author Maurycy Wojtowicz
 */
//public class ScaleImageView extends AppCompatImageView {
public class ScaleImageView extends SimpleDraweeView {
    private ImageChangeListener imageChangeListener;
    private boolean scaleToWidth = false; // this flag determines if should measure height manually dependent of width
    private int srcWidth;
    private int srcHeight;
    private double scale;

    public ScaleImageView(Context context) {
        super(context);
        init(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.setScaleType(ScaleType.CENTER_INSIDE);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        if (imageChangeListener != null)
            imageChangeListener.changed((bm == null));
    }

    @Override
    public void setImageDrawable(Drawable d) {
        super.setImageDrawable(d);
        if (imageChangeListener != null)
            imageChangeListener.changed((d == null));
    }

    @Override
    public void setImageResource(int id) {
        super.setImageResource(id);
    }

    public interface ImageChangeListener {
        // a callback for when a change has been made to this imageView
        void changed(boolean isEmpty);
    }

    public ImageChangeListener getImageChangeListener() {
        return imageChangeListener;
    }

    public void setImageChangeListener(ImageChangeListener imageChangeListener) {
        this.imageChangeListener = imageChangeListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        /**
         * if both width and height are set scale width first. modify in future if necessary
         */

        if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
            scaleToWidth = true;
        } else if (heightMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.AT_MOST) {
            scaleToWidth = false;
        } else
            throw new IllegalStateException("width or height needs to be set to match_parent or a specific dimension");

        if (getDrawable() == null || getDrawable().getIntrinsicWidth() == 0) {
            // nothing to measure
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        } else {
            if (scaleToWidth) {
                int iw = this.getDrawable().getIntrinsicWidth();
                int ih = this.getDrawable().getIntrinsicHeight();
                int heightC = width * ih / iw;
                if (srcHeight != 0 && srcWidth != 0) {
                    heightC = width * srcHeight / srcWidth;
                }
                if (height > 0)
                    if (heightC > height) {
                        // dont let hegiht be greater then set max
                        heightC = height;
                        width = heightC * iw / ih;
                    }

                this.setScaleType(ScaleType.CENTER_CROP);
                setMeasuredDimension(width, heightC);

            } else {
                // need to scale to height instead
                int marg = 0;
                if (getParent() != null) {
                    if (getParent().getParent() != null) {
                        marg += ((RelativeLayout) getParent().getParent()).getPaddingTop();
                        marg += ((RelativeLayout) getParent().getParent()).getPaddingBottom();
                    }
                }

                int iw = this.getDrawable().getIntrinsicWidth();
                int ih = this.getDrawable().getIntrinsicHeight();

                width = height * srcWidth / srcHeight;
                height -= marg;
                setMeasuredDimension(width, height);
            }

        }
    }
    @Override
    public void setImageURI(Uri uri) {
        BitmapFactory.Options options = new BitmapFactory.Options();

        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        String filePath = getFilePath(getContext(), uri);
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options); // 此时返回的bitmap为null
        srcHeight = options.outHeight;
        srcWidth = options.outWidth;
        scale = srcHeight / srcWidth;
        super.setImageURI(uri);
    }

    public static String getFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;

        final String scheme = uri.getScheme();
        String data = null;

        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
