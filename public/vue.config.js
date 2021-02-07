module.exports = {
    devServer: {
        //proxy: 'http://localhost:8080',
        proxy: {
            '/solution': {
                target: 'http://localhost:8080',
                changeOrigin: true
            }
        }
    }
}
module.exports = {
    devServer: {
        proxy: {
            '/solution': {
                target: 'http://localhost:8080',
                changeOrigin: true
            }
        }
    }
}
vue.config.js

const path = require('path')

module.exports = {
    chainWebpack: config => {
        config.resolve.alias.set(
            'vue$',
            // If using the runtime only build
            path.resolve(__dirname, 'node_modules/vue/dist/vue.runtime.esm.js')
            // Or if using full build of Vue (runtime + compiler)
            // path.resolve(__dirname, 'node_modules/vue/dist/vue.esm.js')
        )
    }
}