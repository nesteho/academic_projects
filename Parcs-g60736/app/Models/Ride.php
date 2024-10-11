<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Ride extends Model
{
    use HasFactory;
    protected $fillable = ['description'];

/*     public static function park_rides($r_id)
    {
        return self::where('park_id', $r_id)->get();
    } */
    public static function park_rides($r_id)
    {
/*         return DB::table('rides')
                    ->join('types', 'rides.type_id', '=', 'types.id')
                    ->where('rides.park_id', $r_id)
                    ->select('rides.name as ride_name', 'rides.capacity', 'types.name as type_name')
                    ->get(); */
        return self::where('park_id', $r_id)
                ->join('types', 'rides.type_id', '=', 'types.id')
                ->select('rides.name as ride_name', 'rides.capacity', 'types.name as type_name')
                ->get();

    }
    public static function updateRide(array $data)
    {
        $ride = self::find($data[$id]);
        if ($ride) {
            if (array_key_exists('description', $data)) {
                $ride->description = $data['description'];
            }
            $ride->save();
            return $ride;
        }
        return null;
    }
}
