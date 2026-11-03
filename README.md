# 🎵 Music Buddy

**Interactive Music Theory Learning App for Android**

Music Buddy is an open-source Android application designed to help beginners learn music fundamentals through interactive lessons, visual notation references, and quizzes. Originally developed at Loyola University Chicago as a COMP 412 project in 2013 and fully modernized in 2026.

---

## 📱 Screenshots

> Launch the app to explore the dark-themed interactive dashboard, lesson cards, and quiz screens.

---

## ✨ Features

- **Learn Music Fundamentals** — Treble Clef Notes, Bass Clef Notes, Dynamics, and Note & Rest Types with visual notation images
- **Interactive Quizzes** — Music Theory quiz with multiple choice questions and scoring
- **Background Music** — App theme music plays automatically with a convenient **🔊 Mute / 🔇 Unmute** toggle on the main screen
- **Curated Resources** — Verified active music education links (updated 2026):
  - [MusicTheory.net](https://www.musictheory.net)
  - [Teoria Music Theory](https://www.teoria.com)
  - [Capotasto Music — Free Beginner Piano Scores](https://www.capotastomusic.com/piano-sheet-music/basic.htm)
  - [How to Read Music — TED-Ed (YouTube)](https://www.youtube.com/watch?v=ZN41d7Txcq0)
  - [Pre Piano One](https://prepiano.com/)
  - [IMSLP Petrucci Music Library](https://imslp.org/)
- **Dark Theme** — Full Material Design 3 Dark Theme for comfortable reading in any lighting

---

## 🛠 Tech Stack

| Component | Details |
|---|---|
| Language | Java |
| UI Framework | Material Design 3 (AndroidX) |
| Min SDK | API 21 (Android 5.0 Lollipop) |
| Target SDK | API 34 (Android 14) |
| Build System | Gradle 8.7 + Android Gradle Plugin 8.5 |
| Theme | Theme.Material3.Dark.NoActionBar |
| Audio | MediaPlayer via background Service |

---

## 🚀 Getting Started

### Prerequisites
- [Android Studio](https://developer.android.com/studio) (Hedgehog or later recommended)
- Android SDK 34
- JDK 17+ (bundled with Android Studio)

### Clone & Build

\\\ash
git clone https://github.com/tstytrd/music-buddy.git
cd music-buddy
\\\

Open the project folder in **Android Studio** — it will automatically detect the Gradle build files and sync dependencies.

Then run the app on a device or emulator using the **Run ▶** button, or from the terminal:

\\\ash
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
\\\

---

## 📁 Project Structure

\\\
music-buddy/
├── app/
│   └── src/main/
│       ├── java/edu/luc/mb/
│       │   ├── Splash.java               # Splash screen
│       │   ├── MainActivity.java         # Main dashboard
│       │   ├── Learn.java                # Learn hub (Treble, Bass, Dynamics, Notes)
│       │   ├── HorizontalListViewDemo.java # Visual notation browser
│       │   ├── Quizzes.java              # Quiz selection screen
│       │   ├── QuizFunActivity.java      # Quiz runner
│       │   ├── QuestionActivity.java     # Individual question display
│       │   ├── Resources.java            # Verified external resources
│       │   ├── ContactUs.java            # About & project info
│       │   └── MusicService.java         # Background audio service
│       ├── res/
│       │   ├── drawable/                 # Musical notation images
│       │   ├── layout/                   # Material 3 screen layouts
│       │   ├── raw/                      # music_buddy_theme.mp3
│       │   └── values/                   # Colors, Strings, Styles
│       └── AndroidManifest.xml
├── build.gradle
├── settings.gradle
└── gradle.properties
\\\

---

## 🎓 Original Project

This app was originally created as part of **COMP 412 — Open Source Software Development** at **Loyola University Chicago** in Spring 2013.

### Original Team

| Name | Role |
|---|---|
| Juan Antonio Fuentes | Owner · Main Developer |
| Lauren VanVleet | Owner · Main Developer |
| Rafal Zukowski | Committer · Main Developer |
| Amy Klouse | UI Tester |
| Anthony Borrow, S.J. | Committer · Moodle Integration Specialist |
| Gabriel Fuentes | UI Tester |
| Kevin Karam | Content Contributor · Musical Concepts |
| Corby Schmitz | Tester |

---

## 📜 License

This project is licensed under the **GNU General Public License v3.0**.
See [LICENSE](https://www.gnu.org/licenses/gpl-3.0.html) for details.

---

## 🔄 Changelog

| Version | Date | Highlights |
|---|---|---|
| 2.0.0 | Nov 2026 | Migrated to Gradle + AndroidX, Material 3 Dark Theme, background music service with mute button, verified resource links, GitHub migration |
| 1.2.7 | May 2013 | Optional background music via menu ON/OFF |
| 1.2.6 | 2013 | Integrated Music Buddy theme, Google Play submission |
| 1.2.5 | 2013 | Grammar fixes, small code corrections |
| 1.0.0 | Feb 2013 | Initial release |

---

> *Built with ❤️ for music learners everywhere.*