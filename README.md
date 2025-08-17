# MeteoApi 🌤️

**MeteoApi** est une API météo simple créée avec Ktor. Elle permet d’obtenir la météo actuelle d’une ville donnée dans un pays spécifique.

---


---

## 🚀 Fonctionnalités


---

## 🌡️ Endpoint principal : Météo

**GET** `/meteo`

### Paramètres

| Paramètre | Type   | Description                 |
|-----------|--------|-----------------------------|
| `pays`   | string | Nom du pays (ex: Cameroun) |
| `ville`  | string | Nom de la ville (ex: Yaoundé) |

### Exemple de requête


```bash
    curl -X GET "http://127.0.0.1:8080/meteo?pays=cameroun&ville=yaounde"

```

### Exemple de réponse

```json
{
    "adresse": "Yaoundé, Cameroun",
    "jour": "2025-08-17",
    "conditions": "Partially cloudy",
    "temperature": 70.3
}


