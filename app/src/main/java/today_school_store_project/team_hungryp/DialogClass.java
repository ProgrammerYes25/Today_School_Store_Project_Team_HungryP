package today_school_store_project.team_hungryp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class DialogClass extends Dialog {
    Context context;
    String name, price;
    ImageView prImageView;
    TextView nameTextview, priceTextview;
    Button okButton;
    StorageReference storageReference;

    public DialogClass(@NonNull Context context, String name, String price, StorageReference storageReference) {
        super(context);
        this.context = context;
        this.name = name;
        this.price = price;
        this.storageReference =storageReference;
    }
    public DialogClass(@NonNull Context context, String name, String price) {
        super(context);
        this.context = context;
        this.name = name;
        this.price = price;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);

        Objects.requireNonNull(getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        nameTextview = findViewById(R.id.name_text_view);
        priceTextview = findViewById(R.id.price_text_view);
        okButton = findViewById(R.id.ok_button);
        prImageView = findViewById(R.id.pr_image_view);
        nameTextview.setText(name);
        priceTextview.setText("가격 : "+price+"원");
        if(storageReference!=null){
            //참조객체로 부터 이미지의 다운로드 URL을 얻어오기
            Log.d("확인", "가져오기 성공");
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    //Log.d("확인", "가져오기 성공");
                    //다운로드 URL이 파라미터로 전달되어 옴.
                    Glide.with(context).load(uri).into(prImageView);
                }
            });

        }else{
                    Log.d("확인 : ", "가져오기 실패");
                }
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

