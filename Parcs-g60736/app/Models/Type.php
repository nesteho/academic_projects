<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Type extends Model
{
    use HasFactory;

 // Définition de la jointure : Ride->Type
 public function type()
 {
     return $this->belongsTo(Type::class);
 }
}


