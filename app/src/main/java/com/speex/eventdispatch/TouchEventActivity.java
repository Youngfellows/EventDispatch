package com.speex.eventdispatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.speex.eventdispatch.event.TouchEventUtil;

public class TouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.w("sunzn", "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.w("sunzn", "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
        //TouchEventActivity 的 onTouchEvent 进行消费，后续的事件则会跳过 TouchEventFather 和 TouchEventChilds 直接由 TouchEventActivity 的 onTouchEvent 消费来自 TouchEventActivity 自身分发的事件
        return super.onTouchEvent(event);
    }
}
