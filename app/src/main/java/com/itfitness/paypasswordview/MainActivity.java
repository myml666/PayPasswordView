package com.itfitness.paypasswordview;

import android.content.DialogInterface;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itfitness.paypasswordview.widget.PayKeyBoardView;
import com.itfitness.paypasswordview.widget.PayPasswordView;
import com.itfitness.paypasswordview.widget.listeners.InputPassWordFinshListener;

import static android.view.KeyEvent.KEYCODE_BACK;
import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private PayPasswordView ppv6;
    private PayPasswordView ppv8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ppv6 = (PayPasswordView) findViewById(R.id.ppv_6);
        ppv8 = (PayPasswordView) findViewById(R.id.ppv_8);
        ppv6.addInputPassWordFinshListener(new InputPassWordFinshListener() {
            @Override
            public void onInputPassWordFinsh(String password) {
                Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT).show();
            }
        });
        ppv8.addInputPassWordFinshListener(new InputPassWordFinshListener() {
            @Override
            public void onInputPassWordFinsh(String password) {
                Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClick(View v){
        final StringBuffer stringBuffer=new StringBuffer();
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        //==============关闭触摸其他位置关闭弹框=============
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        //=================================================
        //点击返回键关闭弹框
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KEYCODE_BACK){
                    dialog.dismiss();
                }
                return false;
            }
        });
        View paydialogView = View.inflate(this,R.layout.dialog_pay, null);
        ImageView myModuleDialogPayImgClose= (ImageView) paydialogView.findViewById(R.id.dialog_pay_img_close);
        final PayPasswordView myModuleDialogPayPv= (PayPasswordView) paydialogView.findViewById(R.id.dialog_pay_ppv);
        PayKeyBoardView keyboard= (PayKeyBoardView) paydialogView.findViewById(R.id.dialog_pay_pkv);
        //添加键盘点击事件
        keyboard.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if(position<9){
                    if(myModuleDialogPayPv.getPassLength()>stringBuffer.length()){
                        stringBuffer.append(position+1);
                        myModuleDialogPayPv.setPassWord(stringBuffer.toString().trim());
                    }
                }else if(position==10){
                    if(myModuleDialogPayPv.getPassLength()>stringBuffer.length()){
                        stringBuffer.append(0);
                        myModuleDialogPayPv.setPassWord(stringBuffer.toString().trim());
                    }
                }else if(position==11){
                    if(stringBuffer.length()>0){
                        stringBuffer.delete(stringBuffer.length()-1,stringBuffer.length());
                        myModuleDialogPayPv.setPassWord(stringBuffer.toString().trim());
                    }
                }
            }
        });

        myModuleDialogPayImgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //添加密码输入完毕事件
        myModuleDialogPayPv.addInputPassWordFinshListener(new InputPassWordFinshListener() {
            @Override
            public void onInputPassWordFinsh(String password) {
                Toast.makeText(MainActivity.this, password, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.setContentView(paydialogView);
        dialog.show();
    }
}
