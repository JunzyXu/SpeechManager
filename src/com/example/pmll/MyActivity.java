package com.example.pmll;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tablayout.MainActivity;
import com.example.tablayout.R;

public class MyActivity extends Activity {
	SharedPreferences sharedPreferences;
	private TextView mTxt;
	private EditText account;
	private EditText password;
	private Button login, register, exit;
	MemberDAO myHelper;
	SQLiteDatabase db;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * sharedPreferences = getPreferences(MODE_PRIVATE); //int count =
		 * sharedPreferences.getInt("nameCount", 0); Editor editor =
		 * sharedPreferences.edit(); editor.clear().commit();
		 * editor.putString("pml", "111");//��ʼ�˺� editor.putString("aaa",
		 * "aaa");//��ʼ�˺� editor.commit();
		 */

		/*----------SQLiteOpenHelper����-----------*/
		// ����MySQLiteOpenHelper���������
		myHelper = new MemberDAO(this);
		// �˴������Լ��������ݿ⣬ͨ��MySQLiteOpenHelper��ȡ����
		// db = openOrCreateDatabase("menber.db",
		// SQLiteDatabase.CREATE_IF_NECESSARY, null);

		db = myHelper.getWritableDatabase();

		/*---------------��ʼ���˺�--------------
		//ʹ��insert��������в�������
		ContentValues values = new ContentValues();		

		values.put("name", "pml");
		values.put("tel", "10086");
		values.put("password", "111");
		values.put("e_mail", "222");

			
		//���÷�����������
		db.insert("account_info", null, values);
		//Toast.makeText(getApplicationContext(),"rid:" + rid,
		//	Toast.LENGTH_SHORT).show();
		values.clear();
		values.put("name", "aaa");
		values.put("tel", "10086");
		values.put("password", "aaa");
		values.put("e_mail", "222");
		db.insert("account_info", null, values);
		
		//�ر�SQLiteDatabase����
		db.close();
		 */
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		exit = (Button) findViewById(R.id.exit);
		// Toast.makeText(MainActivity.this,
		// sharedPreferences.getString("pml",""), 1).show();

		// ��ȡ�ɶ����ݿ����
		db = myHelper.getReadableDatabase();
		// �����ݿ��в���͸�������
		cursor = db.query("account_info", null, null, null, null, null, null);

		register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyActivity.this,
						RegisterActivity.class);
				startActivity(intent);
			}
		});

		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// ��ȡname�е�����
				int nameIndex = cursor.getColumnIndex("name");
				// ��ȡpassword�е�����
				int passwordIndex = cursor.getColumnIndex("password");

				if (account.getText().toString().equals("")) {
					Toast.makeText(MyActivity.this, "�������˺�", 1).show();
				} else if (password.getText().toString().equals("")) {
					Toast.makeText(MyActivity.this, "����������", 1).show();
				} else {
					for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
							.moveToNext()) {
						if (cursor.getString(nameIndex).equals(
								account.getText().toString())) {
							// �˺��������ȷ��
							if (cursor.getString(passwordIndex).equals(
									password.getText().toString())) {
								Intent intent = new Intent(MyActivity.this,
										RegisterActivity.class);
								startActivity(intent);
							}
							// ���ݿ��д��ڸ��˺ţ����������
							else {
								Toast.makeText(MyActivity.this,
										"�˺Ż��������", 1).show();
							}
							break;
						}
					}
					// ���ݿ��в����ڸ��˺�
					if (cursor.isAfterLast()) {
						Toast.makeText(MyActivity.this, "�˺Ż��������", 1)
								.show();
					}

				}
				/*
				 * else
				 * if(sharedPreferences.getString(account.getText().toString(),
				 * "").equals(password.getText().toString())) { Intent intent =
				 * new Intent(MainActivity.this,RegisterActivity.class);
				 * startActivity(intent); }
				 */
				Intent intent = new Intent(MyActivity.this, MainActivity.class);
				startActivity(intent);
				finish();

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
