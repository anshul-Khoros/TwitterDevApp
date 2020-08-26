path = require("path");
webpack = require("webpack");

module.exports = {
    mode: "development",
    entry: "./src/js/main.js",
    output: {
        filename: "bundle.js"
    },
    devServer: {
        contentBase: path.resolve(__dirname, "./dist"),
        publicPath: "/js/",
        watchContentBase: true, // live reload
        compress: true,
        port: 9000
    },
    devtool: "source-map", // map
    module: {
        rules: [{
            test: /\.(scss)$/,
            use: [
                "style-loader",
                "css-loader",
                "sass-loader"
            ]
        },
        {
            test: /\.(js|jsx)$/,
            use: {
                loader: 'babel-loader',
                options: {
                    presets: ['@babel/preset-env']
                }
            }
        }]
    }
}