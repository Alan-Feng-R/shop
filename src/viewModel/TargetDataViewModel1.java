package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TargetDataViewModel1 {
    private StringProperty name = new SimpleStringProperty();

    private static TargetDataViewModel1 viewModel1 = new TargetDataViewModel1();

    private TargetDataViewModel1(){}

    public static TargetDataViewModel1 getInstance(){
        return viewModel1;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public static TargetDataViewModel1 getViewModel() {
        return viewModel1;
    }

    public static void setViewModel(TargetDataViewModel1 viewModel1) {
        TargetDataViewModel1.viewModel1 = viewModel1;
    }
}
