package com.fivegearszerochill.noted.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fivegearszerochill.noted.R;
import com.fivegearszerochill.noted.databinding.FragmentUnsplashBinding;
import com.fivegearszerochill.noted.unsplash.models.Photo;
import com.fivegearszerochill.noted.unsplash.requests.UnsplashSearchRequest;
import com.fivegearszerochill.noted.viewmodel.UnsplashViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UnsplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UnsplashFragment extends Fragment {

    private FragmentUnsplashBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final String TAG = "UnsplashFragment";
    private UnsplashViewModel unsplashViewModel;
    private String mParam1;
    private String mParam2;

    public UnsplashFragment() {
    }

    /**
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UnsplashFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UnsplashFragment newInstance(String param1, String param2) {
        UnsplashFragment fragment = new UnsplashFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUnsplashBinding.inflate(inflater, container, false);
        BottomSheetBehavior.from(binding.bottomSheet);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        handleButtonClick();
    }

    private void handleButtonClick() {
        binding.pullDataButton.setOnClickListener(view -> {
            UnsplashSearchRequest request = new UnsplashSearchRequest.Builder("skyline")
                    .setPage(1)
                    .setPerPage(20)
                    .build();
            Map<String, Object> map = request.toMap();
            Log.d(TAG, String.valueOf(map));
            unsplashViewModel = new ViewModelProvider(this).get(UnsplashViewModel.class);
            unsplashViewModel.init();
            unsplashViewModel.getPhotos(request.toMap()).observe(this, photos -> {
                if (photos != null) {
                    for (Photo p :
                            photos.getResults()) {
                        Log.d(TAG, p.getUrls().getSmall());
                    }
                }
            });
        });
    }
}