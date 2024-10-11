<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Contracts\Auth\Authenticatable;
use Illuminate\Auth\Authenticatable as AuthenticableTrait;

class Tuser extends Model implements Authenticatable
{
    use HasFactory;
    use AuthenticableTrait;

    protected $table = 'tuser'; 
    protected $primaryKey = 'login';
    protected $fillable = ['login'];
    public $incrementing = false; // ESSENTIEL !! sinon  Auth::user()->login; renvoie 0 
}
