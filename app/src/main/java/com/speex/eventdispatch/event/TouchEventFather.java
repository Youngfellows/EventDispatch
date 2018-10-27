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

public class TouchEventFather extends LinearLayout {
    public TouchEventFather(Context context) {
        super(context);
    }

    public TouchEventFather(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventFather(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 事件分发
     *
     * @param ev
     * @return
     */
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("sunzn", "TouchEventFather | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));

        //case 3/4
        return super.dispatchTouchEvent(ev);//表示对事件进行分发，并向下传递给自身的 onInterceptTouchEvent 方法,询问是否事件拦截

        //case 1
        /**
         * 该控件的 dispatchTouchEvent 返回 false，
         *1.表示对获取到的事件停止向下传递
         *2.同时也不对该事件进行消费。
         *3.由于 TouchEventFather 获取的事件直接来自 TouchEventActivity ，则会将事件返回给 TouchEventActivity  的 onTouchEvent 进行消费，最后直接由 TouchEventActivity 来响应手指按下、移动和抬起事件。
         */
//        return false;//拦截事件，停止事件向下传递。将事件返回父控件的onTouchEvent进行处理

        //case 2
        /**
         * 1.事件首先由 TouchEventActivity 的 dispatchTouchEvent 方法分发给 TouchEventFather 控件的 dispatchTouchEvent.
         * 2.而该控件的 dispatchTouchEvent 返回 true
         * 3.表示分发事件到 TouchEventFather 控件并由该控件的 dispatchTouchEvent 进行消费；
         * 4.TouchEventActivity 不断的分发事件到 TouchEventFather 控件的 dispatchTouchEvent，
         *   而 TouchEventFather 控件的 dispatchTouchEvent 也不断的将获取到的事件进行消费。
         */
//        return true;//在dispatchTouchEvent中消费掉父控件分发的事件
    }

    /**
     * 事件拦截
     *
     * @param ev
     * @return
     */
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("sunzn", "TouchEventFather | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        //case 1/2/
//        return super.onInterceptTouchEvent(ev);//默认不拦截事件

        //case 3
        /**
         * 1.事件首先由 TouchEventActivity 的 dispatchTouchEvent 方法分发给 TouchEventFather 控件的 dispatchTouchEvent，
         * 2.而该控件的 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev)，
         * 3.表示对事件进行分发并向下传递给 TouchEventFather 控件的 onInterceptTouchEvent 方法，
         * 4. onInterceptTouchEvent 方法返回 true 表示对所获取到的事件进行拦截并将事件传递给 TouchEventFather 控件的 onTouchEvent 进行处理，
         * 5.TouchEventFather 控件的 onTouchEvent 返回 super.onTouchEvent(ev) 表示对事件没有做任何处理直接将事件返回给上级控件，
         * 6.由于 TouchEventFather 获取的事件直接来自 TouchEventActivity，所以 TouchEventFather 控件的 onTouchEvent 会将事件以冒泡方式直接返回给 TouchEventActivity 的 onTouchEvent 进行消费，
         * 7.后续的事件则会跳过 TouchEventFather 直接由 TouchEventActivity 的 onTouchEvent 消费来自 TouchEventActivity 自身分发的事件。
         */
//        return true;//返回 true 表示对所获取到的事件进行拦截并将事件传递给自身的 onTouchEvent 进行处理

        //case 4/5
        /**
         * 1.事件首先由 TouchEventActivity 的 dispatchTouchEvent 方法分发给 TouchEventFather 控件的 dispatchTouchEvent，
         * 2.而该控件的 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev)，
         * 3.表示对事件进行分发并向下传递给 TouchEventFather 控件的 onInterceptTouchEvent 方法，
         * 4.onInterceptTouchEvent 该方法返回 false 表示事件会被放行并传递到子控件 TouchEventChilds 的 dispatchTouchEvent 方法，
         * 5.同样 TouchEventChilds 的 dispatchTouchEvent 返回 super.dispatchTouchEvent(ev)，
         * 6.表示对事件进行分发并向下传递给 TouchEventChilds 控件的 onInterceptTouchEvent 方法，
         * 7.TouchEventChilds 的 onInterceptTouchEvent 方法返回 super.onInterceptTouchEvent(ev)
         * 8.默认会将事件传递给 TouchEventChilds 的 onTouchEvent 进行处理，
         * 9.TouchEventChilds 的 onTouchEvent 返回 super.onTouchEvent(ev)
         * 10.表示对事件没有做任何处理直接将事件返回给上级控件，
         * 11.由于 TouchEventChilds 获取的事件直接来自 TouchEventFather，
         * 12.所以 TouchEventChilds 控件的 onTouchEvent 会将事件以冒泡方式直接返回给 TouchEventFather 的 onTouchEvent 进行消费，
         * 13.而 TouchEventFather 的 onTouchEvent 也返回了 super.onTouchEvent(ev)，
         * 14.同样 TouchEventFather 的 onTouchEvent 也会将事件返回给上级控件，
         * 15.而 TouchEventFather 获取的事件直接来自 TouchEventActivity，所以 TouchEventFather 控件的 onTouchEvent 会将事件以冒泡方式直接返回给 TouchEventActivity 的 onTouchEvent 进行消费，
         * 16.后续的事件则会跳过 TouchEventFather 和 TouchEventChilds 直接由 TouchEventActivity 的 onTouchEvent 消费来自 TouchEventActivity 自身分发的事件。
         */
        return false;//返回 false 表示事件会被放行并传递到子控件 TouchEventChilds 的 dispatchTouchEvent 方法
    }

    /**
     * 事件消费
     *
     * @param ev
     * @return
     */
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("sunzn", "TouchEventFather | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        //case 1/2/3/4
        return super.onTouchEvent(ev);// onTouchEvent 返回 super.onTouchEvent(ev) 表示对事件没有做任何处理直接将事件返回给上级控件
    }
}
