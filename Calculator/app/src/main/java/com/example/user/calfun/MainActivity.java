package com.example.user.calfun;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;


public class MainActivity extends ActionBarActivity {
    TextView status;
    float value1;
    String initialnumber;
    public static final String TAG = "StatusActivity";
    String chartoberemoved;               // this is used to concatenate characters to separate those in case of backspace
    double ythrootofx;
    String charunite="";
    boolean clearPressed=false;
    String historyElement="";
    String pophistoryElement;             // this is to store the history elements
    Stack equation = new Stack();         // main stack used during all operations
    Stack memory=new Stack();             // this stack is used for history.It is storing all the buttons pressed
    Stack numberMemory=new Stack();       // this will store only the numbers
    boolean position = true;
    String RHS,LHS,valueStored,myText2;
    Float addition;
    Float catchmemoryvalue= Float.valueOf(0);     // this is to store the MS value in future if reqd for M+ or M-
    Float subtraction,modwith;
    Float mult,ininumber,finalnumber;  // ininumber & finalnumber used in M+ operation
    Float div;
    Float percentage;
    Float reciprocal;
    Float square;
    Float cube;
    Float fact;
    Float factorial;
    Float negate;
    Float firstnumber;
    String remainingchartobeprinted;
    double powerof10;
    double powerxofy=1;
    double logarithm,sqroot,cuberoot,base,index;
    boolean equalPressed=false;
boolean backspaceClicked=false;
    int i = 0;
    int buttonpress = 0;
    int op = 0;
    String operator;
    int count = 0;
    String array[];
    String finalString = "";           // initially it is kept blank
    String store = new String();       // button once pressed is stored here
    String savepreviousnumber=new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        status = (TextView) findViewById(R.id.calresult);
        status.setText("0");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    static boolean fillspace = true; // this is used for displaying thing
    static boolean checkpos = true;



    public void num_Clicked(View v) {
        Button update = (Button) v;

        switch(update.getText().toString()) {  // once clicked by user,these are the only buttons,I want to display on the screen
            case"0":
            case"1":
            case"2":
            case"3":
            case"4":
            case"5":
            case"6":
            case"7":
            case"8":
            case"9":
            case"+":
            case"-":
            case"*":
            case"/":
            case"=":
            case".":
            case"π":
                if (fillspace && update.getText().toString().equals("0")) {  // fillspace is true,user has typed 0,we will ignore
                    return;
                }
                if (fillspace ) {  // fillspace is true, but user pressed button other than 0,we will display it
                    status.setText(update.getText());
                    fillspace = false;  // making fillspace false as for consecutive press,we have to append the buttons

                } else {
                    status.append(update.getText()); // new clicks are appended
                }

        }
        // Display logic completed, now moving to calculations
        store = update.getText().toString();
        if(store.equals("π")){
            equation.push("3.14");
        }

        switch(store){
            case"0":
            case"1":
            case"2":
            case"3":
            case"4":
            case"5":
            case"6":
            case"7":
            case"8":
            case"9":
                numberMemory.push(store); // stores only numbers for the memory case
        }
        pophistoryElement=store;
        historyElement+=pophistoryElement; // Storing all elements as to print when History button is pressed by the user
        memory.push(pophistoryElement);  // Button will be stored in stack after each press,no matter which button it is

        Log.d((TAG), store);
        Log.d((TAG), String.valueOf(equation));
        switch(store) {                   // this switch decides whether, the number has to be just set in stack or it hs to be appended to previous number
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                finalString += store;
                if (count != 0 && backspaceClicked==false) {  // if the buttonpress is not first time and if backspace is not pressed then first pop the number and proceed.
                    equation.pop();
                }
                equation.push(finalString);
                count++;
                Log.d((TAG), String.valueOf(equation));

                break;
            case "+": // buttons considered here, are all I want to display on screen, so all these buttons will show results after = is pressed,hence their further logic is continued
                operator = store;    //in a case"=" :
                equation.push(store);
                count = 0;          // count is initialized to zero for each button here, as after this the number entering in the stack will be the second operand, so has to be
                Log.d((TAG), String.valueOf(equation));  //different than the first one.
                finalString = "";

                break;
            case "-":
                operator = store;
                equation.push(store);
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";

                break;
            case "*":
                operator = store;
                equation.push(store);
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";

                break;
            case "/":
                operator = store;
                equation.push(store);
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";
                break;
            case "n!":
                operator = store;
                status.append("!");
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";
                break;
            case"x^y":
                operator = store;
                status.append("raise to");
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";
                break;
            case"y√x":
                operator = store;
                status.append("th root of");
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";
                break;
            case"Mod":
                operator = store;
                status.append("mod");
                count = 0;
                Log.d((TAG), String.valueOf(equation));
                finalString = "";
                break;
            case "=":
                if (operator.equals("+")) {
                    RHS = (String) equation.pop();
                    Log.d((TAG), RHS);
                    operator = (String) equation.pop();
                    Log.d((TAG), operator);
                    LHS = (String) equation.pop(); // numbers are popped from the stack
                    Log.d((TAG), LHS);
                    addition = Float.parseFloat(RHS) + Float.parseFloat(LHS);
                    Log.d((TAG), String.valueOf(addition));
                    myText2 = Float.toString(addition);
                    Log.d((TAG), myText2);
                    equation.push(myText2);               // addition pushed on the stack
                    Log.d((TAG), String.valueOf(equation));
                    status.setText(myText2);
                } else if (operator.equals("-")) {
                    RHS = (String) equation.pop();
                    operator = (String) equation.pop();
                    LHS = (String) equation.pop();       // numbers popped
                    subtraction = Float.parseFloat(LHS) - Float.parseFloat(RHS);
                    myText2 = Float.toString(subtraction);
                    equation.push(myText2);               //subtraction pushed on stack
                    Log.d((TAG), String.valueOf(equation));
                    status.setText(myText2);
                } else if (operator.equals("*")) {
                    RHS = (String) equation.pop();
                    operator = (String) equation.pop();
                    LHS = (String) equation.pop();
                    mult = Float.parseFloat(LHS) * Float.parseFloat(RHS);
                    myText2 = Float.toString(mult);
                    equation.push(myText2);
                    Log.d((TAG), String.valueOf(equation));
                    status.setText(myText2);
                } else if (operator.equals("/")) {
                    RHS = (String) equation.pop();
                    operator = (String) equation.pop();
                    LHS = (String) equation.pop();
                    div = Float.parseFloat(LHS) / Float.parseFloat(RHS);
                    myText2 = Float.toString(div);
                    equation.push(myText2);
                    Log.d((TAG), String.valueOf(equation));
                    status.setText(myText2);
                } else if (operator.equals("n!")) {

                    valueStored = (String) equation.pop();
                    Log.d((TAG), valueStored);
                    fact = Float.parseFloat(valueStored);
                    Log.d((TAG), String.valueOf(fact));
                    factorial = fact;
                    Log.d((TAG), String.valueOf(factorial));
                    while (fact > 1) {
                        factorial = factorial * (fact - 1);    // factorial obtained
                        fact--;
                    }
                    myText2 = Float.toString(factorial);
                    status.setText(myText2);
                    equation.push(myText2);                    // factorial pushed
                    Log.d((TAG), String.valueOf(equation));
                }
                else if(operator.equals("x^y")){
                    valueStored= (String) equation.pop();
                    index=Float.parseFloat(valueStored);

                    Log.d((TAG), String.valueOf((double) index));
                    valueStored= (String) equation.pop();
                    base=Float.parseFloat(valueStored);
                    Log.d((TAG), String.valueOf((double) base));
                    while(index>0) {
                        powerxofy = powerxofy*base;
                        index--;
                    }
                    Log.d((TAG), String.valueOf(powerxofy));
                    myText2 = Float.toString((float) powerxofy);
                    status.setText(myText2);
                    equation.push(myText2);                     // x^y pushed
                }
                else if(operator.equals("Mod")){
                    valueStored= (String) equation.pop();
                    firstnumber=Float.parseFloat(valueStored);
                    Log.d((TAG), String.valueOf((double) firstnumber));
                    valueStored= (String) equation.pop();
                    modwith=Float.parseFloat(valueStored);
                    Log.d((TAG), String.valueOf((double) modwith));
                    modwith=modwith%firstnumber;
                    myText2 = Float.toString((float) modwith);
                    status.setText(myText2);
                    equation.push(myText2);
                }
                else if(operator.equals("y√x")){
                    valueStored= (String) equation.pop();
                    firstnumber=Float.parseFloat(valueStored);
                    Log.d((TAG), String.valueOf((double) firstnumber));
                    valueStored= (String) equation.pop();
                    modwith=Float.parseFloat(valueStored);
                    Log.d((TAG), String.valueOf((double) modwith));
                    ythrootofx=Math.pow(firstnumber,(1/modwith));
                    myText2 = Float.toString((float) ythrootofx);
                    status.setText(myText2);
                    equation.push(myText2);
                }
                equalPressed = true;                         // this is for memory thing to store accordingly in MS
                break;
            case "%":
                valueStored = (String) equation.pop();
                percentage = Float.parseFloat(valueStored);
                percentage = percentage / 100;
                String myText = Float.toString(percentage);
                status.setText(myText);
                equation.push(myText);
                Log.d((TAG), String.valueOf(equation));
                percentage = Float.valueOf(0);

                break;
            case "1/x":
                valueStored = (String) equation.pop();
                reciprocal = Float.parseFloat(valueStored);
                reciprocal = 1 / reciprocal;
                myText2 = Float.toString(reciprocal);
                status.setText(myText2);
                equation.push(myText2);
                Log.d((TAG), String.valueOf(equation));
                reciprocal = Float.valueOf(0);
                break;
            case "x^2":
                valueStored = (String) equation.pop();
                square = Float.parseFloat(valueStored);
                square = square * square;
                myText2 = Float.toString(square);
                status.setText(myText2);
                equation.push(myText2);
                Log.d((TAG), String.valueOf(equation));
                Log.d((TAG), String.valueOf(memory));
                square = Float.valueOf(0);
                break;
            case "x^3":
                valueStored = (String) equation.pop();
                cube = Float.parseFloat(valueStored);
                cube = cube * cube * cube;
                myText2 = Float.toString(cube);
                status.setText(myText2);
                equation.push(myText2);
                Log.d((TAG), String.valueOf(equation));
                Log.d((TAG), String.valueOf(memory));
                square = Float.valueOf(0);
                break;

            case "log":
                valueStored = (String) equation.pop();
                logarithm = Float.parseFloat(valueStored);
                logarithm = Math.log10(logarithm);
                myText2 = Float.toString((float) logarithm);
                status.setText(myText2);
                equation.push(myText2);
                break;
            case "√":
                valueStored = (String) equation.pop();
                sqroot = Float.parseFloat(valueStored);
                sqroot = Math.sqrt(sqroot);
                myText2 = Float.toString((float) sqroot);
                status.setText(myText2);
                equation.push(myText2);
                break;
            case "x^(1/3)":
                valueStored = (String) equation.pop();
                cuberoot = Float.parseFloat(valueStored);
                cuberoot = Math.cbrt(cuberoot);
                myText2 = Float.toString((float) cuberoot);
                status.setText(myText2);
                equation.push(myText2);
                break;
            case "C":
                status.setText("0");           // clearing the screen
                finalString = "";              // making it blank, so the next number will be stored separately
             //   equation.pop();
                fillspace = true;
                break;
            case "History":

                status.setText(historyElement);
                Log.d((TAG), String.valueOf(memory));
                break;
            case "←":
                chartoberemoved=status.getText().toString();
                Log.d((TAG),chartoberemoved);
                remainingchartobeprinted=chartoberemoved.replaceFirst(".$","");  // last character removed
                Log.d((TAG),remainingchartobeprinted);
                status.setText(remainingchartobeprinted);
                Log.d((TAG), String.valueOf(equation));
                equation.pop();
                Log.d((TAG), String.valueOf(equation));
                finalString="";                           // again next character should be separate on stack
                backspaceClicked=true;
                //fillspace=true;
                break;
            case"CE":
                chartoberemoved=status.getText().toString();
                Log.d((TAG),chartoberemoved);
                remainingchartobeprinted=chartoberemoved.replaceFirst(".$","");
                Log.d((TAG),remainingchartobeprinted);
                status.setText(remainingchartobeprinted);
                Log.d((TAG), String.valueOf(equation));
                equation.pop();
                Log.d((TAG), String.valueOf(equation));
                finalString="";
                backspaceClicked=true;
                break;
            case "MR":                     // number is popped from numberMemory stack and is printed
                status.setText(savepreviousnumber);
                equation.push(savepreviousnumber);
                break;
            case"MS":
                if (equalPressed) {             // if some value is calculated, it will store the value & MS will save the same.
                    savepreviousnumber = myText2; // result of any operation is stored here
                    Log.d((TAG), savepreviousnumber);
                    catchmemoryvalue=Float.parseFloat(savepreviousnumber);    // this is stored in temporary variable, because it may require i future for M+ , M- calculations
                }
                else {
                    savepreviousnumber = (String) numberMemory.pop();  // this will be the last number pressed, no operation considered here.
                    Log.d((TAG), savepreviousnumber);
                    catchmemoryvalue=Float.parseFloat(savepreviousnumber);
                }
                equalPressed=false;
                break;
            case"M+":
                if(equalPressed){
                    savepreviousnumber = myText2;                        // updated answer will be stored
                    ininumber=Float.parseFloat(savepreviousnumber);
                    finalnumber=ininumber+catchmemoryvalue;
                    Log.d((TAG), String.valueOf(finalnumber));
                    savepreviousnumber= String.valueOf(finalnumber);    // This will be the case when obtained operation will be added & displayed on screen

                }
                else{
                    savepreviousnumber = (String) numberMemory.pop();    //pressed number will be stored
                    ininumber=Float.parseFloat(savepreviousnumber);
                    finalnumber=ininumber+catchmemoryvalue;
                    Log.d((TAG), String.valueOf(finalnumber));
                    savepreviousnumber= String.valueOf(finalnumber);     // added value is stored in saveprevnumber & when MR is pressed, it will print added number

                }
                equalPressed=false;
                break;
            case"M-":
                if(equalPressed){
                    savepreviousnumber = myText2;                        // updated answer will be stored
                    ininumber=Float.parseFloat(savepreviousnumber);
                    finalnumber=catchmemoryvalue-ininumber;
                    Log.d((TAG), String.valueOf(finalnumber));
                    savepreviousnumber= String.valueOf(finalnumber);    // This will be the case when obtained operation will be added & displayed on screen

                }
                else{
                    savepreviousnumber = (String) numberMemory.pop();    //pressed number will be stored
                    ininumber=Float.parseFloat(savepreviousnumber);
                    finalnumber=catchmemoryvalue-ininumber;
                    Log.d((TAG), String.valueOf(finalnumber));
                    savepreviousnumber= String.valueOf(finalnumber);     // added value is stored in saveprevnumber & when MR is pressed, it will print added number

                }
                equalPressed=false;
                break;
            case"MC":
                numberMemory.clear();
                savepreviousnumber="";
                break;
            case"+-":
                valueStored = (String) equation.pop();
                negate = Float.parseFloat(valueStored);
                negate = -1*negate;                           // negating the number
                myText2 = Float.toString( negate);
                status.setText(myText2);
                equation.push(myText2);
                break;
            case"10^x":
                valueStored = (String) equation.pop();
                powerof10 = Float.parseFloat(valueStored);
                powerof10=Math.pow(10,powerof10);              // 10 raised to x is calculated
                myText2 = Float.toString((float) powerof10);
                status.setText(myText2);
                equation.push(myText2);                        // value stored on the stack
                break;
        }
    }
}

