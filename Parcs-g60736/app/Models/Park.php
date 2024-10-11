<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Park extends Model
{
    use HasFactory;
    public $timestamps = false; // Désactive les colonnes de timestamp
    protected $fillable = ['name', 'image'];

    public static function createPark(array $data)
    {
        return self::create([
            'name' => $data['name'],
            'image' => $data['image'],
        ]);
    }
    
    public static function getPark($id)
    {
        return self::find($id);
    }

    public static function getParks()
    {
        return self::all();
    }
}