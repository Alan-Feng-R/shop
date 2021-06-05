package viewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SourceDataviewModel1 {
    private StringProperty name = new SimpleStringProperty();

    private static SourceDataviewModel1 viewModel1 = new SourceDataviewModel1();

    private SourceDataviewModel1(){}

    public static SourceDataviewModel1 getInstance() {
        return viewModel1;
    }

    public void setTargetData() {
        TargetDataViewModel1 viewModel1 = TargetDataViewModel1.getInstance();
        viewModel1.setName(name.get());
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

    public static SourceDataviewModel1 getviewModel1() {
        return viewModel1;
    }

    public static void setviewModel1(SourceDataviewModel1 viewModel1) {
        SourceDataviewModel1.viewModel1 = viewModel1;
    }
}
