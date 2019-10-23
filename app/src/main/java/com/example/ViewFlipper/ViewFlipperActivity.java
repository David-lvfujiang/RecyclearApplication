package com.example.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ViewFlipper;

import com.example.recyclearapplication.R;

public class ViewFlipperActivity extends AppCompatActivity {
    private GestureDetector mDetector;
    private ViewFlipper mViewFlipper;
    private RadioGroup mRadiioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        initView();
        initData();

    }

    public void initView() {
        mViewFlipper = (ViewFlipper) findViewById(R.id.vflp_help);
        mRadiioGroup = findViewById(R.id.radio_group);
    }

    public void initData() {
        //创建手势识别对象，传入我们的回调监听对象
        mDetector = new GestureDetector(this, new MyGestureDetector());
        RadioButton radioButton = (RadioButton) mRadiioGroup.getChildAt(0);
        mRadiioGroup.setEnabled(false);
        radioButton.setChecked(true);
    }

    /**
     * 手势回调方法
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    /**
     * 手势监听类
     */
    class MyGestureDetector implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent m1, MotionEvent m2, float v, float v1) {
            //左滑
            if (m1.getX() > m2.getX()) {
                //左滑后下一张进来的动画
                mViewFlipper.setInAnimation(ViewFlipperActivity.this, R.anim.right_in);
                //左滑当前页面消息的动画
                mViewFlipper.setOutAnimation(ViewFlipperActivity.this, R.anim.right_out);
                //下一张
                mViewFlipper.showNext();
                //mViewFlipper.getDisplayedChild()获取当前展示的position
                RadioButton radioButton = (RadioButton) mRadiioGroup.getChildAt(mViewFlipper.getDisplayedChild());
                radioButton.setChecked(true);
                Log.e("TAG", "" + mViewFlipper.getDisplayedChild());
            }
            //右滑
            else if (m1.getX() < m2.getX()) {
                //右滑后下一张进来的动画
                mViewFlipper.setInAnimation(ViewFlipperActivity.this, R.anim.left_in);
                //右滑当前页面消息的动画
                mViewFlipper.setOutAnimation(ViewFlipperActivity.this, R.anim.left_out);
                //上一张
                mViewFlipper.showPrevious();
                RadioButton radioButton = (RadioButton) mRadiioGroup.getChildAt(mViewFlipper.getDisplayedChild());
                radioButton.setChecked(true);
                Log.e("TAG", "" + mViewFlipper.getDisplayedChild());

            }
            return false;
        }
    }
}
