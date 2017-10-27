package com.dong.decorator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dong.decorator.decorator.Coffee;
import com.dong.decorator.decorator.MilkBubbleDecorator;
import com.dong.decorator.decorator.MilkDecorator;
import com.dong.decorator.decorator.MochaDecorator;
import com.dong.decorator.decorator.PureCoffee;
import com.dong.decorator.decorator.SugarDecorator;

/**
 * 装饰者模式的优缺点:
 *
 * 把类中的装饰功能从类中搬除，可以简化原来的类
 * 可以把类的 核心职责和装饰功能区分开来，结构清晰明了并且可以去除相关类的重复的装饰逻辑
 *
 * 装饰者模式概述：动态地给一个对象添加一些额外的职责。就增加功能来说，装饰者模式比生成子类更灵活。
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTvPureCoffee;
    private TextView mTvCoffee;
    private Coffee mCoffee;
    private PureCoffee mPureCoffee;
    private static final String TAG = "dong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTvPureCoffee = (TextView) findViewById(R.id.pure_coffee);
        mTvCoffee = (TextView) findViewById(R.id.coffee);

        mCoffee = new PureCoffee();
        mPureCoffee = new PureCoffee();

        mTvPureCoffee.setText("单价=" + mPureCoffee.getPrice());
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            // 加牛奶 2
            case R.id.btn_addMilk:
//                mCoffee = new PureCoffee();
                mCoffee = new MilkDecorator(mCoffee);
                int priceMIlk = mCoffee.getPrice();
                mTvCoffee.setText("追加牛奶价格="+priceMIlk);
                break;
            // 加奶泡 3
            case R.id.btn_addMIlkBubble:
                mCoffee = new PureCoffee();
                mCoffee = new MilkBubbleDecorator(mCoffee);
                int priceMIlkBubble = mCoffee.getPrice();
                mTvCoffee.setText("单加奶泡价格="+priceMIlkBubble);
                break;
            // 加糖 1
            case R.id.btn_addSugar:
                mCoffee = new PureCoffee();
                mCoffee = new SugarDecorator(mCoffee);
                int priceSugar = mCoffee.getPrice();
                mTvCoffee.setText("单加糖价格="+priceSugar);
                break;
            // 加抹茶 4
            case R.id.btn_addMocha:
                mCoffee = new PureCoffee();
                mCoffee = new MochaDecorator(mCoffee);
                int priceMocha = mCoffee.getPrice();
                mTvCoffee.setText("单加抹茶价格="+priceMocha);
                break;
            // 全加
            case R.id.btn_addAll:
                mCoffee = new PureCoffee();
                mCoffee = new SugarDecorator(mCoffee);
                mCoffee = new MilkDecorator(mCoffee);
                mCoffee = new MilkBubbleDecorator(mCoffee);
                mCoffee = new MochaDecorator(mCoffee);
                int priceAll = mCoffee.getPrice();
                mTvCoffee.setText("加牛奶、奶泡、糖、抹茶的价格="+priceAll);
                System.out.println("price1="+priceAll);
                break;
            default:
                break;
        }

    }
}
