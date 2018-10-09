package com.itfitness.paypasswordview.widget.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itfitness.paypasswordview.R;

import java.util.List;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.adapters
 * @ClassName: My_Module_PayDialog_KeyBoardAdapter
 * @Description: java类作用描述
 * @Author: LML
 * @CreateDate: 2018/10/9 11:12
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/9 11:12
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class KeyBoardAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {
    public KeyBoardAdapter(int layoutResId, @Nullable List<Integer> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        TextView myModuleDialogPayTvKeyboard=helper.getView(R.id.my_module_dialog_pay_tv_keyboard);
        ImageView myModuleDialogPayImageKeyboard=helper.getView(R.id.my_module_dialog_pay_image_keyboard);
        if(item==10){
            myModuleDialogPayTvKeyboard.setVisibility(View.INVISIBLE);
        }else if(item==11){
            myModuleDialogPayTvKeyboard.setText("0");
        }else if(item==12){
            myModuleDialogPayTvKeyboard.setVisibility(View.GONE);
            myModuleDialogPayImageKeyboard.setVisibility(View.VISIBLE);
        }else {
            myModuleDialogPayTvKeyboard.setText(item+"");
        }
    }
}
