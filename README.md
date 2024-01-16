## Getting Started

Welcome to Java db guardian, an application that automatically runs the SQL files that have been added into your project if they have not been run yet.
This exists to keep your database up to date in a project where multiple people will be adding SQL files into the repository, this way you do not have to manually keep track of files anymore.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
