package lk.ijse.riceMil.to;

public class Customer {
    private String nic;
    //private String id;
    private String name;
    private String address;
    private String telNo;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
//
//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Customer(String nic, String name, String address,String telNo) {
        this.nic = nic;
       // this.id = id;
        this.name = name;
        this.address = address;
        this.telNo = telNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "nic='" + nic + '\'' +
            //    ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telNo='" + telNo + '\'' +
                '}';
    }
}
