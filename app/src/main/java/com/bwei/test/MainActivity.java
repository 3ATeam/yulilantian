package com.bwei.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 作者：李亚雷
 * 时间：2017/6/12
 * 类用途：界面展示
 * 思路：1.通过button的点击事件开始扩充内圆
 *      2.通过myview的settext，setFilletWinth设置myview的文本显示内容以及内圆的宽
 *      3.通过handler完成线程间通讯
 *      4.for循环一百次，用时5秒，所以每一次睡眠时间为50毫秒
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start_Button;
    private MyView myview;
    private int ringWinth;
    private int filletWinth;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {

                case 0: {
                    //得到文本内容，设置zidingyiview并刷新试图
                    String  text = (String) msg.obj;
                    myview.setFilletWinth(filletWinth);
                    myview.setText(text);
                    myview.invalidate();
                }


            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initData();

    }

    private void initData() {

        ringWinth = myview.getRingWinth();
        filletWinth = myview.getFilletWinth();

    }

    private void initView() {
        start_Button = (Button) findViewById(R.id.start_Button);
        myview = (MyView) findViewById(R.id.myview);

        start_Button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_Button:

                final int winth = ringWinth  / 100;

                new Thread() {

                    @Override
                    public void run() {
                        super.run();

                        for (int i = 0; i < 101; i++) {
                            try {
                                if (i==0){
                                    filletWinth=0;
                                }

                                filletWinth = filletWinth + winth;

                                //像handler发送消息
                                Message message = new Message();
                                message.obj=i+"";
                                handler.sendMessage(message);
                                sleep(50);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                }.start();


                break;
        }
    }
}
