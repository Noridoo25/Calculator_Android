package hcmute.edu.vn.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnPlus, btnMin, btnMulti, btnDiv, btnAC, btnDel, btnEqual;

    private TextView textHistory, textResult;

    private String number=null;

    double  lastnumber=0, firstnumber=0;

    boolean operator=false;

    String status=null;

    String history, result;

    boolean isEqual=false;

    boolean dot=true;

    boolean del=true;

    String pattern="###,###,###.###";

    DecimalFormat decimalFormat=new DecimalFormat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0=this.findViewById(R.id.btn0);
        btn1=this.findViewById(R.id.btn1);
        btn2=this.findViewById(R.id.btn2);
        btn3=this.findViewById(R.id.btn3);
        btn4=this.findViewById(R.id.btn4);
        btn5=this.findViewById(R.id.btn5);
        btn6=this.findViewById(R.id.btn6);
        btn7=this.findViewById(R.id.btn7);
        btn8=this.findViewById(R.id.btn8);
        btn9=this.findViewById(R.id.btn9);

        btnPlus=this.findViewById(R.id.btnPlus);
        btnMin=this.findViewById(R.id.btnMin);
        btnMulti=this.findViewById(R.id.btnMulti);
        btnDiv=this.findViewById(R.id.btnDiv);

        btnDot=this.findViewById(R.id.btnDot);
        btnEqual=this.findViewById(R.id.btnEqual);
        btnAC=this.findViewById(R.id.btnAC);
        btnDel=this.findViewById(R.id.btnDel);

        textHistory=this.findViewById(R.id.textHistroty);
        textResult=this.findViewById(R.id.textResult);

        btn0.setOnClickListener(view -> numberclick("0"));
        btn1.setOnClickListener(view -> numberclick("1"));
        btn2.setOnClickListener(view -> numberclick("2"));
        btn3.setOnClickListener(view -> numberclick("3"));
        btn4.setOnClickListener(view -> numberclick("4"));
        btn5.setOnClickListener(view -> numberclick("5"));
        btn6.setOnClickListener(view -> numberclick("6"));
        btn7.setOnClickListener(view -> numberclick("7"));
        btn8.setOnClickListener(view -> numberclick("8"));
        btn9.setOnClickListener(view -> numberclick("9"));

        btnPlus.setOnClickListener(view -> {

            if((isEqual)){
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+"+"));
            }else {
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+result+"+"));
            }
            if(operator){
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="div"){
                        Div();
                    }else{
                        if(status=="minus"){
                            Minus();
                        }else{
                            Plus();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="sum";
            isEqual=false;
            dot=true;
        });

        btnMin.setOnClickListener(view -> {

            if((isEqual)){
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+"-"));
            }else {
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+result+"-"));
            }
            if(operator){
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="div"){
                        Div();
                    }else{
                        if(status=="sum"){
                            Plus();
                        }else{
                            Minus();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="minus";
            isEqual=false;
            dot=true;
        });

        btnMulti.setOnClickListener(view -> {

            if((isEqual)){
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+"*"));
            }else {
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+result+"*"));
            }
            if(operator){
                if(status=="minus"){
                    Minus();
                }else{
                    if(status=="div"){
                        Div();
                    }else{
                        if(status=="sum"){
                            Plus();
                        }else{
                            Multi();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="multi";
            isEqual=false;
            dot=true;
        });

        btnDiv.setOnClickListener(view -> {

            if((isEqual)){
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+"/"));
            }else {
                history=textHistory.getText().toString();
                result=textResult.getText().toString();
                textHistory.setText((history+result+"/"));
            }
            if(operator){
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="minus"){
                        Minus();
                    }else{
                        if(status=="sum"){
                            Plus();
                        }else{
                            Div();
                        }
                    }
                }
            }
            operator=false;
            number=null;
            status="div";
            isEqual=false;
            dot=true;
        });

        btnEqual.setOnClickListener(view -> {

            history=textHistory.getText().toString();
            result=textResult.getText().toString();
            textHistory.setText((history+result));

            if(operator){
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="div"){
                        Div();
                    }else{
                        if(status=="minus"){
                            Minus();
                        }else
                            if(status=="sum"){

                            Plus();
                        }else {
                                firstnumber=Double.parseDouble(textResult.getText().toString());
                            }
                    }
                }
            }
            operator=false;
            isEqual=true;
            dot=false;
        });

        btnAC.setOnClickListener(view -> {
            number=null;
            operator=false;
            textResult.setText("0");
            textHistory.setText("");
            firstnumber=0;
            lastnumber=0;
            dot=true;
            del=true;

        });

        btnDel.setOnClickListener(view -> {

            if(del){
                textResult.setText("0");
            }
            else
            {
                number=number.substring(0,number.length()-1);
                if(number.length()==0){
                    btnDel.setClickable(false);
                }
                else {
                    if(number.contains(".")){
                        dot=false;
                    }
                    else {
                        dot=true;
                    }
                }
            }
            textResult.setText(number);
        });

        btnDot.setOnClickListener(view -> {

            if(dot){
                if(number==null){
                    number="0.";
                }
                else
                {
                    number=number+".";
                }
                textResult.setText(number);
            }
            dot=false;
        });

    }

    public void numberclick(String view) {

        if(number==null){
            number=view;
        }
        else
        {
            number=number+view;
        }
        textResult.setText(number);
        operator=true;
        del=false;
        btnDel.setClickable(true);
    }

    public void Minus(){
        if(firstnumber==0){
            firstnumber=Double.parseDouble(textResult.getText().toString());
        }
        else
        {
            lastnumber= Double.parseDouble(textResult.getText().toString());
            firstnumber=firstnumber-lastnumber;
        }
        textResult.setText(decimalFormat.format(firstnumber));
    }

    public void Plus(){

        lastnumber=Double.parseDouble(textResult.getText().toString());
        firstnumber=firstnumber+lastnumber;
        textResult.setText(decimalFormat.format(firstnumber));
    }

    public  void Multi(){

        if(firstnumber==0){
            firstnumber=1;
        }
        lastnumber=Double.parseDouble(textResult.getText().toString());
        firstnumber=firstnumber*lastnumber;
        textResult.setText(decimalFormat.format(firstnumber));
    }

    public void Div(){

        if(firstnumber==0){
            lastnumber=Double.parseDouble(textResult.getText().toString());
            firstnumber=lastnumber;
        }
        else
        {
            lastnumber=Double.parseDouble(textResult.getText().toString());
            firstnumber=firstnumber/lastnumber;
        }
        textResult.setText(decimalFormat.format(firstnumber));
    }
}