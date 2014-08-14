<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
		<title>Sharks</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="author" content="Jelena Tabas">
		<link href="mystyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="http://mbostock.github.com/d3/d3.v2.js"></script>
  <style type="text/css">
    #world {
      fill: #E5F5F9;
      stroke: #2CA25F;
      stroke-width: 0.5;
    }
    #axes {
      stroke: #E60000;
      stroke-width: 0.5;
    }
    #vis {
    	margin: 2% auto;
    	width: 80%;
    	border: 1px groove white;
    }
  </style>

	</head>
	<body>
		<div id="wrapper">
			<header>
				<img id="logo" src="images/logo.png" />
				<hgroup>
					<h1>Sharks</h1>
				</hgroup>
			</header>

			<div id="vis">
  			<script type="text/javascript">
  				<?php
					$url = "clustered.json";
								
					$contents = file_get_contents($url); 
					$contents = utf8_encode($contents); 
					$results = json_decode($contents);
					$coordinates = "var coordinates = [";
					$cityRadius = "var radius = [";
					$pop = array();
				
					$i=-1;

					for ($j=0; $j <$i ; $j++) { 
						$urlCity= "clustered.json";
                                                $contentsCity = file_get_contents($urlCity); 
						$contentsCity = utf8_encode($contentsCity); 
						$resultsCity = json_decode($contentsCity);
						$cityRadius .= $pop[$j].',';
						$coordinates .= '['.$resultsCity->lon.','.$resultsCity->lat.'],';
					}
					$urlCity= "clustered.json";
					$contentsCity = file_get_contents($urlCity); 
					$contentsCity = utf8_encode($contentsCity); 
					$resultsCity = json_decode($contentsCity);
					$cityRadius .= $pop[$i].'];';
					$coordinates .= '['.$resultsCity->lon.','.$resultsCity->lat.']];';
					echo $cityRadius;
					echo $coordinates;
				?>

    			var w = 800;
    			var h = 400;
    			var proj = d3.geo.mercator();
    			var path = d3.geo.path().projection(proj);
    			var t = proj.translate(); // the projection's default translation
    			var s = proj.scale() // the projection's default scale

    			var map = d3.select("#vis").append("svg:svg")
        			.attr("width", w)
        			.attr("height", h)
        			.call(d3.behavior.zoom().on("zoom", redraw));


    			var axes = map.append("svg:g").attr("id", "axes");

    			var xAxis = axes.append("svg:line")
        			.attr("x1", t[0])
        			.attr("y1", 0)
        			.attr("x2", t[0])
        			.attr("y2", h);

    			var yAxis = axes.append("svg:line")
        			.attr("x1", 0)
        			.attr("y1", t[1])
        			.attr("x2", w)
        			.attr("y2", t[1]);

    			var world = map.append("svg:g").attr("id", "world");

    			d3.json("world-countries.json", function (json) {
      				world.selectAll("path")
          			.data(json.features)
        			.enter().append("svg:path")
          			.attr("d", path);
    			});

    			var circles = map.append("svg:g").attr("id", "circle");

				var c = [];
				var projected = [];
				for (var i = 0; i < radius.length; i++) {
					projected.push(proj(coordinates[i]));
				}

				for (var i = 0; i < radius.length; i++) {
					c.push(circles.append("svg:circle")
					.attr("cx",projected[i][0])
					.attr("cy",projected[i][1])
					.attr("r",radius[i]*s)
					.style("fill","green"));
				};

    			function redraw() {
      				// d3.event.translate (an array) stores the current translation from the parent SVG element
      				// t (an array) stores the projection's default translation
      				// we add the x and y vales in each array to determine the projection's new translation
      				var tx = t[0] * d3.event.scale + d3.event.translate[0];
      				var ty = t[1] * d3.event.scale + d3.event.translate[1];
      				proj.translate([tx, ty]);

      				// now we determine the projection's new scale, but there's a problem:
      				// the map doesn't 'zoom onto the mouse point'
      				proj.scale(s * d3.event.scale);

      				// redraw the map
      				world.selectAll("path").attr("d", path);

      				// redraw the x axis
      				xAxis.attr("x1", tx).attr("x2", tx);

      				// redraw the y axis
      				yAxis.attr("y1", ty).attr("y2", ty);
					
					circles.remove();
					circles = map.append("svg:g").attr("id", "circle");

					for (var i = 0; i < radius.length; i++) {
						projected[i] = proj(coordinates[i]);
					}

      				//redraw the circles
					for (var i = 0; i < radius.length; i++) {
						c[i] = circles.append("svg:circle")
						.attr("cx",projected[i][0])
						.attr("cy",projected[i][1])
						.attr("r",radius[i]*proj.scale())
						.style("fill","green");
					}


    			}
  			</script>
  			</div>
			<footer>
				Copyright 2014.
			</footer>
		</div>
	</body>
</html>