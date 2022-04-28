import server.Client;

public class GameStartClient {
    public static void main(String[] args) throws Exception {
        // If on different computer, replace parameter "localhost" by IP Address shown by Server
        new Client("localhost");
    }
}
