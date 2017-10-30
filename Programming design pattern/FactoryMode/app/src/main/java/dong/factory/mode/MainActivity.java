package dong.factory.mode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dong.factory.mode.cloth.LongSleeve;
import dong.factory.mode.cloth.SevenPointShirt;
import dong.factory.mode.cloth.ShortSleeve;
import dong.factory.mode.factory.AbstractClothFactory;
import dong.factory.mode.factory.ClothFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnLongSleeve;
    private Button mBtnSevenPointShirt;
    private Button mBtnShortSleeve;
    private AbstractClothFactory mClothFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mBtnLongSleeve = (Button) findViewById(R.id.btn_long_sleeve);
        mBtnSevenPointShirt = (Button) findViewById(R.id.btn_seven_point_shirt);
        mBtnShortSleeve = (Button) findViewById(R.id.btn_short_sleeve);

        mClothFactory = ClothFactory.getInstance();
        mBtnLongSleeve.setOnClickListener(this);
        mBtnSevenPointShirt.setOnClickListener(this);
        mBtnShortSleeve.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_long_sleeve:
                LongSleeve longSleeve = mClothFactory.makeCloth(LongSleeve.class);
                longSleeve.getName();
                longSleeve.getStyle();
                break;
            case R.id.btn_seven_point_shirt:
                SevenPointShirt sevenPointShirt = mClothFactory.makeCloth(SevenPointShirt.class);
                sevenPointShirt.getName();
                sevenPointShirt.getStyle();
                break;
            case R.id.btn_short_sleeve:
                ShortSleeve shortSleeve = mClothFactory.makeCloth(ShortSleeve.class);
                shortSleeve.getName();
                shortSleeve.getStyle();
                break;
            default:
                break;
        }
    }

}
