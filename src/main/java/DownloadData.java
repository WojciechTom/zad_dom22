import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.*;

public class DownloadData {

    private String user;
    private String password;
    Connection con;
    Statement stat;


    public DownloadData(String user, String password) throws ClassNotFoundException, SQLException {
        this.user = user;
        this.password = password;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/gry?&serverTimezone=UTC";
        con = DriverManager.getConnection(url, user, password);
        stat = con.createStatement();
    }


    public void ShowAll() throws SQLException, ClassNotFoundException, IOException {
        String query = "select * from games";
        ResultSet res = stat.executeQuery(query);
        print(res);
        ResultSet res1 = stat.executeQuery(query);
        saveToFile(res1, "Query1");
    }


    public void ShowGamesAboveLevel(int MinRating) throws SQLException, IOException {
        String query = "select * from games where rating > " + MinRating + " ";
        ResultSet res = stat.executeQuery(query);
        print(res);
        ResultSet res1 = stat.executeQuery(query);
        saveToFile(res1, "Query2");
    }


    public void ShowSomeSortOfGames(String sort) throws SQLException, IOException {
        String query = "select * from games where sort  = \"" + sort + "\" ";
        ResultSet res = stat.executeQuery(query);
        print(res);
        ResultSet res1 = stat.executeQuery(query);
        saveToFile(res1, "query3");
    }



    private void print(ResultSet res) throws SQLException {
        String title, producer, sort, type;
        int price, rating;
        while (res.next()) {
            // res.next();
            title = res.getString(2);
            producer = res.getString(3);
            sort = res.getString(4);
            type = res.getString(5);
            price = res.getInt(6);
            rating = res.getInt(7);

            System.out.println(title + " | " + producer + " | " + sort + " | " + type + " | " + price + " | " + rating);
        }
    }


    private void saveToFile(ResultSet res, String fileName) throws IOException, SQLException {

        String title, producer, sort, type;
        int price, rating;
        FileWriter fw = new FileWriter((fileName + ".csv"));
        BufferedWriter bw = new BufferedWriter(fw);
        while (res.next()) {
            // res.next();
            title = res.getString(2);
            producer = res.getString(3);
            sort = res.getString(4);
            type = res.getString(5);
            price = res.getInt(6);
            rating = res.getInt(7);
        bw.write(title + ";" + producer + ";" + sort + ";" + type + ";" + price + ";" + rating);
        bw.newLine();
        }

        bw.close();


    }

}


