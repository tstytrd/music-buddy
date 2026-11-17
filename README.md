# ðŸŽµ Music Buddy

**Interactive Music Theory Learning App for Android**

Music Buddy is an open-source Android application designed to help beginners learn music fundamentals through interactive lessons, visual notation references, and quizzes. Originally developed at Loyola University Chicago as a COMP 412 project in 2013 and fully modernized in 2026.

---

## ðŸ“± Screenshots

> Launch the app to explore the dark-themed interactive dashboard, lesson cards, and quiz screens.

---

## âœ¨ Features

- **Learn Music Fundamentals** â€” Treble Clef Notes, Bass Clef Notes, Dynamics, and Note & Rest Types with visual notation images
- **Interactive Quizzes** â€” Music Theory quiz with multiple choice questions and scoring
- **Background Music** â€” App theme music plays automatically with a convenient **ðŸ”Š Mute / ðŸ”‡ Unmute** toggle on the main screen
- **Curated Resources** â€” Verified active music education links (updated 2026):
  - [MusicTheory.net](https://www.musictheory.net)
  - [Teoria Music Theory](https://www.teoria.com)
  - [Capotasto Music â€” Free Beginner Piano Scores](https://www.capotastomusic.com/piano-sheet-music/basic.htm)
  - [How to Read Music â€” TED-Ed (YouTube)](https://www.youtube.com/watch?v=ZN41d7Txcq0)
  - [Pre Piano One](https://prepiano.com/)
  - [IMSLP Petrucci Music Library](https://imslp.org/)
- **Dark Theme** â€” Full Material Design 3 Dark Theme for comfortable reading in any lighting

---

## ðŸ›  Tech Stack

| Component | Details |
|---|---|
| Language | Java |
| UI Framework | Material Design 3 (AndroidX) |
| Min SDK | API 21 (Android 5.0 Lollipop) |
| Target SDK | API 34 (Android 14) |
| Build System | Gradle 8.7 + Android Gradle Plugin 8.5 |
| Theme | `Theme.Material3.Dark.NoActionBar` |
| Audio | `MediaPlayer` via background `Service` |

---

## ðŸš€ Getting Started

### Prerequisites
- [Android Studio](https://developer.android.com/studio) (Hedgehog or later recommended)
- Android SDK 34
- JDK 17+ (bundled with Android Studio)

### Clone & Build

```sh
git clone https://github.com/tstytrd/music-buddy.git
cd music-buddy
```

Open the project folder in **Android Studio** â€” it will automatically detect the Gradle build files and sync dependencies.

Then run the app on a device or emulator using the **Run â–¶** button, or from the terminal:

```sh
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

---

## ðŸ“ Project Structure

```
music-buddy/
â”œâ”€â”€ app/
â”‚   â””â”€â”€ src/main/
â”‚       â”œâ”€â”€ java/edu/luc/mb/
â”‚       â”‚   â”œâ”€â”€ Splash.java                 # Splash screen
â”‚       â”‚   â”œâ”€â”€ MainActivity.java           # Main dashboard
â”‚       â”‚   â”œâ”€â”€ Learn.java                  # Learn hub (Treble, Bass, Dynamics, Notes)
â”‚       â”‚   â”œâ”€â”€ HorizontalListViewDemo.java # Visual notation browser
â”‚       â”‚   â”œâ”€â”€ Quizzes.java                # Quiz selection screen
â”‚       â”‚   â”œâ”€â”€ QuizFunActivity.java        # Quiz runner
â”‚       â”‚   â”œâ”€â”€ QuestionActivity.java       # Individual question display
â”‚       â”‚   â”œâ”€â”€ Resources.java              # Verified external resources
â”‚       â”‚   â”œâ”€â”€ ContactUs.java              # About & project info
â”‚       â”‚   â””â”€â”€ MusicService.java           # Background audio service
â”‚       â”œâ”€â”€ res/
â”‚       â”‚   â”œâ”€â”€ drawable/                   # Musical notation images
â”‚       â”‚   â”œâ”€â”€ layout/                     # Material 3 screen layouts
â”‚       â”‚   â”œâ”€â”€ raw/                        # music_buddy_theme.mp3
â”‚       â”‚   â””â”€â”€ values/                     # Colors, Strings, Styles
â”‚       â””â”€â”€ AndroidManifest.xml
â”œâ”€â”€ build.gradle
â”œâ”€â”€ settings.gradle
â””â”€â”€ gradle.properties
```

---

## ðŸŽ“ Original Project

This app was originally created as part of **COMP 412 â€” Open Source Software Development** at **Loyola University Chicago** in Spring 2013.

### Original Team

| Name | Role |
|---|---|
| Juan Antonio Fuentes | Owner Â· Main Developer |
| Lauren VanVleet | Owner Â· Main Developer |
| Rafal Zukowski | Committer Â· Main Developer |
| Amy Klouse | UI Tester |
| Anthony Borrow, S.J. | Committer Â· Moodle Integration Specialist |
| Gabriel Fuentes | UI Tester |
| Kevin Karam | Content Contributor Â· Musical Concepts |
| Corby Schmitz | Tester |

---

## ðŸ“œ License

This project is licensed under the **GNU General Public License v3.0**.
See [LICENSE](https://www.gnu.org/licenses/gpl-3.0.html) for details.

---

## ðŸ”„ Changelog

| Version | Date | Highlights |
|---|---|---|
| 2.0.0 | Nov 2026 | Migrated to Gradle + AndroidX, Material 3 Dark Theme, background music service with mute button, verified resource links, GitHub migration |
| 1.2.7 | May 2013 | Optional background music via menu ON/OFF |
| 1.2.6 | 2013 | Integrated Music Buddy theme, Google Play submission |
| 1.2.5 | 2013 | Grammar fixes, small code corrections |
| 1.0.0 | Feb 2013 | Initial release |

---

> *Built with â¤ï¸ for music learners everywhere.*
