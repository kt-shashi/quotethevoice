package com.shashi.qtvapp.ui.socialmedia;

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
import com.shashi.qtvapp.model.SocialMediaModelClass;

import java.util.ArrayList;

public class SocialMediaFragment extends Fragment {

    private SocialMediaAdapter adapter;
    private CollectionReference collectionReference;
    private ProgressBar progressBar;
    public static final String COLLECTION_NAME = "socialmedia";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_social_media, container, false);
        initViews(root);
        initData();
        return root;

    }

    private void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_social_media);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        progressBar = view.findViewById(R.id.progress_bar_social_media_fragment);
        progressBar.setVisibility(View.GONE);

        adapter = new SocialMediaAdapter(getActivity());

        recyclerView.setAdapter(adapter);
        FirebaseFirestore firestoredb = FirebaseFirestore.getInstance();
        collectionReference = firestoredb.collection(COLLECTION_NAME);
    }

    private void initData() {
        progressBar.setVisibility(View.VISIBLE);

        ArrayList<SocialMediaModelClass> list = new ArrayList<>();

        collectionReference.get().addOnSuccessListener(queryDocumentSnapshots -> {

            for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                SocialMediaModelClass data = snapshot.toObject(SocialMediaModelClass.class);

                list.add(data);

            }

            adapter.updateSocialMediaList(list);
            progressBar.setVisibility(View.GONE);

        }).addOnFailureListener(e -> {
            Toast.makeText(getActivity(), "Could not load images", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
        });
    }

}