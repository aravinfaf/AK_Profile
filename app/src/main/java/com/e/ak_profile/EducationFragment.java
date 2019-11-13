package com.e.ak_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e.ak_profile.adapter.Education_Adapter;
import com.e.ak_profile.model.EducationModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EducationFragment extends Fragment {

    @BindView(R.id.edu_RV)
    RecyclerView edu_RV;

    ArrayList<EducationModel> data;
    Education_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_education,container,false);
        ButterKnife.bind(this,view);

        educationdata();

        return view;
    }

    private void educationdata() {

        data=new ArrayList<EducationModel>();
        data.add(new EducationModel("Bannari Amman Istitue of Technology","Sathyamangalam,Erode","Aug'2011 - May'2105","B.E.ECE"));

        edu_RV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new Education_Adapter(getActivity(),data);
        edu_RV.setAdapter(adapter);
    }
}
