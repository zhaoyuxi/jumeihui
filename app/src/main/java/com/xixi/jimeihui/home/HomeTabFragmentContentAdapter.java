package com.xixi.jimeihui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xixi.comm.basic.TemplateType;
import com.xixi.comm.basic.Works;
import com.xixi.comm.home.ArticleWorks;
import com.xixi.jimeihui.Interface.OnItemClickListener;
import com.xixi.jimeihui.R;
import com.xixi.jimeihui.extra.ImgLoader;

import java.util.Collections;
import java.util.List;

public class HomeTabFragmentContentAdapter extends RecyclerView.Adapter {
    private List<ArticleWorks> lists;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public HomeTabFragmentContentAdapter(List<ArticleWorks> lists, Context context) {
        this.lists = lists;
        this.context = context;
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
        worksView.setFooterSource(works.getAuthorName());
        worksView.setFooterCommentsCounter(works.getCommentsCounter());
        worksView.setFooterPublishTime(works.getStrPublishTimeStamp());

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
        lists.addAll(0, newlist);
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

        public void setFooterCommentsCounter(String footerCommentsCounter) {
            this.footerCommentsCounter.setText(footerCommentsCounter);
        }

        public void setFooterPublishTime(String footerPublishTime) {
            this.footerPublishTime.setText(footerPublishTime);
        }

        private TextView title;
        private TextView footerSource;
        private TextView footerCommentsCounter;
        private TextView footerPublishTime;

        private OnItemClickListener onItemClickListener = null;

        public WorksView(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            worksView = itemView;
            title = (TextView) worksView.findViewById(R.id.title);
            footerSource = (TextView) worksView.findViewById(R.id.footer_src);
            footerCommentsCounter = (TextView) worksView.findViewById(R.id.footer_comments_count);
            footerPublishTime = (TextView) worksView.findViewById(R.id.footer_publish_time);

            //点击事件
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
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
}
