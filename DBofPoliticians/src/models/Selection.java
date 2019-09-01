package models;

public class Selection {
    private String selection;
    private String politician;
    private String party;
    private String email;
    private String constituency;
    private int telephone;

    public Selection(String selection, String politician, String party, String email, String constituency, int telephone){
        this.constituency = constituency;
        this.email = email;
        this.politician = politician;
        this.selection = selection;
        this.telephone = telephone;
        this.party = party;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getPolitician() {
        return politician;
    }

    public void setPolitician(String politician) {
        this.politician = politician;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
