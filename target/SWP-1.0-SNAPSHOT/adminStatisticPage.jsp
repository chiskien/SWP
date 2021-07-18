<!doctype html>
<html>
<head>
    <title>Makisu ~ CSS 3D Dropdown Concept</title>
    <link href='http://fonts.googleapis.com/css?family=Days+One' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="asset/css/adminStatisticPage.css">
</head>
<body style="background-image: url('asset/img/adminStatisticPage.jpg')">

    <header class="header">
        <hgroup>
            <h1 style='color:#ff6699; font-size: 300%;'>Bromics</h1>
            <h2 style='color:#ff6699'>Admin statistic page</h2>
        </hgroup>
    </header>

    <section class="demo">

        <dl class="list nigiri">
            <dt>Most View Books</dt>
            <dd><a href="#">Maguro</a></dd>
            <dd><a href="#">Sake</a></dd>
            <dd><a href="#">Unagi</a></dd>
            <dd><a href="#">Buri</a></dd>
            <dd><a href="#">Suzuki</a></dd>
            <dd><a href="#">Saba</a></dd>
            <dd><a href="#">Iwashi</a></dd>
            <dd><a href="#">Kohada</a></dd>
            <dd><a href="#">Hirame</a></dd>
            <dd><a href="#">Tobiwo</a></dd>
        </dl>

        <dl class="list maki">
            <dt>Most Favourite Books</dt>
            <dd><a href="#">Ana-kyu</a></dd>
            <dd><a href="#">Chutoro</a></dd>
            <dd><a href="#">Kaiware</a></dd>
            <dd><a href="#">Kampyo</a></dd>
            <dd><a href="#">Kappa</a></dd>
            <dd><a href="#">Natto</a></dd>
            <dd><a href="#">Negitoro</a></dd>
            <dd><a href="#">Oshinko</a></dd>
            <dd><a href="#">Otoro</a></dd>
            <dd><a href="#">Tekka</a></dd>
        </dl>

        <dl class="list sashimi">
            <dt style="overflow:hidden">Most FavouritePublisher</dt>
            <dd><a href="#">Maguro</a></dd>
            <dd><a href="#">Toro</a></dd>
            <dd><a href="#">Ebi</a></dd>
            <dd><a href="#">Saba</a></dd>
            <dd><a href="#">Ika</a></dd>
            <dd><a href="#">Tako</a></dd>
            <dd><a href="#">Tomago</a></dd>
            <dd><a href="#">Kani</a></dd>
            <dd><a href="#">Katsuo</a></dd>
            <dd><a href="#">Maguro</a></dd>
        </dl>

        <a href="/ChartController" class="" style='box-shadow: 0 1px 4px rgb(0 0 0 / 15%);
           z-index: 1000;
    border-radius: 3px;
    text-transform: uppercase;
    letter-spacing: -1px;
    line-height: 50px;
    margin-left: -70px;
    margin-top: -20px;
    background: #2b2b2b;
    text-align: center;
    font-size: 12px;
    position: absolute;
    height: 50px;
    bottom: 10%;
    width: 140px;
    color: #fff;
    left: 50%;'>To Charts</a>

    </section>

   


    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <script src="asset/js/adminStatisticPage.js"></script>
    <script>

        // The `enabled` flag will be `false` if CSS 3D isn't available

        if ( $.fn.makisu.enabled ) {

            var $sashimi = $( '.sashimi' );
            var $nigiri = $( '.nigiri' );
            var $maki = $( '.maki' );

            // Create Makisus

            $nigiri.makisu({
                selector: 'dd',
                overlap: 0.85,
                speed: 1.7
            });

            $maki.makisu({
                selector: 'dd',
                overlap: 0.6,
                speed: 0.85
            });

            $sashimi.makisu({
                selector: 'dd',
                overlap: 0.2,
                speed: 0.5
            });

            // Open all
            
            $( '.list' ).makisu( 'open' );

            // Toggle on click

            $( '.toggle' ).on( 'click', function() {
                $( '.list' ).makisu( 'toggle' );
            });

            // Disable all links

            $( '.demo a' ).click( function( event ) {
                event.preventDefault();
            });

        } else {

            $( '.warning' ).show();
        }

    </script>
</body>
</html>