@extends('canevas')
@section('title', 'Parks')
@section('content')   
<div class="parks">
    @foreach(json_decode($parks) as $park)
    @php $p_id = $park->id; @endphp
    @php $p_name = $park->name; @endphp
    <div data-park-id="{{ $p_id }}">
        <a href="{{ route('park_rides',['park_id'=> $p_id  ]) }}" class="park">
            <img class="image" src="{{ asset($park->image) }}" alt="" > 
        </a> 
        <h3> 
            <a href="{{ route('park_rides',['park_id'=> $p_id  ]) }}" class="park"> {{$park->name }}</a> 
        </h3>
    </div>
    <div class="popup hide" id="popup-{{ $p_id }}">
    <table class="ride" id="ride-{{ $p_id }}"> </table>
    <button class="close-popup">Fermer</button>
</div>
    
    @endforeach
</div>    

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $('.park').each(function() {
            $(this).click(function(event) {
                console.log("HERE");
                event.preventDefault(); 
                // var parkId = $(this).attr('href').split('/')[5]; // vt check avec log!!!!
                //var parkId = $(this).data('park-id'); // Utilisation de l'attribut data
                const parkId = $(this).closest('div').data('park-id')
                let popup=$("#popup-" + parkId);
                console.log("PID",parkId);
                popup.toggleClass("hide");
                if (popup.hasClass("hide")) {
                    return; 
                }
                 let rides = $("#ride-" + parkId);
                $.ajax({
                    url: '/api/parks/' + parkId + '/rides',
                    method: 'GET',
                    headers: {
                        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
                    },
                    dataType: 'json',
                    success: function(data) {
                        rides.children('.ride').remove();

                        var ridesTr = $('<tr></tr>'); //but rides ajoute mais garde comme var aussi
                        ridesTr.append('<th>Nom</th> <th>Type</th> <th>Capacité</th> <th>Description</th>');
                        rides.append(ridesTr);

                        $.each(data, function(index, ride) {
                            // Crée une nouvelle ligne pour chaque ride avec ses informations
                            var ridesTr = rides.append('<tr> </tr>');
                            ridesTr.append('<td class="name">' + ride.ride_name + '</td>');
                            ridesTr.append('<td class="type"> ' + ride.type_name + '</td>');
                            ridesTr.append('<td class="capacity"> ' + ride.capacity + '</td>');
                            ridesTr.append('<td class="description">' + ride.description + '</td>');
                            console.log("RIDE",ride);
                        });
                    },
                    error: function(error) {
                        console.error('Erreur lors de la récupération des rides :', error);
                    }
                });
            });
        });
        $(".close-popup").on('click', function() {
            console.log("CLOSE");
            $(this).closest('.popup').toggleClass("hide");
        });
    });
</script>          
@endsection
