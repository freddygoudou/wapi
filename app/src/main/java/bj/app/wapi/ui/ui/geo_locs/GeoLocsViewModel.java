package bj.app.wapi.ui.ui.geo_locs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GeoLocsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GeoLocsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}