package com.itfitness.paypasswordview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itfitness.paypasswordview.R;
import com.itfitness.paypasswordview.widget.listeners.InputPassWordFinshListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.widget
 * @ClassName: PayView
 * @Description: 支付密码View
 * @Author: LML
 * @CreateDate: 2018/10/8 15:07
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/8 15:07
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class PayPasswordView extends RelativeLayout {
    private Context mContext;
    private EditText myModuleViewPayEtPass;
    private LinearLayout myModuleViewPayLayoutpass;
    private List<TextView> textViews;
    private int mPassLength=6;
    private GradientDrawable mEtPassDrawable;
    private InputPassWordFinshListener mInputPassWordFinshListener;
    private int mPassTextColor;
    private float mPassTextSize;
    private int mBorderWidth;
    private int mBorderColor;
    private String mPassText;

    public void addInputPassWordFinshListener(InputPassWordFinshListener mInputPassWordFinshListener) {
        this.mInputPassWordFinshListener = mInputPassWordFinshListener;
    }

    public int getPassLength() {
        return mPassLength;
    }

    public void setPassLength(int mPassLength) {
        this.mPassLength = mPassLength;
    }

    public PayPasswordView(Context context) {
        this(context,null);
    }
    public PayPasswordView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public PayPasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initAttrs(attrs);
        initView();
    }
    /**
     * @method  initAttrs
     * @description 加载自定义属性
     * @date: 2018/10/9 15:48
     * @author: LML
     * @param attrs 自定义属性
     * @return void
     */
    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.PayPasswordView);
        if (typedArray != null) {
            mBorderWidth = (int) typedArray.getDimension(R.styleable.PayPasswordView_borderWidth, 1);
            mBorderColor = typedArray.getColor(R.styleable.PayPasswordView_borderColor, getResources().getColor(R.color.colorAccent));
            mPassLength = typedArray.getInteger(R.styleable.PayPasswordView_passLength, 6);
            float radius = typedArray.getDimension(R.styleable.PayPasswordView_radius, 4);
            mPassTextColor = typedArray.getColor(R.styleable.PayPasswordView_passTextColor, getResources().getColor(R.color.colorAccent));
            mPassTextSize = typedArray.getDimension(R.styleable.PayPasswordView_passTextSize, 16);
            mPassText = typedArray.getString(R.styleable.PayPasswordView_passText);
            mEtPassDrawable=new GradientDrawable();
            mEtPassDrawable.setStroke(mBorderWidth, mBorderColor);
            mEtPassDrawable.setCornerRadius(radius);
            typedArray.recycle();
        }
    }

    public void setPassWord(String passWord){
        myModuleViewPayEtPass.setText(passWord);
    }
    private void initView() {
        RelativeLayout.LayoutParams contentLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(contentLayoutParams);
        RelativeLayout.LayoutParams etLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        myModuleViewPayEtPass = new EditText(mContext);
        myModuleViewPayEtPass.setLayoutParams(etLayoutParams);
        myModuleViewPayEtPass.setTextSize(0);
        myModuleViewPayEtPass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(mPassLength)});//设置过滤器让控件只能输入设定密码长度的字符数
        myModuleViewPayEtPass.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);
        if(mEtPassDrawable!=null){
            myModuleViewPayEtPass.setBackground(mEtPassDrawable);
        }else {
            myModuleViewPayEtPass.setBackgroundResource(R.drawable.my_module_view_pay_bg);
        }
        myModuleViewPayEtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()<=mPassLength){
                    initDatas(s);
                    if(s.length()==mPassLength){
                        if(mInputPassWordFinshListener!=null){
                            mInputPassWordFinshListener.onInputPassWordFinsh(s.toString().trim());
                        }
                    }
                }
            }
        });
        myModuleViewPayLayoutpass = new LinearLayout(mContext);
        myModuleViewPayLayoutpass.setLayoutParams(contentLayoutParams);
        myModuleViewPayLayoutpass.setOrientation(LinearLayout.HORIZONTAL);
        addView(myModuleViewPayEtPass);
        addView(myModuleViewPayLayoutpass);
        initPassView();
    }
    /**
     * @method  initPassView
     * @description 添加显示密文“●”的控件
     * @date: 2018/10/9 15:46
     * @author: LML
     * @return void
     */
    private void initPassView(){
        if(textViews==null){
            textViews=new ArrayList<>();
        }
        TextView textViewPass;
        View view;
        LinearLayout.LayoutParams passParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams spliteParams = new LinearLayout.LayoutParams(mBorderWidth, LayoutParams.MATCH_PARENT);
        passParams.weight = 1;
        for(int x=0;x<mPassLength;x++){
            textViewPass=new TextView(mContext);
            textViewPass.setLayoutParams(passParams);
            textViewPass.setTextSize(mPassTextSize);
            textViewPass.setGravity(Gravity.CENTER);
            textViewPass.setTextColor(mPassTextColor);
            textViews.add(textViewPass);
            myModuleViewPayLayoutpass.addView(textViewPass);
            //添加密文分割线，分割线数量为密码长度-1
            if(x<mPassLength-1){
                view=new View(mContext);
                view.setBackgroundColor(mBorderColor);
                view.setLayoutParams(spliteParams);
                myModuleViewPayLayoutpass.addView(view);
            }
        }
    }
    public void initDatas(Editable s) {
        if(s.length() > 0)
        {
            for(int i = 0; i < mPassLength; i++)
            {
                if(i < s.length())
                {
                    if(TextUtils.isEmpty(mPassText)){
                        textViews.get(i).setText("●");
                    }else {
                        textViews.get(i).setText(mPassText);
                    }
                }
                else
                {
                    textViews.get(i).setText("");
                }
            }
        }
        else
        {
            for(int i = 0; i < mPassLength; i++)
            {
                textViews.get(i).setText("");
            }
        }
    }
}
