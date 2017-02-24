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
        if(v.getId()==R.id.tv_hello){//点击控制Popupwindow生成显示与隐藏
            if(null!=suozaidiPopupWindow){
                if(suozaidiPopupWindow.isShowing()){
                    suozaidiPopupWindow.dismiss();//Popupwindow消失
                }else{

                    suozaidiPopupWindow.showAsDropDown(v);//Popupwindow显示
                    backgroundAlpha(0.8f);
                }
            }else {//Popupwindow生成并且显示
                suozaidiPopupWindow=new SuozaidiPopupWindow(MainActivity.this);
                //这里设置Popwindow消失事件。恢复屏幕亮度
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
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//引入图片
        if(group.getId()==R.id.rg_suozaidi){//
            switch(checkedId){//通过点击。设置后方对勾那个图片
                case R.id.rb_zonghe://
                    ((RadioButton)group.getChildAt(2)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(1)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(0)).setCompoundDrawables(null,null,drawable,null);
                    break;
                case R.id.rb_zuijin://
                    ((RadioButton)group.getChildAt(2)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(1)).setCompoundDrawables(null,null,drawable,null);
                    ((RadioButton)group.getChildAt(0)).setCompoundDrawables(null,null,null,null);
                    break;
                case R.id.rb_xingji://
                    ((RadioButton)group.getChildAt(2)).setCompoundDrawables(null,null,drawable,null);
                    ((RadioButton)group.getChildAt(1)).setCompoundDrawables(null,null,null,null);
                    ((RadioButton)group.getChildAt(0)).setCompoundDrawables(null,null,null,null);
                    break;
            }
        }
    }
    /**这里调节背景亮度。出现遮罩效果，高仿dialog.单纯的Popupwindow不能很好的出现遮罩*/
    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = f;
         getWindow().setAttributes(lp);
    }
}
