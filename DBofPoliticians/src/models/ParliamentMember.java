package models;

public class ParliamentMember {
    private String name;
    private String party;
    private String constituency;
    private String telephone;
    private String workPhone;
    private String email;

    public ParliamentMember(String name, String party, String constituency,
                            String email, String telephone, String workPhone){
        this.name = name;
        this.party = party;
        this.constituency = constituency;
        this.telephone = telephone;
        this.workPhone = workPhone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getConstituency() {
        return constituency;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
