package com.rdi.currencyconverter.presentation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rdi.currencyconverter.data.CurrenciesRepository;
import com.rdi.currencyconverter.data.CurrencyConverter;
import com.rdi.currencyconverter.domain.ConversionInteractor;
import com.rdi.currencyconverter.domain.CurrenciesInteractor;
import com.rdi.currencyconverter.domain.ICurrenciesRepository;
import com.rdi.currencyconverter.presentation.utils.ResourceWrapper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Фабрика вьюмоделей конвертера валют
 **/
public class CurrencyViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mApplicationContext;

    public CurrencyViewModelFactory(@NonNull Context context) {
        mApplicationContext = context.getApplicationContext();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (CurrencyConverterViewModel.class.equals(modelClass)) {
            ICurrenciesRepository currenciesRepository = new CurrenciesRepository(new CurrencyConverter());
            CurrenciesInteractor interactor = new CurrenciesInteractor(currenciesRepository);
            Executor executor = Executors.newSingleThreadExecutor();
            ResourceWrapper resourceWrapper = new ResourceWrapper(mApplicationContext.getResources());
            // noinspection unchecked
            return (T) new CurrencyConverterViewModel(
                    interactor,
                    executor,
                    resourceWrapper,
                    new ConversionInteractor(resourceWrapper));
        } else {
            return super.create(modelClass);
        }
    }
}