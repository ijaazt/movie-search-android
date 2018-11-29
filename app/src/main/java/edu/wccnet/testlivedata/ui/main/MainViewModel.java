package edu.wccnet.testlivedata.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import edu.wccnet.testlivedata.MovieItem;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    static MainViewModel instance;
    // TODO: Implement the ViewModel
    @NonNull
    Application app;
    private Repository repo;
    @NonNull
    private LiveData<List<MovieItem>> myLiveData;

    MainViewModel(Application application) {
        super(application);
        // The local live data needs to reference the repository live data
        app = getApplication();
        repo = Repository.getInstance(app);
        myLiveData = repo.getMyLiveData();
    }

    @NonNull
    public LiveData<List<MovieItem>> getMySearchResults() {
        return myLiveData;
    }

    public void searchLiveMovies(String search) {
        repo.searchMovie(search);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application mApplication;

        public Factory(@NonNull Application application) {
            mApplication = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new MainViewModel(mApplication);
        }
    }
}
