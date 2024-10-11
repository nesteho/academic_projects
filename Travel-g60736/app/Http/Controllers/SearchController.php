<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Auth;
use Illuminate\Http\Request;
use App\Models\Search; 

class SearchController extends Controller
{
    function getSearches(){
        $data['tuser_login'] = Auth::user()->login; 
        $searches = Search::getSearches($data);
        return view('searches', ['searches' => $searches]);
    }
    public static function store(Request $request){
        $data = $request->validate([
            'fromAirport' => 'required|string|size:3',
            'toAirport' => 'required|size:3',
        ]);
        $data['tuser_login'] = Auth::user()->login;
        Search::store($data);
    }
}
