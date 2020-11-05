package com.shashi.qtvapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.shashi.qtvapp.R;
import com.shashi.qtvapp.model.DataSetList;
import com.shashi.qtvapp.model.YoutubeLinkModel;
import com.shashi.qtvapp.ui.videos.VideosFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;

    private ImageSlider imageSlider;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(root);
        initData();

        return root;
    }

    private void initViews(View view) {
        imageSlider = view.findViewById(R.id.image_slider_home_fragment);

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("imageslider");
    }

    private void initData() {

        final List<SlideModel> homeImageList = new ArrayList<>();

        collectionReference
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {

                        homeImageList.add(new SlideModel(snapshot.getString("url"), snapshot.getString("title"), ScaleTypes.CENTER_CROP));

                    }

                    imageSlider.setImageList(homeImageList, ScaleTypes.CENTER_CROP);

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getActivity(), "Could not load Images at this moment", Toast.LENGTH_SHORT).show();
                });
    }
}