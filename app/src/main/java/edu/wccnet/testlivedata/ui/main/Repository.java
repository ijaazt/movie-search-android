package edu.wccnet.testlivedata.ui.main;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import edu.wccnet.testlivedata.AppDatabase;
import edu.wccnet.testlivedata.JsonAdapter;
import edu.wccnet.testlivedata.MovieItem;
import edu.wccnet.testlivedata.MovieItemDao;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class Repository {
    private static Repository instance;
    // Note the use of MutableLiveData, this allows changes to be made
    @NonNull
    private Application application;
    private MutableLiveData<List<MovieItem>> myLiveData = new MutableLiveData<>();
    private MovieItemDao itemDao;

    // This method runs some work for 3 seconds. It then posts a status update to the live data.
    // This would effectively be the "doInBackground" method from AsyncTask.
    public Repository(Application application) {
        this.application = application;
        AppDatabase appDatabase = AppDatabase.Companion.getDatabase(application);
        itemDao = appDatabase.movieItemDao();
    }

    // The getter upcasts to LiveData, this ensures that only the repository can cause a change
    @NonNull
    public LiveData<List<MovieItem>> getMyLiveData() {
        return myLiveData;
    }

    public void searchMovie(String movieTitle) {
        new Thread(() -> {
            try {
                URLConnection urlConnection = new URL("http://www.omdbapi.com/?s=" + URLEncoder.encode(movieTitle, "UTF-8") + "&apikey=75be55f8&plot=full").openConnection();
                InputStream is = urlConnection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String inputLine;
                String fileString = "";
                while ((inputLine = in.readLine()) != null)
                    fileString += inputLine;
                JsonAdapter adapter = new JsonAdapter(new JSONObject(fileString));
                myLiveData.postValue(adapter.getMovies());
            } catch (Exception e) {
                Log.e("Repository", e.getMessage());
            }
        }).start();
    }

    public void getLikedMovies() {

    }

    public static Repository getInstance(Application application) {
        if (instance == null) {
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository(application);
                }
            }
        }
        return instance;
    }
}
