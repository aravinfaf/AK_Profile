package com.e.ak_profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.e.ak_profile.adapter.Experience_Adapter;
import com.e.ak_profile.model.ExperienceModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.net.URLEncoder;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainScreen extends Fragment {

    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    @BindView(R.id.contactno_TV)
    TextView contactno_TV;
    @BindView(R.id.cvIV)
    ImageView cvIV;
    @BindView(R.id.mail_TV)
    TextView mail_TV;

    @BindView(R.id.experienceinfo_RL)
    RecyclerView experienceinfo_RL;

    ArrayList<ExperienceModel> data;
    Experience_Adapter adapter;

    boolean isImageFitToScreen;

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

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
        bottomSheetDialog.show();

        LinearLayout sms_LL=bottomSheetDialog.findViewById(R.id.sms_LL);
        LinearLayout dial_LL=bottomSheetDialog.findViewById(R.id.dial_LL);
        LinearLayout whatsapp_LL=bottomSheetDialog.findViewById(R.id.whatsapp_LL);

        dial_LL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+contactno_TV.getText().toString().trim()));
                startActivity(intent);
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

                if(packageManager!=null) {

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
                }else{
                    Toast.makeText(getActivity(),"WhatsApp not installed!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.mail_TV)
    public void sendmail(View view){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:"+mail_TV.getText().toString()+"?subject=Job Opening&body=Say Something...");
        intent.setData(data);
        startActivity(intent);
    }

    @OnClick(R.id.profile_image)
    void zoom(View view){

        final Dialog nagDialog = new Dialog(getActivity());
        nagDialog.setCancelable(true);
        nagDialog.setContentView(R.layout.preview_screen);
        Button btnClose = (Button)nagDialog.findViewById(R.id.btnIvClose);
        ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.iv_preview_image);
        ivPreview.setBackgroundResource(R.drawable.ic_profile);

        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                nagDialog.dismiss();
            }
        });
        nagDialog.show();

    }

    @OnClick(R.id.cvIV)
    public void viewcv(View view){
        startActivity(new Intent(getActivity(),ViewResume.class));
    }
}
