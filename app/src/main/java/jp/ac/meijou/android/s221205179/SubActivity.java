package jp.ac.meijou.android.s221205179;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijou.android.s221205179.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Optional.ofNullable(getIntent().getStringExtra("text"))
                .ifPresent(text->binding.textView.setText(text));

        binding.button20.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.button21.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}