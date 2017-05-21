package demo.rsa.gkbn.rsademo.sqlcore;

/**
 * Created by Yangyl on 2017/5/22.
 */

public interface IBaseDao<T> {
    long insert(T data);
    int updata(T entity,T there);

}
