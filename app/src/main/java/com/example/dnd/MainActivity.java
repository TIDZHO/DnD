package com.example.dnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;

import static android.view.KeyEvent.KEYCODE_ENTER;

public class MainActivity extends AppCompatActivity {

    String[] set = {"Лавкрафт", "Фэнтези", "Постапокалипсис" };
    String[] loc = {"Вокзал", "Площадь независимости", "Черная пещера", "Ратуша", "Таверна", "Замок", "Лес", "Подземелье", "Выжженная земля", "Обломки корабля", "Бункер", "Руины", "Пустошь"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView btn = findViewById(R.id.btn);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, set);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               if (parent.getItemAtPosition(position).equals("Выбрана локация: ")) { }
               else { String setitem = parent.getItemAtPosition(position).toString(); } }

           @Override
            public void onNothingSelected(AdapterView<?> parent) { }});

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, loc);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Выбрна локация: ")) { }
                else { String locitem = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Выбран: " + locitem, Toast.LENGTH_SHORT).show();} }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String setitem = spinner.getSelectedItem().toString();
                    String locitem = spinner2.getSelectedItem().toString();
                    EditText name = findViewById(R.id.name);
                    EditText obj = findViewById(R.id.name2);
                    String sendname  = name.getText().toString();
                    String sendobj  = obj.getText().toString();
                    String sendset = setitem;
                    String sendloc = locitem;
                    ((EditText)findViewById(R.id.name)).setOnEditorActionListener(
                            (v1, actionId, event) -> {
                                if (actionId == EditorInfo.IME_ACTION_DONE ||
                                        event != null &&
                                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                    if (event == null || !event.isFunctionPressed()) {
                                        // the user is done typing.

                                        return true; // consume.
                                    }
                                }
                                return false; // pass on to other listeners.
                            }
                    );

                    Intent intent = new Intent(MainActivity.this, Text.class);
                    intent.putExtra("name", sendname);
                    intent.putExtra("obj", sendobj);
                    intent.putExtra("set", sendset);
                    intent.putExtra("loc", sendloc);



                    startActivity(intent);finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}




