package com.image.mymemorandum.home.puzzle_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.image.mymemorandum.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 123 on 2017/12/20.
 */

public class PintuView extends RelativeLayout implements View.OnClickListener {


    private int mColum = 3;
    /**
     * 图片的内边距
     */
    private int mPadding;
    /**
     * 每个图片的间距(dp)
     */
    private int mMargin = 3;

    private ImageView[] mGamePintuItem;

    private int mItemWidth;

    /**
     * 游戏图片
     */
    private Bitmap mBitmap;

    private boolean once;

    private List<ImagePice> mItemBitmap;

    private Context context;
    /**
     * 游戏界面宽度
     */
    private int mViewWidth;

    private boolean isGameSuccess = false;

    /**
     * 回调接口,下一关，时间，游戏结束
     */
    public interface GamePintuListener {
        void nextLevel(int next);

        void timechanger(int currentTime);

        void gameOver();
    }

    public GamePintuListener pintuListener;

    public void getPintuListener(GamePintuListener listener) {
        this.pintuListener = listener;
    }

    public void setPintuListener(GamePintuListener pintuListener) {
        this.pintuListener = pintuListener;
    }

    private static final int TIME_CHANG = 0;
    private static final int NEXT_LEVE = 1;

    public boolean isTimeEnble = false;
    private int level = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case TIME_CHANG:
                    break;
                case NEXT_LEVE:
                    level = level + 1;
                    if (pintuListener != null) {
                        pintuListener.nextLevel(level);
                    }else {
                        nextLeve();
                    }
                    break;
            }
        }
    };

    /**
     * 设置开始游戏时间
     *
     * @param isTimeEnble
     */
    public void setGameTimeEnable(boolean isTimeEnble) {
        this.isTimeEnble = isTimeEnble;
    }

    public PintuView(Context context) {
        this(context, null);
    }

    public PintuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PintuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void init() {
        //将3dp转成px
        mMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics());
        mPadding = minPadding(getPaddingLeft(), getPaddingRight(), getPaddingTop(), getPaddingBottom());
    }

    /**
     * 获取参数最小值
     *
     * @return
     */
    private int minPadding(int... parms) {
        int min = parms[0];
        for (int parm : parms) {
            if (parm < min) {
                min = parm;
            }
        }
        return 0;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = Math.min(getMeasuredHeight(), getMeasuredWidth());
        if (!once) {
            //对图片切图，排序
            initBitmap();
            //设置itemView的宽高
            initItem();
            once = true;
        }
        setMeasuredDimension(mViewWidth, mViewWidth);
    }

    private void initItem() {
        mItemWidth = (mViewWidth - mPadding * 2 - mMargin * (mColum - 1)) / mColum;
        mGamePintuItem = new ImageView[mColum * mColum];

        //生产item,设置位置关系
        for (int i = 0; i < mGamePintuItem.length; i++) {
            ImageView ig = new ImageView(getContext());
            ig.setOnClickListener(this);
            ig.setImageBitmap(mItemBitmap.get(i).getBitmap());
            mGamePintuItem[i] = ig;
            ig.setId(i + 1);
            //在item的Tag存放index
            ig.setTag(i + "_" + mItemBitmap.get(i).getIndex());
            LayoutParams lp = new LayoutParams(mItemWidth, mItemWidth);

            //设置item横向间距，
            // 不是最后一列
            if ((i + 1) % mColum != 0) {
                lp.rightMargin = mMargin;
            }

            //不是第一列
            if (i % mColum != 0) {
                lp.addRule(RelativeLayout.RIGHT_OF, mGamePintuItem[i - 1].getId());
            }

            //不是第一行
            if (i + 1 > mColum) {
                lp.topMargin = mMargin;
                lp.addRule(RelativeLayout.BELOW, mGamePintuItem[i - mColum].getId());
            }
            addView(ig, lp);
        }
    }

    private void initBitmap() {
        if (mBitmap == null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_tao);
        }
        mItemBitmap = ImageUilt.splitImage(mBitmap, mColum);
        //乱序排列
        Collections.sort(mItemBitmap, new Comparator<ImagePice>() {
            @Override
            public int compare(ImagePice o1, ImagePice o2) {
                return Math.random() > 0.5 ? -1 : 1;
            }
        });
    }


    private ImageView mFrist;
    private ImageView mSeconde;

    private RelativeLayout mAnimLayout;
    private boolean isAnim = false;

    @Override
    public void onClick(View v) {

        if (isAnim) return;
        //两次点击一个item图片
        if (mFrist == v) {
            mFrist.setColorFilter(null);
            mFrist = null;
            return;
        }


        if (mFrist == null) {
            mFrist = (ImageView) v;
            mFrist.setColorFilter(Color.parseColor("#55FF0000"));
        } else {
            mSeconde = (ImageView) v;
            exchangeView();
        }
    }

    /**
     * 交换图片
     */
    private void exchangeView() {
        mFrist.setColorFilter(null);

        setUpAnimLayout();

        final String fristTag = (String) mFrist.getTag();
        final String secondTag = (String) mSeconde.getTag();


        final Bitmap firstBitmap = mItemBitmap.get(getImageTag(fristTag)).getBitmap();
        final Bitmap secondBitmap = mItemBitmap.get(getImageTag(secondTag)).getBitmap();

        ImageView first = new ImageView(getContext());
        first.setImageBitmap(firstBitmap);
        LayoutParams lp = new LayoutParams(mItemWidth, mItemWidth);
        lp.leftMargin = mFrist.getLeft() - mPadding;
        lp.topMargin = mFrist.getTop() - mPadding;
        first.setLayoutParams(lp);
        mAnimLayout.addView(first);

        ImageView second = new ImageView(getContext());
        second.setImageBitmap(secondBitmap);
        LayoutParams lp2 = new LayoutParams(mItemWidth, mItemWidth);
        lp2.leftMargin = mSeconde.getLeft() - mPadding;
        lp2.topMargin = mSeconde.getTop() - mPadding;
        second.setLayoutParams(lp2);
        mAnimLayout.addView(second);

        TranslateAnimation anim = new TranslateAnimation(0, mSeconde.getLeft() - mFrist.getLeft(),
                0, mSeconde.getTop() - mFrist.getTop());
        anim.setDuration(300);
        anim.setFillAfter(true);
        first.startAnimation(anim);

        TranslateAnimation animSecond = new TranslateAnimation(0, -mSeconde.getLeft() + mFrist.getLeft(),
                0, -mSeconde.getTop() + mFrist.getTop());
        animSecond.setDuration(300);
        animSecond.setFillAfter(true);
        second.startAnimation(animSecond);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mFrist.setVisibility(INVISIBLE);
                mSeconde.setVisibility(INVISIBLE);
                isAnim = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mFrist.setImageBitmap(secondBitmap);
                mSeconde.setImageBitmap(firstBitmap);


                mFrist.setTag(secondTag);
                mSeconde.setTag(fristTag);

                mFrist.setVisibility(VISIBLE);
                mSeconde.setVisibility(VISIBLE);
                mFrist = mSeconde = null;
                mAnimLayout.removeAllViews();

                //每次完成，判断是否组图成功
                checkSuccess();
                isAnim = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    /**
     * 判断是否成功过关
     */
    private void checkSuccess() {
        boolean isSuccess = true;
        for (int i = 0; i < mGamePintuItem.length; i++) {
            if (getImageIndex((String) mGamePintuItem[i].getTag()) != i) {
                isSuccess = false;
            }
        }

        if (isSuccess) {
            Toast.makeText(context, "恭喜过关!", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessage(NEXT_LEVE);
        }
    }


    public void nextLeve() {
        this.removeAllViews();
        mAnimLayout = null;
        mColum++;
        isGameSuccess = false;
        initBitmap();
        initItem();
    }

    public void nextLeveNewBitmap(Bitmap bitmap) {
        this.removeAllViews();
        mAnimLayout = null;
        isGameSuccess = false;
        this.mBitmap= bitmap;
        initBitmap();
        initItem();
    }

    @NonNull
    private int getImageTag(String Tag) {
        String[] split = Tag.split("_");
        return Integer.parseInt(split[0]);
    }

    @NonNull
    private int getImageIndex(String Tag) {
        String[] split = Tag.split("_");
        return Integer.parseInt(split[1]);
    }

    private void setUpAnimLayout() {
        if (mAnimLayout == null) {
            mAnimLayout = new RelativeLayout(getContext());
            addView(mAnimLayout);
        }

    }
}
