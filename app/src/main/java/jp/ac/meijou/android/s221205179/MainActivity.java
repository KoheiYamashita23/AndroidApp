package jp.ac.meijou.android.s221205179;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import jp.ac.meijou.android.s221205179.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore=PrefDataStore.getInstance(this);

        prefDataStore.getString("name")
                        .ifPresent(name->binding.text.setText(name));
        binding.changeButton.setOnClickListener(view->{
           var text=binding.editTextText.getText().toString();
           binding.text.setText(text);
        });

        binding.saveButton.setOnClickListener(view ->{
           var text=binding.editTextText.getText().toString();
           prefDataStore.setString("name",text);
        });

        Log.d("yamashita","onCreate text:"+binding.text.getText());


    }
    @Override
    protected void onStart(){
        super.onStart();
        prefDataStore.getString("name")
                .ifPresent(name->binding.text.setText(name));
        Log.d("yamashita","onStart text:"+binding.text.getText());
    }
}