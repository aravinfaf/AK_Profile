package com.e.ak_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.e.ak_profile.adapter.Experience_Adapter;
import com.e.ak_profile.model.ExperienceModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainScreen extends Fragment {

    @BindView(R.id.contactno_TV)
    TextView contactno_TV;

    @BindView(R.id.mail_TV)
    TextView mail_TV;

    @BindView(R.id.experienceinfo_RL)
    RecyclerView experienceinfo_RL;

    ArrayList<ExperienceModel> data;
    Experience_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_main_screen,container,false);
        ButterKnife.bind(this,view);

        experience_data();

        return view;
    }

    private void experience_data() {

        data=new ArrayList<ExperienceModel>();

        data.add(new ExperienceModel("KG Information and Systems","Coimbatore,TamilNadu","July'2019 - Till Present","Android Application Developer",getString(R.string.desc_kg)));
        data.add(new ExperienceModel("Indus Novateur Softech Pvt Ltd","Coimbatore,TamilNadu","Oct'2018 - July'2019","Android Application Developer",getString(R.string.desc_indus)));
        data.add(new ExperienceModel("Open Source Academy","Coimbatore,TamilNadu","Feb'2016 - Sep'2018","Android Application Developer",getString(R.string.osa)));

        experienceinfo_RL.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter=new Experience_Adapter(getActivity(),data);
        experienceinfo_RL.setAdapter(adapter);

    }

    @OnClick(R.id.contactno_TV)
    public void contactcall(View view){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactno_TV.getText().toString(), null)));
    }

    @OnClick(R.id.mail_TV)
    public void sendmail(View view){

        String[] TO = {mail_TV.getText().toString()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reg - Aravindkumar Raj");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Something About Aravind");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            getActivity().finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(),"There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
