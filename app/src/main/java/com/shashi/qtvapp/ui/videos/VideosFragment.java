package com.shashi.qtvapp.ui.videos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.shashi.qtvapp.R;
import com.shashi.qtvapp.YoutubeAdapter;
import com.shashi.qtvapp.model.DataSetList;
import com.shashi.qtvapp.model.YoutubeLinkModel;

import java.util.ArrayList;
import java.util.Collections;

public class VideosFragment extends Fragment {

    public VideosFragment() {

    }

    private RecyclerView recyclerView;
    private ArrayList<DataSetList> finalArrayList;
    private YoutubeAdapter youtubeAdapter;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;

    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_videos, container, false);

        progressBar = root.findViewById(R.id.progressBarVideo);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = root.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        finalArrayList = new ArrayList<>();

        youtubeAdapter = new YoutubeAdapter(finalArrayList, getActivity());
        recyclerView.setAdapter(youtubeAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("youtubelink");

        initData();

        return root;
    }

    private void initData() {

        ArrayList<YoutubeLinkModel> linkArrayList = new ArrayList<>();

        collectionReference
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                        YoutubeLinkModel model = snapshot.toObject(YoutubeLinkModel.class);
                        linkArrayList.add(model);
                    }

                    Collections.sort(linkArrayList);

                    for (YoutubeLinkModel linkModel : linkArrayList) {
                        DataSetList dataSetList = new DataSetList(linkModel.getLink());
                        finalArrayList.add(dataSetList);
                    }

                    youtubeAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getActivity(), "Could not load videos at this moment", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                });

    }
}