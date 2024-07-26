import requests
import json
import os

def get_movie_data(title):
    api_key = "77c4fd45"
    base_url = "http://www.omdbapi.com/"
    params = {
        't': title,
        'apikey': api_key
    }
    response = requests.get(base_url, params=params)
    if response.status_code == 200:
        return response.json()
    else:
        return {"error": f"Request failed with status code {response.status_code}: {response.text}"}

titles = [
    "Inception", "The Dark Knight", "Interstellar", "Pulp Fiction", "Fight Club",
    "Forrest Gump", "The Matrix", "The Shawshank Redemption", "The Godfather", "The Lord of the Rings",
    "Star Wars", "Jurassic Park", "Titanic", "The Silence of the Lambs", "Se7en",
    "The Green Mile", "Gladiator", "Avatar", "The Departed", "Schindler's List",
    "The Prestige", "The Lion King", "Toy Story", "Finding Nemo", "Back to the Future",
    "Braveheart", "The Truman Show", "Good Will Hunting", "Saving Private Ryan", "12 Angry Men",
    "Casablanca", "Citizen Kane", "Psycho", "A Clockwork Orange", "2001: A Space Odyssey",
    "Taxi Driver", "Apocalypse Now", "The Shining", "E.T. the Extra-Terrestrial", "The Great Dictator",
    "The Big Lebowski", "No Country for Old Men", "There Will Be Blood", "The Pianist", "Amélie",
    "Life Is Beautiful", "The Sixth Sense", "Memento", "American Beauty", "City of God",
    "Requiem for a Dream", "Pan's Labyrinth", "Oldboy", "Spirited Away", "The Avengers",
    "Guardians of the Galaxy", "Iron Man", "The Dark Knight Rises", "Django Unchained",
    "Inglourious Basterds", "Kill Bill", "Reservoir Dogs", "The Hateful Eight", "The Godfather: Part II",
    "The Godfather: Part III", "Scarface", "The Wolf of Wall Street", "Goodfellas", "Casino",
    "Raging Bull", "Mean Streets", "The Irishman", "Donnie Brasco", "Heat",
    "L.A. Confidential", "Chinatown", "The Big Sleep", "Vertigo", "Rear Window",
    "North by Northwest", "Strangers on a Train", "Rope", "Dial M for Murder", "Rebecca",
    "Shadow of a Doubt", "Notorious", "To Catch a Thief", "Suspicion", "Spellbound",
    "The Birds", "Frenzy", "Topaz", "Torn Curtain", "The Man Who Knew Too Much",
    "The 39 Steps", "Blackmail", "The Lady Vanishes", "Sabotage", "The Lodger"
]

movies_data = []

for title in titles:
    movie_data = get_movie_data(title)
    movies_data.append(movie_data)

# Alternativer Speicherpfad definieren
save_path = r"C:\MisaAppProject\app\src\main\python\Movies.json"

# Erstellen des Verzeichnisses, falls es nicht existiert
os.makedirs(os.path.dirname(save_path), exist_ok=True)

# Daten in eine JSON-Datei speichern
with open(save_path, 'w', encoding='utf-8') as f:
    json.dump(movies_data, f, ensure_ascii=False, indent=4)

print(f"Movies data has been saved to {save_path}")
