var plots = [];

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

function draw(canvasName) {
	/* Variables */
	var canvas = document.getElementById(canvasName);
	var context = canvas.getContext('2d');

	canvas.width = canvas.clientWidth;
	canvas.height = canvas.clientHeight;

	var info = document.getElementById('info');

	context.font = '48pt Arial';
	context.fillStyle = 'cornflowerblue';
	context.strokeStyle = 'blue';

	context.fillText('Hello Canvas', canvas.width / 2 - 150,
			canvas.height / 2 + 15);
	context.strokeText('Hello Canvas', canvas.width / 2 - 150,
			canvas.height / 2 + 15);

	var textNode = document.createTextNode(canvas.width + 'x' + canvas.height);
	info.appendChild(textNode);
}

/**
 * This method draws an image into a canvas and also takes DPI into account to
 * give best experience possible.
 * 
 * @param opts
 */
function drawImage(opts) {

	if (!opts.canvas) {
		throw ("A canvas is required");
	}
	if (!opts.image) {
		throw ("Image is required");
	}

	// get the canvas and context
	var canvas = opts.canvas, context = canvas.getContext('2d'), image = opts.image,

	// now default all the dimension info
	srcx = opts.srcx || 0, srcy = opts.srcy || 0, srcw = opts.srcw
			|| image.naturalWidth, srch = opts.srch || image.naturalHeight, desx = opts.desx
			|| srcx, desy = opts.desy || srcy, desw = opts.desw || srcw, desh = opts.desh
			|| srch, auto = opts.auto,

	// finally query the various pixel ratios
	devicePixelRatio = window.devicePixelRatio || 1, backingStoreRatio = context.webkitBackingStorePixelRatio
			|| context.mozBackingStorePixelRatio
			|| context.msBackingStorePixelRatio
			|| context.oBackingStorePixelRatio
			|| context.backingStorePixelRatio || 1,

	ratio = devicePixelRatio / backingStoreRatio;

	// ensure we have a value set for auto.
	// If auto is set to false then we
	// will simply not upscale the canvas
	// and the default behaviour will be maintained
	if (typeof auto === 'undefined') {
		auto = true;
	}

	// upscale the canvas if the two ratios don't match
	if (auto && devicePixelRatio !== backingStoreRatio) {

		var oldWidth = canvas.width;
		var oldHeight = canvas.height;

		canvas.width = oldWidth * ratio;
		canvas.height = oldHeight * ratio;

		canvas.style.width = oldWidth + 'px';
		canvas.style.height = oldHeight + 'px';

		// now scale the context to counter
		// the fact that we've manually scaled
		// our canvas element
		context.scale(ratio, ratio);

	}

	context.drawImage(pic, srcx, srcy, srcw, srch, desx, desy, desw, desh);
}
