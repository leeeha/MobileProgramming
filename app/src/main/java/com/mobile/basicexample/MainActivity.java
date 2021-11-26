package com.mobile.basicexample;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnUpdate, btnDelete, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumberResult = findViewById(R.id.edtNumberResult);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);

        // Create a helper object to create, open, and/or manage a database.
        // 데이터베이스의 생성, 오픈 등을 관리하는 헬퍼 객체 생성
        myHelper = new myDBHelper(this);

        // 초기화 버튼
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and/or open a database that will be used for reading and writing.
                // 읽고 쓸 수 있는 기존의 데이터베이스를 열거나, 새로 생성 후 열기
                sqlDB = myHelper.getWritableDatabase();

                // 테이블 삭제 후 다시 생성
                myHelper.onUpgrade(sqlDB, 1, 2);

                sqlDB.close();

                Toast.makeText(getApplicationContext(), "초기화됨", Toast.LENGTH_SHORT).show();

                // 초기화 후 결과 확인
                btnSelect.callOnClick();
            }
        });

        // 입력 버튼
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();

                if(!edtName.getText().toString().isEmpty()){
                    // execSQL 함수는 SELECT 명령을 제외한 모든 SQL 문장을 실행한다.
                    // edtName은 문자열이니까 작은 따옴표로 감싸고, edtNumber는 정수니까 그대로
                    sqlDB.execSQL("INSERT INTO groupTBL VALUES ('" + edtName.getText() + "', " +
                            edtNumber.getText() + ");");

                    sqlDB.close();

                    Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();

                    // 입력 후 결과 확인
                    btnSelect.callOnClick();
                }
            }
        });

        // 수정 버튼
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();

                // 기본키인 그룹 이름을 제외하고 내용 수정 가능
                // 데이터베이스는 위치가 아니라 내용으로 검색함.
                if(!edtName.getText().toString().isEmpty()){
                    sqlDB.execSQL("UPDATE groupTBL SET gNumber = " +
                            edtNumber.getText() + " WHERE gName = '" +
                            edtName.getText() + "';");

                    sqlDB.close();

                    Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_SHORT).show();

                    // 수정 후 결과 확인
                    btnSelect.callOnClick();
                }
            }
        });

        // 삭제 버튼
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getWritableDatabase();

                // 데이터베이스는 위치가 아니라 내용으로 검색함.
                if (!edtName.getText().toString().isEmpty()) {
                    sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '" +
                            edtName.getText() + "';");

                    sqlDB.close();

                    Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_SHORT).show();

                    // 삭제 후 결과 확인
                    btnSelect.callOnClick();
                }
            }
        });

        // 조회 버튼
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and/or open a database.
                // 기존에 있는 데이터베이스 열거나, 새로 생성 후 열기
                sqlDB = myHelper.getReadableDatabase();

                // 여러 행의 테이블을 한꺼번에 조회할 때는 커서 객체 사용
                Cursor cursor;

                // rawQuery 메소드는 사용자가 데이터베이스에 요청한 정보(쿼리)를 반환함.
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "그룹 이름" + "\r\n" + "-------------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

                // 커서를 다음 줄로 옮겨가면서 한 행씩 문자열로 합치기
                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n"; // 첫번째 속성값 반환
                    strNumbers += cursor.getString(1) + "\r\n"; // 두번째 속성값 반환
                }

                // 쿼리 결과를 에디트텍스트에 보여주기
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();

                Toast.makeText(getApplicationContext(), "조회됨", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // SQLiteOpenHelper 클래스 상속 받아서 테이블 생성 및 삭제하기
    // https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context){
            super(context, "groupDB", null, 1);
        }

        @Override // 테이블 생성
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override // 테이블 삭제 후 다시 생성
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}