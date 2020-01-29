package com.e.ak_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.net.URLEncoder;

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
    @BindView(R.id.linkedin_IV)
    ImageView linkedin_LL;
    @BindView(R.id.facebook_IV)
    ImageView facebook_IV;
    @BindView(R.id.insta_IV)
    ImageView insta_IV;

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

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        bottomSheetDialog.show();

        LinearLayout sms_LL=bottomSheetDialog.findViewById(R.id.sms_LL);
        LinearLayout dial_LL=bottomSheetDialog.findViewById(R.id.dial_LL);
        LinearLayout whatsapp_LL=bottomSheetDialog.findViewById(R.id.whatsapp_LL);

        dial_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri callUri = Uri.parse("tel: "+contactno_TV.getText().toString().trim());
                Intent callIntent = new Intent(Intent.ACTION_DIAL,callUri);
                startActivity(callIntent);
            }
        });

        sms_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("smsto:" + contactno_TV.getText().toString().trim());
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
            }
        });

        whatsapp_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PackageManager packageManager = getActivity().getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);

                try {
                    String url = "https://api.whatsapp.com/send?phone=" + contactno_TV.getText().toString().trim() + "&text=" + URLEncoder.encode("", "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));
                    if (i.resolveActivity(packageManager) != null) {
                        getActivity().startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    @OnClick(R.id.mailLL)
    void mail(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:"+mail_TV.getText().toString()+"?subject=Job Opening&body=Say Something...");
        intent.setData(data);
        startActivity(intent);
    }
    @OnClick(R.id.linkedin_IV)
    void linkedin(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.linkedin)));
        startActivity(browserIntent);
    }
    @OnClick(R.id.facebook_IV)
    void facebook(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook)));
        startActivity(browserIntent);
    }
    @OnClick(R.id.insta_IV)
    void instagram(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.insta)));
        startActivity(browserIntent);
    }
}
