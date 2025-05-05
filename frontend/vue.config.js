const { defineConfig } = require('@vue/cli-service');
const path = require('path');

module.exports = defineConfig({
  outputDir: path.resolve(__dirname, '../src/main/resources/static'),
  //outputDir: '../src/main/resources/static', // Pour le build de prod
  publicPath:'/',
  transpileDependencies: true,
  devServer: {
    port : 8081,
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Ton backend Spring Boot
        changeOrigin: true
      }
    }
  }
});
