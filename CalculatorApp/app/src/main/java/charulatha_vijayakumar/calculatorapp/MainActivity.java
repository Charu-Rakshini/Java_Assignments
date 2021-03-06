package charulatha_vijayakumar.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements TextView.OnClickListener, TextView.OnLongClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Button objects
        Button number1 = findViewById(R.id.bNum1);
        number1.setOnClickListener(this);

        Button number2 = findViewById(R.id.bNum2);
        number2.setOnClickListener(this);

        Button number3 = findViewById(R.id.bNum3);
        number3.setOnClickListener(this);

        Button number4 = findViewById(R.id.bNum4);
        number4.setOnClickListener(this);

        Button number5 = findViewById(R.id.bNum5);
        number5.setOnClickListener(this);

        Button number6 = findViewById(R.id.bNum6);
        number6.setOnClickListener(this);

        Button number7 = findViewById(R.id.bNum7);
        number7.setOnClickListener(this);

        Button number8 = findViewById(R.id.bNum8);
        number8.setOnClickListener(this);

        Button number9 = findViewById(R.id.bNum9);
        number9.setOnClickListener(this);

        Button number0 = findViewById(R.id.bNum0);
        number0.setOnClickListener(this);

        Button decimalDot = findViewById(R.id.bDot); // Decimal point
        decimalDot.setOnClickListener(this);

        Button add = findViewById(R.id.buttonPlus); // add(+) sign
        add.setOnClickListener(this);

        Button subtract = findViewById(R.id.buttonMinus); // subtract(-) sign
        subtract.setOnClickListener(this);

        Button multiply = findViewById(R.id.buttonMultiply); // multiply(*) sign
        multiply.setOnClickListener(this);

        Button divide = findViewById(R.id.buttonDivide); // divide(/) sign
        divide.setOnClickListener(this);

        Button clear = findViewById(R.id.bClear); //clear button
        clear.setOnClickListener(this);
        clear.setOnLongClickListener(this);

        Button equals = findViewById(R.id.bNumEqual); // equals button
        equals.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        int currEleID = v.getId();
        TextView currTV=findViewById(currEleID);
        String currNum = currTV.getText().toString(); // current value pressed

        TextView calcArea = findViewById(R.id.tvCalc);
        TextView calcExpression = findViewById(R.id.tvExpression);
        String currCalc = calcArea.getText().toString(); //getting current value from result tab
        String currExpression = calcExpression.getText().toString(); // getting current value from expression tab

        //Clear button logic for deleting one character at a time
        if (currEleID == R.id.bClear){
            try{
                int calcLength = currCalc.length();
                currCalc = currCalc.substring(0,calcLength-1);
                calcArea.setText(currCalc);
                currExpression=currCalc;
                calcExpression.setText(currExpression);
            }catch (Exception e){
            }
        }
        //Using calculate method for computing result correspondingly when equals button is pressed
        else if(currEleID == R.id.bNumEqual){
            if(currCalc.contains("+")) {
                calculateResult("add",currNum,currCalc, currExpression,calcArea,calcExpression);
            }
            else if (currCalc.contains("-")) {
                calculateResult("sub",currNum,currCalc, currExpression,calcArea,calcExpression);
            }
            else if (currCalc.contains("x")) {
                calculateResult("mul",currNum,currCalc, currExpression,calcArea,calcExpression);
            }
            else if (currCalc.contains("/")) {
                calculateResult("div",currNum,currCalc, currExpression,calcArea,calcExpression);
            }
            calcExpression.setText(currCalc);
            calcArea.setTextSize(36); // Enlarging text for highlighting result
        }
        //clearing text for next expression
        else if(currCalc.length()<currExpression.length()){
            calcArea.setTextSize(30);
            currExpression=currNum;
            currCalc=currNum;
            calcArea.setText(currCalc);
            calcExpression.setText(currExpression);
        }
        //adding numbers to result and expression tab
        else {
            currExpression+=currNum;
            currCalc+=currNum;
            calcArea.setText(currCalc);
            calcExpression.setText(currExpression);
        }

    }

    //On long press of Clear button, all values in result and expression tab are deleted
    @Override
    public boolean onLongClick(View v) {
        TextView calcArea = findViewById(R.id.tvCalc);
        TextView calcExpression = findViewById(R.id.tvExpression);
        calcArea.setText("");
        calcExpression.setText("");
        calcArea.setTextSize(30);
        return true;
    }

    //calculate method definition for add, subtract, multiply and divide logic
    public void calculateResult(String operation, String currNum,String currCalc,String currExpression, TextView calcArea,TextView calcExpression){
        String strResult="";
        String[] variables;
            switch (operation){
                case "add":
                    variables = currCalc.split("\\+",2);//escape sequence used for + sign
                    strResult=String.valueOf(Float.parseFloat(variables[0]) + Float.parseFloat(variables[1]));
                    break;
                case "sub":
                    variables = currCalc.split("-",2);
                    strResult=String.valueOf(Float.parseFloat(variables[0]) - Float.parseFloat(variables[1]));
                    break;
                case "mul":
                    variables = currCalc.split("x",2);
                    strResult=String.valueOf(Float.parseFloat(variables[0]) * Float.parseFloat(variables[1]));
                    break;
                case "div":
                    variables = currCalc.split("/",2);
                    strResult=String.valueOf(Float.parseFloat(variables[0]) / Float.parseFloat(variables[1]));
                    break;
            }
            System.out.println("strResult "+strResult);

            String decimalSplit[] = strResult.split(".",2);
            if(strResult.contains(".0") || decimalSplit[1] == ".0"){
                currCalc=strResult.replace(".0","");
            }
            else{
                currCalc=strResult;
            }
            calcArea.setText(currCalc);
            calcExpression.setText(currExpression);


    }
}