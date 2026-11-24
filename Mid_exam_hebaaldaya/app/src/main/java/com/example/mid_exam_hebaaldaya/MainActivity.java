package com.example.mid_exam_hebaaldaya;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private static final long EXIT_INTERVAL_MS = 3000;

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private long lastBackPressTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateInputs();
            }
        };

        etEmail.addTextChangedListener(watcher);
        etPassword.addTextChangedListener(watcher);

        btnLogin.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ModeActivity.class)));

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                long now = SystemClock.elapsedRealtime();

                if (now - lastBackPressTime < EXIT_INTERVAL_MS) {
                    finishAffinity();
                } else {
                    lastBackPressTime = now;

                    Snackbar.make(
                            findViewById(R.id.main_root),
                            R.string.press_back_again,
                            Snackbar.LENGTH_LONG
                    ).show();
                }
            }
        });
    }

    private void validateInputs() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        boolean emailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean passwordValid = password.length() >= 6;

        etEmail.setError(!email.isEmpty() && !emailValid
                ? getString(R.string.error_email) : null);

        etPassword.setError(!password.isEmpty() && !passwordValid
                ? getString(R.string.error_password) : null);

        btnLogin.setEnabled(emailValid && passwordValid);
    }
}