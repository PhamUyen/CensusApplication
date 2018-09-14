package com.uyenpham.censusapplication.views;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uyenpham.censusapplication.R;


public class CustomToolbar extends RelativeLayout implements View.OnClickListener{
    ImageView imvBack;
    TextView tvTitle;
    ImageView imvDrawer;
    private IClickBack iClickBack;
    private IClickDrawer iClickDrawer;
    private IClickMenu iClickMenu;

    public CustomToolbar(Context context) {
        super(context);
        initView();
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.toolbar_layout, this);
        imvBack = view.findViewById(R.id.imv_back);
        tvTitle = view.findViewById(R.id.tv_title);
        imvDrawer = view.findViewById(R.id.imv_drawer);

        //=====onClick=====
        imvBack.setOnClickListener(this);
        imvDrawer.setOnClickListener(this);
    }



    public void setTilte(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
                if (iClickBack != null) {
                    iClickBack.onBackClick();
                }
                break;
            case R.id.imv_drawer:
                if(iClickDrawer != null){
                    iClickDrawer.onDrawerClick();
                }
                break;
            default:
                break;
        }
    }

    public void setiClickBack(IClickBack iClickBack) {
        this.iClickBack = iClickBack;
    }

    public void setiClickDrawer(IClickDrawer iClickDrawer) {
        this.iClickDrawer = iClickDrawer;
    }

    public void setiClickMenu(IClickMenu iClickMenu) {
        this.iClickMenu = iClickMenu;
    }

    public interface IClickBack {
        void onBackClick();
    }

    public interface IClickDrawer {
        void onDrawerClick();
    }
    public interface IClickMenu {
        void onMenuClick();
    }
}
