package com.mazouri.smartcode_android.thread_threadpool.loader;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mazouri.smartcode_android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoaderActivity extends AppCompatActivity {

    @BindView(R.id.loader_list)
    ListView mLoaderListView;

    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        ButterKnife.bind(this);

        initLoader();
    }

    private void initLoader() {
//      1.得到LoaderManager对象
        getSupportLoaderManager().initLoader(LOADER_ID, null, loaderCallbacks);

//        在Fragment中要使用下面的方法
//        getLoaderManager().initLoader(LOADER_ID, null, loaderCallbacks);
    }

    //2.覆写LoaderCallbacks相关方法
    private final LoaderCallbacks<Cursor> loaderCallbacks = new LoaderCallbacks<Cursor>() {

//      2.1创建一个新的CursorLoader对象，携带游标
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            //内容提供者的uri信息
            Uri uri = Uri.parse("content://com.baidu.provider/music");
            return new CursorLoader(LoaderActivity.this, uri, null, null, null, null);
        }

        //  2.2加载完成后，更新UI信息
        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            if(cursor == null){
                Toast.makeText(LoaderActivity.this, "失败", 1).show();
                return;
            }

            //  2.2.1往指定的集合中写入读取到的数据
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            while (cursor.moveToNext()) {
                Map<String, String> map = new HashMap<>();
                String id = cursor.getString(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String age = cursor.getString(cursor.getColumnIndex("age"));

                map.put("id", id);
                map.put("name", name);
                map.put("age", age);
                list.add(map);
            }

            //  2.2.2新建一个适配器对象,更新数据
            MyAdapter adapter = new MyAdapter();
            adapter.setList(list);
            mLoaderListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    //  3.自定义适配器类
    class MyAdapter extends BaseAdapter {
        private List<Map<String, String>> list;     //集合，存储数据
        public void setList(List<Map<String, String>> list) {
            this.list = list;
        }
        public int getCount() {
            return list.size();
        }
        public Object getItem(int arg0) {
            return list.get(arg0);
        }
        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        public View getView(int position, View view, ViewGroup group) {
            View v = null;
            if (view == null) {
                v = LayoutInflater.from(LoaderActivity.this).inflate(
                        R.layout.listitem_loader_activity, null);
            } else {
                v = view;
            }

            TextView t1 = (TextView) v.findViewById(R.id.t1);
            TextView t2 = (TextView) v.findViewById(R.id.t2);
            TextView t3 = (TextView) v.findViewById(R.id.t3);
            t1.setText(list.get(position).get("id"));
            t2.setText(list.get(position).get("name"));
            t3.setText(list.get(position).get("age"));
            return v;
        }

    }
    //  4.在用户点击向数据库写入数据按钮
    public void add(View view) {
        final View v = LayoutInflater.from(this).inflate(R.layout.dialogview_loader_activity, null);
        //配置对话框信息
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("添加信息")
                .setView(v)
                .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //  包含用户名 和 年龄
                        EditText et_name = (EditText) v.findViewById(R.id.name);
                        EditText et_age = (EditText) v.findViewById(R.id.age);
                        String name = et_name.getText().toString();
                        int age = Integer.parseInt(et_age.getText().toString());
                        Uri uri = Uri.parse("content://com.baidu.provider/music");
                        ContentValues values =new ContentValues();
                        values.put("name", name);
                        values.put("age", age);
                        //  根据用户填入的信息，使用内容提供者，提交数据
                        Uri u = getContentResolver().insert(uri, values);
                        if(u != null){
                            //  如果插入数据成功，则提醒loadermanager重新开始loader对象，根据指定的ID
                            LoaderActivity.this.getSupportLoaderManager().restartLoader(0, null, loaderCallbacks);
                            Toast.makeText(LoaderActivity.this, "插入成功", 1).show();
                        }else{
                            Toast.makeText(LoaderActivity.this, "插入失败", 1).show();
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}
