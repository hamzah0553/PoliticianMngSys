package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static system.DB.getDB;

public class ParliamentFetcher {

    public ArrayList<ParliamentMember> getParliamentFromDB(){

        //Get the parliament members and store them in the arraylist
        ArrayList<ParliamentMember> members = new ArrayList<>();
        int count = 0;

        System.out.println("Dk is running");
        Connection connection = getDB().getConnection();
        String sql = "SELECT pol.name, par.name, pol.email, con.name, pol.telephone, pol.workPhone " +
                     "FROM Politician pol, Party par, Constituency con " +
                     "WHERE pol.partyId = par.id AND pol.constituencyId = con.id;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                members.add(new ParliamentMember(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(4),
                        resultSet.getString(3), resultSet.getString(5),
                        resultSet.getString(6)));
            }
            return members;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
