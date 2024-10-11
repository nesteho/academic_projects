<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Tuser; 
use Illuminate\Support\Facades\Auth;

class LogController extends Controller
{
    function login(Request $request) {

        $creditentials = $request->validate(['login' => 'required|string']);
        $user = Tuser::where('login',$creditentials['login'])->first();
        if (!$user) { //si null 
            return response()->json(['error' => 'Login not found'], 404);
        }
        Auth::login($user);
        $request->session()->regenerate();

        return redirect('/')->with('message', 'Login successful');
    }

    public function logout(Request $request) 
    {
        Auth::logout();
        $request->session()->invalidate();
        $request->session()->regenerateToken();
        return redirect('/')->with('message', 'Logout successful');
        return response()->json(['message' => 'Logout successful', 'redirect' => url('/')], 200);                                
    }
}
