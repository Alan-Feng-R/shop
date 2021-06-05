package domain;

public class User {
    private String username;
    private String password;
    private Integer type;
    private String companyName;

    public static final int ADMIN_TYPE=1;
    public static final int USER_TYPE=0;


    public User() {
    }

    public User(String username, String password, Integer type, String companyName) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.companyName = companyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
