package com.itfitness.paypasswordview.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itfitness.paypasswordview.R;
import com.itfitness.paypasswordview.widget.adapter.KeyBoardAdapter;
import com.itfitness.paypasswordview.widget.recycledivider.KeyBoardDevider;


import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.widget
 * @ClassName: PayKeyBoardView
 * @Description: 支付键盘
 * @Author: LML
 * @CreateDate: 2018/10/9 11:36
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/9 11:36
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class PayKeyBoardView extends RelativeLayout {
    private RecyclerView myModuleViewKeyboardRecyclerview;
    private View mView;
    private GridLayoutManager gridLayoutManager;
    private List<Integer> mKeyBoardDatas;
    private KeyBoardAdapter keyBoardAdapter;
    public PayKeyBoardView(Context context) {
        this(context,null);
    }

    public PayKeyBoardView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PayKeyBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = View.inflate(context, R.layout.view_keyboard, this);
        initView();
        initDatas();
    }

    private void initView() {
        myModuleViewKeyboardRecyclerview = (RecyclerView) mView.findViewById(R.id.my_module_view_keyboard_recyclerview);
        if(gridLayoutManager==null){
            gridLayoutManager = new GridLayoutManager(getContext(), 3);
        }
        myModuleViewKeyboardRecyclerview.setLayoutManager(gridLayoutManager);
        myModuleViewKeyboardRecyclerview.addItemDecoration(new KeyBoardDevider(getContext()));
    }
    private void initDatas() {
        if(mKeyBoardDatas==null){
            mKeyBoardDatas=new ArrayList<>();
            for (int x=1;x<13;x++){
                mKeyBoardDatas.add(x);
            }
        }
        initAdapter();
    }
    public void setOnItemClickListener(BaseQuickAdapter.OnItemClickListener onItemClickListener){
        keyBoardAdapter.setOnItemClickListener(onItemClickListener);
    }
    private void initAdapter() {
        if(keyBoardAdapter ==null){
            keyBoardAdapter =new KeyBoardAdapter(R.layout.item_paykeyboard,mKeyBoardDatas);
        }
        myModuleViewKeyboardRecyclerview.setAdapter(keyBoardAdapter);
    }
}
