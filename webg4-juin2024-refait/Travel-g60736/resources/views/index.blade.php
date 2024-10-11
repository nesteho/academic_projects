@extends('canevas')
@section('title','Accueil')
@section('content')
<div id="logDiv">
    @guest
        
    
    <form id="loginForm" action="/login" method="post">
        @csrf
        <label for=""> Login : </label>
        <input id="login" name="login" type="text">
        <button id="loginButton" type="submit">Se connecter</button>
    </form>
    @endguest
    @if(session('message'))
    <p id="success-message">{{ session('message') }}. 
        @auth()
            You are logged as {{ Auth::user()->login }}.
        @endauth
    </p>
    @endif

    @if($errors->any())
    <div class="error-messages">
        @foreach($errors->all() as $error)
        <p>{{ $error }}</p>
        @endforeach
    </div>
    @endif
</div>
<img id="avion" src="{{asset('images/avion.png')}}" alt="">

@endsection
