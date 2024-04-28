import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    private BufferedReader reader;

    public InputReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readCurrency(String message) throws IOException {
        System.out.print(message);
        return reader.readLine();
    }

    public double readAmount(String message) throws IOException {
        System.out.print(message);
        return Double.parseDouble(reader.readLine());
    }

    public boolean continuar(String message) throws IOException {
        System.out.print(message);
        String respuesta = reader.readLine().trim().toLowerCase();
        return respuesta.equals("s");
    }



}

