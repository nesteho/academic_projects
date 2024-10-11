@extends('canevas')
@section('title','Historique des recherches')
@section('content')

@if(isset($searches))
<div id="display-searches">
    <h2>Historique des recherches</h2>
    <table id="searches-table">
        <thead>
            <td> De</td>
            <td> Vers</td>
            <td> Créé le</td>
        </thead>
        @foreach($searches as $search)
        <tr>
            <td>{{ $search->fromAirport }}</td>
            <td>{{ $search->toAirport }}</td>
            <td>{{ $search->created_at }}</td>
        </tr>
        @endforeach
    </table>
</div>
@else
<p id="noSearchResult">Aucune recherche dans l'historique.</p>
@endif

@endsection
