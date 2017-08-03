package com.example.mycalender.mycalender;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText et_login;
    private TextInputEditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init() {

        et_login = (TextInputEditText) findViewById(R.id.et_login);
        et_password = (TextInputEditText) findViewById(R.id.et_password);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        TextView tv_register = (TextView) findViewById(R.id.tv_register);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_login:

                if(checkValidity(et_login) && checkValidity(et_password)) {

                    Intent main_menu = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(main_menu);
                    finish();
                }
                break;

            case R.id.tv_register:

//                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(register);
                break;
        }
    }

    public static boolean checkValidity(TextInputEditText et) {
        if (TextUtils.isEmpty(et.getText().toString())) {
            et.setError("Field must not be empty");
            et.requestFocus();
            return false;
        }

        return true;
    }
}
