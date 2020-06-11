package bj.app.wapi.ui.ui.saisons;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaisonsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SaisonsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}