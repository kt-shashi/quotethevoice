package com.shashi.qtvapp.ui.videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shashi.qtvapp.R;
import com.shashi.qtvapp.YoutubeAdapter;
import com.shashi.qtvapp.model.DataSetList;

import java.util.ArrayList;

public class VideosFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_videos, container, false);

        RecyclerView recyclerView;

        ArrayList<DataSetList> arrayList;

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();

        DataSetList dataSetList = new DataSetList("https://www.youtube.com/embed/Q6yWAwBo88M");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/fRJLuDr-K7A");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/4YMLl5ZMb5k");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/5NI7RrI3C7E");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/0DbVFvUtinQ");
        arrayList.add(dataSetList);
        dataSetList = new DataSetList("https://www.youtube.com/embed/qYg31ppjnYg");
        arrayList.add(dataSetList);

        YoutubeAdapter youtubeAdapter = new YoutubeAdapter(arrayList, getActivity());
        recyclerView.setAdapter(youtubeAdapter);

        return root;
    }
}