package presentation;

import model.MainModelImplementation;
import model.model_interface.MainModel;
import repositories.HttpConnect;
import ui.TransferResultCallBack;
import ui.view_interface.MainViewController;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainPresenterImplementation extends BasePresenter {
    private MainViewController view;
    private MainModel model = new MainModelImplementation();
    private final HttpConnect httpClient = new HttpConnect();

    public MainPresenterImplementation(MainViewController view) {
        this.view = view;
    }

    public void initRate() {
        try {
            httpClient.sendGet();
            view.setRate(new BigDecimal(httpClient.getRate(true)).setScale(2, RoundingMode.HALF_UP).toString());
        } catch (IOException | InterruptedException | IllegalArgumentException | SecurityException e) {
            view.alertGetRate();
        }
    }

    public String getRate(Boolean inversely) {
        return httpClient.getRate(inversely);
    }

    public void transfer(TransferResultCallBack callBack, String newValue, Boolean inversely) {
        callBack.setResult(model.transfer(newValue, getRate(inversely)));
    }
}
