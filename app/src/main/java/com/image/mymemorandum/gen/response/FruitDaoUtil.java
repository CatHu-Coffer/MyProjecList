package com.image.mymemorandum.gen.response;

import android.content.Context;
import android.util.Log;

import com.image.mymemorandum.gen.DaoManager;

import java.util.List;

/**
 * Created by 123 on 2018/1/10.
 */

public class FruitDaoUtil<T> {
    private DaoManager mManager;
    private String TAG = "flag";
    private static FruitDaoUtil fruitDaoUtil;


    //操作Fruit的构造函数
    public FruitDaoUtil(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    public static FruitDaoUtil getIntacen(Context context) {
        if (fruitDaoUtil == null) {
            fruitDaoUtil = new FruitDaoUtil(context);
            return fruitDaoUtil;
        } else {
            return fruitDaoUtil;
        }
    }

    /**
     * 完成fruit记录的插入，如果表未创建，先创建fruit表
     *
     * @param fruit
     * @return 返回long值 -1为失败
     */
    public boolean insertOrReplaceFruit(T fruit) {
        boolean flag = false;
        flag = mManager.getDaoSession().insertOrReplace(fruit) == -1 ? false : true;
        Log.i(TAG, "-------插入一条的结果为" + flag);
        return flag;
    }


    /**
     * 完成fruit记录的插入，如果表未创建，先创建fruit表
     *
     * @param fruit
     * @return 返回long值 -1为失败
     */
    public boolean insertFruit(T fruit) {
        boolean flag = false;
        flag = mManager.getDaoSession().insert(fruit) == -1 ? false : true;
        Log.i(TAG, "-------插入一条的结果为" + flag);
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     *
     * @return
     */
    public boolean insertListFruit(final List<T> mList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (T fruit : mList) {
                        mManager.getDaoSession().insertOrReplace(fruit);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 刷新指定数据
     */
    public boolean reFreshFruit(T fruit) {
        boolean flag = false;
        mManager.getDaoSession().refresh(fruit);
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @return
     */
    public boolean updateFruit(T fruit) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(fruit);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @return
     */
    public boolean deleteFruit(T fruit) {
        boolean flag = false;
        try {
            mManager.getDaoSession().delete(fruit);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll(Class<T> mclass) {
        boolean flag = false;
        try {
            mManager.getDaoSession().deleteAll(mclass);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<T> queryAllFruit(Class<T> mclass) {
        return mManager.getDaoSession().loadAll(mclass);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public T queryFruitById(long key, Class<T> mclass) {
        return mManager.getDaoSession().load(mclass, key);
    }

    /**
     * 使用 sql语句进行查询操作
     * 参数一sql语句  参数二查询条件限定
     */
    public List<T> queryFruitBySql(Class<T> mclass,String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(mclass, sql, conditions);
    }

}
