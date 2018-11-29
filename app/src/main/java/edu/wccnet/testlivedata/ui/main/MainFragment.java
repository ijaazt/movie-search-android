package edu.wccnet.testlivedata.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.wccnet.testlivedata.ListFragment;
import edu.wccnet.testlivedata.R;

public class MainFragment extends Fragment implements ListFragment.OnFragmentInteractionListener {
    public static MainViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainViewModel.Factory factory = new MainViewModel.Factory(
                getActivity().getApplication());
        mViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.search_result_container, ListFragment.newInstance(mViewModel.getMySearchResults(), "Test")).commit();
        // This will start the off-the-UI-thread work that we want to perform.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView searchTxt = view.findViewById(R.id.search_txt);
        view.findViewById(R.id.search_btn).setOnClickListener((v) -> {
            mViewModel.searchLiveMovies(searchTxt.getText().toString());
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }
}
