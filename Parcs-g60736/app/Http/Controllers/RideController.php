<?php

namespace App\Http\Controllers;
use App\Models\Ride; 

class RideController extends Controller
{
    public static function park_rides(){
        $p_id = request('park_id');
        if (empty($p_id)) {
            //todo error 
            return ;
        }
        $rides = Ride::park_rides(request('park_id'));
        return json_encode($rides);
    }
    public static function update_description(){
        $r_id = request('ride_id');
        if (empty($r_id)) {
            //todo error 
            return ;
        }
        $rides = Ride::updateRide(request('ride_id'));
        return view('index')->with('parks', json_encode($parks));
    }
}   
