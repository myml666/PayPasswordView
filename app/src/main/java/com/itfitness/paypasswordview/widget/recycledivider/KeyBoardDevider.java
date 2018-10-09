package com.itfitness.paypasswordview.widget.recycledivider;

import android.content.Context;

/**
 * @ProjectName: ECanal2.0
 * @Package: com.example.yuyi.my_module.widget.recycledivider
 * @ClassName: KeyBoardDevider
 * @Description: 支付密码的分割线
 * @Author: LML
 * @CreateDate: 2018/10/9 11:17
 * @UpdateUser: 更新者：
 * @UpdateDate: 2018/10/9 11:17
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */

public class KeyBoardDevider extends DividerItemDecoration {
    public KeyBoardDevider(Context context) {
        super(context);
    }

    @Override
    public Divider getDivider(int itemPosition) {
        Divider divider = null;
        switch (itemPosition % 3) {
            case 0:
            case 1:
                //每一行第一个和第二个显示rignt和bottom
                divider = new DividerBuilder()
                        .setRightSideLine(true, 0xE2E7ED, 2, 0, 0)
                        .setBottomSideLine(true, 0xE2E7ED, 2, 0, 0)
                        .create();
                break;
            case 2:
                //最后一个只显示bottom
                divider = new DividerBuilder()
                        .setBottomSideLine(true, 0xE2E7ED, 2, 0, 0)
                        .create();
                break;
            default:
                break;
        }
        return divider;
    }
}
