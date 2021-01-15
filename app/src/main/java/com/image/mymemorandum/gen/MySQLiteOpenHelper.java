package com.image.mymemorandum.gen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.image.mymemorandum.gen.response.User;

/**
 * Created by 123 on 2018/1/10.
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {


    private SQLiteDatabase readableDatabase = null;

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    public SQLiteDatabase getDatabase() {
        if (readableDatabase == null) {
            readableDatabase = this.getReadableDatabase();
        }
        return readableDatabase;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        // 使用for实现跨版本升级数据库
        for (int i = oldVersion; i < newVersion; i++) {
            switch (i) {
                case 1:
                    //具体的数据转移在MigrationHelper2类中
                    /**
                     *  将db传入     将gen目录下的所有的Dao.类传入(原有的和你需要修改添加的)
                     */
//                    MigrationHelper2.migrate(db,User.class, PeoreDao.class);
                    break;
            }
        }
    }
}
