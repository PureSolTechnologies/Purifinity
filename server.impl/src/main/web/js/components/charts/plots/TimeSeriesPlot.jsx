import Plot from './Plot';

export default class TimeSeriesPlot implements Plot {

    render( ctx, x, y, width, height ) {
        const axisWidth = this.renderAxis( ctx, x, y, width, height );
        ctx.rect( axisWidth, 0, width - axisWidth, height - axisWidth );
        ctx.clip();
        ctx.translate( axisWidth, 0 );
        this.renderGraph( ctx, 0, 0, width - axisWidth, height - axisWidth );
    }

    renderAxis( ctx, x, y, width, height ): number {
        let titleFontSize = height * 0.075;
        if ( titleFontSize > 16 ) {
            titleFontSize = 16;
        }
        let labelFontSize = height * 0.05;
        if ( labelFontSize > 10 ) {
            labelFontSize = 10;
        }
        ctx.textAlign = "center";
        ctx.textBaseline = "bottom";
        ctx.font = titleFontSize + "px sans-serif"
        ctx.fillText( "X-axis", width / 2, height );
        ctx.translate( 0, height / 2 )
        ctx.rotate( -Math.PI / 2 );
        ctx.textBaseline = "top";
        ctx.fillText( "Y-axis", 0, 0 );
        ctx.rotate( Math.PI / 2 );
        ctx.translate( 0, -height / 2 );
        const axisWidth = titleFontSize + labelFontSize;
        ctx.strokeRect( axisWidth, 0, width - axisWidth, height - axisWidth );
        return axisWidth;
    }

    renderGraph( ctx, x, y, width, height ): void {
        ctx.beginPath();
        ctx.moveTo( 0, 0 );
        ctx.lineTo( width, height );
        ctx.moveTo( width, 0 );
        ctx.lineTo( 0, height );
        ctx.stroke();
        ctx.closePath();
    }
}