<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Search extends Model
{
    use HasFactory;
    protected $table = 'search'; 
    protected $fillable = ['fromAirport', 'toAirport','tuser_login']; 

    public static function getSearches(array $data){
        $user= $data['tuser_login'];
        return self::where('tuser_login',$user)
                    ->select('fromAirport', 'toAirport','created_at')
                    ->orderBy('id')
                    ->get();
    }

    public static function store(array $data){
        return self::create([
            'fromAirport' => $data['fromAirport'],
            'toAirport' => $data['toAirport'],
            'tuser_login' => $data['tuser_login']
        ]);
    }

}
