This is a Kotlin Multiplatform project targeting Android, Desktop.

## Setup & Installation

### Android

1.  Download the app APK.
2.  Enable "Installation from Unknown Sources" in your Android phone's settings (if you haven't already). This setting might be under "Security" or "Apps".
3.  Open the downloaded `.apk` file and follow the prompts to install the application.
4.  Enjoy the app!

### Desktop (macOS) [.dmg]

1.  Ensure you have a compatible version of macOS.
2.  Download the latest `.dmg` file of the app.
3.  Open the downloaded `.dmg` file.
4.  Drag the application icon into your "Applications" folder.
5.  You can now launch the app from your Applications folder or Launchpad.
6.  Enjoy!

### Architecture. 

This project is divided into three layers
 - Data
   - Local
     - Database
       - Used to save the data of the favorites breed.
   - Remote
     - Apis
       - Used to get data from the API.
 - Domine
   - Used to handle the business logic.
 - Presentation
   - MVVM
     - Used to handle the business logic and the UI.
   - MVI 
     - Used to handle the state of the UI.

### How to use the app

1. Install the app.
2. Sign in with email and password.
    - The app validates the email and password structure.
    - The password must have:
        - a lowercase letter
        - an uppercase letter
        - a number
        - length from 8 to 10 characters
3. After the first login, the app will remember the user, and in a second use, it won't show the register form.
4. The app will show a view with a list of cat's breeds.
5. If you tap over a card, it will show the full detail of the breed.
6. You can save the breed as your favorite by pushing the heart button, but if the breed is already in your favorites and you push it again, it will be removed from your favorite list.
7. At the top bar you will see a heart icon; if you push it, you will see a list of your favorite breeds. 