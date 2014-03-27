package com.ajitjangra.simplecalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalActivity extends Activity {
   EditText et;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_cal);
      
      et = (EditText) findViewById(R.id.editText1);
   }
   
   public void onClick(View v) {
      switch (v.getId()) {
      
         case R.id.button1:
            addOperator("+");
            break;
         case R.id.button2:
            addOperator("-");
            break;
         case R.id.button3:
            addOperator("*");
            break;
         case R.id.button4:
            addOperator("/");
            break;
         case R.id.button5:
            String resultString = readEditText(et);
            if(resultString.equals("")){
               Toast.makeText(CalActivity.this, "There is nothing to calculate.", Toast.LENGTH_SHORT).show();
               
            } else if(resultString.endsWith("+") || resultString.endsWith("-") || resultString.endsWith("*") || resultString.endsWith("/")){
               Toast.makeText(CalActivity.this, "Syntex error.", Toast.LENGTH_SHORT).show();
            }else{
               long result = getResult(resultString);
               Toast.makeText(CalActivity.this, "Result is "+result, Toast.LENGTH_SHORT).show();
               et.setText(""+result);
               et.setSelection(readEditText(et).length());
            }
            
            break;
      }
   }
   
   private long getResult(String resultString) {
      String[] arr = resultString.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
      
      String op = null;
      long result = 0;
      
      for (String s : arr) {
         System.out.println("ajit " + s);
         
         if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            op = s;
         } else {
            if (op == null) {
               result = Long.parseLong(s);
            } else {
               if (op.equals("+")) {
                  result = result + Long.parseLong(s);
               } else if (op.equals("-")) {
                  result = result - Long.parseLong(s);
               } else if (op.equals("*")) {
                  result = result * Long.parseLong(s);
               } else if (op.equals("/")) {
                  result = result / Long.parseLong(s);
               }
            }
         }
      }
      
      return result;
   }
   
   private String readEditText(EditText et2) {
      
      return et2.getText().toString().trim();
   }
   
   private void addOperator(String operator) {
      String etText = readEditText(et);
      
      if (etText.length() > 0) {
         char lastChar = etText.charAt(etText.length() - 1);
         
         if (lastChar == '+' || lastChar == '-' || lastChar == '*' || lastChar == '/') {
            Toast.makeText(CalActivity.this, "Invalid operation", Toast.LENGTH_LONG).show();
         } else {
            et.setText(etText + operator);
            et.setSelection(et.getText().length());
         }
      } else {
         Toast.makeText(CalActivity.this, "Invalid operation", Toast.LENGTH_LONG).show();
      }
      
   }
}
