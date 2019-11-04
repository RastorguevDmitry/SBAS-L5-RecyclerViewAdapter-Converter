package com.rdi.converter;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ConvertActivity extends AppCompatActivity {
    private TextView nameStartConvert;
    private TextView nameResultConvert;
    private EditText editTextStartConvert;
    private EditText editTextResultConvert;

    private ListView listViewStartConvert;
    private ListView listViewResultConvert;
    private int listViewStartChoose = 0;
    private int listViewResultChoose = 1;

    static Conversion mConversion;
    private List<String> lableUnitList;
    private List<Unit> unitList;

    public static Intent getStartIntent(MainActivity mainActivity, Conversion conversion) {
        Intent intent = new Intent(mainActivity, ConvertActivity.class);
        mConversion = conversion;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        setTitle(mConversion.getLable());
        lableUnitList = mConversion.getLableUnitList();
        unitList = mConversion.getUnitList();
        initView();

        editTextStartConvert.addTextChangedListener(TextWatcherForEditTextStartConvert);
        editTextResultConvert.addTextChangedListener(TextWatcherForEditTextResultConvert);
        listViewInitAndListeners();
        updateViewAfterChooseListElement();
    }


    private void initView() {
        nameStartConvert = findViewById(R.id.name_start_convert);
        nameResultConvert = findViewById(R.id.name_result_convert);
        editTextStartConvert = findViewById(R.id.editText_start_convert);
        editTextResultConvert = findViewById(R.id.editText_result_convert);

        listViewStartConvert = findViewById(R.id.list_start_convert);
        listViewResultConvert = findViewById(R.id.list_result_convert);
    }


    private TextWatcher TextWatcherForEditTextStartConvert = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            editTextResultConvert.removeTextChangedListener(TextWatcherForEditTextResultConvert);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0)
                convertValuesFromList(Double.parseDouble(String.valueOf(s)), false);
        }

        @Override
        public void afterTextChanged(Editable s) {
            editTextResultConvert.addTextChangedListener(TextWatcherForEditTextResultConvert);
        }
    };

    TextWatcher TextWatcherForEditTextResultConvert = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            editTextStartConvert.removeTextChangedListener(TextWatcherForEditTextStartConvert);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0)
                convertValuesFromList(Double.parseDouble(String.valueOf(s)), true);
        }

        @Override
        public void afterTextChanged(Editable s) {
            editTextStartConvert.addTextChangedListener(TextWatcherForEditTextStartConvert);
        }
    };

    void listViewInitAndListeners() {
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lableUnitList);

        listViewStartConvert.setAdapter(adapter);
        listViewResultConvert.setAdapter(adapter);
        listViewStartConvert.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        listViewResultConvert.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        listViewStartConvert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                listViewStartConvert.
                        getChildAt(listViewStartChoose).
                        setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                listViewStartChoose = position;
                updateViewAfterChooseListElement();
            }
        });

        listViewResultConvert.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                listViewResultConvert.
                        getChildAt(listViewResultChoose).
                        setBackgroundColor(
                                getResources().
                                        getColor(
                                                R.color.colorPrimary));
                listViewResultChoose = position;
                updateViewAfterChooseListElement();
            }
        });
        
    }

    private void updateViewAfterChooseListElement() {
        listViewStartConvert.post(new Runnable() {
            @Override
            public void run() {
                listViewStartConvert.
                        getChildAt(listViewStartChoose).
                        setBackgroundColor(getResources().getColor(R.color.colorAccent));
                listViewResultConvert.
                        getChildAt(listViewResultChoose).
                        setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        nameStartConvert.setText(lableUnitList.get(listViewStartChoose));
        nameResultConvert.setText(lableUnitList.get(listViewResultChoose));
        convertValuesFromList(String.valueOf(
                editTextStartConvert.getText()).equals("") ? 0 :
                Double.parseDouble(String.valueOf(
                        editTextStartConvert.getText())), false);
    }

    void convertValuesFromList(double inputValue, boolean revers) {
        int convertToBasic;
        double convertFromBasic;
        double result;

        convertToBasic = unitList.get(listViewStartChoose).getConvertToBasic();
        convertFromBasic = unitList.get(listViewResultChoose).getConvertFromBasic();
        if (revers) {
            result = inputValue / convertToBasic / convertFromBasic;
            editTextStartConvert.setText(String.valueOf(result));
        } else {
            result = inputValue * convertToBasic * convertFromBasic;
            editTextResultConvert.setText(String.valueOf(result));
        }
    }
}
