# Paper World Manager

**A Paper plugin to clone, manage and switch between worlds – with a world GUI, scoreboard, tablist and a daily happy hour.**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Paper](https://img.shields.io/badge/Paper-1.21-0A84FF?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-3D3929?style=for-the-badge)

<br />

## Features

- **Clone worlds** – copy any world into a new one with a single command
- **Personal home worlds** – teleport to your own world
- **Delete worlds** safely, including unloading players first
- **World GUI** – browse and join worlds from an inventory menu
- **Scoreboard & tablist** – live sidebar (FastBoard) and custom tablist
- **Happy hour** – a daily event that starts automatically at 22:00
- Swap-hand shortcut to open the menu

<br />

## Commands

| Command | Description |
|:--|:--|
| `/cloneworld` | Clone a world |
| `/homeworld` | Teleport to your home world |
| `/deleteworld` | Delete a world |
| `/gui` | Open the world menu |

<br />

## Building

```bash
mvn package
```

Copy the jar from `target/` into your server's `plugins/` folder.

<br />

## License

Released under the [MIT License](LICENSE).
