package com.mazouri.smartcode_android.third_library.eventbus;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mazouri.smartcode_android.R;
import com.mazouri.smartcode_android.third_library.eventbus.dummy.DummyContent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity implements ItemListFragment.OnListFragmentInteractionListener
        , ItemDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDummyItemUpdate(DummyContent.DummyItem item) {
        if (item != null) {
            System.out.println(item.content);
            Toast.makeText(this, "item:" + item.content, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        System.out.println(item.details);

        ItemDetailFragment itemDetailFragment = (ItemDetailFragment) getSupportFragmentManager().findFragmentById(R.id.item_detail_container);
        itemDetailFragment.notifyItemChanged(item);
    }

    private OnListItemChangedListener mOnListItemChangedListener;

    public void registerListItemChangedListener(OnListItemChangedListener listener) {
        mOnListItemChangedListener = listener;
    }

    public void unRegisterListItemChangedListener(OnListItemChangedListener listener) {
        mOnListItemChangedListener = null;
    }

    public interface OnListItemChangedListener {
        void onListItemChanged(DummyContent.DummyItem item);
    }
}
