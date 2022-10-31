package com.prachi.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
          String oldString = display.getText().toString();
          int cursorPosition = display.getSelectionStart();
          String leftString=oldString.substring(0,cursorPosition);
          String rightString=oldString.substring(cursorPosition);

          if(getString(R.string.display).equals(display.getText().toString())){
              display.setText(strToAdd);
          }
          else {
              display.setText(String.format("%s%s%s", leftString, strToAdd, rightString));
          }
        display.setSelection(cursorPosition+1);

    }
    public void zeroButton(View view){
        updateText("0");
    }

    public void oneButton(View view){
        updateText("1");
    }

    public void twoButton(View view){
        updateText("2");
    }

    public void threeButton(View view){
        updateText("3");
    }

    public void fourButton(View view){
        updateText("4");
    }

    public void fiveButton(View view){
        updateText("5");
    }
    public void sixButton(View view){
        updateText("6");
    }

    public void sevenButton(View view){
        updateText("7");
    }

    public void eightButton(View view){
        updateText("8");
    }

    public void nineButton(View view){
        updateText("9");
    }

    public void clearButton(View view){
        display.setText("");
    }

    public void equalButton(View view){
        String expression=display.getText().toString();
        expression=expression.replaceAll("×","*");
        expression=expression.replaceAll("÷","/");

        Expression exp = new Expression(expression);

        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceButton(View view){
        int cursorPosition = display.getSelectionStart();
        int textLen=display.getText().length();

        if(cursorPosition!=0&&textLen!=0){
            SpannableStringBuilder selection =(SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition-1,cursorPosition,"");
            display.setText(selection);
            display.setSelection(cursorPosition-1);
        }
    }

    public void pointButton(View view){
        updateText(".");
    }

    public void plusMinusButton(View view){
        int cursorPosition = display.getSelectionStart();
        String text = display.getText().toString();
        display.setText(String.format("-%s", text));
        display.setSelection(cursorPosition+1);

    }

    public void addButton(View view){
        updateText("+");
    }

    public void subtractButton(View view){
        updateText("-");
    }

    public void multiplyButton(View view){
        updateText("×");
    }

    public void divideButton(View view){
        updateText("÷");
    }

    public void exponentButton(View view){
        updateText("^");
    }

    public void parenthesesButton(View view){
        int cursorPosition=display.getSelectionStart();
        int openParentheses=0;
        int closeParentheses=0;
        int textLen= display.getText().length();

        for(int i=0;i<cursorPosition;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openParentheses++;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closeParentheses++;
            }

        }

        if(openParentheses==closeParentheses || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }

        else if(closeParentheses<openParentheses && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPosition+1);



    }
}