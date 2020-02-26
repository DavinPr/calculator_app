package com.belajarandroid.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btn_0)
    Button btn_0;
    @BindView(R.id.btn_1)
    Button btn_1;
    @BindView(R.id.btn_2)
    Button btn_2;
    @BindView(R.id.btn_3)
    Button btn_3;
    @BindView(R.id.btn_4)
    Button btn_4;
    @BindView(R.id.btn_5)
    Button btn_5;
    @BindView(R.id.btn_6)
    Button btn_6;
    @BindView(R.id.btn_7)
    Button btn_7;
    @BindView(R.id.btn_8)
    Button btn_8;
    @BindView(R.id.btn_9)
    Button btn_9;
    @BindView(R.id.btn_plus)
    Button btn_plus;
    @BindView(R.id.btn_minus)
    Button btn_minus;
    @BindView(R.id.btn_times)
    Button btn_times;
    @BindView(R.id.btn_divide)
    Button btn_divide;
    @BindView(R.id.btn_mod)
    Button btn_mod;
    @BindView(R.id.btn_point)
    Button btn_point;
    @BindView(R.id.btn_plus_minus)
    Button btn_plus_minus;
    @BindView(R.id.btn_result)
    Button btn_result;
    @BindView(R.id.btn_clear)
    Button btn_clear;
    @BindView(R.id.btn_delete)
    Button btn_delete;
    @BindView(R.id.edt_number)
    EditText tvNumber;

    private boolean state_numeric;
    private boolean stateError;
    private boolean state_dot;
    private boolean state_operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        click();
    }

    private void setNumericOperation(String n) {
        if (stateError) {
            tvNumber.setText(n);
            stateError = false;
        } else {
            tvNumber.append(n);
        }
        state_numeric = true;
        state_operation = false;
    }

    private void setAritmethicOperation(String op) {
        if (state_numeric && !stateError && !state_operation) {
            tvNumber.append(op);
            state_numeric = false;
            state_dot = false;
            state_operation = true;
        }
    }

    private void setDecimalOperation() {
        if (state_numeric && !stateError && !state_dot && !state_operation) {
            tvNumber.append(".");
            state_numeric = false;
            state_dot = true;
        }
    }

    private void setClearOperation() {
        state_numeric = false;
        state_dot = false;
        stateError = false;
        state_operation = false;
        tvNumber.setText("");
    }

    @SuppressLint("SetTextI18n")
    private void setEqualOperation() {
        if (state_numeric && !stateError) {
            String text = tvNumber.getText().toString();
            Expression expression = new ExpressionBuilder(text).build();
            try {
                double doubleResult = expression.evaluate();
                String textResult = String.valueOf(doubleResult);
                if (textResult.substring(textResult.length() - 1).equals("0")) {
                    int intResult = Double.valueOf(doubleResult).intValue();
                    tvNumber.setText(String.valueOf(intResult));
                } else {
                    tvNumber.setText(textResult);
                }
                state_numeric = true;
                state_dot = false;
                state_operation = false;
            } catch (ArithmeticException e) {
                tvNumber.setText("Syntax Error!");
                stateError = true;
                state_numeric = false;
            }
        }
    }

    private void setDeleteOperation() {
        if (!stateError) {
            if (tvNumber.length() == 0) {
                stateError = true;
            } else {
                int startValue = tvNumber.length() - 1;
                int lastValue = tvNumber.length();
                String lastTxt = tvNumber.getText().toString();
                String substring = lastTxt.substring(lastTxt.length() - 2, lastTxt.length() - 1);
                boolean lastPlus = substring.equals("+");
                boolean lastMinus = substring.equals("-");
                boolean lastMulti = substring.equals("*");
                boolean lastDiv = substring.equals("/");
                boolean lastDot = substring.equals(".");

                String newText = tvNumber.getText().delete(startValue, lastValue).toString();
                tvNumber.setText(newText);

                if (lastPlus || lastMinus || lastMulti || lastDiv) {
                    state_operation = true;
                    state_numeric = false;
                    state_dot = false;
                } else if (lastDot) {
                    state_dot = true;
                    state_numeric = false;
                } else {
                    state_numeric = true;
                }
            }
        }
    }

    private void click() {
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_times.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_mod.setOnClickListener(this);
        btn_plus_minus.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_result.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_0:
                setNumericOperation("0");
                break;
            case R.id.btn_1:
                setNumericOperation("1");
                break;
            case R.id.btn_2:
                setNumericOperation("2");
                break;
            case R.id.btn_3:
                setNumericOperation("3");
                break;
            case R.id.btn_4:
                setNumericOperation("4");
                break;
            case R.id.btn_5:
                setNumericOperation("5");
                break;
            case R.id.btn_6:
                setNumericOperation("6");
                break;
            case R.id.btn_7:
                setNumericOperation("7");
                break;
            case R.id.btn_8:
                setNumericOperation("8");
                break;
            case R.id.btn_9:
                setNumericOperation("9");
                break;
            case R.id.btn_plus:
                setAritmethicOperation("+");
                break;
            case R.id.btn_minus:
                setAritmethicOperation("-");
                break;
            case R.id.btn_times:
                setAritmethicOperation("*");
                break;
            case R.id.btn_divide:
                setAritmethicOperation("/");
                break;
            case R.id.btn_mod:
                setAritmethicOperation("%");
                break;
            case R.id.btn_result:
                setEqualOperation();
                break;
            case R.id.btn_clear:
                setClearOperation();
                break;
            case R.id.btn_point:
                setDecimalOperation();
                break;
            case R.id.btn_delete:
                setDeleteOperation();
                break;
        }
    }
}
