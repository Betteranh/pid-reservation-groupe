const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  outputDir: '../src/main/resources/static', // Pour le build de prod
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Ton backend Spring Boot
        changeOrigin: true
      }
    }
  }
});
