const path = require( 'path' );
const webpack = require( 'webpack' );
const { getIfUtils, removeEmpty } = require( 'webpack-config-utils' );
const UglifyJsPlugin = require( 'uglifyjs-webpack-plugin' );

const BUILD_DIR = path.resolve( __dirname, './build' )

module.exports = env => {
    const { ifProd, ifNotProd } = getIfUtils( env )

    return {
        mode: 'development',
        entry: ['./js/index.jsx',],
        context: __dirname,
        output: {
            path: BUILD_DIR,
            filename: 'bundle.js',
            publicPath: '/build/',
            pathinfo: ifNotProd(),
        },
        resolve: {
            extensions: [".jsx", ".js"]
        },
        devtool: ifNotProd( 'source-map', 'eval' ),
        devServer: {
            host: "0.0.0.0",
            port: 9090,
            historyApiFallback: true,
            open: true
        },
        module: {
            rules: [
                { test: /\.css$/, loader: 'style-loader!css-loader' },
                { test: /\.html$/, exclude: /node_modules/, loader: "html-loader" },
                { test: /\.jsx?$/, exclude: /node_modules/, loader: 'babel-loader' },
                { test: /\.jsx?$/, exclude: /node_modules/, loader: "source-map-loader" },
                { test: /(\.eot|\.woff2|\.woff|\.ttf|\.svg)$/, loader: 'file-loader' }
            ],
        },
        plugins: removeEmpty( [
            ifProd( new webpack.LoaderOptionsPlugin( {
                minimize: false,
                debug: false,
                quiet: false,
            } ) ),
            ifProd( new webpack.DefinePlugin( {
                'process.env': {
                    NODE_ENV: '"production"',
                },
            } ) ),
        ] ),
        optimization: {
            minimizer: [
                // we specify a custom UglifyJsPlugin here to get source maps in
                // production
                new UglifyJsPlugin( {
                    sourceMap: true,
                } )
            ]
        }
    };
};
