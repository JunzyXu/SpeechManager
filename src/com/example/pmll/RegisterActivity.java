package com.example.pmll;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tablayout.R;

public class RegisterActivity extends Activity {

	private EditText groupname;
	private EditText register_password;
	private EditText confirm_password;
	private EditText telephone;
	private EditText e_mail;
	private Button submit, cancel;
	MemberDAO myHelper;
	SQLiteDatabase db;
	Cursor cursor;
	//SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

	    groupname = (EditText)findViewById(R.id.groupname);
	    register_password = (EditText)findViewById(R.id.register_password);
	    confirm_password = (EditText)findViewById(R.id.confirm_password);
	    telephone = (EditText)findViewById(R.id.telephone);
	    e_mail = (EditText)findViewById(R.id.e_mail);    
		submit = (Button)findViewById(R.id.submit);
		cancel = (Button)findViewById(R.id.cancel);	
		
		myHelper = new  MemberDAO(this);
		db = myHelper.getReadableDatabase();
		
		cursor = db.query("account_info", null, null, null, null, null, null);
		
		//sharedPreferences = getPreferences(MODE_PRIVATE);
		
		submit.setOnClickListener( new OnClickListener()  {
			@Override
	    	public void onClick(View v) {
				//Toast.makeText(RegisterActivity.this, sharedPreferences.getString("pml",""), 1).show();
				//Toast.makeText(RegisterActivity.this, sharedPreferences.getString("aaa","a"), 2).show();
				if(groupname.getText().toString().equals("")) {
					Toast.makeText(RegisterActivity.this, "����������", 1).show();
				}
				
				else {
					//��ȡname�е�����
					int nameIndex = cursor.getColumnIndex("name");
					
					for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()) {
						 //���ݿ����Ѵ��ڸ��û������˺� 
						 if(cursor.getString(nameIndex).equals(groupname.getText().toString())) {
							
							 Toast.makeText(RegisterActivity.this, "������ʹ�ã�����������", 1).show();
							 break;
						 }		  
					}
					
				   //���ݿ��в����ڸ��û������˺ţ�����ʹ�ø�����		
				   if(cursor.isAfterLast()) {
					   if(register_password.getText().toString().equals("")) {
						   Toast.makeText(RegisterActivity.this, "����������", 1).show();
					   }
				       else if(confirm_password.getText().toString().equals("")) {
				    	   Toast.makeText(RegisterActivity.this, "������ȷ������", 1).show();
				       }
				       else if(!confirm_password.getText().toString().equals(register_password.getText().toString())) {
				    	   Toast.makeText(RegisterActivity.this, "�������벻һ��", 1).show();
				       }
				       else if(telephone.getText().toString().equals("")) {
				    	   Toast.makeText(RegisterActivity.this, "��������ϵ��ʽ", 1).show();
				       }
				       else if(e_mail.getText().toString().equals("")) {
				    	   Toast.makeText(RegisterActivity.this, "�����������ַ", 1).show();
				       }
				       else {
				    	   Toast.makeText(RegisterActivity.this, "ע���˺ųɹ�", 1).show();
										
				    	   SQLiteDatabase db = myHelper.getWritableDatabase();

				    	   /*---------------�����µ��˺�--------------*/
				    	   //ʹ��insert��������в�������
				    	   ContentValues values = new ContentValues();		

				    	   values.put("name", groupname.getText().toString());
				    	   values.put("tel", telephone.getText().toString());
				    	   values.put("password", register_password.getText().toString());
				    	   values.put("e_mail", e_mail.getText().toString());
				    	   db.insert("account_info", null, values);
						
				    	   //�ر�SQLiteDatabase����
				    	   db.close();		
					
					/*Editor editor = sharedPreferences.edit();		
					editor.clear().commit();
					editor.putString(groupname.getText().toString(),register_password.getText().toString());
					editor.putString(groupname.getText().toString()+"_telephone",telephone.getText().toString());//��ʼ�˺�
					editor.putString(groupname.getText().toString()+"_e_mail",e_mail.getText().toString());
					editor.commit();*/
				    	   Intent intent = new Intent(RegisterActivity.this,MyActivity.class);
				    	   startActivity(intent); 
				       }
				   }
				}
			}});
		
		cancel.setOnClickListener( new OnClickListener()  {
			@Override
	    	public void onClick(View v) {
				Intent intent = new Intent(RegisterActivity.this,MyActivity.class);
				startActivity(intent); 	
				finish();
			}});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
