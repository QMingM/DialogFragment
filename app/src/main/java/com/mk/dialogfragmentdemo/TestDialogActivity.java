package com.mk.dialogfragmentdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mk.mklib.dialog.BHCBaseDialogFragment;
import com.mk.mklib.dialog.BHCCommonConfirmDialog;
import com.mk.mklib.dialog.BHCCommonInputDialog;
import com.mk.mklib.dialog.BHCCommonLoadingDialog;
import com.mk.mklib.dialog.BHCConfirmWithImageDialog;
import com.mk.mklib.dialog.BHCDateDialog;
import com.mk.mklib.dialog.BHCWithWebViewDialog;
import com.mk.mklib.utils.DensityUtils;
import com.mk.mklib.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by mbm on 2019/5/7
 */
public class TestDialogActivity extends AppCompatActivity {

    Button btn_confirm;
    Button btn_single_confirm_dialog;
    Button btn_confirm_with_image;
    Button btn_webview;
    Button btn_edit;
    Button btn_edit_tel;
    Button btn_loading_dialog;
    Button btn_date;
    Button btn_year;
    Button btn_month;
    Button btn_day;
    Button btn_common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);

        btn_confirm = findViewById(R.id.confirm_dialog);
        btn_single_confirm_dialog = findViewById(R.id.single_confirm_dialog);
        btn_confirm_with_image = findViewById(R.id.confirm_with_image_dialog);
        btn_webview = findViewById(R.id.webview_dialog);
        btn_edit = findViewById(R.id.edit_dialog);
        btn_edit_tel = findViewById(R.id.edit_tel_dialog);
        btn_loading_dialog = findViewById(R.id.loading_dialog);
        btn_date = findViewById(R.id.date_dialog);
        btn_year = findViewById(R.id.year_dialog);
        btn_month = findViewById(R.id.month_dialog);
        btn_day = findViewById(R.id.day_dialog);
        btn_common = findViewById(R.id.common_dialog);

        btn_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCWithWebViewDialog.newWithWebViewDialog()
                        .setTitle("条款公约")
                        .setConfirmBtnText("同意")
                        .setCancelBtnText("不同意")
                        .setUrl("https://www.baidu.com")
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener() {
                            @Override
                            public void result(Object result) {

                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {

                            }
                        })
                        .show(getSupportFragmentManager());
            }
        });

        //确认dialog
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonConfirmDialog dialog = BHCCommonConfirmDialog.newConfirmBuilder()
                        .setCanceledOnTouchOutside(true)
                        .setTitle("提示")//标题不设置默认隐藏
                        .setCancelBtnText("暂不更新")
                        .setConfirmBtnText(getResources().getString(R.string.app_name))
                        .setMessage("发现新版本")
                        .build();
                //点击确认回调监听
                dialog.setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<String>() {
                    @Override
                    public void result(String result) {
                        Toast.makeText(TestDialogActivity.this, "你点击了立即更新", Toast.LENGTH_SHORT).show();
                    }
                })
                        //取消回调监听
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {
                                Toast.makeText(TestDialogActivity.this, "你点击了暂不更新", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getSupportFragmentManager());

            }
        });

        //确认dialog
        btn_single_confirm_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonConfirmDialog.newConfirmBuilder()
                        .setTitle("短标题")//标题不设置默认隐藏
                        .setConfirmBtnText("确认")
                        .setMessage("短内容")
                        .setIsShowLeftBtn(false)
                        .build()
                        //点击确认回调监听
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你点击了" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getSupportFragmentManager());

            }
        });


        btn_confirm_with_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCConfirmWithImageDialog.newConfirmBuilder()
                        .setBtnText("按钮")
                        .build()
                        .show(getSupportFragmentManager());
            }
        });

        //带编辑框dialog
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonInputDialog.newInputBuilder()
                        .setHint("请输入...")
                        .setTitle("标题")
                        .setIsShowSubTitle(true)
                        .setSubTitle("hahahahahaha")
                        .setIsShowLastEdit(false)
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 200))
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<String>() {
                            @Override
                            public void result(String result) {
                                if (!TextUtils.isEmpty(result)) {
                                    Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show(getSupportFragmentManager(), "inputDialog");
            }
        });

        //带编辑框dialog
        btn_edit_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonInputDialog.newInputBuilder()
                        .setHint("区号")
                        .setHint1("座机号码")
                        .setTitle("请输入联系人方式")
                        .setIsShowLastEdit(true)
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 200))
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<String>() {
                            @Override
                            public void result(String result) {
                                if (!TextUtils.isEmpty(result)) {
                                    Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {

                            }
                        })
                        .show(getSupportFragmentManager(), "inputDialog");
            }
        });

        /**
         * 加载进度dialog
         */
        btn_loading_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BHCCommonLoadingDialog loadingDialog = BHCCommonLoadingDialog.newBHCCommonLoadingDialog().build();
                loadingDialog.show(getSupportFragmentManager(), "BHCCommonLoadingDialog");

//                if (loadingDialog.getDialog().isShowing()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismiss();
                        }
                    }, 2000);
                }
//            }
        });


        //年月日dialog
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("date")
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 200))
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //年dialog
        btn_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("year")
                        .setSelectedYear(2019)
                        .setYearStart(1992)
                        .setYearEnd(2050)
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //月dialog
        btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("month")
                        .setSelectedMonth(3)
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //日dialog
        btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("day")
                        .setMonth(3)
                        .setSelectedDay(4)
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //自定义数据dialog
        btn_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    data.add("mk" + i);
                }
                BHCDateDialog.newYearDialogBuilder()
                        .setType("common")
                        .setListData(data)
                        .build()
                        .setDialogConfirmListener(new BHCBaseDialogFragment.DialogConfirmListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogCancelListener(new BHCBaseDialogFragment.DialogCancelListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });
    }
}