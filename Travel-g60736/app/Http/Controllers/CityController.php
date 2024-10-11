<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\City; 

class CityController extends Controller
{ 
    function getAll(){
        $cities = City::orderBy('cAirport')->get();
       // return view('flights', json_encode('$cities') );
       return view('flights', ['cities' => $cities]);
    }
}
//route-controller-vue
//Laravel se charge de convertir les données en JSON si nécessaire. donc pas obg faire ?