package com.rdi.currencyconverter.domain;

import androidx.annotation.NonNull;

import com.rdi.currencyconverter.domain.model.Currency;

import java.io.IOException;
import java.util.List;

/**
 * Интерактор для загрузки списка валют
 *
 **/
public class CurrenciesInteractor {
    private final ICurrenciesRepository mCurrenciesRepository;

    public CurrenciesInteractor(@NonNull ICurrenciesRepository currenciesRepository) {
        mCurrenciesRepository = currenciesRepository;
    }

    /**
     * Загружает список валют
     */
    public List<Currency> loadCurrencies() throws LoadCurrenciesException {
        try {
            return mCurrenciesRepository.loadCurrencies();
        } catch (IOException e) {
            throw new LoadCurrenciesException("Не удалось загрузить список валют", e);
        }
    }
}