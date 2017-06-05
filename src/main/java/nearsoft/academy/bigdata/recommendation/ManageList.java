package nearsoft.academy.bigdata.recommendation;

import java.util.HashMap;

/**
 * Created by AMDA on 30/03/2017.
 */
class ManageList {
    private HashMap<String, Long> productList;
    private HashMap<String, Long> userList;
    private HashMap<Long, String> invertProductList;
    private HashMap<Long, String> invertUserList;

    private long productIndex;
    private long userIndex;
    private long totalIndexes;

    public ManageList() {
        setProductList(new HashMap<String, Long>());
        setUserList(new HashMap<String, Long>());

        setInvertProductList(new HashMap<Long, String>());
        setInvertUserList(new HashMap<Long, String>());
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

    public HashMap<String, Long> getProductList() {
        return productList;
    }

    public void setProductList(HashMap<String, Long> productList) {
        this.productList = productList;
    }

    public HashMap<String, Long> getUserList() {
        return userList;
    }

    public void setUserList(HashMap<String, Long> userList) {
        this.userList = userList;
    }

    public HashMap<Long, String> getInvertProductList() {
        return invertProductList;
    }

    public void setInvertProductList(HashMap<Long, String> invertProductList) {
        this.invertProductList = invertProductList;
    }

    public HashMap<Long, String> getInvertUserList() {
        return invertUserList;
    }

    public void setInvertUserList(HashMap<Long, String> invertUserList) {
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
