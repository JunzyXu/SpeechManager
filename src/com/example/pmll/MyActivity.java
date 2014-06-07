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
		 * editor.putString("pml", "111");//初始账号 editor.putString("aaa",
		 * "aaa");//初始账号 editor.commit();
		 */

		/*----------SQLiteOpenHelper操作-----------*/
		// 创建MySQLiteOpenHelper辅助类对象
		myHelper = new MemberDAO(this);
		// 此处不用自己创建数据库，通过MySQLiteOpenHelper获取即可
		// db = openOrCreateDatabase("menber.db",
		// SQLiteDatabase.CREATE_IF_NECESSARY, null);

		db = myHelper.getWritableDatabase();

		/*---------------初始化账号--------------
		//使用insert方法向表中插入数据
		ContentValues values = new ContentValues();		

		values.put("name", "pml");
		values.put("tel", "10086");
		values.put("password", "111");
		values.put("e_mail", "222");

			
		//调用方法插入数据
		db.insert("account_info", null, values);
		//Toast.makeText(getApplicationContext(),"rid:" + rid,
		//	Toast.LENGTH_SHORT).show();
		values.clear();
		values.put("name", "aaa");
		values.put("tel", "10086");
		values.put("password", "aaa");
		values.put("e_mail", "222");
		db.insert("account_info", null, values);
		
		//关闭SQLiteDatabase对象
		db.close();
		 */
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		exit = (Button) findViewById(R.id.exit);
		// Toast.makeText(MainActivity.this,
		// sharedPreferences.getString("pml",""), 1).show();

		// 获取可读数据库对象
		db = myHelper.getReadableDatabase();
		// 向数据库中插入和更新数据
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
				// 获取name列的索引
				int nameIndex = cursor.getColumnIndex("name");
				// 获取password列的索引
				int passwordIndex = cursor.getColumnIndex("password");

				if (account.getText().toString().equals("")) {
					Toast.makeText(MyActivity.this, "请输入账号", 1).show();
				} else if (password.getText().toString().equals("")) {
					Toast.makeText(MyActivity.this, "请输入密码", 1).show();
				} else {
					for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor
							.moveToNext()) {
						if (cursor.getString(nameIndex).equals(
								account.getText().toString())) {
							// 账号密码均正确，
							if (cursor.getString(passwordIndex).equals(
									password.getText().toString())) {
								Intent intent = new Intent(MyActivity.this,
										RegisterActivity.class);
								startActivity(intent);
							}
							// 数据库中存在该账号，但密码错误
							else {
								Toast.makeText(MyActivity.this,
										"账号或密码错误", 1).show();
							}
							break;
						}
					}
					// 数据库中不存在该账号
					if (cursor.isAfterLast()) {
						Toast.makeText(MyActivity.this, "账号或密码错误", 1)
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
