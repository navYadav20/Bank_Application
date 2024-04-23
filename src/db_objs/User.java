package db_objs;

import java.math.BigDecimal;
import java.math.RoundingMode;

// user entity which is used to store user info. (id, username, current Balance)
public class User {

    private final int id;
    private final String username, password;
    private BigDecimal currentBalance;

    public User(int id, String username, String password, BigDecimal currentBalance){
        this.id = id;
        this.username = username;
        this.password = password;
        this.currentBalance = currentBalance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal newBalance){
        // store new value to the 2nd decimal place
        currentBalance = newBalance.setScale(2, RoundingMode.FLOOR);
    }

}
