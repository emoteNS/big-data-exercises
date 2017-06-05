package nearsoft.academy.bigdata.recommendation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AMDA on 30/03/2017.
 */
class ManageList {
    private Map<String, Long> productList;
    private Map<String, Long> userList;
    private Map<Long, String> invertProductList;
    private Map<Long, String> invertUserList;

    private long productIndex;
    private long userIndex;
    private long totalIndexes;

    public ManageList() {
        productList = new HashMap<String, Long>();
        userList = new HashMap<String, Long>();

        invertProductList = new HashMap<Long, String>();
        invertUserList = new HashMap<Long, String>();
    }

    public long addProduct(String idProd) {

        if (!getProductList().containsKey(idProd)) {
            setProductIndex(getProductList().size() + 1);
            getProductList().put(idProd, getProductIndex());
            getInvertProductList().put(getProductIndex(), idProd);
        } else {
            setProductIndex(getProductList().get(idProd));
        }

        return getProductIndex();
    }

    public long addUser(String idUser) {

        if (!getUserList().containsKey(idUser)) {
            setUserIndex(getUserList().size() + 1);
            getUserList().put(idUser, getUserIndex());
            getInvertUserList().put(getUserIndex(), idUser);
        } else {
            setUserIndex(getUserList().get(idUser));
        }

        return getUserIndex();
    }

    public Map<String, Long> getProductList() {
        return productList;
    }

    public void setProductList(Map<String, Long> productList) {
        this.productList = productList;
    }

    public Map<String, Long> getUserList() {
        return userList;
    }

    public void setUserList(Map<String, Long> userList) {
        this.userList = userList;
    }

    public Map<Long, String> getInvertProductList() {
        return invertProductList;
    }

    public void setInvertProductList(Map<Long, String> invertProductList) {
        this.invertProductList = invertProductList;
    }

    public Map<Long, String> getInvertUserList() {
        return invertUserList;
    }

    public void setInvertUserList(Map<Long, String> invertUserList) {
        this.invertUserList = invertUserList;
    }

    public long getProductIndex() {
        return productIndex;
    }

    public void setProductIndex(long productIndex) {
        this.productIndex = productIndex;
    }

    public long getUserIndex() {
        return userIndex;
    }

    public void setUserIndex(long userIndex) {
        this.userIndex = userIndex;
    }

    public long getTotalIndexes() {
        return totalIndexes;
    }

    public void setTotalIndexes(long totalIndexes) {
        this.totalIndexes = totalIndexes;
    }
}
