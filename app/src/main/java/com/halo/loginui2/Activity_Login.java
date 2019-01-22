package com.halo.loginui2;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AuthorizationException;
import com.ibm.cloud.appid.android.api.AuthorizationListener;
import com.ibm.cloud.appid.android.api.LoginWidget;
import com.ibm.cloud.appid.android.api.tokens.AccessToken;
import com.ibm.cloud.appid.android.api.tokens.IdentityToken;
import com.ibm.cloud.appid.android.api.tokens.RefreshToken;

public class Activity_Login extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;
   // private Activity activity;
    private Button btn_login;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        //    rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppID.getInstance().initialize(getApplicationContext(), "54892336-8e5d-4ab7-ba6a-b1d0fc6978ee", AppID.REGION_US_SOUTH);



        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        //  rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash


        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                LoginWidget loginWidget = AppID.getInstance().getLoginWidget();
                loginWidget.launch(Activity_Login.this, new AuthorizationListener() {
                    @Override
                    public void onAuthorizationFailure (AuthorizationException exception) {
                        //Exception occurred
                    }

                    @Override
                    public void onAuthorizationCanceled () {
                        //Authentication canceled by the user
                    }

                    @Override
                    public void onAuthorizationSuccess (AccessToken accessToken, IdentityToken identityToken, RefreshToken refreshToken) {
                        //User authenticated
                        Intent intent = new Intent(Activity_Login.this, Logged_In.class);
                        startActivity(intent);
                    }


                });





            }
        });





    }
}
