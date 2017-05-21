package demo.rsa.gkbn.rsademo.bean.dao;

import demo.rsa.gkbn.rsademo.sqlcore.DbField;
import demo.rsa.gkbn.rsademo.sqlcore.DbTable;

/**
 * Created by Yangyl on 2017/5/22.
 */

@DbTable("tb_user")
public class User {
    @DbField("tb_name")
    public String name;
    @DbField("tb_pass")
    public String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
