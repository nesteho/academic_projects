@extends('canevas')
@section('title','Vols')
@section('content')
@if(!empty($cities))
<form id="flights-select" action="/flights" method="GET">
    <div>
        <label for="">De :</label>
        <select name="depAirports" id="depAirports">
            <option selected disabled>Sélectionnez un aéroport...</option>
            @foreach($cities as $city)
            {{--pas reset select aps appuyer bouton/rafraichissement--}}
            <option value="{{ $city->cAirport }}" {{ isset($fAirport) && $fAirport == $city->cAirport ? 'selected' : '' }}>
                {{$city->cAirport}} - {{$city->cName}} - {{$city->cCountry}}
            </option>
            @endforeach
        </select>
    </div>
    <div>
        <label for="">Vers : </label>
        <select name="arrAirports" id="arrAirports">
            <option selected disabled>Sélectionnez un aéroport...</option>
            @foreach($cities as $city)
            <option value="{{ $city->cAirport }}" {{ isset($toAirport) && $toAirport == $city->cAirport ? 'selected' : '' }}>
                {{$city->cAirport}} - {{$city->cName}} - {{$city->cCountry}}
            </option>
            @endforeach
        </select>
    </div>
    <button type="submit" id="searchFlights">Rechercher</button>
</form>
@endif
@if(@isset($flights)) {{--@if(empty('flights'))-- ko car littéralement la chaine "flights" pas la variable + on veut empty possible --}}

<div id="display-flights">
    <h2>Vols</h2>
    <table id="flights-table">
        <thead>
            <td> Vol</td>
            <td> De</td>
            <td> Vers</td>
            <td> Heure Départ</td>
            <td> Heure Arrivée</td>
            <td> Durée du Vol</td>
            <td> Miles</td>
        </thead>
        @foreach($flights as $flight)
        <tr>
            <td>{{ $flight->fFlight }}</td>
            <td>{{ $flight->fFromAirport }}</td>
            <td>{{ $flight->fToAirport }}</td>
            <td>{{ $flight->fDepartTime }}</td>
            <td>{{ $flight->fArriveTime }}</td>
            <td>{{ $flight->fFlyingTime }}</td>
            <td>{{ $flight->fMiles }}</td>
        </tr>
        @endforeach
    </table>
</div>
@endisset

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {

        function updateToAirports() {


            // .val = attr value    .text() = contenu de opt  
            const selectedFromAirport = $('#depAirports').find('option:selected').val();

            const url = '/api/flights/from/' + selectedFromAirport;
            $.ajax({
                type: 'GET'
                , url: url
                , success: function(data) {
                    let toAirportBox = $('#arrAirports');
                    toAirportBox.empty();

                    $.each(data, function(index, city) {
                        let cityData = city.cAirport + ' - ' + city.cName + ' - ' + city.cCountry;
                        let toAirportOpt = $('<option>', {
                            value: city.cAirport
                            , text: cityData
                        });
                        toAirportBox.append(toAirportOpt);
                    })
                }
            })
        }
        $('#depAirports').change(function() {
            updateToAirports();
        });
        // au rechargement de la page si fromAirport a value: chercher ToAiports associés 
        if ($('#depAirports').val()) {
            updateToAirports();
        }

        @auth
        $('#searchFlights').click(function(event) {
            event.preventDefault();
            const selectedFromAirport = $(depAirports).find('option:selected').val();
            const selectedToAirport = $(arrAirports).find('option:selected').val();
            const url = 'searchs/save';

            $.ajax({
                type: 'POST'
                , url: url
                , data: {
                    fromAirport: selectedFromAirport
                    , toAirport: selectedToAirport
                    , _token: '{{csrf_token() }}'
                }
                , success: function(data) {
                    $('#flights-select').submit();
                }
            })
        })
        @endauth
    })

</script>

@endsection
