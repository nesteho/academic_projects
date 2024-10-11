<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\DB; 
use Illuminate\Http\Request;
use App\Models\Flight; 
use App\Models\City; 

class FlightController extends Controller
{
    function findFlights(Request $request) {

        $fAirport = $request->input('depAirports');
        $toAirport = $request->input('arrAirports');
        $flights = Flight::where('fFromAirport',$fAirport)
                         ->where('fToAirport',$toAirport)
                         ->get();
    
        $cities = City::orderBy('cAirport')->get();
        return view('flights', compact('flights', 'cities','fAirport','toAirport'));
    }

    public function findFlightsFrom(Request $request)
    {
        $fromAirport = $request->route('fromAirport'); 
    
        //ici pas eloquent car pb : orderby distinct quand utilise relation 
        $flights = DB::table('flight')
        ->join('city as toAirport', 'flight.fToAirport', '=', 'toAirport.cAirport')
        ->where('flight.fFromAirport', $fromAirport)
        ->select('toAirport.*')
        ->distinct()
        ->orderBy('toAirport.cAirport')
        ->get();
        return response()->json($flights);

    }
}


