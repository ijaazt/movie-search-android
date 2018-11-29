package edu.wccnet.testlivedata;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class JsonAdapter {
    private List<MovieItem> movies;

    public JsonAdapter(JSONObject movieObject) {
        createMovieItemList(movieObject);
    }

    private void createMovieItemList(JSONObject movieObject) {
        if (movieObject.has("Search")) {
            search(movieObject);
        } else {

        }
    }

    private Bitmap getPhoto(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getMovie(JSONObject convert) {
        try {
            MovieItem mv = new MovieItem(convert.getString("imdbID"), convert.getString("Title"),
                    convert.getString("Year"),
                    getPhoto(convert.getString("Poster")),
                    convert.getString("Rated"),
                    convert.getString("Released"),
                    convert.getString("Genre"),
                    convert.getString("Director"),
                    convert.getString("Writer"),
                    convert.getString("Actors"),
                    convert.getString("Plot"),
                    convert.getString("Language"),
                    convert.getString("Country"),
                    convert.getString("Awards"), "");
            movies.add(mv);
        } catch (Exception e) {
            Log.e("JSONAdapter.getMovie()", e.getMessage());
        }
    }

    private JSONObject getMovieObject(String title) {
        try {
            URLConnection urlConnection = new URL("http://www.omdbapi.com/?t=" + URLEncoder.encode(title, "UTF-8") + "&apikey=75be55f8&plot=full").openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            String inputLine;
            String fileString = "";
            while ((inputLine = in.readLine()) != null)
                fileString += inputLine;
            return new JSONObject(fileString);
        } catch (Exception e) {
            Log.e("JSONAdapter.getMovieObject(" + title + ")", e.getMessage());
        }
        return null;
    }

    private void search(JSONObject movieObject) {
        movies = new ArrayList<>();
        try {
            JSONArray search = movieObject.getJSONArray("Search");
            for (int i = 0; i < search.length(); i++) {
                JSONObject singleMovie = search.getJSONObject(i);
                String title = singleMovie.getString("Title");
                getMovie(getMovieObject(title));
            }
        } catch (Exception e) {
            Log.e("JSONAdapter.search()", e.getMessage());
        }
    }

    public List<MovieItem> getMovies() {
        return movies;
    }
}
