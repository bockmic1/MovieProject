
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EntryController {
    private List<Movie> entries = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public EntryController() {
        try {
            // Lade die Datei als Ressource
            Resource resource = new ClassPathResource("Movies.json");
            InputStream inputStream = resource.getInputStream();
            byte[] jsonData = inputStream.readAllBytes();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);
            for (JsonNode node : rootNode) {
                Movie movie = new Movie();
                movie.setTitle(node.get("Title").asText());
                movie.setYear(node.get("Year").asText());
                movie.setGenre(node.get("Genre").asText());
                movie.setDirector(node.get("Director").asText());

                // Füge Ratings hinzu
                JsonNode ratingsNode = node.get("Ratings");
                if (ratingsNode != null) {
                    for (JsonNode rating : ratingsNode) {
                        String source = rating.get("Source").asText();
                        String value = rating.get("Value").asText();
                        if (source.equals("Internet Movie Database")) {
                            movie.setImdbRating(value);
                        } else if (source.equals("Rotten Tomatoes")) {
                            movie.setRottenTomatoesRating(value);
                        }
                    }
                }
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("entries", entries);
        model.addAttribute("entry", new Movie());
        return "index";
    }

    @PostMapping("/add")
    public String addEntry(@ModelAttribute Movie entry, Model model) {
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(entry.getTitle())) {
                entries.add(movie);
                break;
            }
        }
        model.addAttribute("entries", entries);
        model.addAttribute("entry", new Movie());
        return "index";
    }

    @GetMapping("/suggest")
    @ResponseBody
    public List<String> suggest(@RequestParam String query) {
        return movies.stream()
                .map(Movie::getTitle)
                .filter(title -> title.toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
