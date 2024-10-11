<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\CityController;
use App\Http\Controllers\FlightController;
use App\Http\Controllers\LogController;
use App\Http\Controllers\SearchController;


Route::get('/', function () {
    return view('index');
})->name('welcome');

Route::get('/flights', [CityController::class, 'getAll']);


Route::get('/flights', [FlightController::class, 'findFlights']);

Route::post('/login', [LogController::class, 'login']);

Route::middleware(['auth'])->group(function () {

    Route::post('/logout', [LogController::class, 'logout']);
    Route::get('/searchs',[SearchController::class, 'getSearches']);
    Route::post('/searchs/save', [SearchController::class, 'store']);
});

Route::get('/check-login', [LogController::class, 'checkLogin']);

