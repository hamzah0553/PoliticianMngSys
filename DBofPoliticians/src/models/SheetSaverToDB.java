package models;

import system.DB;

import java.sql.*;
import java.util.ArrayList;

public class SheetSaverToDB {
    private ArrayList<ArrayList<String>> sheetResult;
    private int sheetNumber;

    public SheetSaverToDB(ArrayList<ArrayList<String>> sheetResult, int sheetNumber){
        this.sheetNumber = sheetNumber;
        this.sheetResult = sheetResult;
    }

    public void saveSheetToDb(){
        determineSql();
    }

    private int getPoliticianId(String politicianName){
        System.out.println(politicianName);
        int politicianId = 0;
        try {
            Connection conn = DB.getDB().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT id FROM Politician p WHERE p.name = ? LIMIT 1");
            statement.setString(1, politicianName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) politicianId = resultSet.getInt(1);
            statement.close();
            conn.close();
            return politicianId;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(politicianId);
        return politicianId;
    }

    private int getSelectionId(String selectionName){
        int selectionId = 0;
        try {
            Connection conn = DB.getDB().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT id FROM Selection s WHERE s.name = ? LIMIT 1");
            statement.setString(1, selectionName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) selectionId = resultSet.getInt(1);
            statement.close();
            conn.close();
            return selectionId;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return selectionId;
    }

    private int getPartyId(String partyName){
        int partyId = 0;
        boolean partyExist = false;

        try {
            Connection conn = DB.getDB().getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT* FROM Party WHERE Party.name = ? LIMIT 1");
            statement.setString(1, partyName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                partyId = rs.getInt(1);
                partyExist = rs.getString(2).equals(partyName);

            }

            statement.close();
            conn.close();

            if (!partyExist){
                Connection con = DB.getDB().getConnection();
                PreparedStatement preparedStatement =
                        con.prepareStatement("INSERT INTO Party(name, leaderId) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, partyName);
                preparedStatement.setInt(2, 0);
                preparedStatement.executeQuery();

                ResultSet generatedKey = preparedStatement.getGeneratedKeys();
                if (generatedKey.next()){
                    partyId = generatedKey.getInt(1);
                }
                preparedStatement.close();
                con.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return partyId;
    }

    private int getConstituencyId(String constituencyName){
        int constituencyId = 0;
        boolean constituencyExist;
        constituencyName = constituencyName.replaceAll("\\s+", "");

        try {
            Connection conn = DB.getDB().getConnection();
            PreparedStatement statement =
                    conn.prepareStatement("SELECT id, name FROM Constituency WHERE Constituency.name = ? LIMIT 1");
            statement.setString(1, constituencyName);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                constituencyId = rs.getInt(1);
                constituencyExist = rs.getString(2).equals(constituencyName);
            }
            return constituencyId;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return constituencyId;

    }

    private void determineSql() {
        String sql;
        switch (sheetNumber) {
            case 0:
                saveToParliament();
                break;
            case 1:
                saveSelection();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    public void saveToParliament(){
        String sql = "INSERT INTO Politician(name, partyId, email, telephone, workPhone, constituencyId) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            for (int i = 0; i < sheetResult.size(); i++){
                Connection conn = DB.getDB().getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, sheetResult.get(i).get(1));
                statement.setInt(2, getPartyId(sheetResult.get(i).get(0)));
                statement.setString(3, sheetResult.get(i).get(5));

                String currentPhone = sheetResult.get(i).get(4).replaceAll("\\s+", "");
                String currentWorkPhone = sheetResult.get(i).get(6).replaceAll("\\s+", "");

                if (currentPhone.equals("")) statement.setInt(4, 0);
                else statement.setInt(4, Integer.parseInt(currentPhone));
                if(currentWorkPhone.equals("")) statement.setInt(5, 0);
                else statement.setInt(5, Integer.parseInt(currentWorkPhone));

                statement.setInt(6, getConstituencyId(sheetResult.get(i).get(3)));

                statement.executeQuery();
                statement.close();
                conn.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void saveSelection(){
        String sql = "INSERT INTO SelectionMembers(politicianId, selectionId) VALUES (?, ?)";
        try {
            for (int i = 0; i < sheetResult.size(); i++){
                Connection conn = DB.getDB().getConnection();
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, getPoliticianId(sheetResult.get(i).get(1)));
                statement.setInt(2, getSelectionId(sheetResult.get(i).get(2)));

                statement.executeQuery();
                statement.close();
                conn.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

}
