package com.example.vieir.projetocmu.register;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vieir.projetocmu.database.DbHelper;
import com.example.vieir.projetocmu.models.User;
import com.example.vieir.projetocmu.R;

public class UserRegister extends AppCompatActivity {



    EditText name, email,localidade, username, password,confirmarPassword,text1;
    Button registar,login;
    Context c;
    private void insertUser() throws Exception{
        DbHelper dbHelper = new DbHelper(UserRegister.this);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name.getText().toString());
        values.put("email", email.getText().toString());
        values.put("localidade", localidade.getText().toString());
        values.put("username", username.getText().toString());
        values.put("password", password.getText().toString());
        values.put("confirmarPassword", confirmarPassword.getText().toString());
                long rowId = db.insert("user", null, values);
                if (rowId < 0) {
                    throw new Exception("Erro aqui");
                }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        name = (EditText) findViewById(R.id.insertNome);
        email = (EditText) findViewById(R.id.insertEmail);
        localidade = (EditText) findViewById(R.id.insertLocalidade);
        username = (EditText) findViewById(R.id.insertUsername);
        password = (EditText) findViewById(R.id.insertPassword);
        confirmarPassword = (EditText) findViewById(R.id.insertConfirmPassword);
        registar = (Button) findViewById(R.id.button_salvarPessoa);
        login = (Button) findViewById(R.id.button_Login);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira o seu nome", Toast.LENGTH_LONG).show();
                } else if (email.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira o seu e-mail", Toast.LENGTH_LONG).show();
                } else if (localidade.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira a localidade", Toast.LENGTH_LONG).show();
                } else if (username.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira um username", Toast.LENGTH_LONG).show();
                } else if (password.length() == 0) {
                    Toast.makeText(UserRegister.this, "Insira uma password", Toast.LENGTH_LONG).show();
                } else if (confirmarPassword.length() == 0) {
                    Toast.makeText(UserRegister.this, "Tem que confirmar a password", Toast.LENGTH_LONG).show();
                }else if (password.getText().toString().equals(confirmarPassword.getText().toString())) {
                             Toast.makeText(getApplicationContext(), "register sucessful", Toast.LENGTH_SHORT).show();
                             Intent d = new Intent(getApplicationContext(), Login.class);
                             startActivity(d);

                } else {
                    Toast.makeText(UserRegister.this, "ERROR", Toast.LENGTH_LONG).show();
                }}

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(getApplicationContext(), Login.class);
                startActivity(x);
            }
        });

    }

    private User verificarUserEmail() {
        DbHelper dbHelper = new DbHelper(UserRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String em = email.getText().toString();
        String use = username.getText().toString();

        String query = "SELECT * FROM user WHERE email=?";
        Cursor c = db.rawQuery(query, new String[]{em});

        User user = null;

        try {
            if (c != null && c.moveToFirst()) {

                user = new User();
                user.setEmail(c.getString(2));
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "NAO DA", Toast.LENGTH_SHORT).show();
        }finally {
            if (c != null) {
                c.close();
            }
        }

        return user;
    }

    private User verificarUserUser() {
        DbHelper dbHelper = new DbHelper(UserRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String use = username.getText().toString();

        String query = "SELECT * FROM user WHERE username=?";
        Cursor c = db.rawQuery(query, new String[]{use});

        User user = null;

        try {
            if (c != null && c.moveToFirst()) {

                user = new User();
                user.setEmail(c.getString(5));
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "NAO DA", Toast.LENGTH_SHORT).show();
        }finally {
            if (c != null) {
                c.close();
            }
        }

        return user;
    }


}



