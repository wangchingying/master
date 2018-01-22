package com.cyw.firebaseauthapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cyw.firebaseauthapp.Data.masterDAO;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    private static final String TAG="FACELOG";

    public static masterDAO dao;
    Button login_btn,register_btn;
    EditText id,password;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = new masterDAO(MainActivity.this);
        login_btn=(Button)findViewById(R.id.login_btn);
        register_btn=(Button)findViewById(R.id.register_btn);
        id=(EditText)findViewById(R.id.id);
        password=(EditText)findViewById(R.id.password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(id.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "請輸入手機號碼,不能空白喔~", Toast.LENGTH_SHORT).show();
                }else if(password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "請輸入密碼,不能空白喔~", Toast.LENGTH_SHORT).show();
                }else if(loginSuccess()){
                    Intent it = new Intent(MainActivity.this, MasterActivity.class);
                    startActivity(it);
                }else {
                    //Toast.makeText(MainActivity.this, "帳號密碼錯誤!!", Toast.LENGTH_SHORT).show();
                }
            }
            //比對電話密碼尚未處理
            public boolean loginSuccess()
            {
                Boolean result=false;
                String ID=id.getText().toString();
                String PWD=password.getText().toString();
                //Toast.makeText(MainActivity.this,dao.getMaster(ID).password, Toast.LENGTH_SHORT).show();
                if(dao.getMaster(ID)==null){
                    Toast.makeText(MainActivity.this, "無此電話號碼,請修改或重新註冊!!", Toast.LENGTH_SHORT).show();
                }else if(dao.getMaster(ID).password.equals(PWD)){
                    result=true;
                }else{
                    Toast.makeText(MainActivity.this, "密碼錯誤", Toast.LENGTH_SHORT).show();
                }
               return result;
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(it);
            }
        });


























        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton)findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                //handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                // ...
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            updateUI();
        }

    }

    private void updateUI()
    {
        Intent it = new Intent(MainActivity.this,MasterActivity.class);
        startActivity(it);
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI();
                        }

                        // ...
                    }
                });
    }
}
