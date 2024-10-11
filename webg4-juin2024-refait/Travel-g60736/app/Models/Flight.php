<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Flight extends Model
{
    use HasFactory;
    protected $table = 'flight'; 
    protected $primaryKey = 'fid';

    public function toAirport()
    {
        return $this->belongsTo(City::class, 'fToAirport', 'cAirport');
    }//jointure fFromAirport est clé etrangère ref city.cAirport
}
