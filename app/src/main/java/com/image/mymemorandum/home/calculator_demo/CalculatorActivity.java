package com.image.mymemorandum.home.calculator_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.image.mymemorandum.R;

public class CalculatorActivity extends AppCompatActivity {

    private Button zero, one, tow, three, four, five, six, seven, eight, nine, add, remove, multiply, divide, equals, back, clean;
    private TextView result;
    private TextView ed_number;

    private String number = "";
    private String operNumber = "";
    private boolean operating = false;

    private boolean addFalg = false, removeFalg = false, multFalg = false, divideFalg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        iniView();
    }

    private void iniView() {
        zero = ((Button) findViewById(R.id.calculator_zero_btn));
        one = ((Button) findViewById(R.id.calculator_one_btn));
        tow = ((Button) findViewById(R.id.calculator_tow_btn));
        three = ((Button) findViewById(R.id.calculator_three_btn));
        four = ((Button) findViewById(R.id.calculator_four_btn));
        five = ((Button) findViewById(R.id.calculator_five_btn));
        six = ((Button) findViewById(R.id.calculator_six_btn));
        seven = ((Button) findViewById(R.id.calculator_seven_btn));
        eight = ((Button) findViewById(R.id.calculator_eight_btn));
        nine = ((Button) findViewById(R.id.calculator_nine_btn));

        add = ((Button) findViewById(R.id.calculator_add_btn));
        remove = ((Button) findViewById(R.id.calculator_remove_btn));
        multiply = ((Button) findViewById(R.id.calculator_multiply_btn));
        divide = ((Button) findViewById(R.id.calculator_divide_btn));
        equals = ((Button) findViewById(R.id.calculator_equal_btn));

        back = ((Button) findViewById(R.id.calculator_back_btn));
        clean = ((Button) findViewById(R.id.calculator_clean_btn));

        result = ((TextView) findViewById(R.id.calculator_results));
        ed_number = ((TextView) findViewById(R.id.calculator_ed_number));

        ed_number.setText(number);
    }

    public void calculatorOnClick(View view) {
        switch (view.getId()) {
            case R.id.calculator_zero_btn:
                getNumber("0");
                break;
            case R.id.calculator_one_btn:
                getNumber("1");
                break;
            case R.id.calculator_tow_btn:
                getNumber("2");
                break;
            case R.id.calculator_three_btn:
                getNumber("3");
                break;
            case R.id.calculator_four_btn:
                getNumber("4");
                break;
            case R.id.calculator_five_btn:
                getNumber("5");
                break;
            case R.id.calculator_six_btn:
                getNumber("6");
                break;
            case R.id.calculator_seven_btn:
                getNumber("7");
                break;
            case R.id.calculator_eight_btn:
                getNumber("8");
                break;
            case R.id.calculator_nine_btn:
                getNumber("9");
                break;
            case R.id.calculator_point_btn:
                getNumber(".");
                break;
            case R.id.calculator_add_btn:
                operating = true;
                setFalg(1);
                getNumber("");
                break;
            case R.id.calculator_remove_btn:
                operating = true;
                setFalg(2);
                getNumber("");
                break;
            case R.id.calculator_multiply_btn:
                operating = true;
                setFalg(3);
                getNumber("");
                break;
            case R.id.calculator_divide_btn:
                operating = true;
                setFalg(4);
                getNumber("");
                break;
            case R.id.calculator_equal_btn:
                resulteInfo();
                break;
            case R.id.calculator_back_btn:
                String showNumber = ed_number.getText().toString().trim();
                if (showNumber.length() > 0) {
                    showNumber = showNumber.substring(0, showNumber.length() - 1);
                    if (!operating) {
                        number = showNumber;
                    } else {
                        operNumber = showNumber;
                    }
                } else {
                    return;
                }
                ed_number.setText(showNumber);
                break;
            case R.id.calculator_clean_btn:
                ed_number.setText("");
                result.setText("");
                setFalg(5);
                operNumber = "";
                number = "";
                operating = false;
                break;
        }
    }

    private void resulteInfo() {
        double v = 0;
        if ("".equals(number) || "".equals(operNumber)) {
            return;
        }
        if (addFalg) {
            v = Double.valueOf(number) + Double.valueOf(operNumber);
        }
        if (removeFalg) {
            v = Double.valueOf(number) - Double.valueOf(operNumber);

        }
        if (multFalg) {
            v = Double.valueOf(number) * Double.valueOf(operNumber);

        }
        if (divideFalg) {
            Double oper = Double.valueOf(operNumber);
            if (oper != 0) {
                v = Double.valueOf(number) / oper;
            } else {
                Toast.makeText(this, "除数不能为“0”", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        result.setText(v + "");
        number = result.getText().toString().trim();
        ed_number.setText("");
        operNumber = "";
        setFalg(5);

    }

    public String getNumber(String str) {
        if (!operating) {
            number += str;
            ed_number.setText(number);
            return number;
        } else {
            operNumber += str;
            result.setText(number);
            ed_number.setText(operNumber);
            return operNumber;
        }
    }

    public void setFalg(int stype) {
        switch (stype) {
            case 1:
                addFalg = true;
                removeFalg = false;
                multFalg = false;
                divideFalg = false;
                break;
            case 2:
                addFalg = false;
                removeFalg = true;
                multFalg = false;
                divideFalg = false;
                break;
            case 3:
                addFalg = false;
                removeFalg = false;
                multFalg = true;
                divideFalg = false;
                break;
            case 4:
                addFalg = false;
                removeFalg = false;
                multFalg = false;
                divideFalg = true;
                break;
            default:
                addFalg = false;
                removeFalg = false;
                multFalg = false;
                divideFalg = false;
                break;
        }
    }
}
