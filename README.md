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

Architecture. 

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