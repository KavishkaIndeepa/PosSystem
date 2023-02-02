package lk.ijse.riceMil.to;

public class Supplier {
    private String nic;
    //private String supId;
    private String name;
    private String address;
    private String telNo;
    private String statues;

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

//    public String getSupId() {
//        return supId;
//    }
//
//    public void setSupId(String supId) {
//        this.supId = supId;
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

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public Supplier(String nic,String name, String address, String telNo, String statues) {
        this.nic = nic;
      //  this.supId = supId;
        this.name = name;
        this.address = address;
        this.telNo = telNo;
        this.statues = statues;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "nic='" + nic + '\'' +
               // ", supId='" + supId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telNo='" + telNo + '\'' +
                ", statues='" + statues + '\'' +
                '}';
    }
}
