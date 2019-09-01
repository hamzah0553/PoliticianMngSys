package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static system.DB.getDB;

public class SelectionFetcher {

    public ArrayList<Selection> getSelectionsFromDb(){

        //Get the parliament members and store them in the arraylist
        ArrayList<Selection> selections = new ArrayList<>();
        int count = 0;

        System.out.println("Dk is running");
        Connection connection = getDB().getConnection();
        String sql = "SELECT pol.name, par.name, sel.name, con.name, pol.telephone, pol.email " +
                "FROM Politician pol, Party par, Constituency con, Selection sel, SelectionMembers selMem " +
                "WHERE pol.id = selMem.politicianId AND pol.constituencyId = con.id AND pol.partyId = par.id AND sel.id = selMem.selectionId;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                selections.add(new Selection(resultSet.getString(3),
                        resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(6), resultSet.getString(4),
                        resultSet.getInt(5)));
            }
            return selections;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
