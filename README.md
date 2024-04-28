# Note App

Description
-------------

<p> The project can keep your notes. You can edit your notes and delete them. You need to log in with e-mail and password. You can also use Google authentication to log in. <p>

<table align="center">
  <tr>
    <th>Pages in the app</th>
  </tr>
  <tr>
    <td>Login Screen</td>
  </tr>
  <tr>
    <td>Notes Screen</td>
  </tr>
  <tr>
    <td>Add Edit Note Screen</td>
  </tr>
</table>

## Prerequisites

1. Clone the repo

```sh
https://github.com/enesokurterzi/note-app.git
```

2. Enter your web client id in `local.properties`

```JS
WEB_CLIENT_ID='ENTER YOUR API'
```

## Module structure

<div align="center">
	<img src="https://github.com/enesokurterzi/note-app/assets/113862251/cbd56dd2-76aa-4098-a63e-aa7361b3fbce">
</div>

## Tech Stack

- `Multi-Module Setup`: Utilizing a sophisticated structural design, the project logically segregates its elements to facilitate maintenance and enhance scalability.
- `Clean Architecture Approach`: Following clean architecture principles, the project ensures modularity, independence, and testability of code components.
- `MVI Design Pattern`: Employing the Model-View-Intent (MVI) design pattern, the project establishes a distinct division between user interface actions, state management, and business logic, resulting in improved code clarity and ease of maintenance.
- `Dependency Injection With Hilt`: Leveraging Hilt for dependency injection, the project reduces dependencies between components and enhances testability.
- `Coroutines & Flows`: Handles asynchronous tasks smoothly and efficiently, enhancing UI responsiveness with simplified code for complex tasks.
- `Firebase`: Firebase is a platform by Google offering developers a range of tools for app development, including user authentication, database management, file storage, analytics, messaging, and more, speeding up development and scaling processes.
- `Android Credentials`: Provides tools for storing and managing user credentials (such as passwords and usernames) securely in Android applications, offering APIs to securely store and share credentials across apps, enhancing user experience and application security.
- `Google Identity`: Offers functionality for managing Google Identity, enabling seamless authentication and access to user information across Android applications.
- `Secrets Gradle Plugin`: Facilitates secure management of API keys and other sensitive information within Android projects.
- `KSP`: Provides the Kotlin Symbol Processing (KSP) framework, enabling advanced compile-time code generation and analysis for Kotlin projects, enhancing developer productivity and performance.
- `Material 3`: Google's latest design language for Android, enhancing visual appeal and user experience.
- `Splash Screen`: The Splash Screen library for Android apps is designed to provide users with a welcoming screen upon app launch.
- `Localization`: Ensures that an app can adapt its content and user interface to different languages and regions, enabling it to reach a diverse global audience.
- `Dark / Light Theme`: Dark and Light themes in Android allow users to customize their app's appearance based on their preferences and environmental conditions, providing flexibility and enhancing usability in various lighting environments.


  ## Images from the Project

### Light Theme

| Login Screen | Login Screen | Notes Screen | Add Edit Note Screen |
| ----------- | ---------------- | ---------------- | ---------------- |
| ![light_theme_login_screen_login](https://github.com/enesokurterzi/note-app/assets/113862251/91690879-e8d0-4a6b-b191-9922cac425ca) | ![light_theme_login_screen_sign_up](https://github.com/enesokurterzi/note-app/assets/113862251/330bed65-29c5-4709-abd9-d7458fe94d1c) | ![light_theme_notes_screen](https://github.com/enesokurterzi/note-app/assets/113862251/49b64359-9cdf-4396-98ec-ab752ed6cb4c) | ![light_theme_add_edit_note_screen](https://github.com/enesokurterzi/note-app/assets/113862251/74adbf20-438b-4824-8565-87ce70184323)

### Dark Theme

| Login Screen | Login Screen | Notes Screen | Add Edit Note Screen |
| ----------- | ---------------- | ---------------- | ---------------- |
| ![dark_theme_login_screen_login](https://github.com/enesokurterzi/note-app/assets/113862251/aa63d3d6-da71-4a37-9d1c-9fab65ef5275) | ![dark_theme_login_screen_sign_up](https://github.com/enesokurterzi/note-app/assets/113862251/b13d5a46-eaa8-467e-bb12-77d955b2d70e) | ![dark_theme_notes_screen](https://github.com/enesokurterzi/note-app/assets/113862251/6f0157a8-1150-4f71-af86-bccda171aac9) | ![dark_theme_add_edit_note_screen](https://github.com/enesokurterzi/note-app/assets/113862251/d1079741-1c06-4521-9eda-51d69944a2f9)

## Video from the Project

<div align="center">
	<video src="https://github.com/enesokurterzi/note-app/assets/113862251/0e7b7b74-9af0-4815-92ac-f4f57e7db4f2">
</div>
