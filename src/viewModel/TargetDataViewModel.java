package viewModel;

import domain.AssetSelling;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TargetDataViewModel {
    private StringProperty name = new SimpleStringProperty();
    private StringProperty name1 = new SimpleStringProperty();
    private StringProperty name2 = new SimpleStringProperty();
    private AssetSelling assetSelling;


    private static TargetDataViewModel viewModel = new TargetDataViewModel();

    private TargetDataViewModel(){}

    public static TargetDataViewModel getInstance(){
        return viewModel;
    }

    public AssetSelling getAssetSelling() {
        return assetSelling;
    }

    public void setAssetSelling(AssetSelling assetSelling) {
        this.assetSelling = assetSelling;
    }

    public String getName1() {
        return name1.get();
    }

    public StringProperty name1Property() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1.set(name1);
    }

    public String getName2() {
        return name2.get();
    }

    public StringProperty name2Property() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2.set(name2);
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

    public static TargetDataViewModel getViewModel() {
        return viewModel;
    }

    public static void setViewModel(TargetDataViewModel viewModel) {
        TargetDataViewModel.viewModel = viewModel;
    }
}
