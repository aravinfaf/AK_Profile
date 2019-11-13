package com.e.ak_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment {

    @BindView(R.id.address_LL)
    LinearLayout address_LL;
    @BindView(R.id.contactnoLL)
    LinearLayout contactnoLL;
    @BindView(R.id.contactno_TV)
    TextView contactno_TV;
    @BindView(R.id.mailLL)
    LinearLayout mailLL;
    @BindView(R.id.mail_TV)
    TextView mail_TV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.address_LL)
    void showmap(View view){
        //10.919652, 77.489925
        Double myLatitude = 10.919652;
        Double myLongitude = 77.489925;
        String labelLocation = "Aravind @ Kangeyam";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:<" + myLatitude  + ">,<" + myLongitude + ">?q=<" + myLatitude  + ">,<" + myLongitude + ">(" + labelLocation + ")"));
        startActivity(intent);
    }
    @OnClick(R.id.contactnoLL)
    void call(View view){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactno_TV.getText().toString(), null)));
    }
    @OnClick(R.id.mailLL)
    void mail(View view){
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
