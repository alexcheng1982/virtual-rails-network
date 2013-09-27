requirejs.config({
	baseUrl: 'bower_components',
	paths: {
		jquery: 'jquery/jquery'
	}
});

define(['jquery'], function($) {
	function stopsToString(route) {
		var stops = [];
		for (var i = 0, n = route.length; i < n; i++) {
			stops.push(route[i].name);
		}
		return stops.join(' -> ');
	}
	
	$(function() {
		$('#calculate-distance').click(function() {
			$.get('service/calculateDistance.do', {
				stations: $('#distance-stations').val()
			}).done(function(data) {
				$('#distance-result').removeClass().addClass('label label-success').text(data);
			}).fail(function(xhr) {
				$('#distance-result').removeClass().addClass('label label-danger').text(xhr.responseText);
			});
		});
		$('#plan').click(function() {
			var url = 'service/planJourney';
			if (!$('#plan-stops').val()) {
				url += '/all.do';
			}
			else {
				if ($('#plan-max').prop('checked')) {
					url += '/max.do';
				}
				else {
					url += '/exact.do';
				}
			}
			$.getJSON(url, {
				start: $('#plan-from-station').val(),
				end: $('#plan-to-station').val(),
				stops: $('#plan-stops').val()
			}).done(function(data) {
				var html = "";
				for (var i = 0, n = data.length; i < n; i++) {
					html += stopsToString(data[i]) + '<br>';
				}
				$('#plan-result').removeClass().html(html);
			}).fail(function(xhr) {
				$('#plan-result').removeClass().addClass('label label-danger').text(xhr.responseText);
			});
		});
		$('#calculate-shortest-route').click(function() {
			$.getJSON('service/calculateShortestRoute.do', {
				start: $('#shortest-from-station').val(),
				end: $('#shortest-to-station').val()
			}).done(function(data) {
				$('#shortest-result').text(stopsToString(data.route) + ' : ' + data.distance);
			}).fail(function(xhr) {
				$('#shortest-result').removeClass().addClass('label label-danger').text(xhr.responseText);
			});
		});
	});
});