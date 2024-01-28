## Getting Started

Welcome to Java db guardian, an application that automatically runs the SQL files that have been added into your project if they have not been run yet.
This exists to keep your database up to date in a project where multiple people will be adding SQL files into the repository, this way you do not have to manually keep track of files anymore.

db-guardian will always prioritise the folder called main in the target directory.

> Note the current version does not support delimiter changing, this is planned to be released soon

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

This project is dependent on the following packages:
- MariaDB Connector/J 3.3.2
