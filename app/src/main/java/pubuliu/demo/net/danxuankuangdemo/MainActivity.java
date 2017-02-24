package pubuliu.demo.net.danxuankuangdemo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{
    TextView v;
    Drawable drawable;
    SuozaidiPopupWindow suozaidiPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawable= getResources().getDrawable(R.drawable.duigou_hui);
        v=(TextView)findViewById(R.id.tv_hello);
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_hello){
            if(null!=suozaidiPopupWindow){
                if(suozaidiPopupWindow.isShowing()){
                    suozaidiPopupWindow.dismiss();
                }else{

                    suozaidiPopupWindow.showAsDropDown(v);
                    backgroundAlpha(0.8f);
                }
            }else {
                suozaidiPopupWindow=new SuozaidiPopupWindow(MainActivity.this);
                suozaidiPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1f);
                    }
                });
                suozaidiPopupWindow.showAsDropDown(v);
                backgroundAlpha(0.8f);
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        if(group.getId()==R.id.rg_suozaidi){//所在地
            switch(checkedId){
                case R.id.rb_zonghe://综合排序
                    ((RadioButton)group.getChildAt(2)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(1)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(0)).setCompoundDrawables(null,null,drawable,null);
                    break;
                case R.id.rb_zuijin://距离最近
                    ((RadioButton)group.getChildAt(2)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(1)).setCompoundDrawables(null,null,drawable,null);
                    ((RadioButton)group.getChildAt(0)).setCompoundDrawables(null,null,null,null);
                    break;
                case R.id.rb_xingji://星级最高
                    ((RadioButton)group.getChildAt(2)).setCompoundDrawables(null,null,drawable,null);
                    ((RadioButton)group.getChildAt(1)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(0)).setCompoundDrawables(null,null,null,null);
                    break;
            }
        }
    }
    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = f;
         getWindow().setAttributes(lp);
    }
}
