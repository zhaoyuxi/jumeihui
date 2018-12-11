package com.xixi.jimeihui.home;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.xixi.comm.basic.TemplateType;
import com.xixi.comm.basic.Works;
import com.xixi.comm.home.ArticleWorks;
import com.xixi.jimeihui.Interface.OnItemClickListener;
import com.xixi.jimeihui.MainActivity;
import com.xixi.jimeihui.R;
import com.xixi.jimeihui.extra.ImgLoader;
import com.xixi.jimeihui.video.VideoActivity;
import com.xixi.jimeihui.video.ViewPagerLayoutManagerActivity;

import java.util.List;

public class HomeTabFragmentContentAdapter extends RecyclerView.Adapter {
    private List<ArticleWorks> lists;
    private OnItemClickListener onItemClickListener;
    private Context context;
    private AppCompatActivity activity;


    public HomeTabFragmentContentAdapter(List<ArticleWorks> lists, Context context, AppCompatActivity activity) {
        this.lists = lists;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        ArticleWorks works = lists.get(position);
        return TemplateType.toTemplateType(works.getTemplateType()).getIndex();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (TemplateType.toTemplateType(viewType)) {
            case FullWords: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_fullwords, parent, false);
                return new FullWordsView(view, onItemClickListener);
            }
            case WordsLeftImageRight: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_wordsleft_imageright, parent, false);
                return new WordsLeftImageRightView(view, onItemClickListener);
            }
            case WordsTop1ImageBelow: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_wordstop_1imagebelow, parent, false);
                return new WordsTop1ImageBelowView(view, onItemClickListener);
            }
            case WordsTop3ImageBelow: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_wordstop_3imagebelow, parent, false);
                return new WordsTop3ImageBelowView(view, onItemClickListener);
            }
            case Video: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_video, parent, false);
                return new MyVideoView(view, onItemClickListener);
            }
            case Unknown:
            default:
                // TODO Logo
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Works works = lists.get(position);
        WorksView worksView = (WorksView) holder;
        worksView.setTitle(works.getTitle());
        worksView.setFooterSource(works.getFooterText());

        switch (TemplateType.toTemplateType(((ArticleWorks) works).getTemplateType())) {
            case FullWords: {
                //FullWordsView vh = (FullWordsView) holder;
                break;
            }
            case WordsLeftImageRight: {
                WordsLeftImageRightView vh = (WordsLeftImageRightView) holder;
                ArticleWorks articleWorks = (ArticleWorks) works;
                new ImgLoader(context).disPlayimg(articleWorks.getImage1URL(), vh.imageView);
                break;
            }
            case WordsTop1ImageBelow: {
                WordsTop1ImageBelowView vh = (WordsTop1ImageBelowView) holder;
                ArticleWorks articleWorks = (ArticleWorks) works;
                new ImgLoader(context).disPlayimg(articleWorks.getImage1URL(), vh.imageView);
                break;
            }
            case WordsTop3ImageBelow: {
                WordsTop3ImageBelowView vh = (WordsTop3ImageBelowView) holder;
                ArticleWorks articleWorks = (ArticleWorks) works;
                new ImgLoader(context).disPlayimg(articleWorks.getImage1URL(), vh.imageView1);
                new ImgLoader(context).disPlayimg(articleWorks.getImage2URL(), vh.imageView2);
                new ImgLoader(context).disPlayimg(articleWorks.getImage3URL(), vh.imageView3);
                break;
            }
            case Video: {
                worksView.setFooterSource("SHIPIN " + works.getFooterText());
                final MyVideoView vh = (MyVideoView) holder;
                ArticleWorks articleWorks = (ArticleWorks) works;
                vh.initVideoView(articleWorks);
                //new ImgLoader(context).disPlayimg(articleWorks.getImage1URL(), vh.thumbnail);
                break;
            }
            case Unknown:
            default:
                // TODO Logo
                break;
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void add(List<ArticleWorks> newlist) {
        if (newlist !=null && !newlist.isEmpty()) {
            lists.addAll(0, newlist);
        }
    }

    //点击接口
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    //作品的模板
    class WorksView extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View worksView;
        private Works works;

        public void setTitle(String title) {
            this.title.setText(title);
        }

        public void setFooterSource(String footerSource) {
            this.footerSource.setText(footerSource);
        }

        private TextView title;
        private TextView footerSource;

        private OnItemClickListener onItemClickListener = null;

        public WorksView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            worksView = itemView;
            title = (TextView) worksView.findViewById(R.id.title);
            footerSource = (TextView) worksView.findViewById(R.id.footer_text);


            //点击事件
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //((MainActivity) activity).startVideoActivity();
            if (onItemClickListener != null) {
                onItemClickListener.onClick(v, getLayoutPosition());
            }
        }

        public Works getWorks() {
            return works;
        }

        public void setWorks(Works works) {
            this.works = works;
        }
    }

    //没有图片的模板
    class FullWordsView extends WorksView {
        public FullWordsView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
        }
    }

    class WordsLeftImageRightView extends WorksView {
        private ImageView imageView;

        public WordsLeftImageRightView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    class WordsTop1ImageBelowView extends WorksView {
        private ImageView imageView;

        public WordsTop1ImageBelowView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    class WordsTop3ImageBelowView extends WorksView {
        private ImageView imageView1;
        private ImageView imageView2;
        private ImageView imageView3;

        public WordsTop3ImageBelowView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            imageView1 = (ImageView) itemView.findViewById(R.id.image1);
            imageView2 = (ImageView) itemView.findViewById(R.id.image2);
            imageView3 = (ImageView) itemView.findViewById(R.id.image3);
        }
    }

    class MyVideoView extends WorksView {
        private ImageView thumbnail;
        private final VideoView videoView;
        private final  int[] imgs = {R.mipmap.img_video_1,R.mipmap.img_video_2};
        private final int[] videos = {R.raw.video_1,R.raw.video_2};
        private View itemView;
        private int position;

        public MyVideoView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView, onItemClickListener);
            this.itemView = itemView;
            videoView = (VideoView) itemView.findViewById(R.id.video);
            //video.setback
        }

        @Override
        public void onClick(View v) {
            //onItemClickListener.onClick(v, getLayoutPosition());
            //((MainActivity) activity).startVideoActivity();
        }

        private void initVideoView(ArticleWorks articleWorks) {
            videoView.setVideoURI(Uri.parse("android.resource://"+itemView.getContext().getPackageName()+"/"+ videos[position%2]));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            videoView.setLayoutParams(layoutParams);
            //设置视频控制器
            videoView.setMediaController(new MediaController(activity));

            //设置点击事件，OnClickListener不好用
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //Toast.makeText(application, "" + vh.videoView.isPlaying(), Toast.LENGTH_SHORT).show();
                    if (videoView.isPlaying()) {
                        videoView.pause();
                    } else {
                        videoView.start();
                    }
                    return false;
                }
            });

            //设置循环播放
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                    mp.setLooping(true);
                }
            });
        }
    }
}
