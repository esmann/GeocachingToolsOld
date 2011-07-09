function loadCaches()
{
    console.log("loading caches");
    $.ajax(
    {
        type: 'POST',
        url: '/loadcaches',
        success: showCaches,
        dataType: 'json'
    });

    function showCaches(caches)
    {
        console.log("showing %o caches", caches.length);
        $.each(caches, function (index, cache)
        {
            var myLatlng = new google.maps.LatLng(cache.latitude, cache.longitude);
            var marker = new google.maps.Marker({
                position: myLatlng,
                map: window.map,
                title:cache.name
            });
        })
    }
}