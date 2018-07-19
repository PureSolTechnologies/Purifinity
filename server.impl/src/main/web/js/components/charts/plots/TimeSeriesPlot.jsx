import Plot from './Plot';
import TimeSeriesData from './TimeSeriesData';

export default class TimeSeriesPlot implements Plot {

    data: TimeSeriesData;

    constructor( data: TimeSeriesData ) {
        this.data = data;
    }

    render( ctx, x, y, width, height ) {
        const axisWidth = this.renderAxis( ctx, x, y, width, height );
        ctx.rect( axisWidth, 0, width - axisWidth, height - axisWidth );
        ctx.clip();
        ctx.translate( axisWidth + 2, 0 );
        this.renderGraph( ctx, 2, 0, width - axisWidth, height - axisWidth );
    }

    renderAxis( ctx, x, y, width, height ): number {
        let titleFontSize = height * 0.075;
        if ( titleFontSize > 16 ) {
            titleFontSize = 16;
        }
        let labelFontSize = height * 0.05;
        if ( labelFontSize > 12 ) {
            labelFontSize = 12;
        }
        /* 
         * X labels
         */
        ctx.textAlign = "center";
        ctx.textBaseline = "bottom";
        ctx.font = titleFontSize + "px sans-serif"
        ctx.fillText( this.data.xAxisTitle, width / 2, height );
        ctx.font = labelFontSize + "px sans-serif"
        ctx.fillText( this.data.xAxisTitle, width / 2, height - titleFontSize );
        /*
         * Y labels 
         */
        ctx.translate( 0, height / 2 )
        ctx.rotate( -Math.PI / 2 );
        ctx.textBaseline = "top";
        ctx.font = titleFontSize + "px sans-serif"
        ctx.fillText( this.data.yAxisTitle, 0, 0 );
        ctx.font = labelFontSize + "px sans-serif"
        ctx.fillText( this.data.yAxisTitle, 0, titleFontSize );
        ctx.rotate( Math.PI / 2 );
        ctx.translate( 0, -height / 2 );

        const axisWidth = titleFontSize + labelFontSize + 2;
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