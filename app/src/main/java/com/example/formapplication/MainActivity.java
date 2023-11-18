package com.example.formapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Button submit;
EditText name,email;
RadioGroup radioGroup;
RadioButton radioButton;
TextView showName,showEmail,showGenders,showCheckBox,dropdownResult;
CheckBox java,cpp,python,javascript;
Spinner countryDropdown;
ArrayList<String> countryArray = new ArrayList<String>();
ArrayAdapter<String> arrayAdapter;
private String selectedCountry = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        radioGroup = findViewById(R.id.radioGroup);
        showName = findViewById(R.id.showName);
        showEmail = findViewById(R.id.showEmail);
        showGenders = findViewById(R.id.showGenders);
        java = findViewById(R.id.java);
        cpp = findViewById(R.id.cpp);
        python = findViewById(R.id.python);
        javascript = findViewById(R.id.javascript);
        showCheckBox = findViewById(R.id.showCheckBox);
        countryDropdown = findViewById(R.id.countryDropdown);
        dropdownResult = findViewById(R.id.dropdownResult);
        submit = findViewById(R.id.submit);

        countryArray.add("Pakistan");
        countryArray.add("India");
        countryArray.add("China");
        arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,countryArray);
        countryDropdown.setAdapter(arrayAdapter);

        countryDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected country from the Spinner and store it in the global variable
                selectedCountry = countryArray.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case where nothing is selected (optional)
                selectedCountry = ""; // You can set some default text here
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Submit();
            }
        });
    }
    public void Submit(){
        int selectedButton = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedButton);
        if (selectedButton == -1){
            Toast.makeText(this, "Nothing Selected Gender.", Toast.LENGTH_SHORT).show();
        }else{
            showName.setText(name.getText().toString());
            showEmail.setText(email.getText().toString());
            showGenders.setText(radioButton.getText().toString());
        }

        StringBuilder checkBoxResult = new StringBuilder();

        if(java.isChecked()){
            checkBoxResult.append("\n Java");
        }
        if(cpp.isChecked()){
            checkBoxResult.append("\n C++");
        }
        if(python.isChecked()){
            checkBoxResult.append("\n Python");
        }
        if(javascript.isChecked()){
            checkBoxResult.append("\n JavaScript");
        }
        showCheckBox.setText(checkBoxResult.toString());
        dropdownResult.setText(selectedCountry);

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("Name",name.getText().toString());
        intent.putExtra("Email",email.getText().toString());
        intent.putExtra("Gender",radioButton.getText().toString());
        intent.putExtra("Courses",checkBoxResult.toString());
        intent.putExtra("Country",selectedCountry);
        startActivity(intent);

//        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}