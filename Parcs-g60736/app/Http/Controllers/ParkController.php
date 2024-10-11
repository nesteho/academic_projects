<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Park; 

class ParkController extends Controller
{
    public static function parks(){    
        $parks =  Park::all();
        return view('index')->with('parks', json_encode($parks));
    }
}   

