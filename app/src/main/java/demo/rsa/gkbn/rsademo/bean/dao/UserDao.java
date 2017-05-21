package demo.rsa.gkbn.rsademo.bean.dao;

import demo.rsa.gkbn.rsademo.sqlcore.AbstactBaseDao;

/**
 * Created by Yangyl on 2017/5/22.
 */

public class UserDao extends AbstactBaseDao<User> {
    @Override
    public String createTable() {
        return "CREATE TABLE IF NOT EXISTS tb_user" +
                "(" +
                "tb_name VARCHAR(20)," +
                "tb_pass VARCHAR(20)" +
                ");";
    }
}
