# Disney Attractions Around the World

This project is a website showcasing the attractions at Disney parks around the world. It provides detailed information about each attraction, including posters and descriptions, to enhance visitors' experiences.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Environment Configuration](#environment-configuration)
- [Data Import](#data-import)
- [Launch Laravel Server](#launch-laravel-server)
- [Authors](#authors)

## Features
- Presentation of attractions from Disney parks around the world.
- Display of details for each attraction, including posters and descriptions.
- Enhanced user experience with an intuitive interface.

## Technologies Used
- **PHP**: Main programming language.
- **Laravel**: Framework used to develop the application.
- **Blade**: Laravel's template engine for rendering views.
- **HTML**: Structure of web pages.
- **CSS**: Styles of web pages.
- **MySQL**: Database management system used to store data.
- **XAMPP**: Development environment used to run Apache and MySQL.

## Installation

### 1. Clone the project
Clone the GitHub repository into your local environment:

```bash
git clone https://github.com/nesteho/academic_projects.git
cd guide_disney_land_paris

```

### 2. Install dependencies
Install Laravel dependencies using Composer

```bash
composer install
```
##  Environment Configuration

### 1. .env Setup
Copy the .env.example file to .env and modify it to match your local database configuration.

```bash
cp .env.example .env
```

### 2. Generate an application key 
At the root of your Laravel project, run the following command:

```bash
php artisan key:generate
```

### 3. Run migrations

```bash
php artisan migrate
```

### Importation des données

### 1.  Connect to MySQL 

```bash
mysql -u {username} -p
```
### 2. Create the database

```sql
CREATE DATABASE parkdb;
```
### 3. Verify that the database has been created

```sql
SHOW DATABASES;
```
### 4. Import project data
```sql
USE parkdb;
SOURCE database/sql/parkdb.sql;
EXIT;
```
### Launch Laravel Server
Démarrez le serveur Laravel en exécutant la commande suivante :

```bash
php artisan serve
```
The Laravel application will be accessible at http://localhost:8000.



### Authors

This project was developed by Nesteho. The database used for this project was provided by TNI as part of the course materials.