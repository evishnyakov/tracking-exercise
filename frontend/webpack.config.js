var path = require('path');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var CopyWebpackPlugin = require('copy-webpack-plugin');
var webpack = require('webpack');

module.exports = {
	context: path.resolve(__dirname, './src'),

	entry: ["./app.js"],
	output: {
        path: path.resolve(__dirname, './dist/static'),
        filename: "bundle.js"
    },
    module: {
	    rules: [
	        {
	            test: /\.js$/,
	            exclude: /node_modules/,
	            use: {
	                loader: 'babel-loader',
	                options: { presets: ['es2015'] },
	            },
	        },
	        {
                test: /\.(sass|scss)$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'sass-loader',
                ]
            },
            {   test: /\.css$/, use: ['style-loader','css-loader'] },
            {   test: /\.(svg|ttf|woff|woff2|eot)$/, use: 'url-loader?limit=5000' },
	        
	    ],
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: 'jquery',
            jQuery: 'jquery'
        }),
    	new CopyWebpackPlugin([{ from: './index.html' }])
    ],
    devtool: "source-map",
	devServer: {
    	contentBase: path.resolve(__dirname, './dist/static'),  // New
  	}
}