package brayan.rivera.whitecity.ui.museos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MuseosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MuseosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}