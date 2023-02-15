import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * @author amandasena97@gmail.com
 */
public class Desafio{
    public static void main(String[] args) throws IOException{
        
        //Parte 1
        
        Path path = Paths.get("src/main/resources/input.txt");

        
        Map<String, String> agrupamento = Files.lines(path)
            .map(linha -> linha.split(","))
            .collect(Collectors.groupingBy(arr->arr[1],
            Collectors.mapping(it->it[0], Collectors.joining(","))));
            
        List<String> linhas = agrupamento.entrySet().stream().map(
                it -> String.format("Idade: %s -> [%s]",
                it.getKey(), it.getValue())).collect(Collectors.toList());

        Path outputpath = Paths.get("src/main/resources/output.txt");

        // parte 2

        OptionalDouble media = Files.lines(path).mapToInt(it 
            -> Integer.parseInt(it.split(",")[1]))
            .average();
        Double mediaDouble = media.orElseThrow(IllegalStateException::new);
        linhas.add(String.format("\nMédia de idade é %s",mediaDouble));
        
        Files.write(outputpath, linhas, StandardOpenOption.TRUNCATE_EXISTING);

    }
}