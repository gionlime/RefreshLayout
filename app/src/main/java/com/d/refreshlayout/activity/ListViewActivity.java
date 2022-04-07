package com.d.refreshlayout.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;

import com.d.refreshlayout.R;
import com.d.refreshlayout.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ListViewActivity
 * Created by D on 2018/5/31.
 */
public class ListViewActivity extends AppCompatActivity {
    private ListView lvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        lvList = (ListView) findViewById(R.id.lv_list);
        init();
    }

    private void init() {
        lvList.setAdapter(new ListViewAdapter(this, getDatas(), R.layout.adapter_item));
    }

    @NonNull
    private List<String> getDatas() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("" + i);
        }
        return datas;
    }
}
