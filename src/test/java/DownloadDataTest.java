import java.io.IOException;
import java.sql.SQLException;

public class DownloadDataTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        DownloadData dd = new DownloadData("root", "admin");
        dd.ShowAll();
        System.out.println("-------------------------------------");
        dd.ShowSomeSortOfGames("computer game");
        System.out.println("-------------------------------------");
        dd.ShowGamesAboveLevel(4);
    }
}
