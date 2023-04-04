package today_school_store_project.team_hungryp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DialogClass extends Dialog {
    Context context;
    String name, price;

    TextView nameTextview, priceTextview;
    Button okButton;

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

        nameTextview.setText(name);
        priceTextview.setText("가격 : "+price+"원");

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}

