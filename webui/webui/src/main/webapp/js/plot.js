var plots = [];
var oldOnresize = window.onresize;
window.onresize = function() {
	if (oldOnresize) {
		oldOnresize();
	}
	drawPlots();
};

function drawPlots() {
	plots.forEach(function(plot) {
		plot.render()
	});
}

/**
 * This is the constructor for a Plot object.
 * 
 * @param canvasId
 *            is the id of the canvas element where the plot is to be rendered
 *            in.
 */
function Plot(canvasId) {
	this.canvasId = canvasId;
}

Plot.prototype = {
	render : function() {
		draw(this.canvasId);
	}
}

function getDevicePixelRatio() {
	return window.devicePixelRatio || 1;
}

function getBackingStoreRatio(context) {
	return context.webkitBackingStorePixelRatio
			|| context.mozBackingStorePixelRatio
			|| context.msBackingStorePixelRatio
			|| context.oBackingStorePixelRatio
			|| context.backingStorePixelRatio || 1;
}

function draw(canvasName) {
	/* Variables */
	var //
	canvas = document.getElementById(canvasName), //
	context = canvas.getContext('2d'), //
	devicePixelRatio = getDevicePixelRatio(), //
	backingStoreRatio = getBackingStoreRatio(context), //
	ratio = devicePixelRatio / backingStoreRatio;

	canvas.width = canvas.clientWidth * ratio;
	canvas.height = canvas.clientHeight * ratio;

	context.font = '48pt Arial';
	context.fillStyle = 'cornflowerblue';
	context.strokeStyle = 'blue';

	var text = canvas.width + 'x' + canvas.height + '(' + devicePixelRatio
			+ '/' + backingStoreRatio + ')';
	context.fillText(text, canvas.width / 2 - 150, canvas.height / 2 + 15);
	context.strokeText(text, canvas.width / 2 - 150, canvas.height / 2 + 15);

	context.beginPath();
	for (var col = 0; col < canvas.width; col += 10) {
		context.moveTo(col, 0);
		context.lineTo(col, canvas.height - 1);
	}
	context.stroke();

	context.beginPath();
	for (var row = 0; row < canvas.width; row += 10) {
		context.moveTo(0, row);
		context.lineTo(canvas.width - 1, row);
	}
	context.stroke();
}
