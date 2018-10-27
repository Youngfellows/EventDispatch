package com.speex.eventdispatch.event;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Byron on 2018/10/27.
 */

public class TouchEventChilds extends LinearLayout {
    public TouchEventChilds(Context context) {
        super(context);
    }

    public TouchEventChilds(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventChilds(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("sunzn", "TouchEventChilds | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));

        //case 1/2/3/4
        // TouchEventChilds 的 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev)，表示对事件进行分发并向下传递给 TouchEventChilds 控件的 onInterceptTouchEvent 方法，
        //return super.dispatchTouchEvent(ev);

        //case 5
        /**
         * 1.事件首先由 TouchEventActivity 的 dispatchTouchEvent 方法分发给 TouchEventFather 控件的 dispatchTouchEvent，
         * 2.该控件的 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev)，
         * 3.事件会分发到 TouchEventFather 的 onInterceptTouchEvent，
         * 4.onInterceptTouchEvent 返回 false 表示放行当先事件；
         * 5.事件会被传递到子控件 TouchEventChilds 的 dispatchTouchEvent 方法，
         * 6.dispatchTouchEvent 返回 true 表示事件被分发到 TouchEventChilds 控件并由该控件的 dispatchTouchEvent 方法消费。
         * 7.后续的事件也会不断的重复上面的逻辑最终被 TouchEventChilds 的 dispatchTouchEvent 消费。
         */
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("sunzn", "TouchEventChilds | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));

        // //case 1/2/3/4/5
        //TouchEventChilds 的 onInterceptTouchEvent 方法返回 super.onInterceptTouchEvent(ev) 默认会将事件传递给 TouchEventChilds 的 onTouchEvent 进行处理
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("sunzn", "TouchEventChilds | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        //case 1/2/3/4/5
        //TouchEventChilds 的 onTouchEvent 返回 super.onTouchEvent(ev) 表示对事件没有做任何处理直接将事件返回给上级控件
        return super.onTouchEvent(ev);
    }

}
