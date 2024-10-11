<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="{{ asset('css/style.css') }}">
    <title>@yield('title')</title>
</head>

<body>
<div id="container">
    <header>
        <h1>WEBII-Parcs</h1>
    </header>

    <nav>
            <ul>
                <li> <a href="{{ route('index') }}"> Accueil </a></li>
                <li><a href="{{ route('update_description') }}">Ajouteur une Description </a></li>
        </ul>
    </nav>
    <main>
            @yield('content')
    </main>
    <footer>
        <h3>
            WEBG4-WEBII-g60736 AHMED KAHIN Nesteho
        </h3>
    </footer>
    </div>
</body>

</html>