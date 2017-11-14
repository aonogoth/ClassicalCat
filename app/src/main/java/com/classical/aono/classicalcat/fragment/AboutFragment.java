package com.classical.aono.classicalcat.fragment;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.activity.BookDetailActivity;
import com.classical.aono.classicalcat.adapter.WorkAdapter;
import com.classical.aono.classicalcat.common.ThreadPool;
import com.classical.aono.classicalcat.domain.ResultOut;
import com.classical.aono.classicalcat.domain.Work;
import com.classical.aono.classicalcat.http.AboutSupplier;
import com.classical.aono.classicalcat.http.WorkDetailSupplier;
import com.classical.aono.classicalcat.http.WorksSupplier;
import com.classical.aono.classicalcat.widget.RecyclerItemClickListener;
import com.google.android.agera.BaseObservable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Supplier;
import com.google.android.agera.Updatable;

import java.util.List;

/**
 * Created by gotha on 2017/10/11.
 */

public class AboutFragment extends Fragment {

    private TextView mAboutView1;
    private TextView mAboutView2;

    private TextView mAboutFeedbackView;
    private Button mAboutFeedbackButton;

    private AboutSupplier aboutSupplier;
    //private Repository<Result<ResultOut>> sendfbRepository;
    //private SearchObservable searchObservable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, null);

        mAboutView1 = (TextView) view.findViewById(R.id.tv_card_about_1_1);
        mAboutView2 = (TextView) view.findViewById(R.id.tv_about_version);

        mAboutView2.setText("版本："+getLocalVersionName(getActivity()));

        mAboutFeedbackView = (TextView) view.findViewById(R.id.tv_feedback_content);
        mAboutFeedbackButton = (Button) view.findViewById(R.id.bt_submit_feedback);
        mAboutFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(networkTask).start();
            }
        });

        return view;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            if(msg.obj == "true")
//            {
                mAboutFeedbackView.setText("");
                Toast.makeText(getContext(),"您的建议已经提交...感谢！",Toast.LENGTH_LONG).show();
           // }
            super.handleMessage(msg);

        }

    };
    Runnable networkTask = new Runnable() {
        @Override
        public void run() {
            // TODO
            Message msg = new Message();
            aboutSupplier = new AboutSupplier(mAboutFeedbackView.getText().toString());
            ResultOut ro = aboutSupplier.get().get();
            msg.obj = ro.getSuccess().toString();
            mHandler.sendMessage(msg);
        }
    };

//    @Override
//    public void update() {
//        if (sendfbRepository.get().isPresent()) {
//            ResultOut ro = sendfbRepository.get().get();
//            Toast.makeText(getContext(),ro.getSuccess(),Toast.LENGTH_SHORT).show();
//        }
//    }
//    private void setUpRepository(String content) {
//        searchObservable = new AboutFragment.SearchObservable();
//        aboutSupplier = new AboutSupplier(content);
//        sendfbRepository = Repositories
//                .repositoryWithInitialValue(Result.<ResultOut>absent())
//                .observe(searchObservable)
//                .onUpdatesPerLoop()
//                .goTo(ThreadPool.executor)
//                .thenGetFrom(aboutSupplier)
//                .compile();
//    }
//
//    public class SearchObservable extends BaseObservable {
//    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
