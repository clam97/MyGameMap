package org.myproject;

public class User {
//    +----------+-------------+------+-----+---------+-------+
//            | Field    | Type        | Null | Key | Default | Extra |
//            +----------+-------------+------+-----+---------+-------+
//            | ID       | int(11)     |  NO  |PRI  | NULL    |       |
//            | QQ_EMAIL | varchar(50) |  NO  |     | NULL    |       |
//            | PASSWORD | varchar(50) |  NO  |     | NULL    |       |
//            | VIP      | int(11)     |  NO  |     | NULL    |       |
//            +----------+-------------+------+-----+---------+-------+
//

public User(){

}
    public User(String QQ_EMAIL, String PASSWORD) {
        this.QQ_EMAIL = QQ_EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public User(String QQ_EMAIL, String PASSWORD, int VIP) {
        this.QQ_EMAIL = QQ_EMAIL;
        this.PASSWORD = PASSWORD;
        this.VIP = VIP;
    }


    @Override
    public String toString() {
        return "account:"+this.QQ_EMAIL+"\n"+"password:"+this.PASSWORD;
    }

    //数据库的主键
    private int ID;
    //qq邮箱
    private String QQ_EMAIL;
    //密码
    private String PASSWORD;
    //0代表非会员，1代表会员
    private int VIP;

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getQQ_EMAIL() {
        return QQ_EMAIL;
    }

    public void setQQ_EMAIL(String QQ_EMAIL) {
        this.QQ_EMAIL = QQ_EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    public int getVIP() {
        return VIP;
    }

    public void setVIP(int VIP) {
        this.VIP = VIP;
    }
}
