package com.xixi.jimeihui.home;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xixi.client.ResultNotification;
import com.xixi.comm.home.ArticleWorks;
import com.xixi.jimeihui.Interface.OnItemClickListener;
import com.xixi.jimeihui.PathRandom;
import com.xixi.jimeihui.R;
import com.xixi.jimeihui.allfragment.RecycleViewDivider;
import com.xixi.tester.TesterClient;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTabFragment extends Fragment {


    public static final String ARGS_PAGE = "args_page";
    private TextView tv;
    private static String PATH = new PathRandom().getHomePath();

    private List<ArticleWorks> newList;
    private HomeTabFragmentContentAdapter adapter;
    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;

    public static HomeTabFragment newInstance() {
        HomeTabFragment fragment = new HomeTabFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_page, container, false);
        initView();
        return view;
    }

    public void initView() {
        newList = new ArrayList<ArticleWorks>();
        adapter = new HomeTabFragmentContentAdapter(newList, getContext());
        rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.addItemDecoration(new RecycleViewDivider(
                this.getContext(), LinearLayoutManager.VERTICAL, R.dimen.height_divider, getResources().getColor(R.color.divider)));
        adapter.add(newList);
        //设置点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Intent i = new Intent(getContext(), ArticleContentShow.class);
                //i.putExtra("share_url", "http://www.toutiao.com" + newList.get(position).getGroup_id());
                //startActivity(i);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        swipeRefreshLayout.setRefreshing(true);
        createThread();
        seeRefresh();
    }

    //设置刷新
    public void seeRefresh() {
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimaryDark);
        //监听刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PATH = new PathRandom().getHomePath();
                createThread();
            }
        });
    }


    //创建进程
    public void createThread() {
        new Thread() {
            public void run() {
                postJson();
            }
        }.start();
    }

    //获取数据
    private void postJson() {
        TesterClient client = new TesterClient();
        client.Request("", "", new ResultNotification() {
            @Override
            public void Notify(com.xixi.client.Response resp) {
                Message msg = new Message();
                msg.obj = resp.getBody();
                handler.sendMessage(msg);
            }
        });
    }

    //数据处理
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String str = msg.obj + "";
            List<ArticleWorks> works = JSON.parseObject(str, new TypeReference<ArrayList<ArticleWorks>>() {
            });
            adapter.add(works);
            swipeRefreshLayout.setRefreshing(false);
            adapter.notifyDataSetChanged();
        }
    };
}
