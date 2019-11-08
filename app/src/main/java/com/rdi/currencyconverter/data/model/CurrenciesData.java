package com.rdi.currencyconverter.data.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация о валютах и курсах конвертации
 *
 **/

//http://www.cbr.ru/scripts/XML_daily.asp
// ValCurs - название всего массива из API
@Root(name = "ValCurs", strict = false) //strict - если поле отсутствует, пропустить
public class CurrenciesData {

    @ElementList(inline = true)
    //@ElementList(inline = true)
    private List<CurrencyData> mCurrencies;

    public List<CurrencyData> getCurrencies() {
        return new ArrayList<>(mCurrencies);
    }
}
