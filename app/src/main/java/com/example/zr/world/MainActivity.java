package com.example.zr.world;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    TextView et4;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.ll_one)
    LinearLayout llOne;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.tv_selectspinner)
    TextView tvSelectspinner;
    @BindView(R.id.btn_getcp1)
    Button btnGetcp1;
    @BindView(R.id.btn_getcp2)
    Button btnGetcp2;

    private String num = "";
    private String count;
    private SharedPreferences sp;
    private int price = 0;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sp = getSharedPreferences("dh", Context.MODE_PRIVATE);
        Const.ip = sp.getString("ip", Const.ip);
        ed1.setText(Const.ip);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.profession);
                title = languages[position];
                tvSelectspinner.setText(title);
                if (languages[position].equals("单注随机")) {
                    llOne.setVisibility(View.VISIBLE);
                    llMore.setVisibility(View.GONE);
                    et4.setText(null);
                } else if (languages[position].equals("复式随机")) {
                    llOne.setVisibility(View.GONE);
                    llMore.setVisibility(View.VISIBLE);
                    et4.setText(null);
                } else {
                    llOne.setVisibility(View.GONE);
                    llMore.setVisibility(View.GONE);
                    et4.setText(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //获取单注彩票，自己选择总注数
    private void randomone(String count) {
        HashSet<String> integerHashSet = new HashSet<String>();
        String red = "";
        String bule = "";
        Random random = new Random();
        if (TextUtils.isEmpty(count)) {
            count = "1";
        }
        System.out.println("需要的注数:" + Integer.valueOf(count));
        for (int i = 0; i < Integer.valueOf(count); i++) {
            for (int ii = 0; ii < 6; ii++) {
                String randomInt = String.valueOf(random.nextInt(33) + 1);
                System.out.println("生成的randomInt=" + randomInt);
                if (Integer.valueOf(randomInt)<10){
                    randomInt=0+randomInt;
                }
                if (!integerHashSet.contains(randomInt)) {
                    integerHashSet.add(randomInt);
                    System.out.println("添加进HashSet的randomInt=" + randomInt);
                } else {
                    System.out.println("该数字已经被添加,不能重复添加");
                    ii--;
                }
                if (integerHashSet.size() == 6) {
                    TreeSet ts = new TreeSet(integerHashSet);
                    ts.comparator();
                    System.out.println(ts);
                    red = ts.toString().replace(",", " ").replace("[", "").replace("]", "");
                    integerHashSet.clear();
                }
            }
            for (int iii = 0; iii < 1; iii++) {
                bule = String.valueOf(random.nextInt(16) + 1);
                if (Integer.valueOf(bule) < 10) {
                    bule = 0 + bule;
                }
                System.out.println("生成的蓝号randomInt=" + bule);
            }
            num = "红球: " + red + " " + " 篮球: " + bule + "\n" + num;
            System.out.println("生成的号码=" + num);
            price = Integer.valueOf(count) * 2;
            et4.setText(num + "总金额:" + price + "元");
        }
        num = "";
    }

    //获取复式彩票，自己选择红球和篮球个数
    private void randommore(String redcount, String bulecount) {
        HashSet<String> integerHashSetred = new HashSet<String>();
        HashSet<String> integerHashSetblue = new HashSet<String>();
        String red = "";
        String bule = "";
        Random random = new Random();
        if (Integer.valueOf(redcount) >= 6 && Integer.valueOf(redcount) < 34) {
            for (int ii = 0; ii < Integer.valueOf(redcount); ii++) {
                String randomInt = String.valueOf(random.nextInt(33) + 1);
                System.out.println("生成的randomInt=" + randomInt);
                if (Integer.valueOf(randomInt)<10){
                    randomInt=0+randomInt;
                }
                if (!integerHashSetred.contains(randomInt)) {
                    integerHashSetred.add(randomInt);
                    System.out.println("添加进HashSet的randomInt=" + randomInt);
                } else {
                    System.out.println("该数字已经被添加,不能重复添加");
                    ii--;
                }
                if (integerHashSetred.size() == Integer.valueOf(redcount)) {
                    TreeSet ts = new TreeSet(integerHashSetred);
                    ts.comparator();
                    System.out.println(ts);
                    red = ts.toString().replace(",", " ").replace("[", "").replace("]", "");
                    integerHashSetred.clear();
                }
            }
        } else {
            et4.setText("红球少于6个或大于33个");
            return;
        }
        if (Integer.valueOf(bulecount) >= 1 && Integer.valueOf(bulecount) < 17) {
            for (int iii = 0; iii < Integer.valueOf(bulecount); iii++) {
                String randomInt = String.valueOf(random.nextInt(16) + 1);
                System.out.println("生成的蓝号randomInt=" + randomInt);
                if (Integer.valueOf(randomInt)<10){
                    randomInt=0+randomInt;
                }
                if (!integerHashSetblue.contains(randomInt)) {
                    integerHashSetblue.add(randomInt);
                    System.out.println("添加进HashSet的randomInt=" + randomInt);
                } else {
                    System.out.println("该数字已经被添加,不能重复添加");
                    iii--;
                }
                if (integerHashSetblue.size() == Integer.valueOf(bulecount)) {
                    TreeSet ts = new TreeSet(integerHashSetblue);
                    ts.comparator();
                    System.out.println(ts);
                    bule = ts.toString().replace(",", " ").replace("[", "").replace("]", "");
                    integerHashSetblue.clear();
                }
            }
            num = "红球: " + red + " " + "\n" + "篮球: " + bule + num;
            System.out.println("生成的号码=" + num);
            price = getPrice(6, Integer.valueOf(redcount), 1, Integer.valueOf(bulecount));
            et4.setText(num + "\n" + "总金额:" + price + "元");
        } else {
            et4.setText("篮球少于1个或大于16个");
        }
        num = "";
    }

    //排列组合方法，没有顺序,算出总金额
    private int getPrice(int needredcount, int totalredcount, int needbulecount, int totalbulecount) {
        int needredx = 1;
        for (int x = 1; x < needredcount + 1; x++) {
            needredx = x * needredx;
        }
        int totalredx = totalredcount;
        int xy = totalredcount;
        for (int y = 1; y < needredcount; y++) {
            --xy;
            totalredx = totalredx * xy;
        }
        int xx = (totalredx / needredx) * 2 * totalbulecount;
        return xx;
    }

    @OnClick({R.id.btn_getcp1, R.id.btn_getcp2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getcp1:
                count = ed1.getText().toString();
                if (!TextUtils.isEmpty(count) && Integer.valueOf(count) > 0) {
                    SharedPreferences sp = getSharedPreferences("dh", Context.MODE_PRIVATE);
                    sp.edit().putString("ip", count).commit();
                    randomone(count);
                } else {
                    et4.setText("请输入大于0的整数");
                }
                break;
            case R.id.btn_getcp2:
                if (TextUtils.isEmpty(et2.getText().toString()) || TextUtils.isEmpty(et3.getText().toString())) {
                    et4.setText("请输入具体的红球数和篮球数");
                } else {
                    randommore(et2.getText().toString(), et3.getText().toString());
                }
                break;
        }
    }
}
