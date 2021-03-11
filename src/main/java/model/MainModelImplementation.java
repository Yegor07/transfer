package model;

import model.model_interface.MainModel;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainModelImplementation extends BaseModel implements MainModel {

    public String transfer(String newValue, String rate) {

        BigDecimal value = new BigDecimal(newValue);
        value = value.multiply(new BigDecimal(rate));
        return value.setScale(2, RoundingMode.HALF_UP).toString();
    }

}
