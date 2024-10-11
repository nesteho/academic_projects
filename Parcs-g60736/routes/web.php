<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ParkController;
use App\Http\Controllers\RideController;

Route::get('/', [ParkController::class, 'parks'])->name('index');
Route::get('/api/parks/{park_id}/rides',[RideController::class, 'park_rides'])->name('park_rides');

Route::get('/addDescription', [RideController::class, 'add_description_form'])->name('add_description_form');
Route::post('/addDescription', [RideController::class, 'update_description'])->name('update_description');
