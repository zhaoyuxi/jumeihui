package com.xixi.jimeihui.video;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.tamic.novate.BaseSubscriber;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.xixi.jimeihui.Constant;
import com.xixi.jimeihui.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Random;
import java.util.zip.CRC32;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class VideoActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private View rootView;
    private PlayerView player;
    private Intent data;
    private Toolbar toolbar;
    private SharedPreferences sp;
    private String json;
    private Novate novate;
    private String share_url;
    private String mainurl;
    private String video_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        toolbar = (Toolbar) findViewById(R.id.contentToolbar);
        toolbar.setTitle("聚美汇 - 图片内容");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        sp = getSharedPreferences("user_auth", Activity.MODE_PRIVATE);
        toolbar.setBackgroundColor(Color.parseColor(sp.getString("theme", "#3F51B5")));
        novate = new Novate.Builder(VideoActivity.this)
                .baseUrl("http://ib.365yg.com/")
                .build();

        data = getIntent();
        share_url = data.getStringExtra("share_url");
        video_title=data.getStringExtra("video_title");
        playVideo(share_url);
    }

    private void playVideo(final String url) {
        rootView = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
        setContentView(rootView);
        player = new PlayerView(this)
                .setTitle(video_title)
                .hideSteam(true)
                .setScaleType(PlayStateParams.f4_3)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(VideoActivity.this)
                                .load(url)
                                //.error(R.color.colorPrimary)?????
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(url)
                .startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
