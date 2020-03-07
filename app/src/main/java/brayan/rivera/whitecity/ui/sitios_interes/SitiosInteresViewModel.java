package brayan.rivera.whitecity.ui.sitios_interes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SitiosInteresViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SitiosInteresViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}