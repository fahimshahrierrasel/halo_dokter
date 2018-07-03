package com.treebricks.dokuter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.treebricks.dokuter.utils.MultiTextWatcher;
import com.treebricks.dokuter.R;

import java.util.Locale;

public class FAQActivity extends AppCompatActivity {

    private EditText etFaqDescription;
    private TextView tvFaqDescCharCounter;
    private EditText etFaqTitle;
    private TextView tvFaqTitleCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        tvFaqDescCharCounter = findViewById(R.id.tvFaqDescCharCounter);
        tvFaqTitleCounter = findViewById(R.id.tvFaqTitleCharCounter);
        etFaqDescription = findViewById(R.id.etFaqDescription);
        etFaqTitle = findViewById(R.id.etFaqTitle);

        new MultiTextWatcher()
                .registerEditText(etFaqTitle)
                .registerEditText(etFaqDescription)
                .setCallback(new MultiTextWatcher.TextWatcherWithInstance() {
                    @Override
                    public void beforeTextChanged(EditText editText, CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(EditText editText, CharSequence s, int start, int before, int count) {
                        int charCounter = s.length();
                        if(editText.getId() == R.id.etFaqTitle)
                        {
                            tvFaqTitleCounter.setText(String.format(Locale.US, "%d / 40", charCounter));
                        }else  if(editText.getId() == R.id.etFaqDescription)
                        {
                            tvFaqDescCharCounter.setText(String.format(Locale.US, "%d / 1000", charCounter));
                        }
                    }
                    @Override
                    public void afterTextChanged(EditText editText, Editable editable) {

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.faq_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_submit:
                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
