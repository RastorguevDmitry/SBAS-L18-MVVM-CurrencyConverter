package com.rdi.currencyconverter.presentation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.rdi.currencyconverter.R;

public class CurrencyConverterActivity extends AppCompatActivity {
    private EditText editTextStartConvert;
    private TextView textViewResultConvert;
    private TextView mConversionRate;
    private ListView listViewStartConvert;
    private ListView listViewResultConvert;

    private int listViewStartChoose = 0;
    private int listViewResultChoose = 1;

    private CurrencyConverterViewModel mViewModel;
    private View mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setupMvvm();

        listViewInitAndListeners();
      //  someCurrencyChose();
    }

    private void initView() {

        mConversionRate = findViewById(R.id.conversionRate);
        editTextStartConvert = findViewById(R.id.editText_start_convert);
        textViewResultConvert = findViewById(R.id.editText_result_convert);
        mLoadingView = findViewById(R.id.loading_view);
        listViewStartConvert = findViewById(R.id.list_start_convert);
        listViewStartConvert.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewStartConvert.setItemChecked(listViewStartChoose, true);

        listViewResultConvert = findViewById(R.id.list_result_convert);
        listViewResultConvert.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewResultConvert.setItemChecked(listViewResultChoose, true);

        editTextStartConvert.addTextChangedListener(TextWatcherForEditTextStartConvert);
    }


    private void setupMvvm() {
        mViewModel = ViewModelProviders.of(this, new CurrencyViewModelFactory(this))
                .get(CurrencyConverterViewModel.class);
        mViewModel.getCurrenciesLable().observe(this, currencies -> {
            listViewStartConvert.setAdapter(new ArrayAdapter<>(this,
                    R.layout.item_convert, currencies));
            listViewResultConvert.setAdapter(new ArrayAdapter<>(this,
                    R.layout.item_convert, currencies));
        });
        mViewModel.getConvertedText().observe(this, convertedText ->
                textViewResultConvert.setText(convertedText));
        mViewModel.isLoading().observe(this, isLoading ->
                mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mViewModel.getConversionRate().observe(this, rate -> mConversionRate.setText(rate));
        mViewModel.getErrors().observe(this, error ->
                Toast.makeText(this, error, Toast.LENGTH_LONG).show());
        mViewModel.loadCurrencies();
    }


    void listViewInitAndListeners() {

        listViewStartConvert.setOnItemClickListener((parent, itemClicked, position, id) -> {
            listViewStartChoose = listViewStartConvert.getCheckedItemPosition();
            someCurrencyChose();
        });

        listViewResultConvert.setOnItemClickListener((parent, itemClicked, position, id) -> {
            listViewResultChoose = listViewResultConvert.getCheckedItemPosition();
            someCurrencyChose();
        });

    }

    private void someCurrencyChose() {
        Log.d("someCurrencyChose", "someCurrencyChose: " + listViewStartChoose + listViewResultChoose );
        mViewModel.updateConversionRate(listViewStartChoose,
                listViewResultChoose);
    }

    private TextWatcher TextWatcherForEditTextStartConvert = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0)
                mViewModel.convert(
                        listViewStartChoose,
                        listViewResultChoose,
                        String.valueOf(editTextStartConvert.getText()));
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };



}
