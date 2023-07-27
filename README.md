

# Mimblu App - Mental Health Support

![floating_screen_phone_mockup](https://github.com/th3kumar/mimblu_assignment/assets/72141924/3db175ff-14b9-45b3-a078-3562769626cf)



## Overview

Mimblu is a mobile app designed to provide mental health support and connect users with professional therapists. It offers features such as symptom selection, match options, and personalized therapy plans.

## Technologies Used

- Kotlin: The app is primarily developed using Kotlin, a modern programming language for Android development.

- Android Architecture Components: MVVM - Utilized ViewModel and LiveData for handling UI-related data and lifecycle management.

- Retrofit: To perform API calls and handle network operations efficiently.

- RecyclerView: For displaying scrollable lists of match options and symptoms.

- Gson: To parse the JSON API responses into Kotlin data classes.


## Data Classes

The app uses Kotlin data classes to represent the data entities:

### SymptomItem

```kotlin
data class SymptomItem(
    val id: Int,
    val title: String,
    val isSelected: Boolean
)
```

The `SymptomItem` data class represents a symptom with its unique `id`, `title`, and the `isSelected` flag indicating whether the symptom is selected by the user.

### MatchOption

```kotlin
data class MatchOption(
    val id: Int,
    val title: String,
    val description: String,
    val duration: String,
    val videoDescription: String,
    val final_price: String // Use string value for final_price
)
```

The `MatchOption` data class represents a match option with its unique `id`, `title`, `description`, `duration`, `videoDescription`, and `final_price`.

### ApiResponse

```kotlin
data class ApiResponse(
    @SerializedName("list")
    val symptoms: List<SymptomItem>,
    @SerializedName("copyrighths")
    val copyrighths: String
)
```

The `ApiResponse` data class represents the response from the API, containing a list of `SymptomItem` objects and copyright information.

### MatchOptionsApiResponse

```kotlin
data class MatchOptionsApiResponse(
    @SerializedName("list")
    val matchOptions: List<MatchOption>
)
```

The `MatchOptionsApiResponse` data class represents the response from the API, containing a list of `MatchOption` objects.

## Data Flow

The Mimblu app follows the MVVM (Model-View-ViewModel) architecture, separating the UI (View) from the data and business logic (ViewModel) and the data representation (Model). The data flow in the app is as follows:

1. The `MainActivity` displays a list of symptoms using the `SymptomAdapter`. When the user selects or deselects a symptom, the `SymptomAdapter` updates the `SymptomViewModel`.

2. The `SymptomViewModel` holds the list of symptoms as `LiveData`. It observes changes in the user's selections and updates the UI accordingly.

3. When the user opens the `SecondActivity`, the `MatchOptionViewModel` fetches the list of match options from the API using Retrofit and updates its `LiveData`.

4. The `MatchOptionAdapter` observes changes in the `MatchOptionViewModel`'s `LiveData` and updates the RecyclerView with the latest match options.

5. The `MatchOptionViewModel` also parses the API response using Gson and updates its `LiveData` with the list of match options.

6. The RecyclerView displays the list of match options, and the user can interact with them.

7. When the user selects or deselects a match option, the `MatchOptionAdapter` updates the `MatchOptionViewModel`, which in turn updates the UI and handles the business logic.

---

Feel free to modify and expand on this template to include any additional information or details about the app's features and functionality. Happy coding!
## Features

- **Symptom Selection:** Users can select their symptoms from a list of options to receive personalized match options.

- **Match Options:** Users are provided with various match options based on their selected symptoms. These options include different therapy plans with duration, pricing, and video session details.

- **Personalized Therapy Plans:** Users can choose the most suitable therapy plan from the match options, tailored to their needs.

- **Unlimited Messaging + Voice Notes:** Users can communicate with therapists through unlimited messaging and voice notes, providing flexibility and accessibility to mental health support.

- **Video Sessions:** The selected therapy plan includes video sessions, allowing users to have face-to-face interactions with their therapists.

## Screenshots

![Beige Ilustration Virtual Zoom Background](https://github.com/th3kumar/mimblu_assignment/assets/72141924/fe42f4af-5f89-4559-a542-068dee2e92c6)

## Dark Mode

![Beige Ilustration Virtual Zoom Background (1)](https://github.com/th3kumar/mimblu_assignment/assets/72141924/907f629c-27c6-440b-9bf6-a80211a1277f)



## Getting Started

To use the Mimblu app, follow these steps:

1. Clone the repository: `git clone https://github.com/username/mimblu-app.git`

2. Open the project in Android Studio.

3. Build and run the app on an Android emulator or physical device.


## API Integration

The app fetches data from a backend API to populate the match options and symptom lists. The API provides JSON responses with details about the available therapy plans and symptoms.

## Future Enhancements

1. **User Authentication:** Implement user authentication to enable personalized therapy plan recommendations and user-specific data storage.

2. **Real-Time Messaging:** Integrate real-time messaging features to provide immediate therapist responses to users.

3. **Payment Integration:** Incorporate payment gateways to enable secure and seamless payment processing for therapy plans.

4. **Localization:** Add support for multiple languages to cater to a wider audience.

## Conclusion

Mimblu is a comprehensive mental health support app, empowering users to take charge of their mental well-being. By offering personalized therapy plans and easy access to professional therapists, the app aims to make mental health care accessible to everyone.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
