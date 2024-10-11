<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="{{ csrf_token() }}">
    <title> @yield('title')</title>
    <link rel="stylesheet" href="{{asset('css/style.css')}}">
</head>
<body>
    <div id="container">
        <header>
            <h1> Bienvenue dans le site de consultation de vols </h1>
        </header>
        <nav>
            <ul id="navList">
                <li><a href="/">Accueil</a></li>
                <li><a href="/flights">Vols</a></li>
                <li><a href="/searchs">Recherches</a></li>
                @auth
                <li>
                    <form  action="/logout" method="POST">
                        @csrf
                        <button id="logoutLink" type="submit">
                            Log out of {{Auth::user()->login}}
                        </button>
                    </form>
                </li>
                @endauth

            </ul>
        </nav>
        <main>
            @yield('content')
        </main>
        <footer>
            <h1> WEBG4-WEBII-NRI-JUIN 2024 </h1>
        </footer>
    </div>
</body>
</html>
